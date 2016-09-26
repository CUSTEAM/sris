<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轉帳資料管理</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/bootstrap/plugin/bootstrap-fileinput/css/fileinput.min.css" rel="stylesheet">

<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/bootstrap-fileinput/js/fileinput_locale_zh-TW.js"></script>
<link href="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet"/>
<script src="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="/eis/inc/bootstrap/plugin/silviomoreto-bootstrap-select/js/i18n/defaults-zh_TW.min.js"></script>
<style>
.file-caption-name{width:250px;}
}
</style>
<script src="/eis/inc/js/plugin/stupidtable.min.js"></script>
<script>
$("#table").stupidtable();
</script>
</head>
<body>    
<div class="bs-callout bs-callout-info">
<h4>轉帳資料管理</h4> <small>批次匯入請先下載最新格式，依欄位貼上資料，並按下匯入資料</small>
</div>

<form action="PayAccountManager" method="post" class="form-inline" enctype="multipart/form-data">



<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<table class="table">
	<tr>
		<td>
		<%@ include file="/inc/jsp-kit/dhnSelector.jsp"%>	
		<%int y=Integer.parseInt(request.getServletContext().getAttribute("school_year").toString()); %>
		<select class="selectpicker" data-width="fit" name="year">			
			<%for(int i=y; i>=y-4; i--){%>
			<option <%if(String.valueOf(request.getAttribute("year")).equals(String.valueOf(i))){%>selected<%}%> value="<%=i%>"><%=i%>學年</option>
			<%}%>			
		</select>
		<select class="selectpicker" data-width="fit" name="term">
			<option <c:if test="${term eq''}">selected</c:if> value="">全部學期</option>
			<option <c:if test="${term =='1'}">selected</c:if> value="1">第1學期</option>
			<option <c:if test="${term =='2'}">selected</c:if> value="2">第2學期</option>
		</select>
		<select class="selectpicker" data-width="fit" name="Kind" id="Kind">			
			<c:forEach items="${CODE_DIPOST}" var="c">
			<option <c:if test="${Kind == c.id}">selected</c:if> value="${c.id}">${c.name}</option>
			</c:forEach>
		</select>
		<button class="btn btn-danger" name="method:search">查詢</button>
		</td>
	</tr>
	<tr>
		<td nowrap>
		<div class="input-group" style="width:300px;">
			<div class="input-group-addon">學號</div>
			<input class="form-control" onClick="this.value=''" autocomplete="off" type="text" id="StudentNo" value="${StudentNo}" name="StudentNo"
		 data-provide="typeahead" placeholder="學號或姓名片段" />
		</div>
		<div class="input-group" style="width:120px;">
			<div class="input-group-addon">局號</div>
			<input type="text" name="OfficeNo" value="${OfficeNo}" id="OfficeNo" class="form-control" autocomplete="off" placeholder="局號"/>
		</div>
		<div class="input-group" style="width:200px;">
			<div class="input-group-addon">帳號</div>
			<input type="text" name="AcctNo" value="${AcctNo}" id="AcctNo" class="form-control" autocomplete="off" placeholder="帳號"/>
		</div>
		
		</td>
	</tr>
	
	<tr>
		<td>
		<select class="form-control" data-width="fit" name=occur_month id="occur_month">			
			<option value="">選擇月份</option>
			<c:forEach begin="1" end="12" varStatus="i">
			<option <c:if test="${i.index == occur_month}">selected</c:if> value="${i.index}">${i.index}月份</option>
			</c:forEach>
		</select>
		<div class="input-group">
			<span class="input-group-addon" id="sizing-addon2">金額</span>
			<input type="text" class="form-control" placeholder="$$$" name="Money" value="${Money}"/>
			<span class="input-group-btn">
			<button class="btn btn-danger" name="method:add">新增</button>	
			
			</span>
		</div>
		<div class="btn-group">		
		<a href="PayAccountManager" class="btn btn-default">重設條件</a>
		</div>
		</td>
	</tr>
	<tr>
		<td>
		<table>
			<tr>
				<td>
				<a class="btn btn-primary" href="jsp/fee/sample.xlsx"><span class="glyphicon glyphicon-cloud-download" aria-hidden="true"></span>下載格式</a>
				</td>
				<td style="padding:0px 5px 0px 5px;">
				<input name="upload" type="file" 
				data-show-preview="false" 
				data-show-upload="false" 
				id="upload" 
				class="file"/>
				<script>
				$("#upload").fileinput({
					multiple: false,			        
				    language: "zh-TW",
				    uploadUrl: "",
				    allowedFileExtensions: ["xls", "xlsx"]
				});
				</script>
				</td>			
				<td>
				<button class="btn btn-primary" id="saveTxtFile" name="method:upload" type="submit"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>匯入資料</button>
				</td>
			</tr>
		</table>
		
		
		
		</td>
	</tr>
</table>
</div>


<c:if test="${!empty dipost}">
<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>

<table id="table" class="table table-hover" id="table">
	<thead>
	<tr class="text-info">
		<th></th>
		<th nowrap data-sort="string">種類</th>
		<th nowrap data-sort="int">學年</th>
		<th nowrap data-sort="int">學期</th>
		<th nowrap data-sort="int">月份</th>
		<th nowrap data-sort="string">班級</th>
		<th nowrap data-sort="string">學號</th>
		<th nowrap data-sort="string">姓名</th>		
		<th nowrap data-sort="int">局號</th>
		<th nowrap data-sort="int">帳號</th>
		<th nowrap data-sort="int">金額</th>
		<th nowrap data-sort="string">編輯日期</th>
		
	</tr>
	</thead>
	<c:forEach items="${dipost}" var="c">
	<tr>
		<td><input type="checkBox" name="del" value="${c.Oid}" /></td>
		<td nowrap>${c.name}</td>
		<td nowrap>${c.SchoolYear}</td>
		<td nowrap>${c.SchoolTerm}</td>
		<td nowrap>${c.occur_month}</td>
		<td nowrap>${c.ClassName}</td>
		<td nowrap>${c.student_no}</td>
		<td nowrap>${c.student_name}</td>		
		<td nowrap>${c.OfficeNo}</td>
		<td nowrap>${c.AcctNo}</td>
		<td nowrap>${c.Money}</td>
		<td nowrap>${c.LastModified}</td>
	</tr>
	</c:forEach>
</table>
<div class="panel-body">
<button class="btn btn-primary" name="method:del" type="submit"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>刪除勾選</button>

</div>
</c:if>







</form>
<script>  
$(document).ready(function() {
	//$("#occur_month").hide("slow");
	
	/*$("#Kind").change(function(){
		if($(this).val()==3){
			$("#occur_month").show("slow");
		}else{
			$("#occur_month").hide("slow");
		}
	});*/
	$("#StudentNo" ).focus();
		
});
//document.getElementById("StudentNo").focus();
//$("#StudentNo" ).focus();

$("#StudentNo").typeahead({
		//remote:"#student_no",
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
</script>
</body>
</html>