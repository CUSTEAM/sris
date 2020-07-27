<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<title>獨招報名查詢</title>	
</head>
<body>    


<div class="bs-callout bs-callout-info">
<h4>獨招報名查詢</h4> 
<small>點選名稱顯示各系所報名狀況, 或直接下載 <a disabled class="btn btn-default btn-xs">考生列表</a></small>

</div>



<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
	
	<c:forEach items="${enrolls}" var="e">	
	
	
	<div class="panel panel-primary" onMouseOver="$('#registOid${e.Oid}').val(${e.Oid})">
    	<div class="panel-heading" role="tab" id="heading${e.Oid}">
	      	<table width="100%"><tr><td width="100%"nowrap>
	      		<h4 class="panel-title">
		        <a role="button" data-toggle="collapse" 
		        data-parent="#accordion" href="#collapse${e.Oid}" 
		        aria-expanded="true" aria-controls="collapse${e.Oid}">		        
				${e.enroll_name}</a></h4>
		        </td>
		        <td nowrap>
				<h4 class="panel-title">
				<a role="button" data-toggle="collapse" 
		        data-parent="#accordion" href="#collapse${e.Oid}" 
		        aria-expanded="true" aria-controls="collapse${e.Oid}">		        
				<span class="btn btn-default btn-xs">檢視 </span></a>
		        </h4>
		        </td>
		        </tr></table>		       
    		</div>
    		<div class="panel-body" >				
			報名期間: ${fn:substring(e.sign_begin, 0, 10)} ~ ${fn:substring(e.sign_end, 5, 10)}, 
			成績: ${fn:substring(e.open_score, 5, 10)}, 放榜: ${fn:substring(e.open_match, 5, 10)}
			
			
    		<div class="btn-group" role="group" aria-label="...">
			<button disabled onClick="$('#enrollOid').val('${e.Oid}')" name="method:managStmd" class="btn btn-default">考生管理</button>
		  	<a href="/pis/EnrollDoc?EnrollOid=${e.Oid}" class="btn btn-default">考生列表</a>
		</div>
    		
    		</div>
	    	<div id="collapse${e.Oid}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${e.Oid}">
	    	
	    	<table class="table">
	    		<tr>
	      			<th>系所</td>
	      			<th nowrap>第一志願</td>
	      			<th nowrap>其他志願</td>
	      		</tr>
	      		<c:forEach items="${e.one}" var="o" varStatus="i">
	      		<tr>
	      			<td>${o.dept_name}</td>
	      			<td>${o.cnt}</td>
	      			<td>${e.sec[i.index].cnt}</td>
	      		</tr>
	      		</c:forEach>
	      	</table>
	    	
	    	</div>
  	</div>
  	</c:forEach>
  
</div>

</body>
</html>