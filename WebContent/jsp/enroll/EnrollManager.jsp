<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>考試管理</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js"></script>

<link href="/eis/inc/bootstrap/plugin/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput_locale_zh-TW.js"></script>
	
</head>
<body>    


<div class="bs-callout bs-callout-info">
<h4>考試管理</h4> 
<small>選擇範圍點選列印，標題、統計等其它細節資料，於報表中的頁首/頁尾，請以預覽列印檢視</small>

</div>


<form action="EnrollManager" method="post" class="form-inline" enctype="multipart/form-data" autocomplete="off">
<input type="hidden" name="enrollOid" id="enrollOid" value="${enrollOid}"/>

<c:if test="${empty enrol&& empty stmds}">
<div class="panel panel-primary">
<div class="panel-heading">招生考試列表</div>
<div class="panel panel-body"></div>
<table class="table table-hover">
	<tr>
		<td>名稱</td>
		<td>報名期間</td>
		<td>成績與放榜</td>
		
		<td nowrap>人數</td>
		<td><button onClick="" name="method:create" class="btn btn-success">建立新的獨立招生</button></td>
	</tr>
	<c:forEach items="${enrolls}" var="e">
	<tr>
		<td>${e.enroll_name}</td>
		<td nowrap>${fn:substring(e.sign_begin, 0, 10)}<br>${fn:substring(e.sign_end, 0, 10)}</td>
		<td nowrap>${fn:substring(e.open_score, 0, 10)}<br>${fn:substring(e.open_match, 0, 10)}</td>
		<td>${e.con}/${e.cnt}</td>
		<td nowrap>
		<div class="btn-group" role="group" aria-label="...">
			<button onClick="$('#enrollOid').val('${e.Oid}')" name="method:managEnroll" class="btn btn-default">考試管理</button>
		  	<button onClick="$('#enrollOid').val('${e.Oid}')" name="method:managStmd" class="btn btn-default">考生管理</button>
		  	<a href="/pis/EnrollDoc?EnrollOid=${e.Oid}" class="btn btn-default">考生列表</a>
		</div>
		<button onClick="return(confirm('已輸入的所有資料將無法重複使用, 確定刪除?'))" onMouseOver="$('#enrollOid').val('${e.Oid}')" name="method:del" class="btn btn-danger">刪除</button>
		</td>
	</tr>
	</c:forEach>
</table>

</div>

</c:if>

<c:if test="${!empty stmds}">




<div class="panel panel-primary">
<div class="panel-heading">${stmds[0].enroll_name}</div>
<ul class="list-group">
  <li class="list-group-item"><button type="button" class="btn btn-default btn-xs">管理排名</button> <button class="btn btn-danger btn-xs" name="method:saveScore">儲存</button> <a href="EnrollManager" class="btn btn-default btn-xs">或返回考試列表</a> <button class="btn btn-danger btn-xs" name="method:saveScore">儲存</button> 會將修改過的欄位全部儲存</li>
  <!-- li class="list-group-item"><button class="btn btn-danger btn-xs" name="method:saveScore">儲存</button> 鍵會將修改過的欄位全部儲存 </li-->
</ul>

</div>



