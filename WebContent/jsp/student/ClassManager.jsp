<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>班級管理</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
</head>
<body>    
<div class="bs-callout bs-callout-info">
<h4>班級管理</h4> 
<small></small>
</div>

<form action="ClassManager" method="post" class="form-inline" onSubmin="$.blockUI(); ">
<input type="hidden" id="Oid" name="Oid" value="${Oid}"/>



<div <c:if test="${!empty cl}">style="display:none;"</c:if> class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<table class="table">
	<tr>
		<td>
		
		<select name="Type" class="selectpicker" data-width="auto">
			<option value="">所有類型</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_CLASS_TYPE}" var="c">
			<option <c:if test="${c.id eq Type}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新類型</option>
		</select>
		
		<select name="CampusNo" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有校區</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option <c:if test="${c.id eq CampusNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新校區</option>
		</select>
		
		
		<select name="InstNo" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有學院</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_COLLEGE}" var="c">
			<option <c:if test="${c.id eq InstNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新學院</option>
		</select>
		
		<select name="graduate" class="selectpicker" data-width="auto">
			<option value="">是否為畢業班</option>
			<option <c:if test="${graduate eq '1'}">selected</c:if> value="1">畢業班</option>
			<option <c:if test="${graduate eq '0'}">selected</c:if> value="0">非畢業班</option>
		</select>
		
		<select name="nobody" class="selectpicker" data-width="auto">
			<option value="">是否有學生</option>
			<option <c:if test="${nobody eq '1'}">selected</c:if> value="1">有學生</option>
			<option <c:if test="${nobody eq '0'}">selected</c:if> value="0">無學生</option>
		</select>
				
		</td>
	</tr>
	<tr>
		<td>
		<select name="SchoolType" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有部制</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_SCHOOL_TYPE}" var="c">
			<option <c:if test="${c.id eq SchoolType}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新部制</option>
		</select>			
		
		<select name="SchoolNo" class="selectpicker" data-width="auto" data-header="選擇學制"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有學制</option>
			<c:forEach items="${CODE_SCHOOL}" var="c">
			<option <c:if test="${c.id eq SchoolNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新學制</option>
		</select>
		
		<select name="DeptNo" class="selectpicker" data-width="auto" 
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有科系</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_DEPT}" var="c">
			<option <c:if test="${c.id eq DeptNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新科系</option>
		</select>
		
		<select name="Grade" class="selectpicker" data-width="auto">
			<option value="">所有年級</option>
			<option <c:if test="${Grade eq '1'}">selected</c:if> value="1">1</option>
			<option <c:if test="${Grade eq '2'}">selected</c:if> value="2">2</option>
			<option <c:if test="${Grade eq '3'}">selected</c:if> value="3">3</option>
			<option <c:if test="${Grade eq '4'}">selected</c:if> value="4">4</option>
			<option <c:if test="${Grade eq '0'}">selected</c:if> value="0">延修/學分</option>
		</select>
		
		
		<select name="SeqNo" class="selectpicker" data-width="auto">
			<option value="">所有班級</option>
			<option <c:if test="${SeqNo eq '1'}">selected</c:if> value="1">甲班</option>
			<option <c:if test="${SeqNo eq '2'}">selected</c:if> value="2">乙班</option>
			<option <c:if test="${SeqNo eq '3'}">selected</c:if> value="3">丙班</option>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>
		<div class="btn-group" role="group" aria-label="...">
		<button class="btn btn-primary" name="method:search" onClick="jQuery.blockUI();">查詢班級</button>
		<a class="btn btn-default" href="ClassManager">重新查詢</a>
		</div>
		<button class="btn btn-success" name="method:add">建立新班級</button>
		</td>
	</tr>
	


</table>
</div>



<c:if test="${!empty cls}">
<div class="panel panel-primary">
<div class="panel-heading">班級列表</div>

<display:table name="${cls}" id="row" class="table table-bordered table-striped" sort="list"  requestURI="ClassManager?method=search">
	  	<display:column style="width:1%;">
			<button class="btn btn-default" name="method:edit" onClick="edit('${row.Oid}')">修改</button>
		</display:column>
		<display:column style="white-space:nowrap; width:5%;" title="昨日人數" property="stds" sortable="true" />
	  	<display:column style="white-space:nowrap; width:5%;" title="型態" property="typeName" sortable="true" />
	  	<display:column style="white-space:nowrap; width:5%;" title="班級代碼" property="ClassNo" sortable="true" />
	  	<display:column style="white-space:nowrap; width:5%;" title="班級名稱" property="ClassName" sortable="true" />	  	
		<display:column style="white-space:nowrap; width:10%;" title="導師" sortable="true">
		${row.cname} ${row.CellPhone}
		</display:column>
		
	  	<display:column style="white-space:nowrap; text-aling:right;" title="修改者" sortable="true">
	  	${row.editname} ${row.editime}
	  	</display:column>
