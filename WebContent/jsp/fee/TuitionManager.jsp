<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>收費檔學生名冊</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js" type="text/javascript"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js" type="text/javascript"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script>  
$(document).ready(function() {	
	$('.help').popover("show");
	setTimeout(function() {
		$('.help').popover("hide");
	}, 5000);	
});
</script>


</head>
<body>    
<div class="alert">
<button type="button" class="close" data-dismiss="alert">&times;</button>
<strong>匯出收費檔</strong> 
<div rel="popover" title="說明" 
data-content="收費項目不在報表格式中的情況下,合計金額仍會加總,但報表中無法顯示該收費項目金額" data-placement="right" class="btn btn-warning help">?</div>
</div>
<form action="TuitionManager" method="post" class="form-horizontal");">
<table class="table">
	<tr>
		<td nowrap>		
		<div class="input-prepend">
		<span class="add-on">繳費學期</span>
		<select name="term">
			<option <c:if test="${term eq '1'}">selected</c:if> value="1">第1學期</option>
			<option <c:if test="${term eq '2'}">selected</c:if> value="2">第2學期</option>
		</select>
		</div>
		<div class="input-prepend">
		<span class="add-on">繳費對象</span>
		<select name="scope">
			<option <c:if test="${scope eq ''}">selected</c:if> value="">全部</option>
			<option <c:if test="${scope eq '1'}">selected</c:if> value="1">新生</option>
			<option <c:if test="${scope eq '2'}">selected</c:if> value="2">舊生</option>
		</select>
		</div>		
		<div class="input-prepend">
		<span class="add-on">繳費期限</span>
		<input class="span3" placeholder="期限" name="edate" id="edate" value="${edate}" type="text" style="ime-mode:disabled" autocomplete="Off"/>	
		</div>	
		
		<div class="btn-group">
	    	<button class="btn" name="method:tuitionPrint">列印學雜費</button>
	    	<button class="btn" name="method:nottuitionPrint">列印代辦費</button>
	    </div>	
		</td>
	</tr>
	<tr>
		<td nowrap width="100%">
		<%@ include file="/inc/jsp-kit/grad_selector.jsp"%>	
		<div class="btn-group">
		<button class="btn" name="method:search">查詢</button>
		<button class="btn btn-danger" name="method:add">新增</button>	
		</div>
		</td>
	</tr>
