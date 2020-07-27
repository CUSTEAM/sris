<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成績管理</title>
<script src="/eis/inc/bootstrap/plugin/bootstrap-typeahead.js"></script>

<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>


</head>
<body>
<div class="bs-callout bs-callout-info">
<h4>成績管理</h4> 
<small>依班級、課程或學號範圍列出成績輸入狀況，查詢結果所有欄位可排序。<br>
可單筆修改或點選列表下方<button type="button" class="btn btn-danger btn-xs">全部儲存</button>批次修改</small>
</div>
<form action="ScoreManager" method="post" class="form-horizontal" onSubmit="$.blockUI({message:null});">
<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<table class="table">
	<tr>
		<td nowrap>指定班級</td>
		<td width="100%">
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<select name="graduate" class="selectpicker" data-width="auto">
			<option value="">全部學生</option>
			<option <c:if test="${graduate eq '1'}">selected</c:if> value="1">畢業班學生</option>
			<option <c:if test="${graduate eq '0'}">selected</c:if> value="0">非畢業班學生</option>
		</select>
		
		<button class="btn btn-primary" name="method:searchClass" type="submit">查詢班級課程</button>
		</td>
	</tr>
	<tr>
		<td nowrap>指定課程</td>
		<td width="100%">
			<div class="col-sm-4">
			<input class="form-control" onClick="this.value=''" autocomplete="off" type="text" id="Dtime_oid" value="${Dtime_oid}" name="Dtime_oid"
			 data-provide="typeahead"  placeholder="課程編號、代碼、教師姓名或班級代碼與班級名稱片段" />
			</div>
		    <button class="btn btn-primary" id="searchDtime" name="method:searchDtime" type="submit">查詢課程</button>		    
		
		</td>
	</tr>
	<tr>
		<td nowrap>指定學生</td>
		<td width="100%" colspan="2">
		<div class="col-sm-4">
		<input class="form-control" onClick="this.value=''" autocomplete="off" type="text" id="student_no" value="${student_no}" name="student_no"
		 data-provide="typeahead" placeholder="學號或姓名片段" />
		</div>
		<button class="btn btn-primary" id="searchStd" name="method:searchStudent" type="submit">本學期成績</button>		    		   
		
		<button class="btn btn-warning" name="method:searchScoreHist" type="submit">歷年成績</button>
		<button id="compel" style="display:none;" name="method:compel" type="submit">compel</button>
		</td>
		 
	</tr>
</table>
</div>

<input type="hidden" name="dOid" value="${dOid}" id="dOid"/>
<input type="hidden" name="stNo" value="${stNo}" id="stNo"/>
<c:if test="${!empty css}">
<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<display:table name="${css}" id="c" class="table table-hover" sort="list" pagesize="20" requestURI="ScoreManager?name=method:searchClass">
	 
	<display:column style="white-space:nowrap" title="編號" property="Oid" sortable="true"/>
	<display:column style="white-space:nowrap" title="開課班級" property="ClassName" sortable="true"/>
	<display:column title="課程名稱" property="chi_name" sortable="true"/>
	<display:column style="white-space:nowrap" title="成績冊">
		<div style="width:80px" class="btn-group">
		<a href="/CIS/Print/teacher/NorRat.do?level=m&dtimeOid=${c.Oid}" class="btn btn-default btn-xs">期中</a>
		<a href="/CIS/Print/teacher/NorRat.do?level=f&dtimeOid=${c.Oid}" class="btn btn-danger btn-xs">期末</a>
		</div>
	</display:column>
	<display:column style="white-space:nowrap" title="教師" property="cname" sortable="true"/>
	<display:column style="white-space:nowrap" title="選別" property="opt" sortable="true"/>
	<display:column style="white-space:nowrap" title="學分" property="credit" sortable="true"/>
	<display:column style="white-space:nowrap" title="時數" property="thour" sortable="true"/>
	<display:column style="white-space:nowrap" title="人數" property="cnt" sortable="true"/>
	<display:column style="white-space:nowrap" title="缺期中" property="score2" sortable="true"/>
	<display:column style="white-space:nowrap" title="缺期末" property="score" sortable="true"/>
	
	
	
	<display:column style="white-space: nowrap">
	
		<div class="btn-group" style="width:80px" onClick="$('#dOid').val('${c.Oid}')">
		<button class="btn btn-default btn-xs" name="method:editSeld">修改</button>
		<button class="btn btn-danger btn-xs" name="method:clearSeld" onClick="javascript:return(confirm('確定刪除本班學生所有選課?')); void($(''))" type="submit">清除</button>
		</div>
	</display:column>
	 
