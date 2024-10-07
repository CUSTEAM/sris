package action.enroll;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import action.BasePrintXmlAction;
import model.Enroll;
import model.EnrollAttach;
import model.EnrollDept;
import model.EnrollRegist;
import model.EnrollRegistDept;
import model.EnrollStmd;
import model.Message;
import model.Stmd;

public class EnrollManager extends BasePrintXmlAction {

	public String enrollOid;
	private File brochure, envelope;
	private String brochureFileName, envelopeFileName;

	public String CampusNo[], SchoolNo[], DeptNo[], dept_name[], quota[];

	public String attach_name[], online[], att_note[];

	public String school_year, sign_begin, sign_end, open_score, open_match, enroll_name, reg_fee, subsel, note;
	public String deptOid[], rank[];

	public String registOid[], score[], score1[], score2[], score3[], no[];
	public String idno[], student_no[], depart_class[];

	public File getBrochure() {
		return brochure;
	}

	public void setBrochure(File brochure) {
		this.brochure = brochure;
	}

	public File getEnvelope() {
		return envelope;
	}

	public void setEnvelope(File envelope) {
		this.envelope = envelope;
	}

	@Override
	public String execute() {

		request.setAttribute("enrolls",
				df.sqlGet("SELECT e.*,"
						+ "(SELECT COUNT(*)FROM Enroll_regist WHERE no IS NOT NULL AND Enroll_oid=e.Oid)as con,"
						+ "(SELECT COUNT(*)FROM Enroll_regist WHERE Enroll_oid=e.Oid)as cnt FROM Enroll e"));

		return SUCCESS;
	}

