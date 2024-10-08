<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/autoComplete.js"></script>
<script src="/eis/inc/js/develop/timeInfo.js"></script>
<script>
$(document).ready(function() {
	$("input[id='nameno']").typeahead({
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
</head>
<body><br>

<!--div class="bs-callout bs-callout-warning" id="callout-helper-pull-navbar">人事資料管理</div-->
<form action="StdsInfoExtendManager" method="post" class="form-inline" enctype="multipart/form-data">

<div class="panel panel-primary">
	<div class="panel-heading">學生附加資料管理</div>	
	<table class="table">
		<tr>
		<td><%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<button class="btn btn-primary" name="method:search"  type="submit">系所班級</button></td>
	</tr>
		<tr>
			<td>
			<div class="input-group">
			<span class="input-group-addon">姓名</span>
			<input class="form-control" onClick="$('#nameno').val(''), $('#student_no').val('');" 
			autocomplete="off" type="text" id="nameno" value="${nameno}" name="nameno"
			style="width:600px;"
			 data-provide="typeahead" onClick="addStd()" placeholder="輸入學號或姓名再點選列表中的學生" />
			<span class="input-group-btn">
       		<button class="btn btn-primary" name="method:namenoSearch"  type="submit">姓名搜尋</button>
     		</span>
		</div>
			</td>
		</tr>
		
		
		
	</table>
	</div>
	
	
	
	<c:if test="${!empty stds}">
	
		<div class="panel panel-primary">
	<div class="panel-heading">學生附加資料管理</div>	
	<table class="table">
		<tr>
			<td>學號</td>
			<td>是否為名額內</td>
			<td>國籍代碼</td>
			<td>國籍別</td>
			<td>教育程度代碼</td>
			<td>教育程度</td>
			<td>入學資格</td>
		</tr>
		<c:forEach items="${stds}" var="s">
		<tr>
			<td>${s.stdNo}<input type="hidden" name="stdNo" value="${s.stdNo}" class="form-control"/></td>
			<td><input type="text" name="within" value="${s.within}" class="form-control"/></td>
			<td><input type="text" name="xsh_id" value="${s.xsh_id}" class="form-control"/></td>
			<td><input type="text" name="xsh_type1" value="${s.xsh_type1}" class="form-control"/></td>
			<td><input type="text" name="grd_id" value="${s.grd_id}" class="form-control"/></td>
			<td><input type="text" name="grd_name" value="${s.grd_name}" class="form-control"/></td>
			<td><input type="text" name="StuEnterQual" value="${s.StuEnterQual}" class="form-control"/></td>
		</tr>
		</c:forEach>
	</table>
	
		

	<div class="panel-body"><button class="btn btn-danger" name="method:save"  type="submit">儲存</button></div>	
	
		
		</c:if>


</div>

</form>
</body>
</html>