</display:table>

</div>

</c:if>
<c:if test="${!empty selds}">
<!-- 班級 -->
<div class="panel panel-primary">
<div class="panel-heading">新增或查詢</div>
<table id="table" class="table table-hover">
	<thead>
	<tr class="text-info">
		<th nowrap data-sort="string">學號</th>
		<th nowrap data-sort="string">姓名</th>
		<th nowrap data-sort="int">課程編號</th>
		<th nowrap data-sort="string">課程名稱</th>
		<th nowrap data-sort="float">學分</th>
		<th nowrap data-sort="int">時數</th>
		<th nowrap data-sort="string">選別</th>
		<th nowrap data-sort="float">期中成績</th>
		<th nowrap data-sort="float">期末成績</th>
		<th nowrap data-sort="float">學期成績</th>
		<th nowrap></th>
	</tr>
	</thead>
	<c:forEach items="${selds}" var="s">
	<tr>
		<td nowrap>${s.student_no}</td>
		<td nowrap>${s.student_name} 
		<button type="button" class="btn btn-default btn-xs" onClick="getSeldHist('${s.student_no}')" 
		data-toggle="modal" data-target="#scoreHist">加退選</button>
		</td>
		<td nowrap>${s.cscode}</td>
		<td nowrap>${s.chi_name}</td>
		<td nowrap>${s.credit}</td>
		<td nowrap>${s.thour}</td>
		<td nowrap>${s.optName}</td>
		<td nowrap>
		
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="form-control" name="score2" value="${s.score2}" />
		
		</td>
		<td>
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="form-control" name="score3" value="${s.score3}"/>
		</td>
		<td>
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="form-control" name="score" value="${s.score}"/>
		<input type="hidden" name="sOid" id="sOid${s.Oid}" />
		</td>
		<td width="100%"><button class="btn btn-danger" onClick="$('#sOid${s.Oid}').val('${s.Oid}')" name="method:saveSeld">修改</button></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="10"><button class="btn btn-danger" name="method:saveSeld">全部儲存</button></td>
	</tr>
</table>
</div>

</c:if>


<c:if test="${!empty stselds}">
<!-- 個人 -->

<div class="panel panel-primary">
<div class="panel-heading">本學期應修 ${info.credit}學分 ${info.thour}時數, 不及格 ${info.dcredit}學分
<button type="button" class="btn btn-default btn-xs" onClick="getSeldHist('${info.student_no}')" data-toggle="modal" data-target="#scoreHist">查看加退選</button></div>
<table class="table table-hover">
	<tr class="text-info">
		<td>學號</td>
		<td>姓名</td>
		<td nowrap>課程編號</td>
		<td nowrap>課程名稱</td>
		<td nowrap>學分</td>
		<td nowrap>時數</td>
		<td>選別</td>
		<td>期中成績</td>
		<td>期末成績</td>
	</tr>
	<c:forEach items="${stselds}" var="s">
	<tr>
		<td nowrap>${s.student_no}</td>
		<td nowrap>${s.student_name}
		<button type="button" class="btn btn-default btn-xs" onClick="getSeldHist('${s.student_no}')" 
		data-toggle="modal" data-target="#scoreHist">加退選</button>
		</td>
		<td nowrap>${s.cscode}</td>
		<td nowrap>${s.chi_name}</td>
		<td nowrap>${s.credit}</td>
		<td nowrap>${s.thour}</td>
		<td nowrap>${s.optName}</td>
		<td nowrap>
		<div class="col-sm-1">
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="form-control form-width-1" name="score2" value="${s.score2}" />
		</div>
		</td>
		<td width="100%">
		<div class="col-sm-1">
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="form-control form-width-1" name="score" value="${s.score}"/>
		</div>
		<div class="btn-group" onClick="$('#dOid').val(${s.Oid})">
		<button class="btn btn-danger" name="method:saveStSeld">修改</button>
		<button class="btn btn-default" disabled name="method:deleteSeld" onClick="javascript:return(confirm('確定刪除此成績?')); void($(''))" type="submit">刪除</button>
		</div>
		<input type="hidden" name="sOid" id="sOid${s.Oid}" />
		
		</td>
	</tr>
	</c:forEach>