	public String printList() throws IOException {

		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		xml2ods(response, getRequest(), date);
		PrintWriter out = response.getWriter();

		out.println("<?xml version='1.0'?>");
		out.println("<?mso-application progid='Excel.Sheet'?>");
		out.println("<Workbook xmlns='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println(" xmlns:o='urn:schemas-microsoft-com:office:office'");
		out.println(" xmlns:x='urn:schemas-microsoft-com:office:excel'");
		out.println(" xmlns:ss='urn:schemas-microsoft-com:office:spreadsheet'");
		out.println(" xmlns:html='http://www.w3.org/TR/REC-html40'>");
		out.println(" <DocumentProperties xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println("  <Author>John</Author>");
		out.println("  <LastAuthor>John</LastAuthor>");
		out.println("  <Created>2024-04-09T21:51:11Z</Created>");
		out.println("  <Version>16.00</Version>");
		out.println(" </DocumentProperties>");
		out.println(" <OfficeDocumentSettings xmlns='urn:schemas-microsoft-com:office:office'>");
		out.println("  <AllowPNG/>");
		out.println(" </OfficeDocumentSettings>");
		out.println(" <ExcelWorkbook xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println("  <WindowHeight>12000</WindowHeight>");
		out.println("  <WindowWidth>28800</WindowWidth>");
		out.println("  <WindowTopX>32767</WindowTopX>");
		out.println("  <WindowTopY>32767</WindowTopY>");
		out.println("  <ProtectStructure>False</ProtectStructure>");
		out.println("  <ProtectWindows>False</ProtectWindows>");
		out.println(" </ExcelWorkbook>");
		out.println(" <Styles>");
		out.println("  <Style ss:ID='Default' ss:Name='Normal'>");
		out.println("   <Alignment ss:Vertical='Center'/>");
		out.println("   <Borders/>");
		out.println("   <Font ss:FontName='新細明體' x:CharSet='136' x:Family='Roman' ss:Size='12'");
		out.println("    ss:Color='#000000'/>");
		out.println("   <Interior/>");
		out.println("   <NumberFormat/>");
		out.println("   <Protection/>");
		out.println("  </Style>");
		out.println(" </Styles>");
		out.println(" <Worksheet ss:Name='工作表1'>");
		out.println("  <Table ss:ExpandedColumnCount='999' ss:ExpandedRowCount='99999' x:FullColumns='1'");
		out.println("   x:FullRows='1' ss:DefaultColumnWidth='54' ss:DefaultRowHeight='16.5'>");
		List<Map<Object, Object>> stmds = df
				.sqlGet("SELECT  es.* FROM Enroll_stmd es, Enroll_regist er WHERE es.idno=er.idno AND er.Enroll_oid="
						+ enrollOid + " GROUP BY es.idno");
		Map tmp;

		out.println("   <Row>");
		for (Map.Entry<Object, Object> entry : stmds.get(0).entrySet()) {
			// System.out.println("Key = " + entry.getKey() + ", Value = " +
			// entry.getValue());
			try {
				out.println("    <Cell><Data ss:Type='String'>" + entry.getKey() + "</Data></Cell>");
			} catch (Exception e) {
				out.println("    <Cell><Data ss:Type='String'></Data></Cell>");
			}

		}

		out.println("   </Row>");

		for (int i = 0; i < stmds.size(); i++) {
			out.println("   <Row>");
			for (Map.Entry<Object, Object> entry : stmds.get(i).entrySet()) {
				// System.out.println(entry.getKey() + ":" + stmds.get(i).get(entry.getKey()));
				try {
					if (stmds.get(i).get(entry.getKey()) != null) {

						out.println("    <Cell><Data ss:Type='String'>" + stmds.get(i).get(entry.getKey())
								+ "</Data></Cell>");
					} else {
						out.println("    <Cell><Data ss:Type='String'></Data></Cell>");
					}

				} catch (Exception e) {
					out.println("    <Cell><Data ss:Type='String'></Data></Cell>");
				}

			}
			out.println("   </Row>");
		}

		out.println("  </Table>");
		out.println("  <WorksheetOptions xmlns='urn:schemas-microsoft-com:office:excel'>");
		out.println("   <PageSetup>");
		out.println("    <Header x:Margin='0.3'/>");
		out.println("    <Footer x:Margin='0.3'/>");
		out.println("    <PageMargins x:Bottom='0.75' x:Left='0.7' x:Right='0.7' x:Top='0.75'/>");
		out.println("   </PageSetup>");
		out.println("   <Print>");
		out.println("    <ValidPrinterInfo/>");
		out.println("    <PaperSizeIndex>9</PaperSizeIndex>");
		out.println("    <HorizontalResolution>600</HorizontalResolution>");
		out.println("    <VerticalResolution>600</VerticalResolution>");
		out.println("   </Print>");
		out.println("   <Selected/>");
		out.println("   <Panes>");
		out.println("    <Pane>");
		out.println("     <Number>3</Number>");
		out.println("     <ActiveRow>2</ActiveRow>");
		out.println("     <ActiveCol>2</ActiveCol>");
		out.println("    </Pane>");
		out.println("   </Panes>");
		out.println("   <ProtectObjects>False</ProtectObjects>");
		out.println("   <ProtectScenarios>False</ProtectScenarios>");
		out.println("  </WorksheetOptions>");
		out.println(" </Worksheet>");
		out.println("</Workbook>");

		out.close();
		out.flush();
		return null;
	}

	public String managEnroll() {

		// Map enroll=df.sqlGetMap("SELECT * FROM Enroll WHERE Oid="+enrollOid);
		// List depts=df.sqlGet("SELECT * FROM Enroll_dept WHERE
		// Enroll_oid="+enrollOid);

		request.setAttribute("enrol", df.sqlGetMap("SELECT * FROM Enroll WHERE Oid=" + enrollOid));

		request.setAttribute("depts", df.sqlGet("SELECT * FROM Enroll_dept WHERE Enroll_oid=" + enrollOid));
		request.setAttribute("attach", df.sqlGet("SELECT * FROM Enroll_attach WHERE Enroll_oid=" + enrollOid));
		return SUCCESS;
	}

	/**
	 * 建立集活動
	 * 
	 * @return
	 */
	public String create() {

		Enroll e = new Enroll();
		e.setEnrollName("未命名的招生考試");
		df.update(e);
		enrollOid = e.getOid().toString();
		return managEnroll();
	}

	/**
	 * 對活動中的考生列表
	 * 
	 * @return
	 */
	public String managStmd() {
		List<Map> stmds = df.sqlGet("SELECT es.student_no,c.ClassName,c.ClassNo, e.enroll_name, es.Oid as esOid, "
				+ "er.Oid as erOid, es.student_name,er.* FROM Enroll_stmd es LEFT "
				+ "OUTER JOIN Class c ON es.depart_class=c.ClassNo, Enroll_regist er, "
				+ "Enroll e WHERE e.Oid=er.Enroll_oid AND es.idno=er.idno AND " + "er.Enroll_oid=" + enrollOid);
		Map tmp;
		for (int i = 0; i < stmds.size(); i++) {
			/*
			 * 在校生的問題 tmp=df.
			 * sqlGetMap("SELECT s.student_no, c.ClassNo, c.ClassName FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND s.idno='"
			 * +stmds.get(i).get("idno")+"'"); if(tmp!=null){ stmds.get(i).putAll(tmp); }
			 */
			stmds.get(i).put("depts", df.sqlGet(
					"SELECT d.quota, d.dept_name, erd.Oid, erd.choice, erd.rank FROM Enroll_regist_dept erd, Enroll_dept d WHERE erd.Enroll_dept_oid=d.Oid AND erd.idno='"
							+ stmds.get(i).get("idno") + "'ORDER BY erd.choice"));
			stmds.get(i).put("files", df.sqlGet(
					"SELECT ea.attach_name, era.path FROM Enroll_attach ea, Enroll_regist er, Enroll_regist_attach era WHERE ea.Oid=era.Enroll_attach_oid AND er.Oid=era.Enroll_regist_oid AND er.Oid="
							+ stmds.get(i).get("Oid") + " ORDER BY era.Oid"));

		}
		request.setAttribute("stmds", stmds);
		return SUCCESS;
	}

	/**
	 * 刪除活動
	 * 
	 * @return
	 */
	public String del() {
		EnrollDept d;
		Enroll e = (Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid=" + enrollOid).get(0);
		Message msg = new Message();
		if (e.getSignBegin() != null)
			if (e.getSignBegin().getTime() < new Date().getTime()) {
				msg.setError("開始報名後不可修改考試資料");
				this.savMessage(msg);
				return managEnroll();
			}
		df.exSql("DELETE FROM Enroll WHERE Oid=" + enrollOid);
		msg.setError("已刪除");
		this.savMessage(msg);
		return execute();
	}

	/**
	 * 儲存活動
	 * 
	 * @return
	 */
	public String saveEnroll() {

		EnrollDept d;
		Enroll e = (Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid=" + enrollOid).get(0);
		Message msg = new Message();
		/*
		 * if(e.getSignBegin()!=null) if(e.getSignBegin().getTime()<new
		 * Date().getTime()){ msg.setError("開始報名後不可修改考試資料"); this.savMessage(msg);
		 * return managEnroll(); }
		 */

		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			e.setEnrollName(enroll_name);
			e.setNote(note);
			e.setOpenMatch(new Timestamp(sf.parse(open_match).getTime()));
			e.setOpenScore(new Timestamp(sf.parse(open_score).getTime()));
			e.setRegFee(Short.parseShort(reg_fee));
			e.setSchoolYear(Short.parseShort(school_year));
			e.setSignBegin(new Timestamp(sf.parse(sign_begin).getTime()));
			e.setSignEnd(new Timestamp(sf.parse(sign_end).getTime()));
			e.setSubsel(Byte.parseByte((subsel)));
			df.update(e);

			if (brochure != null)
				saveFile("brochure", brochure, brochureFileName);// 儲存附件
			if (envelope != null)
				saveFile("envelope", envelope, envelopeFileName);// 儲存附件

			msg.setError("儲存完成");
			this.savMessage(msg);
			return managEnroll();
		} catch (Exception ex) {
			ex.printStackTrace();
			msg.setError("儲存發生錯誤, 請檢查欄位");
			this.savMessage(msg);
			return managEnroll();
		}

		// return managEnroll();
	}

	/**
	 * 儲存參與活動的系所
	 * 
	 * @return
	 */
	public String saveDept() {

		Enroll e = (Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid=" + enrollOid).get(0);
		Message msg = new Message();
		if (e.getSignBegin().getTime() < new Date().getTime()) {
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}
		df.exSql("DELETE FROM Enroll_dept WHERE Enroll_oid=" + enrollOid);
		EnrollDept d;
		for (int i = 0; i < dept_name.length; i++) {

			if (!CampusNo[i].equals("") && !SchoolNo[i].equals("") && !DeptNo[i].equals("") && !dept_name[i].equals("")
					&& !quota[i].equals("")) {
				d = new EnrollDept();
				d.setCampusNo(CampusNo[i]);
				d.setSchoolNo(SchoolNo[i]);
				d.setDeptNo(DeptNo[i]);
				d.setDeptName(dept_name[i]);
				d.setQuota(Short.parseShort(quota[i]));
				d.setEnrollOid(Integer.parseInt(enrollOid));
				df.update(d);
			}

		}

		msg.setError("儲存完成");
		this.savMessage(msg);
		return managEnroll();
	}

	/**
	 * 儲存活動的附件
	 * 
	 * @return
	 */
	public String saveAttach() {

		EnrollDept d;
		Enroll e = (Enroll) df.hqlGetListBy("FROM Enroll WHERE Oid=" + enrollOid).get(0);
		Message msg = new Message();
		if (e.getSignBegin().getTime() < new Date().getTime()) {
			msg.setError("開始報名後不可修改考試資料");
			this.savMessage(msg);
			return managEnroll();
		}

		EnrollAttach a;
		df.exSql("DELETE FROM Enroll_attach WHERE Enroll_oid=" + enrollOid);
		for (int i = 0; i < attach_name.length; i++) {

			if (!attach_name[i].equals("") && !online[i].equals("")) {
				a = new EnrollAttach();
				a.setAttachName(attach_name[i]);
				a.setEnrollOid(Integer.parseInt(enrollOid));
				a.setNote(att_note[i]);
				a.setOnline(online[i]);
				df.update(a);
			}
		}

		msg.setError("儲存完成");
		this.savMessage(msg);
		return managEnroll();
	}

	public String getBrochureFileName() {
		return brochureFileName;
	}

	public void setBrochureFileName(String brochureFileName) {
		this.brochureFileName = brochureFileName;
	}

	public String getEnvelopeFileName() {
		return envelopeFileName;
	}

	public void setEnvelopeFileName(String envelopeFileName) {
		this.envelopeFileName = envelopeFileName;
	}

	/**
	 * 儲存成績
	 * 
	 * @return
	 */
	public String saveScore() {
		EnrollRegist er;
		EnrollRegistDept erd;
		EnrollStmd es;
		Message msg = new Message();
		msg.setMsg("");

		// 准考證和成績
		for (int i = 0; i < registOid.length; i++) {
			if (!registOid[i].equals("")) {
				try {
					er = (EnrollRegist) df.hqlGetListBy("FROM EnrollRegist WHERE Oid=" + registOid[i]).get(0);
					if (!no[i].equals(""))
						er.setNo(no[i]);
					if (!score[i].equals(""))
						er.setScore(Short.parseShort(score[i]));
					if (!score1[i].equals(""))
						er.setScore1(Short.parseShort(score1[i]));
					if (!score2[i].equals(""))
						er.setScore2(Short.parseShort(score2[i]));
					if (!score3[i].equals(""))
						er.setScore3(Short.parseShort(score3[i]));
					df.update(er);
				} catch (Exception e) {
					msg.addMsg("但<br>" + e);
				}
			}
		}

		// 名次
		for (int i = 0; i < deptOid.length; i++) {
			if (!deptOid[i].equals("")) {
				erd = (EnrollRegistDept) df.hqlGetListBy("FROM EnrollRegistDept WHERE Oid=" + deptOid[i]).get(0);
				if (!rank[i].equals("")) {
					erd.setRank(Short.parseShort(rank[i]));
				} else {
					erd.setRank(null);
				}
				df.update(erd);
			}
		}

		// 分班
		List<Stmd> stmds;
		Stmd s;
		for (int i = 0; i < idno.length; i++) {

			if (!student_no[i].equals("") && depart_class[i].indexOf(",") > 0) {
				try {
					es = (EnrollStmd) df.hqlGetListBy("FROM EnrollStmd WHERE idno='" + idno[i] + "'").get(0);
					es.setDepartClass(depart_class[i].substring(0, depart_class[i].indexOf(",")));
					es.setStudentNo(student_no[i]);
					df.update(es);

					stmds = df.hqlGetListBy("FROM Stmd WHERE studentNo='" + es.getStudentNo() + "'");
					if (stmds.size() < 1) {
						s = new Stmd();
					} else {
						s = stmds.get(0);
					}
					s.setDepartClass(es.getDepartClass());
					s.setStudentNo(es.getStudentNo());
					s.setStudentName(es.getStudentName());
					s.setBirthday(es.getBirthday());
					s.setIdno(es.getIdno());
					s.setSex(es.getSex());
					s.setEntrance(Short.parseShort(getContext().getAttribute("school_year") + "09"));
					s.setSchlName(es.getSchlName());
					s.setGradDept(es.getGradDept());
					s.setParentName(es.getParentName());
					if (es.getParentPhone() != null)
						s.setIdentRemark("家長連絡電話" + es.getParentPhone());
					s.setPermPost(es.getPermPost());
					s.setPermAddr(es.getPermAddr());
					s.setCurrAddr(es.getCurrAddr());
					s.setCurrPost(es.getCurrPost());
					s.setEmail(es.getEmail());
					s.setTelephone(es.getTelephone());
					s.setCellPhone(es.getCellPhone());
					df.update(s);
				} catch (Exception e) {
					e.printStackTrace();
					msg.addMsg("<br>學號" + student_no[i] + "學生基本資料請手動建立或更新");
				}

			}
		}

		msg.addMsg("<br>已儲存考生資料");
		this.savMessage(msg);

		return managStmd();
	}

	/**
	 * 上傳文件
	 * 
	 * @param type
	 * @param fileUpload
	 * @param fileUploadFileName
	 */
	private void saveFile(String type, File fileUpload, String fileUploadFileName) {

		if (fileUpload != null) {
			long now = new Date().getTime();
			String fileName;
			String filePath;
			String tmp_path = getContext().getRealPath("/tmp");// 本機目錄
			String target = "host_runtime";
			File dst;
			Map<String, String> ftpinfo;
			File uploadedFile;
			// for (int i = 0; i < fileUpload.length; i++) {
			uploadedFile = fileUpload;
			fileName = now + bio.getExtention(fileUploadFileName);// 置換檔名
			filePath = getContext().getRealPath("/tmp") + "/" + fileName;
			if (getContext().getAttribute("isServer").equals("0")) {// 測試的情況
				target = "host_debug";
				filePath = filePath.replace("\\", "/");
				tmp_path = tmp_path.replace("\\", "/");
			}
			dst = new File(tmp_path);// 暫存資料夾 TBTB
			if (!dst.exists())
				dst.mkdir();
			bio.copyFile(fileUpload, new File(filePath));
			ftpinfo = df.sqlGetMap(
					"SELECT " + target + " as host, username, password, path FROM SYS_HOST WHERE useid='enroll'");

			// 存放於TaskOid資料夾內
			bio.putFTPFile(ftpinfo.get("host"), ftpinfo.get("username"), ftpinfo.get("password"), tmp_path + "/",
					"enroll/", fileName);

			// }
			df.exSql("UPDATE Enroll SET " + type + "='" + fileName + "'WHERE Oid=" + enrollOid);

		}
	}

}
