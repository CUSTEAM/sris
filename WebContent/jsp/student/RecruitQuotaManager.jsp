<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>招生名額資料管理</title>
</head>
<body>

<form action="RecruitQuotaManager" method="post" class="form-inline">

<div class="panel panel-primary">
<div class="panel-heading">查詢條件</div>
<table class="table">
		<tr>
			<td>學年</td>
			<td>學期</td>
			<td>班部別</td>
			<td>日夜別</td>
			<td>系所代碼</td>
			<td>組別代碼</td>
			<td>類別</td>
			<td>招生方式</td>
			<td>名額</td>
		</tr>
		<tr>
			<td><input type="hidden" name="Oid" class="form-control" value="" />
				<input type="text" name="school_year" class="form-control" />
			</td>
			<td><input type="text" name="school_term" class="form-control" /></td>
			<td><input type="text" name="SchoolNo" class="form-control" /></td>
			<td><input type="text" name="SchoolType" class="form-control" /></td>
			<td><input type="text" name="DeptNo" class="form-control" /></td>
			<td><input type="text" name="GroupNo" class="form-control" /></td>
			<td><input type="text" name="Type" class="form-control" /></td>
			<td><input type="text" name="WayOf" class="form-control"/></td>
			<td><input type="text" name="Quoata" class="form-control" /></td>
		</tr>
	
		<c:forEach items="${quota}" var="q">
		<tr>
			<td><input type="hidden" name="Oid" class="form-control" value="${q.Oid}" />
				<input type="text" name="school_year" class="form-control" value="${q.school_year}" />
			</td>
			<td><input type="text" name="school_term" class="form-control" value="${q.school_term}" /></td>
			<td><input type="text" name="SchoolNo" class="form-control" value="${q.SchoolNo}" /></td>
			<td><input type="text" name="SchoolType" class="form-control" value="${q.SchoolType}" /></td>
			<td><input type="text" name="DeptNo" class="form-control" value="${q.DeptNo}" /></td>
			<td><input type="text" name="GroupNo" class="form-control" value="${q.GroupNo}" /></td>
			<td><input type="text" name="Type" class="form-control" value="${q.Type}" /></td>
			<td><input type="text" name="WayOf" class="form-control" value="${q.WayOf}" /></td>
			<td><input type="text" name="Quoata" class="form-control" value="${q.Quoata}" /></td>
		</tr>
		
		</c:forEach>
		
		

</table>
</div>
 <button class="btn btn-danger" name="method:save">儲存</button>
</form>

</body>
</html>