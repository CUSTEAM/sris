<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
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
<small><button type="button" class="btn btn-primary btn-xs">查詢</button> 在校生或離校生的論文成績輸入狀況</small><br>
<small>請先輸入<code>論文成績</code> <button type="button" class="btn btn-default btn-xs">試算</button> 列出算式與計算結果提供參考</small><br>
<small><button type="button" class="btn btn-danger btn-xs">儲存</button> 將所有欄位依學號儲存至歷年成績</small>

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
				value="${score.theses_score}" name="theses_score" id="ts"/>				
				</div>
				</div>
	        	
	        	
	        	<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">學業成績</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.evgr1_score}" name="evgr1_score" id="es"/>				
				</div>
				</div>
				
				<div class="form-group">
				<div class="input-group">
		      	<div class="input-group-addon">畢業成績</div>
				<input class="form-control" autocomplete="off" type="text" 
				value="${score.graduate_score}" name="graduate_score" id="gs"/>				
				</div>
				</div>
	        	<c:if test="${!empty allInfo}">
	        	<button type="button" class="btn btn-default" onClick="count();">試算</button>
          		</c:if>
          		
          		<script>
          		function count(){
          			
          			
          			$('#model').modal('show');
          			
          			$("#es").val(${allScore.score});
          			var ts=$("#ts").val();
          			var es=$("#es").val();
          			if(ts=="")ts=0;
          			if(es=="")es=0;
          			
          			$("#gs").val((parseFloat(ts)+parseFloat(es))/2);
          		}
          		</script>
          			
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





<c:if test="${!empty allInfo}">
<div class="panel panel-primary">
<div class="panel-heading">${student_no} <a class="btn btn-default btn-sm" href="sris/ScorePrint?scoreHist=${fn:substring(student_no, 0, fn:indexOf(student_no, ','))}"><span class="glyphicon glyphicon-print" aria-hidden="true"></span> 歷年成績單</a></div>
<div class="modal fade" id="model" tabindex="-1" role="dialog">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title">算式*</h4>
				      </div>
				      <div class="modal-body">
				        
				        
				        
				        <table class="table table-condensed">
				        	
				        	<c:forEach items="${allInfo}" var="a">
				        	<tr>
				        		<td><c:choose>
        <c:when test="${a.SchNo eq'M'}"><span class="label label-as-badge label-danger">碩</span></c:when>
        <c:otherwise><span class="label label-as-badge label-default">非</span></c:otherwise>
      	</c:choose>${a.chi_name}</td>
				        		<td>${a.credit}</td>
				        		<td>×</td>
				        		<td>${a.score}</td>
				        		<td>=</td>
				        		<td>${a.credit*a.score}</td>
				        	</tr>
				        	
				        	</c:forEach>
				        	<tr>
				        		<td colspan="6">成績加總: ${allScore.cnt}, 學分數加總: ${allScore.credit}</td>
				        	</tr>
				        	<tr>
				        		<td>${allScore.cnt}÷${allScore.credit}=${allScore.score}</td>
				        	</tr>
				        </table>
				       	<p><span class="label label-as-badge label-success">1</span>碩士班課程依開課班級認定, 算式已扣除非碩士班課程</p>
				       	<p><span class="label label-as-badge label-warning">2</span>為防止特殊狀況發生建議驗算結果</p>
				       	<p><span class="label label-as-badge label-primary">3</span>請依各部制規定決定小數點位數後儲存</p>
				       	
				        
				        
				        
				        
				        
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
				        
				      </div>
				    </div><!-- /.modal-content -->
				  </div><!-- /.modal-dialog -->
				</div><!-- /.modal -->
<table class="table">
	<tr>
		<td nowrap>學年</td>
		<td nowrap>學期</td>
		<td nowrap>開課班級</td>
		<td nowrap>科目</td>
		<td nowrap>學分</td>
		<td nowrap>成績</td>
	</tr>
	<c:forEach items="${allInfo}" var="a">
	<tr>
		<td width="50">${a.school_year}</td>
		<td width="50">${a.school_term}</td>
		<td nowrap>
		<c:choose>
        <c:when test="${a.SchNo eq'M'}"><span class="label label-as-badge label-danger">碩</span></c:when>
        <c:otherwise><span class="label label-as-badge label-default">非</span></c:otherwise>
      	</c:choose>${a.ClassName}</td>
		<td nowrap>${a.chi_name}</td>
		<td width="50">${a.credit}</td>
		<td width="100%">${a.score}</td>
	</tr>
	</c:forEach>
</table>
</div>
</c:if>



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