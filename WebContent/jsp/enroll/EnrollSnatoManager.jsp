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
<h4>境外生報名管理</h4> 
<small>選擇範圍點選列印，標題、統計等其它細節資料，於報表中的頁首/頁尾，請以預覽列印檢視</small>

</div>


<form action="EnrollSnatoManager" method="post" class="form-inline" enctype="multipart/form-data" autocomplete="off">
<input type="hidden" name="enrollOid" id="enrollOid" value="${enrollOid}"/>


<div class="panel panel-primary">
<div class="panel-heading">查詢</div>

<table class="table table-hover">
	<tr>
		<td nowrap>科系</td>
		<td nowrap>學生報名期間</td>
		
	</tr>
	
	<tr>		
	<td>
			<select name="dept">
				<option <c:if test="${dept eq ''}">selected</c:if> value="">全部</option>
				<c:forEach items="${CODE_DEPT}" var="d">
				<option <c:if test="${dept eq d.id}">selected</c:if> value="${d.id}">${d.id} - ${d.name}</option>
				</c:forEach>
			</select>
		</td>	
		<td width="85%">
		<input name="begin" value="${begin}" class="form-control login-form-control dateinput" placeholder=""/>
		<input name="end" value="${end}" class="form-control login-form-control dateinput" placeholder=""/>
		<button  class="btn btn-primary" name="method:search">依範圍查詢</button>
		</td>		
		
	</tr>
	
</table>

</div>

<c:if test="${!empty stds}">
<div class="panel panel-primary">
<div class="panel-heading">查詢結果</div>

<table class="table table-hover">
	<tr>
		<td><input type="checkbox" id="boxControl" onClick="checkAll()"/></td>
		<td class="cid">班級 <button type="button" onClick="fillDown()" class="btn btn-xs btn-success">向下填滿</button></td>
		<td class="cid">學號</td>
		<td>護照號碼</td>
		<td>姓名 </td>
		<td>科系</td>
		<td class="hid"></td>
		
	</tr>
	<script>
	function fillDown(){
		//alert();
		<c:forEach items="${stds}" var="s">		
		$("#ClassNo${s.Oid}").val($(".ClassNof:first").val());
		</c:forEach>
	}
	function checkAll(){
			
		if($("#boxControl").prop("checked")==false){
			<c:forEach items="${stds}" var="s">
			$("#box${s.Oid}").prop("checked", false);
			$("#Oid${s.Oid}").val("");
			
			</c:forEach>
		}else{
			<c:forEach items="${stds}" var="s">
			$("#box${s.Oid}").prop("checked", true);
			$("#Oid${s.Oid}").val(${s.Oid});
			</c:forEach>
		}
		
	}

	</script>
	
	<c:forEach items="${stds}" var="s">
	<tr>		
		<td><input type="checkBox" id="box${s.Oid}" onClick="checkOid($(this).prop('checked'), '${s.Oid}')" /></td>
		<td class="cid" style="width:1px;"><input name="ClassNo" id="ClassNo${s.Oid}" class="ClassNof form-control login-form-control depart_class" value="${s.depart_class}" /></td>
		<td class="cid" style="width:1px;"><input name="student_no" class="form-control login-form-control" value="${s.student_no}"/>
		<input type="hidden" name="Oid" id="Oid${s.Oid}" class="form-control login-form-control"/></td>
		
		<td nowrap width="200">${s.passport}</td>
		<td nowrap width="250">${s.cname}<br><small>${s.ename}</small></td>	
		<td nowrap width="200">${s.name}</td>
		<td class="hid" >		 
		<small>
		<c:if test="${!empty s.f1}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f1}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>切結書</a>, </c:if>
		<c:if test="${!empty s.f2}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f2}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>學歷證明</a>, </c:if>
		<c:if test="${!empty s.f3}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f3}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>成績單</a>, </c:if>
		
		<c:if test="${!empty s.f4}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f4}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>身分證</a>, </c:if>
		<c:if test="${!empty s.f5}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f5}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>護照</a>, </c:if>
		<c:if test="${!empty s.f6}"><a target="_blank" href="/pis/getFtpFile?path=enroll&file=${s.f6}"><span class="glyphicon glyphicon-floppy-save" aria-hidden="true"></span>其他</a></c:if></small></td>
	
	
	</tr>
	</c:forEach>
</table>

<div class="panel-body">


</div>

<div class="panel-footer">

<button class="btn btn-danger" name="method:add">批次入學</button>


<button class="btn btn-success" name="method:saveFile">批次下載附件</button>
<button  class="btn btn-default" name="method:print">批次列印申請書</button>
<button  class="btn btn-primary" name="method:printList">列印名單</button>
</div>
</div>


</c:if>





</form>






<script>
function checkOid(p, oid){
	if(p){

		$("#Oid"+oid).val(oid);
	}else{
		$("#Oid"+oid).val("");

	}
	
	
}
//$(".hid").hide("slow");
//$(".cid").hide("slow");
//$(".dateinput").datetimepicker({
$(".dateinput").datepicker({
	changeMonth: true,
	changeYear: true,
	//minDate: '@minDate',
	//yearRange: "-1:+1",
	//showButtonPanel: true,
	//dateFormat: 'yymmdd'
	//defaultDate: new Date(${school_year+1911-20}, 00, 01)
});

$(".depart_class").typeahead({
	//remote:"#agent",
	source : [],
	items : 20,
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




</script>
</body>
</html>