</table>
	<div class="panel-body">
	<button class="btn btn-danger" name="method:saveStSeld">全部儲存</button>
	</div>
</div>

</c:if>

<c:if test="${!empty hist}">

<div class="panel panel-primary">
<div class="panel-heading">應修 ${info.credit}學分, 不及格 ${info.dcredit}學分
<button type="button" class="btn btn-default btn-xs" onClick="getSeldHist('${info.student_no}')" data-toggle="modal" data-target="#scoreHist">查看加退選</button></div>
<table class="table table-hover">
	<tr class="text-info">		
		<td nowrap>學年</td>
		<td nowrap>學期</td>
		<td nowrap>開課班級</td>
		<td nowrap>課程編號</td>
		<td nowrap>學分</td>
		<td nowrap>選別</td>
		<td nowrap>期末成績</td>
		<td nowrap>修課型態</td>
	</tr>
	<tr>		
		<td><input type="text" autocomplete="off" class="form-control form-width-1" name="school_year" /></td>
		<td><input type="text" autocomplete="off" class="form-control form-width-1" name="school_term" /></td>
		<td><input type="text" autocomplete="off" name="stdepart_class" data-provide="typeahead" class="form-control form-width-3 stdepart_class"/></td>
		<td><input type="text" autocomplete="off" name="cscode" data-provide="typeahead" class="form-control form-width-3 cscode"/></td>
		<td><input type="text" autocomplete="off" class="form-control form-width-1" name="credit" /></td>
		<td>
		<select name="opt" class="selectpicker" data-width="auto">
		<c:forEach items="${CODE_DTIME_OPT}" var="e">
		<option value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>
		</td>
		<td><input type="text" autocomplete="off" class="form-control"  name="score" /></td>
		<td width="100%">
		<input type="hidden" name="sOid" id="sOid${s.Oid}" value="" />
		<select name="evgr_type" class="selectpicker" data-width="auto">
		<c:forEach items="${CODE_SCORE_EVGRTYPE}" var="e">
		<option value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>		
		<button class="btn btn-danger" name="method:addScoreHist">新增歷年成績</button>
		</td>
	</tr>
	<c:forEach items="${scoreHist}" var="s">
	<tr>		
		<td><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" class="form-control" name="school_year" value="${s.school_year}" /></td>
		<td><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" class="form-control" name="school_term" value="${s.school_term}" /></td>
		<td>
		<c:if test="${empty s.stdepart_class}"><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" name="stdepart_class" value="" data-provide="typeahead" class="form-control stdepart_class"/></c:if>
		<c:if test="${!empty s.stdepart_class}"><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" name="stdepart_class" value="${s.stdepart_class}, ${s.ClassName}" data-provide="typeahead" class="stdepart_class form-control"/></c:if>
		</td>
		<td><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" name="cscode" value="${s.cscode}, ${s.chi_name}" data-provide="typeahead" class="form-control cscode"/></td>
		<td><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" class="form-control" name="credit" value="${s.credit}" /></td>
		<td>
		<select name="opt" onChange="$('#sOid${s.Oid}').val(${s.Oid})" class="form-control">
		<c:forEach items="${CODE_DTIME_OPT}" var="e">
		<option <c:if test="${s.opt eq e.id}">selected</c:if> value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>
		</td>
		<td><input type="text" autocomplete="off" onfocus="checkSOid('${s.Oid}');" class="form-control" name="score" value="${s.score}" /></td>
		<td width="100%" nowrap>
		
		<input type="hidden" name="sOid" id="sOid${s.Oid}" />
		
		
		
		<select  name="evgr_type" onChange="checkSOid('${s.Oid}');" class="form-control" style="width:auto; float:left;">
		<c:forEach items="${CODE_SCORE_EVGRTYPE}" var="e">
		<option <c:if test="${s.evgr_type eq e.id}">selected</c:if> value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>&nbsp;
		<div class="btn-group" onClick="$('#dOid').val(${s.Oid})" id="sCheck${s.Oid}">
		<button class="btn btn-default" name="method:saveScoreHist">修改</button>
		<button class="btn btn-primary" name="method:deleteScoreHist" onClick="javascript:return(confirm('確定刪除此成績?')); void($(''))" type="submit">刪除</button>
		</div>
		
		</td>
	</tr>
	</c:forEach>
