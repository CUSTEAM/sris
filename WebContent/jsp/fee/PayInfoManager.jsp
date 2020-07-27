<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人事費用資料輸入</title>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js"></script>
<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<!-- 不知是否已廢止停用 -->
</head>
<body>    
<div class="bs-callout bs-callout-info">
<h4>人事費用資料輸入</h4> <!-- small>批次匯入請先下載最新格式，依欄位貼上資料，並按下匯入資料</small-->
</div>

<form action="PayInfoManager" method="post" class="form-inline" enctype="multipart/form-data">
<div class="panel-group" id="accordionSearch" role="tablist" aria-multiselectable="true">  
	<div class="panel panel-primary">    
    <div class="panel-heading" role="tab" id="headingOne">      
      <h4 class="panel-title"><a role="button" data-toggle="collapse" data-parent="#accordionSearch" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">查詢學生</a></h4>    
    </div>
    <!-- div class="panel-body">說明文字</div--> 
    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">      
    	
	<table class="table">
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">學號</div>
				<input class="form-control" style="width:300px;" onClick="this.value=''" 
				autocomplete="off" type="text" id="StudentNo" value="${StudentNo}" name="StudentNo"
			 data-provide="typeahead" placeholder="學號或姓名片段" />
			 
			</div>
			<div class="btn-group" role="group">
			<button class="btn btn-danger" name="method:search">查詢學生</button>			
			<a href="PayInfoManager" class="btn btn-default">重設條件</a>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">學年度自</div>
				<input type="text" name="begin" value="${begin}" class="form-control" style="width:100px;"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon">至</div>
				<input type="text" name="end" value="${end}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">轉帳類型</div>
				<select name="saly" class="form-control">
					<option value="1">僅薪資</option>
					<option <c:if test="${saly eq''}">selected</c:if> value="">全部</option>
				</select>
			</div>		
			</td>
		</tr>
		
	</table> 
	<table class="table">
			<tr>
				<td>
				
				<div class="input-group">
					<div class="input-group-addon">身心障礙</div>
					<select name="dis" class="form-control">
						<option value="0">否</option>
						<option value="1" value="1">是</option>
					</select>
				</div>
				
				<div class="input-group">
					<div class="input-group-addon">加保日期</div>
					<input type="text" name="i_begin" class="date form-control"/>
				</div>
				<div class="input-group">
					<div class="input-group-addon">退保日期</div>
					<input type="text" name="i_end" class="date form-control"/>
				</div>				
				</td>
			</tr>
			
			<tr>
				<td>
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">研究助理</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="job_res" />    
				</span>
				     
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">教學助理</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="job_tech" />
				</span>
			            
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">工讀附服務負擔</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="job_ser"/>
				</span>
			           
				</td>
			</tr>
			<tr>
				<td>
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">助學型</button>
					<input type="checkbox" class="hidden"/>
					<input type="text" class="hidden" name="pay_std"/>    
				</span>
				     
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">勞僱型</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="pay_lab" />
				</span>
			            
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">附服務負擔</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="pay_ser" />
				</span>
			           
				</td>
			</tr>
			<tr>
				<td>	
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">工讀助學金</button>
					<input type="checkbox" class="hidden"/>
					<input type="text" class="hidden" name="money_w" />    
				</span>
				     
				<span class="button-checkbox">
					<button type="button" class="btn" data-color="primary">生活助學金</button>
					<input type="checkbox" class="hidden" />
					<input type="text" class="hidden" name="money_l" />
				</span>
			           
				</td>
			</tr>
			
			<tr>
				<td>
				<div class="btn-group" role="group">
				<button class="btn btn-danger" name="method:printAll" type="submit"><span class="glyphicon glyphicon-print" aria-hidden="true"></span> 列表並統計</button>
				<!-- button class="btn btn-default" name="method:print710" type="submit"><span class="glyphicon glyphicon-print" aria-hidden="true"></span> 項目統計列表</button-->
				</div>
				</td>
			</tr>
		</table>   
    </div>  
  	</div>  
</div>





