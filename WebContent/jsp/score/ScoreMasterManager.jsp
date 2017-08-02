<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>論文成績管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js" type="text/javascript"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script>  
$(document).ready(function() {
	$("input[id='student_no']").typeahead({
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
<body>
<div class="bs-callout bs-callout-info">
<h4>論文成績管理</h4> 
<small>依學號 <button type="button" class="btn btn-primary btn-xs">查詢</button> 論文成績輸入狀況, 在校生與離校生均可查詢</small><br>
<small>滿足所有欄位點選列表下方 <button type="button" class="btn btn-danger btn-xs">儲存</button> 即可新增或修改</small>
</div>
<form action="ScoreMasterManager" method="post" class="form-inline" onSubmit="$.blockUI({message:null});">

<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>

<table class="table">
	
	<tr>
		
		<td>
		<div class="form-group">
		<div class="input-group">
      	<div class="input-group-addon">指定學生</div>
		<input class="form-control" onClick="this.value=''" autocomplete="off" type="text" id="student_no" value="${student_no}" name="student_no"
		data-provide="typeahead" placeholder="學號或姓名片段" style="width:500px;"/>
		<span class="input-group-btn">
		<button class="btn btn-primary" id="searchStd" name="method:search" type="submit">查詢成績</button>		    		   
		
		</div>
		</div>
		</td>
		 
	</tr>
	
	
	
	
	
		 	<tr>
		 		<td>
		 		<input type="hidden" name="Oid" value="${score.Oid}" id="Oid"/>
		 		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">學年</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.school_year}" name="school_year" placeholder="學年"/>				
				</div>
				</div>
		 		
		 		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">學期</div>
		      	
		      	<select class="form-control" name="school_term">
		      		<option <c:if test="${score.school_term eq '1'}">selected</c:if> value="1">第1學期</option>
		      		<option <c:if test="${score.school_term eq '2'}">selected</c:if> value="2">第2學期</option>
		      	</select>
		      				
				</div>
				</div>
		 		
		 		</td>
		 	</tr>	
		 	<tr>
	      		<td>
	      		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">論文中文名稱</div>
				<input style="width:650px;" class="form-control" autocomplete="off" type="text" 
				value="${score.theses_chiname}" name="theses_chiname" placeholder="論文中文名稱"/>				
				</div>
				</div>
	      		
	      			
	      			
	      			
	      		</td>
	      	</tr>
		 	<tr>
	      		<td>
	      		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">論文英文名稱</div>
				<input style="width:650px;"  class="form-control" autocomplete="off" type="text" 
				value="${score.theses_engname}" name="theses_engname" placeholder="論文英文名稱"/>				
				</div>
				</div>	      			
	      		</td>
	      	</tr>
	      	<tr>
	        	<td>
	        	
	        	<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">論文成績</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.theses_score}" name="theses_score" placeholder="整數或小數點後1位"/>				
				</div>
				</div>
	        	
	        	
	        	<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">學業成績</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.evgr1_score}" name="evgr1_score" placeholder="整數或小數點後1位"/>				
				</div>
				</div>
				
				<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">畢業成績</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.graduate_score}" name="graduate_score" placeholder="整數或小數點後1位"/>				
				</div>
				</div>
	        	
	        	
          			
          			
          		</td>
	      	</tr>
	      	<tr>
	      		<td>
	      		
	      		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">線上建檔日期</div>
				<input class="form-control" autocomplete="off" type="text" 
				id="onlineFileDate" value="${score.onlineFileDate}" name="onlineFileDate" placeholder="已查核全國博碩士論文線上建檔資料日期"/>				
				</div>
				</div>
	      		
	      		
	      		
	      		
	      		</td>	
	      	</tr>
	      	<tr>
	      		<td>
	      		
	      		<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">備註</div>
				<input style="width:650px;" class="form-control" autocomplete="off" type="text" 
				id="remark" value="${score.remark}" name="remark"/>				
				</div>
				</div>
	      		
	      		
	      			
	      		</td>
	      	</tr>
			<tr>
				<td>
				<button class="btn btn-danger" name="method:update" type="submit">儲存</button>
				</td>
			</tr>
	
	
</table>
</div>








</form>

<div class="modal fade" id="scoreHist" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="modal-title"></h4>
			</div>
			<div class="modal-body" id="modal-body"></div>
			<div class="modal-footer">
				<button class="btn btn-lg btn-primary" data-dismiss="modal" aria-hidden="true">關閉</button>
			</div>
		</div>
	</div>
</div>



<script>
$("#onlineFileDate").datepicker({
	changeMonth: true,
	changeYear: true,
	//minDate: '@minDate',
	yearRange: "-5:+5",
	//showButtonPanel: true,
	//dateFormat: 'yymmdd'
});
</script>


</body>
</html>