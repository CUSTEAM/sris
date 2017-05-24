<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<small>請選擇審核範圍後查詢</small>
</div>

<form action="ClassManager" method="post" class="form-inline">

</form>
<table class="table">
	<tr>
		<td>
		
		<select name="DeptNo" class="selectpicker" data-width="auto">
			<option value="">所有類型</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_CLASS_TYPE}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新類型</option>
		</select>
		
		<select name="CampusNo" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有校區</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_CAMPUS}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新校區</option>
		</select>
		
		
		<select name="InstNo" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有學院</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_COLLEGE}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新學院</option>
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
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新部制</option>
		</select>	
		
		
		
		<select name="SchoolNo" class="selectpicker" data-width="auto" data-header="選擇學制"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有學制</option>
			<c:forEach items="${CODE_SCHOOL}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新學制</option>
		</select>
		
		<select name="DeptNo" class="selectpicker" data-width="auto" 
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有科系</option>
			<option data-divider="true"></option>
			<c:forEach items="${CODE_DEPT}" var="c">
			<option value="${c.id}">${c.name}</option>
			</c:forEach>
			<option data-divider="true"></option>
			<option value="*">建立新科系</option>
		</select>
		
		<select name="Grade" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有年級</option>
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
			<option value="4">4</option>
			<option value="0">延修</option>
		</select>
		
		
		<select name="SeqNo" class="selectpicker" data-width="auto"
		onChange="if(this.value=='*')location.replace('/eis/CampusManager');">
			<option value="">所有班級</option>
			<option value="1">甲班</option>
			<option value="2">乙班</option>
			<option value="3">丙班</option>
			<option value="4">丁班</option>
		</select>
		</td>
	</tr>
	
	
	


</table>

</body>
</html>