</display:table>


</div>
</c:if>




<c:if test="${!empty cl}">
<div class="panel panel-primary">
<div class="panel-heading">正在修改 ${cl.ClassName} <span class="glyphicon glyphicon-user" aria-hidden="true"></span> ${cl.stds}</div>
<table class="table">
	<tr>
		<td>
		
		<select name="Type1" class="selectpicker" data-width="auto">
			<c:forEach items="${CODE_CLASS_TYPE}" var="c">
			<option <c:if test="${c.id eq cl.Type}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		
		<select name="CampusNo1" class="selectpicker" data-width="auto">
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option <c:if test="${c.id eq cl.CampusNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		
		
		<select name="InstNo1" class="selectpicker" data-width="auto">			
			<c:forEach items="${CODE_COLLEGE}" var="c">
			<option <c:if test="${c.id eq cl.InstNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		
		<select name="graduate1" class="selectpicker" data-width="auto">
			<option value="">是否為畢業班</option>
			<option <c:if test="${cl.graduate eq '1'}">selected</c:if> value="1">畢業班</option>
			<option <c:if test="${cl.graduate eq '0'}">selected</c:if> value="0">非畢業班</option>
		</select>
		
		</td>
	</tr>
	<tr>
		<td>
		<select name="SchoolType1" class="selectpicker" data-width="auto">
			<c:forEach items="${CODE_SCHOOL_TYPE}" var="c">
			<option <c:if test="${c.id eq cl.SchoolType}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>			
		
		<select name="SchoolNo1" class="selectpicker" data-width="auto" data-header="選擇學制">			
			<c:forEach items="${CODE_SCHOOL}" var="c">
			<option <c:if test="${c.id eq cl.SchoolNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		
		<select name="DeptNo1" class="selectpicker" data-width="auto">			
			<c:forEach items="${CODE_DEPT}" var="c">
			<option <c:if test="${c.id eq cl.DeptNo}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		
		<select name="Grade1" class="selectpicker" data-width="auto">
			<option value="">所有年級</option>
			<option <c:if test="${cl.Grade eq '1'}">selected</c:if> value="1">1</option>
			<option <c:if test="${cl.Grade eq '2'}">selected</c:if> value="2">2</option>
			<option <c:if test="${cl.Grade eq '3'}">selected</c:if> value="3">3</option>
			<option <c:if test="${cl.Grade eq '4'}">selected</c:if> value="4">4</option>
			<option <c:if test="${cl.Grade eq '0'}">selected</c:if> value="0">延修/學分</option>
		</select>
		
		
		<select name="SeqNo1" class="selectpicker" data-width="auto">
			<option value="">所有班級</option>
			<option <c:if test="${cl.SeqNo eq '1'}">selected</c:if> value="1">甲班</option>
			<option <c:if test="${cl.SeqNo eq '2'}">selected</c:if> value="2">乙班</option>
			<option <c:if test="${cl.SeqNo eq '3'}">selected</c:if> value="3">丙班</option>
		</select>
		</td>
	</tr>
	
	<tr>
		<td>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">班級代碼</span>
			<input type="text" class="form-control" name="ClassNo" value="${cl.ClassNo}" />
		</div>
		
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">班級名稱</span>
			<input type="text" class="form-control" name="ClassName" value="${cl.ClassName}" />
		</div>
		
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">班級簡稱</span>
			<input type="text" class="form-control" name="ShortName" value="${cl.ShortName}" />
		</div>
		
		
		
		</td>
	</tr>
	
	<!--tr>
		<td>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">舊系統(學/部)對應代碼</span>
			<input type="text" class="form-control" name="Dept" value="${cl.Dept}" />
		</div>
		
		
		
		
		</td>
	</tr-->
	
	<tr>
		<td>
		<button class="btn btn-default" name="method:save">儲存</button>
		<button class="btn btn-danger" name="method:search">離開</button>
		</td>
	</tr>
	
	

</table>
</div>
</c:if>
</form>

<script>
function edit(Oid){
	$("#Oid").val(Oid);	
}
</script>
</body>
</html>