<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	
	<c:forEach items="${stmds}" var="s">	
	
	
	<div class="panel panel-primary" onMouseOver="$('#registOid${s.Oid}').val(${s.Oid})">
    	<div class="panel-heading" role="tab" id="heading${s.Oid}">
	      	<table width="100%"><tr><td width="100%"nowrap>
	      		<h4 class="panel-title">
		        <a role="button" data-toggle="collapse" 
		        data-parent="#accordion" href="#collapse${s.Oid}" 
		        aria-expanded="true" aria-controls="collapse${s.Oid}">		        
				<span class="label label-danger">
				<c:if test="${empty s.no}">未核定準考證</c:if>
				<c:if test="${!empty s.no}">${s.no}</c:if>
				</span>&nbsp;${s.student_name}</a>
		        
	      		</h4>
		        </td>
		        <td nowrap>
				<h4 class="panel-title">
				<a role="button" data-toggle="collapse" 
		        data-parent="#accordion" href="#collapse${s.Oid}" 
		        aria-expanded="true" aria-controls="collapse${s.Oid}">		        
				<span class="btn btn-default btn-xs">檢視 ${s.no}</span>
				
				
		        </a>
		        </h4>
		        </td>
		        </tr></table>		       
    		</div>
    		<div class="panel-body" >				
				<div class="input-group">
				<span class="input-group-addon">准考證</span>
				<input type="hidden" id="registOid${s.Oid}" name="registOid" value=""/>
				<input type="hidden" class="form-control" value="${s.idno}" name="idno">
			    <input type="text" class="form-control" value="${s.no}" name="no" id="no">
			</div>
			
			
			<div class="btn-group">
			<a class="btn btn-default"href="/pis/EnrollDoc?idno=${s.idno}">檢視報名表</a> 
    		<button type="button"class="btn btn-default" data-toggle="collapse" 
		        data-parent="#accordion" href="#collapse${s.Oid}" 
		        aria-expanded="true" aria-controls="collapse${s.Oid}">		        
				管理排名</button>
			<button class="btn btn-primary" name="method:editStmd" disabled>管理基本資料</button>
			</div>
			<br><br>
    		
    		<div class="input-group">
				<span class="input-group-addon">筆試</span>
			    <input type="text" class="form-control" style="width:50px;" value="${s.score1}" name="score1" id="score1">
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">口試</span>
			    <input type="text" class="form-control" style="width:50px;" value="${s.score2}" name="score2" id="score2">
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">書面</span>
			    <input type="text" class="form-control" style="width:50px;" value="${s.score3}" name="score3" id="score3">
			</div>
			
			<div class="input-group">
				<span class="input-group-addon">成績</span>
			    <input type="text" class="form-control" style="width:50px;" value="${s.score}" name="score" id="score">
			</div>
			<div class="btn-group">
    		<button class="btn btn-danger" name="method:saveScore">儲存</button>
    		<a href="EnrollManager" class="btn btn-default">返回考試列表</a>
    		</div>
    		</div>
	    	<div id="collapse${s.Oid}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${s.Oid}">
	    	
	    	<table class="table">
	    		<tr>
	      			<th nowrap>系所</td>
	      			<th nowrap>志願</td>
	      			<th width="100%">
	      			<div class="input-group">
						<span class="input-group-addon">錄取班級</span>
						<c:if test="${!empty s.ClassNo}">
						<input type="text" class="form-control depart_class" data-provide="typeahead" value="${s.ClassNo}, ${s.ClassName}" name="depart_class">
						</c:if>
					    <c:if test="${empty s.ClassNo}">
					    <input type="text" class="form-control depart_class" data-provide="typeahead" value="" name="depart_class">
					    </c:if>
					    
					</div>
			
					<div class="input-group">
						<span class="input-group-addon">學號</span>
					    <input type="text" class="form-control" value="${s.student_no}" name="student_no" id="student_no">
					</div>	      			
	      			</td>
	      		</tr>
	    		<c:forEach items="${s.depts}" var="d">
	      		<tr>
	      			<td nowrap>${d.dept_name}<input type="hidden" id="deptOid${d.Oid}" name="deptOid" value=""/></td>
	      			<td nowrap>${d.choice}</td>
	      			<td>
	      			<select name="rank" class="form-control" onChange="$('#deptOid${d.Oid}').val(${d.Oid})">
	      				<option value="">未錄取</option>
	      				<option <c:if test="${d.rank eq '0'}">selected</c:if> value="0">正取</option>
	      				<c:forEach begin="1" end="${d.quota}" varStatus="i">
	      				<option <c:if test="${d.rank==i.index}">selected</c:if> value="0">備${i.index}</option>
	      				</c:forEach>
	      			</select>	      			
	      			</td>
	      		</tr>
	      		</c:forEach>
	      		<c:if test="${!empty s.files}">
	      		<tr>
	      			<td colspan="3">
	      			<div class="btn-group">
	      			<c:forEach items="${s.files}" var="f">
	      			
	      			<a class="btn btn-default btn-sm" href="/pis/getFtpFile?path=enroll&file=${f.path}">${f.attach_name}</a>
	      			
	      			</c:forEach>
	      			</div>
	      			</td>
	      		</tr>
	      		</c:if>
	      		<tr>
	      			<td colspan="3">
	      			<button class="btn btn-danger" name="method:saveScore">儲存</button>
	      			
	      			</td>
	      		</tr>
	    	</table>
	    	
	    </div>
  	</div>
  	</c:forEach>
  
