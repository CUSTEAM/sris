<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>成績管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/json2.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
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
	$.get("/eis/getSeldHist?stdNo="+stdNo+"&"+Math.floor(Math.random()*999),
		function(d){
		
		str="<table class='table table-bordered table-hover'>";
		
		$("#info").html("");
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
		
		$("#title").text(stdNo);
		$("#info").append(str);
		
	}, "json");	
}
</script>
</head>
<body>
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>成績管理</strong> 
</div>
<form action="ScoreManager" method="post" class="form-horizontal" onSubmit="$.blockUI({message:null});">
<table class="table">
	<tr>
		<td nowrap>指定班級</td>
		<td width="100%">
		<%@ include file="/inc/jsp-kit/classSelector.jsp"%>
		<button class="btn" name="method:searchClass" type="submit">查詢</button>
		</td>
	</tr>
	<tr>
		<td nowrap>指定課程</td>
		<td width="100%">
		<div class="input-append control-group">
			<input class="span6" onClick="this.value=''" autocomplete="off" type="text" id="Dtime_oid" value="${Dtime_oid}" name="Dtime_oid"
			 data-provide="typeahead"  placeholder="課程編號、代碼、教師姓名或班級代碼與班級名稱片段" />
			
		    <button class="btn" id="searchDtime" name="method:searchDtime" type="submit">查詢</button>		    
		</div>
		</td>
	</tr>
	<tr>
		<td nowrap>指定學生</td>
		<td width="100%" colspan="2">
		<div class="input-append control-group" style="float:left;">
			<input class="span4" onClick="this.value=''" autocomplete="off" type="text" id="student_no" value="${student_no}" name="student_no"
			 data-provide="typeahead" placeholder="學號或姓名片段" />
			
		    <button class="btn" id="searchStd" name="method:searchStudent" type="submit">本學期成績</button>		    		   
		</div>&nbsp;
		<button class="btn" name="method:searchScoreHist" type="submit">歷年成績</button>
		<button id="compel" style="display:none;" name="method:compel" type="submit">compel</button>
		</td>
		 
	</tr>
</table>

<input type="hidden" name="dOid" value="${dOid}" id="dOid"/>
<input type="hidden" name="stNo" value="${stNo}" id="stNo"/>
<c:if test="${!empty css}">
<table class="table table-hover">
	<tr class="text-info">
		<td nowrap>課程編號</td>
		<td nowrap>開課班級</td>
		<td nowrap>課程名稱</td>
		<td nowrap>授課教師</td>
		<td nowrap>選別</td>
		<td nowrap>學分</td>
		<td nowrap>時數</td>
		<td nowrap>已選/上限</td>
		<td nowrap>期中</td>
		<td nowrap>期末</td>
		<!--td></td-->
		<td></td>
	</tr>
	<c:forEach items="${css}" var="c">
	<tr>
		<td nowrap>${c.Oid}</td>
		<td nowrap>${c.ClassName}</td>
		<td nowrap>${c.cscode}${c.chi_name}</td>
		<td nowrap>${c.cname}</td>
		<td nowrap>${c.opt}</td>
		<td nowrap>${c.credit}</td>
		<td nowrap>${c.thour}</td>
		<td nowrap>${c.cnt}/${c.Select_Limit}</td>
		<td>${c.score2}</td>
		<td>${c.score}</td>
		<!-- td>  
    		<div class="btn-group">
		    <a class="btn dropdown-toggle" data-toggle="dropdown"><span class="icon-print"></span></a>
		    <ul class="dropdown-menu">
		    	<li><a class="btn btn-link" href="/csis/SylDoc?Oid=${c.Oid}">課程大綱</a></li>
		    	<li><a class="btn btn-link" href="/csis/IntorDoc?Oid=${c.Oid}">中英文簡介</a></li>
		    	<li><a class="btn btn-link" href="/csis/DtimeSelds?Oid=${c.Oid}">選課學生</a></li>
		    </ul>
		    </div>
	   	</td-->
		<td class="text-info" nowrap width="100%">		
		<div class="btn-group" onClick="$('#dOid').val('${c.Oid}')">
		<button class="btn" name="method:editSeld">修改</button>
		<button class="btn" name="method:clearSeld" onClick="javascript:return(confirm('確定刪除本班學生所有選課?')); void($(''))" type="submit">清除</button>
		</div>
		</td>
	</tr>
	</c:forEach>
</table>
</c:if>
<c:if test="${!empty selds}">
<!-- 班級 -->
<table class="table table-hover">
	<tr class="text-info">
		<td>學號</td>
		<td>姓名</td>
		<td nowrap>課程編號</td>
		<td nowrap>課程名稱</td>
		<td nowrap>學分</td>
		<td nowrap>時數</td>
		<td>選別</td>
		<td nowrap>期中成績</td>
		<td nowrap>期末成績</td>
		<td nowrap></td>
	</tr>
	<c:forEach items="${selds}" var="s">
	<tr>
		<td nowrap>${s.student_no}</td>
		<td nowrap>${s.student_name}</td>
		<td nowrap>${s.cscode}</td>
		<td nowrap>${s.chi_name}</td>
		<td nowrap>${s.credit}</td>
		<td nowrap>${s.thour}</td>
		<td nowrap>${s.optName}</td>
		<td nowrap>
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="span1" name="score2" value="${s.score2}" />
		</td>
		<td class="text-info">
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="span1" name="score" value="${s.score}"/>
		<input type="hidden" name="sOid" id="sOid${s.Oid}" />
		</td>
		<td width="100%"><button class="btn btn-danger" onClick="$('#sOid${s.Oid}').val('${s.Oid}')" name="method:saveSeld">修改</button></td>
	</tr>
	</c:forEach>
