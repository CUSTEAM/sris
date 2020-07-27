<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>單筆資格審查</title>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
</head>
<body>
<div class="bs-callout bs-callout-info">
<h4>單筆資格審查</h4> 
<small>請選擇個人審查或班級審查</small>
</div>
<form action="CheckStmdGraduate" method="post" class="form-inline" enctype="multipart/form-data">

<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">查詢列表</div>
  <div class="panel-body">
    <%@ include file="/inc/jsp-kit/classSelector.jsp"%>
    <button class="btn btn-primary" name="method:multSearch">依範圍查詢</button>
    <br><br>
    <div class="input-group">
      <span class="input-group-addon">學號或姓名</span>
      <input autocomplete="off" type="text" name="student_no" class="form-control" id="student_no"/>
    </div>
    <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
    <span id="inputGroupSuccess3Status" class="sr-only">(success)</span>
  
    <button class="btn btn-primary" name="method:justSearch">依學號查詢</button>
  </div>  
</div>
<c:if test="${!empty gradresu}">
<input type="hidden" name="editStmd" id="editStmd" />
<div class="panel panel-primary">
<div class="panel-heading">查詢結果</div>
<display:table name="${gradresu}" requestURI="CheckStmdGraduate?method=multSearch" export="false" id="row" sort="list" class="table table-hover" >
	<display:column title="班級" style="white-space:nowrap;" class="left">${row.ClassName}</display:column>
  	<display:column title="學號" style="white-space:nowrap;" property="stdNo" sortable="true" class="left" />
  	<display:column title="姓名" style="white-space:nowrap;" property="student_name" sortable="false" class="left"/>
  	
  	<display:column title="必修" property="hist_credit1" sortable="true" class="left"/>
  	<display:column title="選修" property="hist_credit2" sortable="true" class="left"/>
  	<display:column title="通識" property="hist_credit3" sortable="true" class="left"/>
  	<display:column title="合計" property="cnt" sortable="true" class="left"/>
  	<display:column title="語言" sortable="true" style="width:100px;">
  	<input type="hidden" name="students_no" value="${row.stdNo}" />
  	<input type="hidden" name="updates" id="updates${row.stdNo}" />
  	<select data-width="100px" class="selectpicker" name="language" onChange="$('#updates${row.stdNo}').val('Y')">
  	
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.language eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>
  	<display:column title="專業" sortable="true" style="width:100px;">
  	<select data-width="100px" class="selectpicker" name="certificate" onChange="$('#updates${row.stdNo}').val('Y')">
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.certificate eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>
  	<display:column title="實習" sortable="true" style="width:100px;">
  	<select data-width="100px" class="selectpicker" name="practice" onChange="$('#updates${row.stdNo}').val('Y')">
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.practice eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
	
	
  	</display:column>
  	
  	
  	<display:column title="資格" sortable="true" style="width:100px;">
  	<select data-width="90px" class="selectpicker" disabled>
		<option value="">未取得</option>
		<option data-icon="glyphicon glyphicon-ok" <c:if test="${row.pass eq 'Y'}">selected</c:if> value="Y">已取得</option>		
	</select>
  	</display:column>  	
  	<display:column title="">
  	<button name="method:edit" onMouseOver="$('#editStmd').val('${row.stdNo}')" class="btn btn-success">內容</button>
  	</display:column>
  	</display:table>
  	<div class="panel-body">
    <button name="method:batchSave" class="btn btn-danger">批次建立</button>
  </div>
<div class="panel-footer ">&nbsp;</div>
</div>
  	
</c:if>













</form>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>
<script>
$(document).ready(function() {
	
	$("#student_no").typeahead({
		remote:"#student_no",
		source : [],
		items : 10,
		updateSource:function(inputVal, callback){			
			$.ajax({
				type:"POST",
				url:"/eis/autoCompleteStmd",
				dataType:"json",
				data:{length:10, nameno:inputVal},
				success:function(d){
					callback(d.list);
				}
			});
		}		
	});
	
});
</script>
</body>
</html>