<c:if test="${!empty diposts}">

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">     
<c:forEach items="${diposts}" var="d">




  <div class="panel panel-primary" onClick="$('#Oid${d.Oid}').val('${d.Oid}')">         
    <div class="panel-heading" role="tab" id="heading${d.Oid}">             
        <h4 class="panel-title">                 
        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse${d.Oid}" aria-expanded="true" aria-controls="collapse${d.Oid}">
        ${d.SchoolYear}學年度 ${d.occur_month}月份${d.name}, 轉帳金額 ${d.Money}元</a></h4>         
    </div>         
    <div id="collapse${d.Oid}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading${d.Oid}">             
      <div class="panel-body">
      <input type="hidden" name="Oid" id="Oid${d.Oid}" value="" />  
      </div>
      <table class="table">
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">身心障礙</div>
				<select name="disabled" class="form-control">
					<option value="0">否</option>
					<option value="1"<c:if test="${d.dis eq'1'}">selected</c:if> value="1">是</option>
				</select>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">加保日期</div>
				<input type="text" name="insurance_begin" value="${d.insurance_begin}" class="date form-control"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon">退保日期</div>
				<input type="text" name="insurance_end" value="${d.insurance_end}" class="date form-control"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">工作時數</div>
				<input type="text" name="hours" value="${d.hours}" class="form-control" style="width:70px;"/>
			</div>
			
			</td>
		</tr>
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">僱主勞保</div>
				<input type="text" name="hirer_labor" value="${d.hirer_labor}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">僱主健保</div>
				<input type="text" name="hirer_health" value="${d.hirer_health}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">二代健保</div>
				<input type="text" name="hirer_health_two" value="${d.hirer_health_two}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">僱主勞退</div>
				<input type="text" name="hirer_retire" value="${d.hirer_retire}" class="form-control" style="width:100px;"/>
			</div>
			
			
			
			</td>
		</tr>
		
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">自付勞保</div>
				<input type="text" name="self_labor" value="${d.self_labor}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">自付健保</div>
				<input type="text" name="self_health" value="${d.self_health}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">二代健保</div>
				<input type="text" name="self_health_two" value="${d.self_health_two}" class="form-control" style="width:100px;"/>
			</div>
			
			
			
			
			</td>
		</tr>
		
		<tr>
			<td>
			
			<div class="input-group">
				<div class="input-group-addon">申報薪資</div>
				<input type="text" name="pubmoney" value="${d.pubmoney}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">應付薪資</div>
				<input type="text" name="payables" value="${d.payables}" class="form-control" style="width:100px;"/>
			</div>
			
			<div class="input-group">
				<div class="input-group-addon">實付薪資</div>
				<input disabled type="text" value="${d.Money}" class="form-control" style="width:100px;"/>
			</div>
			
			</td>
		</tr>
		
		<tr>
			<td>
			<div class="input-group">
				<div class="input-group-addon">教育部經費</div>
				<input type="text" name="origin_edu" value="${d.origin_edu}" class="form-control" style="width:100px;"/>
			</div>		
			<div class="input-group">
				<div class="input-group-addon">科技部經費</div>
				<input type="text" name="origin_mst" value="${d.origin_mst}" class="form-control" style="width:100px;"/>
			</div>		
			<div class="input-group">
				<div class="input-group-addon">學校自籌經費</div>
				<input type="text" name="origin_self" value="${d.origin_self}" class="form-control" style="width:100px;"/>
			</div>
			<div class="input-group">
				<div class="input-group-addon">共他來源經費</div>
				<input type="text" name="origin_other" value="${d.origin_other}" class="form-control" style="width:100px;"/>
			</div>
			</td>
		</tr>
		<tr>
			<td>
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">研究助理</button>
				<input type="checkbox" class="hidden" <c:if test="${d.job_research eq'1'}">checked</c:if>/>
				<input type="text" class="hidden" name="job_research" value="${d.job_research}" />    
			</span>
			     
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">教學助理</button>
				<input type="checkbox" class="hidden" <c:if test="${d.job_teach eq'1'}">checked</c:if> />
				<input type="text" class="hidden" name="job_teach" value="${d.job_teach}" />
			</span>
		            
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">工讀附服務負擔</button>
				<input type="checkbox" class="hidden" <c:if test="${d.job_service eq'1'}">checked</c:if> />
				<input type="text" class="hidden" name="job_service" value="${d.job_service}" />
			</span>
		           
			</td>
		</tr>
		<tr>
			<td>
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">助學型</button>
				<input type="checkbox" class="hidden" <c:if test="${d.pay_study eq'1'}">checked</c:if>/>
				<input type="text" class="hidden" name="pay_study" value="${d.pay_study}" />    
			</span>
			     
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">勞僱型</button>
				<input type="checkbox" class="hidden" <c:if test="${d.pay_labor eq'1'}">checked</c:if> />
				<input type="text" class="hidden" name="pay_labor" value="${d.pay_labor}" />
			</span>
		            
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">附服務負擔</button>
				<input type="checkbox" class="hidden" <c:if test="${d.pay_service eq'1'}">checked</c:if> />
				<input type="text" class="hidden" name="pay_service" value="${d.pay_service}" />
			</span>
		           
			</td>
		</tr>
		<tr>
			<td>	
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">工讀助學金</button>
				<input type="checkbox" class="hidden" <c:if test="${d.money_work eq'1'}">checked</c:if>/>
				<input type="text" class="hidden" name="money_work" value="${d.money_work}" />    
			</span>
			     
			<span class="button-checkbox">
				<button type="button" class="btn" data-color="primary">生活助學金</button>
				<input type="checkbox" class="hidden" <c:if test="${d.money_life eq'1'}">checked</c:if> />
				<input type="text" class="hidden" name="money_life" value="${d.money_life}" />
			</span>
		           
			</td>
		</tr>
		<tr>
			<td>
			<div class="input-group">
				<div class="input-group-addon">專案名稱</div>
				<select name="Rc_aio" class="form-control">
					<c:forEach items="${rcs}" var="r">
					<option <c:if test="${d.Rc_aio eq r.Oid}">selected</c:if> value="${r.Oid}">【${r.school_year}】${r.Rc_name}</option>
					</c:forEach>
				</select>
				
			</div>
			<div class="input-group">
				<div class="input-group-addon">單位名稱</div>
				<select name="unit" class="form-control">
				<option <c:if test="${empty d.unit}">selected</c:if> value=""></option>
				<c:forEach items="${units}" var="c">			
				<option <c:if test="${d.unit eq c.id}">selected</c:if> value="${c.id}"><c:if test="${!empty c.pname}">【${c.pname}】</c:if>${c.name}</option>
				</c:forEach>
				</select>
				
			</div>
			
			</td>
		</tr>
		<tr>
			<td>
			<button class="btn btn-primary" name="method:save" type="submit"><span class="glyphicon glyphicon-cloud-upload" aria-hidden="true"></span>儲存</button>
			</td>
		</tr>
	</table>
    </div>     
  </div>        