</table>
<button class="btn btn-danger" name="method:saveSeld">全部儲存</button>
</c:if>


<c:if test="${!empty stselds}">
<!-- 個人 -->
<div class="alert alert-success">本學期應修 ${info.credit}學分 ${info.thour}時數, 不及格 ${info.dcredit}學分</div>
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
		<td nowrap>${s.student_name}</td>
		<td nowrap>${s.cscode}</td>
		<td nowrap>${s.chi_name}</td>
		<td nowrap>${s.credit}</td>
		<td nowrap>${s.thour}</td>
		<td nowrap>${s.optName}</td>
		<td nowrap>
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="span1" name="score2" value="${s.score2}" />
		</td>
		<td width="100%">
		<input type="text" autocomplete="off" onKeyDown="$('#sOid${s.Oid}').val('${s.Oid}')" class="span1" name="score" value="${s.score}"/>
		<input type="hidden" name="sOid" id="sOid${s.Oid}" />
		<div class="btn-group" onClick="$('#dOid').val(${s.Oid})">
		<button class="btn btn-success" name="method:saveStSeld">修改</button>
		<button class="btn" disabled name="method:deleteSeld" onClick="javascript:return(confirm('確定刪除此成績?')); void($(''))" type="submit">刪除</button>
		</div>
		</td>
	</tr>
	</c:forEach>
</table>
<button class="btn btn-success" name="method:saveStSeld">全部儲存</button>
</c:if>

<c:if test="${!empty hist}">
<div class="alert">應修 ${info.credit}學分, 不及格 ${info.dcredit}學分</div>
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
		<td><input type="text" autocomplete="off" class="span1" name="school_year" /></td>
		<td><input type="text" autocomplete="off" class="span1" name="school_term" /></td>
		<td><input type="text" autocomplete="off" name="stdepart_class" data-provide="typeahead" class="stdepart_class"/></td>
		<td><input type="text" autocomplete="off" name="cscode" data-provide="typeahead" class="cscode"/></td>
		<td><input type="text" autocomplete="off" class="span1" name="credit" /></td>
		<td>
		<select name="opt">
		<c:forEach items="${CODE_DTIME_OPT}" var="e">
		<option value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>
		</td>
		<td><input type="text" autocomplete="off" class="span1" name="score" /></td>
		<td width="100%">
		<input type="hidden" name="sOid" id="sOid${s.Oid}" value="" />
		<select name="evgr_type">
		<c:forEach items="${CODE_SCORE_EVGRTYPE}" var="e">
		<option value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>		
		<button class="btn btn-danger" name="method:addScoreHist">新增歷年成績</button>
		</td>
	</tr>
</table>
</c:if>
<c:if test="${!empty scoreHist}">
<table class="table table-hover">
	<!-- tr class="text-info">		
		<td nowrap>學年</td>
		<td nowrap>學期</td>
		<td nowrap>開課班級</td>
		<td nowrap>課程編號</td>
		<td nowrap>學分</td>
		<td nowrap>選別</td>
		<td nowrap>期末成績</td>
		<td nowrap>修課型態</td>
	</tr-->
	<c:forEach items="${scoreHist}" var="s">
	<tr>		
		<td><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" class="span1" name="school_year" value="${s.school_year}" /></td>
		<td><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" class="span1" name="school_term" value="${s.school_term}" /></td>
		<td>
		
		<c:if test="${empty s.stdepart_class}"><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" name="stdepart_class" value="" data-provide="typeahead" class="stdepart_class"/></c:if>
		<c:if test="${!empty s.stdepart_class}"><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" name="stdepart_class" value="${s.stdepart_class}, ${s.ClassName}" data-provide="typeahead" class="stdepart_class"/></c:if>
		
		
		</td>
		<td><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" name="cscode" value="${s.cscode}, ${s.chi_name}" data-provide="typeahead" class="cscode"/></td>
		<td><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" class="span1" name="credit" value="${s.credit}" /></td>
		<td>
		<select name="opt" onChange="$('#sOid${s.Oid}').val(${s.Oid})">
		<c:forEach items="${CODE_DTIME_OPT}" var="e">
		<option <c:if test="${s.opt eq e.id}">selected</c:if> value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>
		</td>
		<td><input type="text" autocomplete="off" onKeyPress="$('#sOid${s.Oid}').val(${s.Oid})" class="span1" name="score" value="${s.score}" /></td>
		<td width="100%">
		<input type="hidden" name="sOid" id="sOid${s.Oid}" value="" />
		<select name="evgr_type" onChange="$('#sOid${s.Oid}').val(${s.Oid})">
		<c:forEach items="${CODE_SCORE_EVGRTYPE}" var="e">
		<option <c:if test="${s.evgr_type eq e.id}">selected</c:if> value="${e.id}">${e.name}</option>
		</c:forEach>
		</select>
		<div class="btn-group" onClick="$('#dOid').val(${s.Oid})">
		<button class="btn btn-warning" name="method:saveScoreHist">修改</button>
		<button class="btn" name="method:deleteScoreHist" onClick="javascript:return(confirm('確定刪除此成績?')); void($(''))" type="submit">刪除</button>
		</div>
		</td>
	</tr>
	</c:forEach>
</table>
<button class="btn btn-warning" name="method:saveScoreHist">全部儲存</button>

</c:if>
</form>
</body>
</html>