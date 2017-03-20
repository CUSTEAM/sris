package action.print;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import action.BaseAction;
import model.Message;
import print.diploma.eng.day.AssociateDegree;
import print.diploma.eng.day.BachelorDegree;
import print.diploma.eng.day.MasterDegree;
import print.diploma.eng.night.AssociateDegreeNight;
import print.diploma.eng.night.BachelorDegreeNight;
import print.diploma.eng.night.MasterDegreeNight;

public class DocPrintAction extends BaseAction{
	
	public String cno;
	public String tno;	
	public String sno;
	public String dno;
	public String gno;
	public String zno;	
	
	public String type,student_no;	
	
	public String execute(){
		
		return SUCCESS;
	}
	
	public String print() throws IOException{
		List<Map>stds;
		if(student_no.length()>0){
			if(student_no.indexOf(",")<-1){
				Message msg=new Message();
				msg.setError("學號姓名不完整");
				this.savMessage(msg);
				return SUCCESS;				
			}
			stds=df.sqlGet("SELECT student_no FROM stmd WHERE student_no='"+student_no.substring(0, student_no.indexOf(","))+"'");
			if(stds.size()<1)stds=df.sqlGet("SELECT student_no FROM Gstmd WHERE student_no='"+student_no.substring(0, student_no.indexOf(","))+"'");
		}else{
			if(tno.equals("")||sno.equals("")||dno.equals("")||gno.equals("")){
				Message msg=new Message();
				msg.setError("班級不完整");
				this.savMessage(msg);
			}
			StringBuilder sb=new StringBuilder("SELECT student_no FROM stmd s, Class c WHERE s.depart_class=c.ClassNo AND c.CampusNo='"+cno+"'");
			if(!tno.equals(""))sb.append("AND c.SchoolType='"+tno+"'");
			if(!sno.equals(""))sb.append("AND c.SchoolNo='"+sno+"'");
			if(!dno.equals(""))sb.append("AND c.DeptNo='"+dno+"'");
			if(!gno.equals(""))sb.append("AND c.Grade='"+gno+"'");
			if(!zno.equals(""))sb.append("AND c.SeqNo='"+zno+"'");
			stds=df.sqlGet(sb.toString());
		}
		
		//String template;
		switch (type){
        
        case"EDAD":
            //英文日間副學士
            AssociateDegree ad=new AssociateDegree();
            ad.print(response, stds);
            break;
        
        case"EDBD":
            //英文日間學士
            BachelorDegree bd=new BachelorDegree();
            bd.print(response, stds);
            break;
        
        case"EDMD":
            //英文日間碩士
            MasterDegree md=new MasterDegree();
            md.print(response, stds);
            break;
            
            
        case"ENAD":
            //英文夜間副學士
            AssociateDegreeNight adn=new AssociateDegreeNight();
            adn.print(response, stds);
            break;
            
        case"ENMD":
            //英文夜間碩士
            MasterDegreeNight mdn=new MasterDegreeNight();
            mdn.print(response, stds);
            break;
            
        case"ENBD":
            //英文夜間學士
            BachelorDegreeNight bdn=new BachelorDegreeNight();
            bdn.print(response, stds);
            break;
    }
		
		return SUCCESS;
	}

}