</div>










</c:if>

<c:if test="${!empty enrol}">
<div class="panel panel-primary">
<div class="panel-heading">招生考試基本資料</div>
<table class="table table-hover">
	<tr>
		<td>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">學年度</div>
		      <input type="text" style="width:100px;" name="school_year" value="${enrol.school_year}" class="form-control" placeholder="考試名稱">
		      
		    </div>
		</div>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">考試名稱</div>
		      <input type="text" style="width:300px;" name="enroll_name" value="${enrol.enroll_name}" class="form-control" placeholder="考試名稱">
		      <div class="input-group-addon">單獨招生委員會</div>
		    </div>
		</div>
  		</td>
  	</tr>
  	<tr>
		<td>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">報名開始</div>
		      <input type="text"  name="sign_begin" value="${enrol.sign_begin}" class="form-control dateinput" placeholder="考試名稱">
		      
		    </div>
		</div>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">結束</div>
		      <input type="text" name="sign_end" value="${enrol.sign_end}" class="form-control dateinput" placeholder="考試名稱">
		      
		    </div>
		</div>
  		</td>
  	</tr>
  	<tr>
		<td>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">公佈成績</div>
		      <input type="text"  name="open_score" value="${enrol.open_score}" class="form-control dateinput" placeholder="考試名稱">
		      
		    </div>
		</div>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">放榜</div>
		      <input type="text" name="open_match" value="${enrol.open_match}" class="form-control dateinput" placeholder="考試名稱">
		      
		    </div>
		</div>
  		</td>
  	</tr>
  	<tr>
		<td>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">費用</div>
		      <input type="text" style="width:100px;" name="reg_fee" value="${enrol.reg_fee}" class="form-control" placeholder="金額">
		      <div class="input-group-addon">元</div>
		    </div>
		</div>
		<div class="form-group">
		    
		    <div class="input-group">
		      <div class="input-group-addon">名額</div>
		      <input type="text" style="width:100px;" name="subsel" value="${enrol.subsel}" class="form-control" placeholder="錄取人數">
		      <div class="input-group-addon">人</div>
		    </div>
		</div>
  		</td>
  	</tr>
  	
  	<tr>
		<td>
		<label for="brochure"><a href="/pis/getFtpFile?path=enroll&file=${enrol.brochure}">簡章 ${enrol.brochure}</a></label>
		<input name="brochure" type="file" 
				data-show-preview="false" 
				data-show-upload="false" 
				id="brochure" 
				class="file upload"/>
		
		</td>
	</tr>
	<tr>
		<td>
		<label for="envelope"><a href="/pis/getFtpFile?path=enroll&file=${enrol.envelope}">信封 ${enrol.envelope}</a></label>
		<input disabled name="envelope" type="file" 
				data-show-preview="false" 
				data-show-upload="false" 
				id="envelope" 
				class="file upload"/>
				
  		</td>
  	</tr>
</table>
<div class="panel-body">
<div class="btn-group" role="group" aria-label="...">
<button class="btn btn-danger" name="method:saveEnroll">儲存基本資料</button>
<a href="EnrollManager" class="btn btn-default">離開</a>
</div>
</div>
</div>


<div class="panel panel-primary">
<div class="panel-heading">系所列表</div>

<table class="table" id="dept_table">
	<tr>
		<td>校區</td>
		<td>學制</td>
		<td>科系</td>
		<td>名稱</td>
		<td>名額  <button type="button" onClick="showDepts();" class="btn btn-success btn-sm">新增科系</button></td>
	</tr>
	<c:forEach items="${depts}" var="d">
	<tr>
		<td>
		<select data-width="auto" name="CampusNo" class="selectpicker">
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option <c:if test="${d.CampusNo eq c.id}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td>	
		<select data-width="auto" name="SchoolNo" class="selectpicker">
			<c:forEach items="${CODE_SCHOOL}" var="c">
			<option <c:if test="${d.SchoolNo eq c.id}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td>
		<select data-width="auto" name="DeptNo" class="selectpicker">
			<c:forEach items="${CODE_DEPT}" var="c">
			<option <c:if test="${d.DeptNo eq c.id}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td><input type="text" name="dept_name" class="form-control" value="${d.dept_name}"/></td>
		<td width="100%"><input type="text" name="quota" value="${d.quota}" class="form-control" style="width:60px;"/></td>
	</tr>
	</c:forEach>
