<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>證明書列印</title>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<link href="/eis/inc/css/wizard-step.css" rel="stylesheet"/>
</head>
<body>
<div class="bs-callout bs-callout-info">
<h4>證明書列印</h4> 
<small>請選擇個人列印或班級列印</small>
</div>
<form action="DocPrint" method="post" class="form-horizontal" enctype="multipart/form-data">

<div class="panel panel-primary">
  <!-- Default panel contents -->
  <div class="panel-heading">列印範圍</div>
  <div class="panel-body">
    <%@ include file="/inc/jsp-kit/fullSelector.jsp"%>
    
    <br><br>
    <div class="input-group">
      <span class="input-group-addon">學號或姓名</span>
      <input autocomplete="off" type="text" name="student_no" class="form-control" id="student_no"/>
    </div>
    <span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
    <span id="inputGroupSuccess3Status" class="sr-only">(success)</span>
  
    
  </div>  
</div>
<input type="hidden" name="type" id="type" />
<div class="panel panel-primary">
	<div class="panel-heading">列印項目</div>
  		<div class="panel-body">  
  		<!-- select onChange="changType(this.value);" class="selectpicker show-tick" title="中文畢業證書">
			<optgroup label="日間部">
				<option disabled value="CDAD" data-subtext="無對應範本">副學士</option>
				<option disabled value="CDBD" data-subtext="無對應範本">學士</option>
				<option disabled value="CDMD" data-subtext="無對應範本">碩士</option>
			</optgroup>
			<optgroup label="進修部">
				<option disabled value="dAD" data-subtext="無對應範本">副學士</option>
				<option disabled value="dBD" data-subtext="無對應範本">學士</option>
				<option disabled value="dMD" data-subtext="無對應範本">碩士</option>
			</optgroup>
			<optgroup label="進修學院">
				<option disabled value="dAD" data-subtext="無對應範本">副學士</option>
				<option disabled value="dBD" data-subtext="無對應範本">學士</option>
				<option disabled value="dMD" data-subtext="無對應範本">碩士</option>
			</optgroup>
		</select--> 
		
		<select onChange="changType(this.value);" class="selectpicker show-tick" title="英文畢業證書">
			<optgroup label="日間部">
				<option value="EDAD">副學士</option>
				<option value="EDBD">學士</option>
				<option value="EDMD">碩士</option>
			</optgroup>
			<optgroup label="進修部">
				<option disabled value="dAD" data-subtext="無對應範本">副學士</option>
				<option disabled value="dBD" data-subtext="無對應範本">學士</option>
				<option disabled value="dMD" data-subtext="無對應範本">碩士</option>
			</optgroup>
			<optgroup label="進修學院">
				<option disabled value="dAD" data-subtext="無對應範本">副學士</option>
				<option disabled value="dBD" data-subtext="無對應範本">學士</option>
				<option disabled value="dMD" data-subtext="無對應範本">碩士</option>
			</optgroup>
		</select>  
		
		
		
  		<button class="btn btn-default" name="method:print">列印</button>
  		</div>  
</div>

</form>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>
<script>
function changType(val){
	$("#type").val(val);
}

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