</c:forEach>

</div>
</c:if>







</form>
<script>  
$(document).ready(function() {
	$(".date").datepicker({
		changeMonth: true,
		changeYear: true,
		//minDate: '@minDate'
		yearRange: "-100:+0"
		//showButtonPanel: true,
		//dateFormat: 'yy-MM-dd'
	});
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


$(function () {
    $('.button-checkbox').each(function () {

        // Settings
        var $widget = $(this),
            $button = $widget.find('button'),
            $checkbox = $widget.find('input:checkbox'),
            $textinput = $widget.find('input:text'),
            color = $button.data('color'),
            settings = {
                on: {
                    icon: 'glyphicon glyphicon-check'
                },
                off: {
                    icon: 'glyphicon glyphicon-unchecked'
                }
            };

        // Event Handlers
        $button.on('click', function () {
            $checkbox.prop('checked', !$checkbox.is(':checked'));
            $checkbox.triggerHandler('change');
            
            updateDisplay();
        });
        $checkbox.on('change', function () {
            updateDisplay();
        });

        // Actions
        function updateDisplay() {
            var isChecked = $checkbox.is(':checked');

            // Set the button's state
            $button.data('state', (isChecked) ? "on" : "off");

            // Set the button's icon
            $button.find('.state-icon')
                .removeClass()
                .addClass('state-icon ' + settings[$button.data('state')].icon);

            // Update the button's color
            if (isChecked) {
                $button.removeClass('btn-default').addClass('btn-' + color + ' active');
                $textinput.val("1");
            }
            else {
                $button.removeClass('btn-' + color + ' active').addClass('btn-default');
                $textinput.val("0");
            }
        }

        // Initialization
        function init() {

            updateDisplay();

            // Inject the icon if applicable
            if ($button.find('.state-icon').length == 0) {
                $button.prepend('<i class="state-icon ' + settings[$button.data('state')].icon + '"></i> ');
            }
        }
        init();
    });
});
</script>
</body>
</html>