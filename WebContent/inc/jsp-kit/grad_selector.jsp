<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<select name="cno" class="selectpicker" data-width="auto">
	<option value="">所有校區</option>
	<c:forEach items="${allCampus}" var="c">
	<option <c:if test="${c.idno eq cno}">selected</c:if> value="${c.idno}">${c.name}</option>
	</c:forEach>
</select>

<select name="tno" class="selectpicker" data-width="auto">
	<option value="">所有部制</option>
	<option <c:if test="${tno eq 'D'}">selected</c:if> value="D">日間部</option>
	<option <c:if test="${tno eq 'N'}">selected</c:if> value="N">進修部</option>
	<option <c:if test="${tno eq 'H'}">selected</c:if> value="H">進修學院</option>
</select>



<select name="dno" class="selectpicker" data-width="auto">
	<option value="">所有科系</option>
	<c:forEach items="${CODE_DEPT}" var="c">
	<option <c:if test="${c.id eq dno}">selected</c:if> value="${c.id}">${c.name}</option>
	</c:forEach>
</select>


<select name="gno" class="selectpicker" data-width="auto">
	<option value="">所有年級</option>
	<c:forEach var="g" begin="1" end="6">
	<option <c:if test="${gno eq g}">selected</c:if> value="${g}">${g}年級</option>
	</c:forEach>
</select>
