<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>學生資料管理</title>
<script src="/eis/inc/js/plugin/bootstrap-typeahead.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui.js"></script>
<script src="/eis/inc/js/plugin/bootstrap-tooltip.js"></script>
<script src="/eis/inc/js/plugin/jquery-ui-timepicker-addon.js" type="text/javascript"></script>

<link href="/eis/inc/css/jquery-ui.css" rel="stylesheet"/>
<script src="/eis/inc/js/autoComplete.js"></script>
<script src="/eis/inc/js/develop/timeInfo.js"></script>
<script>
$(document).ready(function() {
	$(".grad").hide("slow");
	$(".filter").hide("slow");
	$('.help').popover("show");
	setTimeout(function() {
		$('.help').popover("hide");
	}, 0);
	
	$(".fall").click(function(){
		var checkboxes = $(this).closest('form').find('input[name="filter"]:checkbox');
		if($(this).is(':checked')) {
			checkboxes.prop('checked', 'checked');
		} else {
			checkboxes.removeAttr('checked');
		}		
	});





	

	
});


</script>
</head>
<body><br>
<div class="container">
<!--div class="bs-callout bs-callout-warning" id="callout-helper-pull-navbar">人事資料管理</div-->
<form action="StdsManager" method="post" class="form-inline" enctype="multipart/form-data">

<div class="panel panel-primary">
	<div class="panel-heading">學生資料管理</div>	
	<table class="table">
		<tr>
		<td><%@ include file="/inc/jsp-kit/fullSelector.jsp"%></td>
	</tr>
	</table>
	
	
	
</div>



<br>
  <h2>Dynamic Tabs</h2>
  <p>To make the tabs toggleable, add the data-toggle="tab" attribute to each link. Then add a .tab-pane class with a unique ID for every tab and wrap them inside a div element with class .tab-content.</p>

  <ul class="nav nav-tabs">
    <li><a data-toggle="tab" href="#home">基本資料</a></li>
    <li><a data-toggle="tab" href="#menu1">任職歷程</a></li>
    <li><a data-toggle="tab" href="#menu2">最高學歷</a></li>
    <li class="active"><a data-toggle="tab" href="#menu3">取得證照</a></li>
  </ul>

  <div class="tab-content">
    <div id="home" class="tab-pane fade">
      <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>Menu 1</h3>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>Menu 2</h3>
      <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam.</p>
    </div>
    <div id="menu3" class="tab-pane fade in active">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  </div>
</div>


</form>
</body>
</html>