</table>
<c:if test="${!empty fee}">
<div class="accordion" id="accordion">	
	<c:forEach items="${fee}" var="f">
	<c:if test="${f.mon1>0||f.mon2>0}">
	<div class="accordion-group">
		<div class="accordion-heading">
			<div class="accordion-toggle" >
			<table>
				<tr>
					<td width="50%" data-toggle="collapse" data-parent="#accordion" href="#collapse${f.ClassNo}">${f.ClassName}: ${f.cnt}人</td>
					<td>
						<input type="hidden" name="ClassNo" id="ClassNo${f.ClassNo}" value="" />
						<c:if test="${term eq'1' && f.Grade eq'1'}">						
						<div class="input-prepend">
							<span class="add-on">名額</span>
							<input type="text" name="quota" class="span1" value="${f.quota}" style="ime-mode:disabled" autocomplete="Off"/>
						</div>						
						<div class="input-prepend input-append">
							<span class="add-on">學號起始自</span>
							<input type="text" name="no" class="span2" value="${f.no}" style="ime-mode:disabled" autocomplete="Off"/>
							<button class="btn" onClick="$('#ClassNo${f.ClassNo}').val('${f.ClassNo}')" name="method:save">儲存</button>
						</div>					
						</c:if>					
					</td>
				</tr>
			</table>			
			
			</div>
		</div>
		<div id="collapse${f.ClassNo}" class="accordion-body collapse">
			<div class="accordion-inner">			
			<table>
				<tr>
					<td valign="top" style="padding:5px;">
					<c:forEach items="${f.pay1}" var="p">
					
					<p><div class="input-prepend input-append">
						<span class="add-on" style="width:100px">${p.Name}</span>
						<input type="hidden" name="Fcode" id="Fcode${p.Oid}" value="${p.Fcode}" />
						<input type="text" class="span1" id="Money${f.ClassNo}${p.No}" value="${p.Money}" 
						rel="popover" title="" data-placement="bottom"/>											
						<button class="btn fee1" type="button" 
						onClick="edit('${p.Oid}', $('#Money${f.ClassNo}${p.No}').val(), '1', '${f.ClassNo}','${p.No}')">修改</button>
					</div></p>
					
					</c:forEach>
					<p><div class="input-prepend input-append">
						<span class="add-on" style="width:100px">合計</span>
						<input type="text" id="cnt1${f.ClassNo}" class="span1" value="${f.mon1}" />
						<button class="btn btn-danger fee1" 
						onClick="confirmDel('${f.ClassNo}','${f.ClassName}')" 
						name="method:del">刪除</button>
					</div></p>
					</td>
					
					<td valign="top" style="padding:5px;">
					
					<c:forEach items="${f.pay2}" var="p">
					<p>
					<input type="hidden" name="Fcode" id="Oid${p.Oid}" value="${p.Fcode}" />
					<div class="input-prepend input-append">
						<span class="add-on" style="width:100px">${p.Name}</span>
						<input type="hidden" id="Fcode${p.Oid}" value="${p.Oid}" />
						<input type="text" class="span1" id="Money${f.ClassNo}${p.No}" value="${p.Money}" 
						rel="popover" title="" data-placement="bottom"/>											
						<button class="btn fee2" type="button" onClick="edit('${p.Oid}', $('#Money${f.ClassNo}${p.No}').val(),'2','${f.ClassNo}','${p.No}')">修改</button>
					</div>
					</p>
					
					</c:forEach>
					<p><div class="input-prepend input-append">
						<span class="add-on" style="width:100px">合計</span>
						<input type="text" id="cnt2${f.ClassNo}" class="span1" value="${f.mon2}" />
						<button class="btn btn-danger fee2" onClick="confirmDel('${f.ClassNo}','${f.ClassName}')" name="method:del">刪除</button>
					</div></p>
					</td>
				</tr>
			</table>			
			</div>
		</div>
	</div>
	</c:if>	
	</c:forEach>
</div>
</c:if>
</form>
<script>
$("#edate").datepicker({
	//stepMinute: 10,
	//hourMin: 0,
	//hourMax: 99
	//showTime:false
});

function confirmDel(ClassNo, ClassName){
	if(confirm("確定刪除"+ClassName+"?")){		
		$("#ClassNo"+ClassNo).val(ClassNo);
	}else{
		void('');
	}
}

function edit(Oid, Money, kind, ClassNo,Fcode){	
	$.ajax({
		url:"TuitionAjax",
	    dataType: 'jsonp',
	    jsonp:'back',          //jsonp請求方法
	    data:{Oid:Oid, Money:Money, back:"back", kind:kind, ClassNo:ClassNo, Fcode:Fcode},
	    crossDomain: true,
	    cache:false,
	    type:'POST',
	    success: function(d){	    	
	    	callback(d, kind, ClassNo, Fcode);
	    }	    
	});		
}

function callback(b, kind, ClassNo, Fcode){	
	if(kind==1){		
		$("#cnt1"+ClassNo).val(b.msg.cnt);
	}else{
		$("#cnt2"+ClassNo).val(b.msg.cnt);
	}
	
	$("#Money"+ClassNo+Fcode).attr("title", b.msg.msg);	
	$("#Money"+ClassNo+Fcode).tooltip("show");	
	setTimeout(function() {
		$("#Money"+ClassNo+Fcode).tooltip("hide");
	}, 1000);	
}

</script>
</body>
</html>