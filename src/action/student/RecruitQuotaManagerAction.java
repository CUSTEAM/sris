package action.student;

import action.BaseAction;

public class RecruitQuotaManagerAction extends BaseAction{
	
	public String[] Oid,
	school_year,
	school_term,
	SchoolNo,
	SchoolType,
	DeptNo,
	GroupNo,
	Type,
	WayOf,
	Quoata;
	
	public String execute() {
		
		
		request.setAttribute("quota", df.sqlGet("SELECT * FROM Recruit_quota ORDER BY school_year, school_term"));
		
		return SUCCESS;		
	}	
	
	
	/***
	 * 儲存
	 * @return
	 */
	public String save() {
		
		for(int i=0; i< Oid.length; i++) {
			//新增
			if(Oid[i].equals("")) {
				//有條件則新增
				if(		!school_year[i].equals("")&&
						
						!school_term[i].equals("")&&
						!SchoolNo[i].equals("")&&
						!SchoolType[i].equals("")&&
						!DeptNo[i].equals("")&&
						!Type[i].equals("")&&
						!WayOf[i].equals("")&&
						!Quoata[i].equals("")
				) {
					df.exSql("INSERT INTO Recruit_quota(school_year,school_term,SchoolNo,SchoolType,DeptNo,GroupNo,Type,WayOf,Quoata)VALUES('"+school_year[i]+"','"+school_term[i]+"','"+SchoolNo[i]+"','"+SchoolType[i]+"','"+DeptNo[i]+"','"+GroupNo[i]+"','"+Type[i]+"','"+WayOf[i]+"','"+Quoata[i]+"');");
				}
				
				
			}else {
				
				
				//下方區域有條件修改
				if(!school_year[i].equals("")&&
						!school_year[i].equals("")&&
						!school_term[i].equals("")&&
						!SchoolNo[i].equals("")&&
						!SchoolType[i].equals("")&&
						!DeptNo[i].equals("")&&
						!Type[i].equals("")&&
						!WayOf[i].equals("")&&
						!Quoata[i].equals("")) {
					
					df.exSql("UPDATE Recruit_quota SET "
							+ "school_year='"+school_year[i]+"',"
							+ "school_term='"+school_term[i]+"',"
							+ "SchoolNo='"+SchoolNo[i]+"',"
							+ "SchoolType='"+SchoolType[i]+"',"
							+ "DeptNo='"+DeptNo[i]+"',"
							+ "Type='"+Type[i]+"',"
							+ "WayOf='"+WayOf[i]+"',"
							+ "Quoata='"+Quoata[i]+"'WHERE Oid="+Oid[i]
							
							
							);
					
					
				}else {
					//不符條件則刪除
					df.exSql("DELETE FROM Recruit_quota WHERE Oid="+Oid[i]);
				}
				
				
			}
			
			
			
			
		}
		
		
		return execute();		
	}

}