</table>
</div>
<c:if test="${!empty ScoreHistEditLog}">
<div class="panel panel-primary">
<div class="panel-heading">修改記錄</div>
<ul class="list-group">
	<c:forEach items="${ScoreHistEditLog}" var="s">
  	<li class="list-group-item">${s.edate}, ${s.cname}, ${s.cscode}, ${s.type}</li>
  	</c:forEach>
</ul>
</div>
</c:if>
<script>
function checkSOid(o){
	$("#sOid"+o).val(o);
	$("#sCheck"+o).show("slow");
}
</script>
<button class="btn btn-danger" name="method:saveScoreHist">全部儲存</button>


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
	
	$("input[id='Dtime_oid']").typeahead({
		remote:"#Dtime_oid",
		source : [],
		items : 99999,
		updateSource:function(inputVal, callback){
			
			$.ajax({
				type:"POST",
				url:"/eis/autoCompleteDtime",
				dataType:"json",
				data:{key:inputVal, term:${applicationScope.school_term}},
				success:function(d){
					callback(d.list);
				}
			});
		}		
	});
	
	$(".cscode").typeahead({
		//remote:"#agent",
		source : [],
		items : 10,
		updateSource:function(inputVal, callback){			
			$.ajax({
				url:"/eis/autoCompleteCscode",
			    dataType: 'jsonp',
			    jsonp:'back',          //jsonp請求方法
			    data:{nameno:inputVal},
			    cache:false,
			    type:'POST',
			    success: function(d) {    			    	
			    	callback(d.list);
			    }
			});
		}		
	});
	
	$(".stdepart_class").typeahead({
		//remote:"#agent",
		source : [],
		items : 10,
		updateSource:function(inputVal, callback){			
			$.ajax({
				url:"/eis/autoCompAnyCode",
			    dataType: 'jsonp',
			    jsonp:'back',          //jsonp請求方法
			    data:{bootstrap:1, table:"Class", idCol:"ClassNo",nameCol:"ClassName",value:inputVal},
			    cache:false,
			    type:'POST',
			    success: function(d) {    			    	
			    	callback(d.list);
			    }
			});
		}		
	});
	
	/*
	$('#note1').popover("show");
	$('#note2').popover("show");
	$('#note3').popover("show");
	
	setTimeout(function() {
		$('#note1').popover("hide");
		$('#note2').popover("hide");
		$('#note3').popover("hide");
	}, -1);
	*/
});


function getSeldHist(stdNo){
	
	var str;
	$.get("/eis/getSeldHist?stdNo="+stdNo+"&x="+Math.floor(Math.random()*999),
		function(d){		
		str="<table class='table table-bordered table-hover'>";		
		if(d.list.length>0){
			for(i=0; i<d.list.length; i++){				
				str=str+"<tr><td nowrap>"+d.list[i].ClassName+"</td><td nowrap>"+d.list[i].chi_name+"</td><td>"+d.list[i].edate.replace("T", " ")+"</td><td nowrap>"+d.list[i].cname+"</td>";
				if(d.list[i].type=='A'){
					str=str+"<td class='text-success'>加選</td></tr>";
				}else{
					str=str+"<td class='text-error'>退選</td></tr>";
				}
			}
		}else{
			str=str+"<tr><td>無加退選記錄</td></tr>";
		}
		str=str+"</table>";
		
		$("#modal-title").html(stdNo+"加退選記錄");
		$("#modal-body").html(str);
		
	}, "json");	
}
</script>
</body>
</html>