</table>
<div class="panel-body">
<div class="btn-group" role="group" aria-label="...">
<button class="btn btn-danger" name="method:saveDept">儲存系所資料</button>
<a href="EnrollManager" class="btn btn-default">離開</a>
</div>
</div>

</div>

<div class="panel panel-primary">
<div class="panel-heading">附件列表</div>

<table class="table" id="att_table">
	<tr>
		<td nowrap width="200">名稱</td>
		<td nowrap width="100">線上補件</td>
		<td>說明  <button type="button" onClick="showAttach();" class="btn btn-success btn-sm">新增附件</button></td>
	</tr>
	<c:forEach items="${attach}" var="a">
	<tr>
		<td width="1"><input class="form-control" type="text" name="attach_name" value="${a.attach_name}" /></td>
		<td width="1">
		<select class="form-control" name="online">
			<option value="0">否</option>
			<option <c:if test="${a.online eq'1'}">selected</c:if> value="1">是</option>
			
		</select>
		
		</td>
		<td width="100%"><input class="form-control" type="text" name="att_note" value="" /></td>
	</tr>
	</c:forEach>
	
</table>
<div class="panel-body">
<div class="btn-group" role="group" aria-label="...">
<button class="btn btn-danger" name="method:saveAttach">儲存附件資料</button>
<a href="EnrollManager" class="btn btn-default">離開</a>
</div>
</div>


</c:if>
</form>


<table id="att_tmp" style="display:none;">
	<tr>
		<td width="1"><input class="form-control" type="text" name="attach_name" value="" /></td>
		<td width="1">
		<select class="form-control" name="online">
			<option value=""></option>
			<option value="1">是</option>
			<option value="0">否</option>
		</select>
		
		</td>
		<td><input class="form-control" type="text" name="att_note" value="" /></td>
	</tr>
</table>

<table id="dept_tmp" style="display:none;">

	<tr>
		<td width="1">
		<select data-width="auto" name="CampusNo" class="form-control">
			<option value="">校區</option>
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td width="1">	
		<select data-width="auto" name="SchoolNo" class="form-control">
			<option value="">學制</option>
			<c:forEach items="${CODE_SCHOOL}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td width="1">
		<select data-width="auto" name="DeptNo" class="form-control">
			<option value="">科系</option>
			<c:forEach items="${CODE_DEPT}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>	
		</select>
		</td>
		<td width="1"><input type="text" name="dept_name" class="form-control" value=""/></td>
		<td width="100%"><input type="text" name="quota" value="" class="form-control" style="width:60px;"/></td>
	</tr>


</table>

<script>
$(".upload").fileinput({
	multiple: false,			        
    language: "zh-TW",
    uploadUrl: "",
    //allowedFileExtensions: ["xls", "xlsx"]
});

$(".dateinput").datetimepicker({
	changeMonth: true,
	changeYear: true,
	//minDate: '@minDate',
	yearRange: "-1:+1",
	//showButtonPanel: true,
	//dateFormat: 'yymmdd'
	defaultDate: new Date(${school_year+1911-20}, 00, 01)
});

$(".depart_class").typeahead({
	//remote:"#agent",
	source : [],
	items : 10,
	updateSource:function(inputVal, callback){
		$.ajax({
			url:"/eis/autoCompAnyCode",
		    dataType: 'jsonp',
		    jsonp:'back',          //jsonp請求方法
		    data:{
		    	bootstrap:"1",
		    	idCol:"ClassNo",
		    	nameCol:"ClassName",
		    	table:"Class",
		    	value:inputVal			    
		    },
		    cache:false,
		    type:'POST',
		    success: function(d) {    			    	
		    	callback(d.list);
		    }
		});
	}		
});

function showDepts(){
	$('#dept_table').hide();
	$('#dept_table').append($('#dept_tmp').html());
	$('#dept_table').show("fast");
}

function showAttach(){
	$('#att_table').hide();
	$('#att_table').append($('#att_tmp').html());
	$('#att_table').show("fast");
}
</script>
</body>
</html>