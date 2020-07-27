<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select name="cno" class="selectpicker" data-width="auto">
	<c:forEach items="${allCampus}" var="c">
	<option <c:if test="${c.idno eq cno}">selected</c:if> value="${c.idno}">${c.name}</option>
	</c:forEach>
</select>

<select name="tno" class="selectpicker" data-width="auto">
	<option <c:if test="${tno eq 'D'}">selected</c:if> value="D">日間部</option>
	<option <c:if test="${tno eq 'N'}">selected</c:if> value="N">進修部</option>
	<option <c:if test="${tno eq 'H'}">selected</c:if> value="H">進修學院</option>
</select>