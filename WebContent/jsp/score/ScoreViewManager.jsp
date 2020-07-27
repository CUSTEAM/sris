<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<title>成績開放設定</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/js/plugin/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.css" rel="stylesheet"/>


<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon/jquery-ui-timepicker-addon.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js" type="text/javascript"></script>
</head>
<body>   
<form action="ScoreViewManager" method="post" class="form-horizontal" onSubmit="$.blockUI({message:null});">
<div class="bs-callout bs-callout-warning" id="callout-helper-pull-navbar">
	<h4>成績開放設定</h4>
	應屆生全班下修低年級課程請選擇「應屆生選修」選項, 過濾無應屆生課程<br>教師延誤或修改成績, 需要開放輸入時, 請選擇「全部課程」, 顯示所有課程<br>依條件查詢並修改該課程的各項時間設定<br>
	
	
</div> 
<table class="table">
	<tr>
		<td width="100%">
		<%@ include file="/inc/jsp-kit/grad_selector.jsp"%>
		<select name="only" class="selectpicker bs-select-hidden" data-width="auto">
			<option <c:if test="${only eq'1'}">selected</c:if> value="1">應屆生選修</option>
			<option <c:if test="${only eq'0'}">selected</c:if> value="0">全部課程</option>
		</select>
		<button class="btn btn-primary" name="method:search" type="submit">查詢班級課程</button>
		</td>
	</tr>
	
</table>

<c:if test="${!empty cls}">
<div class="alert alert-danger" role="alert"><h4><b>自動完成參考日期</b></h4>期末瀏覽${exam_grad_view}, 期末輸入${exam_grad}, 期中瀏覽${exam_mid_view}, 期中輸入${exam_mid}</div>
<div class = "panel panel-primary">
	<div class = "panel-heading"><h3 class = "panel-title">可依應屆生 <span class='glyphicon glyphicon-education' aria-hidden='true'></span>人數排序</h3></div>
	<!-- pagesize="20" -->
	<display:table name="${cls}" id="row" class="table table-condensed" sort="list" requestURI="ScoreViewManager?method=search">
		
	  	<display:column style="white-space:nowrap;" title="編號" property="Oid" sortable="true"/>
	  	<display:column style="text-align:left; white-space:nowrap;" title="名稱" property="chi_name" sortable="true"/>
	  	<display:column style="text-align:left; white-space:nowrap;" title="班級" property="ClassName" sortable="true"/>  	
	  	<display:column style="text-align:center; white-space:nowrap;" title="選別" property="opt" sortable="true"/>
	  	<display:column style="white-space:nowrap; text-align:center; white-space:nowrap;" title="<span class='glyphicon glyphicon-education' aria-hidden='true'></span>" property="cnt" sortable="true"/>
	  	
	  	<display:column style="white-space:nowrap;" title="期中輸入">
	  	<div class="input-group">
	      <input type="text" name="mid" id="mid${row.Oid}" class="form-control date" placeholder="手動或自動設定日期" value="${row.exam_mid}">
	      <span class="input-group-btn">
	        <button class="btn btn-danger" type="button" onClick="autoset(${row.Oid})">自動</button>
	      </span>
	    </div>
	  	</display:column>
	  	
	  	<display:column style="white-space:nowrap;" title="開放瀏覽">
	  	<div class="input-group">
	      <input type="text" name="mid_view" id="mid_view${row.Oid}" class="form-control date" placeholder="手動或自動設定日期" value="${row.exam_mid_view}">
	      <span class="input-group-btn">
	        <button class="btn btn-danger" type="button" onClick="autoset(${row.Oid})">自動</button>
	      </span>
	    </div>
	  	</display:column>
	  	
	  	<display:column style="white-space:nowrap;" title="期末輸入">
	  	<div class="input-group">
	      <input type="text" name="fin" id="fin${row.Oid}" class="form-control date" placeholder="手動或自動設定日期" value="${row.exam_fin}">
	      <span class="input-group-btn">
	        <button class="btn btn-danger" type="button" onClick="autoset(${row.Oid})">自動</button>
	      </span>
	    </div>
	  	</display:column>
	  	
	  	<display:column style="white-space:nowrap;" title="開放瀏覽">
	  	<div class="input-group col-sm-10">
	      <input type="text" name="fin_view" id="fin_view${row.Oid}" class="form-control date" placeholder="手動或自動設定日期" value="${row.exam_fin_view}">
	      <span class="input-group-btn">
	        <button class="btn btn-danger" type="button" onClick="autoset(${row.Oid})">自動</button>
	      </span>
	    </div>
	    <input type="hidden" name="DtimeOid" value="${row.Oid}" />
	  	</display:column>
	</display:table>
	<div class="panel-footer">
	<div class="btn-group" role="group" aria-label="...">
		<button name="method:save" class="btn btn-danger chedk">儲存</button>
		<a href="ScoreViewManager" class="btn btn-default">返回</a>
	</div>
	</div>
</div>
</c:if>

</form>
<script>
$(".date" ).datetimepicker({
	changeMonth: true,
	changeYear: true
});

function autoset(Oid){
	$("#fin_view"+Oid).val("${exam_grad_view}");
	$("#fin"+Oid).val("${exam_grad}");
	$("#mid_view"+Oid).val("${exam_mid_view}");
	$("#mid"+Oid).val("${exam_mid}");
}

</script>
</body>
</html>