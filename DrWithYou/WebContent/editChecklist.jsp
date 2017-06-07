<!doctype html>
<%@page import="model.Checklist"%>
<html lang="en">
<head>
	<meta charset="utf-8" />
	<link rel="apple-touch-icon" sizes="76x76" href="assets/img/apple-icon.png">
	<link rel="icon" type="image/png" sizes="96x96" href="assets/img/favicon.png">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />

	<title>医路相随</title>

	<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />
    <meta name="viewport" content="width=device-width" />


    <!-- Bootstrap core CSS     -->
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" />

    <!-- Animation library for notifications   -->
    <link href="assets/css/animate.min.css" rel="stylesheet"/>

    <!--  Paper Dashboard core CSS    -->
    <link href="assets/css/paper-dashboard.css" rel="stylesheet"/>

    <!--  CSS for Demo Purpose, don't include it in your project     -->
    <link href="assets/css/demo.css" rel="stylesheet" />

    <!--  Fonts and icons     -->
	<link href="css/font-awesome.min.css" rel="stylesheet">
    <link href='css/css.css' rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">


	<script type="text/javascript">
		function submit(cid){	
			
		    $.ajax({
                 type: "get",
                 url: "saveChecklist",
                 data: {id: cid,
                	    itemname: $("#itemname").val(),
                	    checktime: $("#checktime").val(),
                	    description: $("#description").val()},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                 success: function(msg){
                	    alert(msg); window.location.href = "checklist"}
                }); 
		}
	
	</script>
</head>
<body>

<div class="wrapper">
	<div class="sidebar" data-background-color="white" data-active-color="danger">

    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a  class="simple-text">
                    医路相随
                </a>
            </div>

            <ul class="nav">
               
                <li >
                    <a href="UserManage">
                        <i class="ti-user"></i>
                        <p>病人管理</p>
                    </a>
                </li>
               
                <li >
                    <a href="patientCheckpoint">
                        <i class="ti-view-list-alt"></i>
                        <p>病情管理</p>
                    </a>
                </li> 
                 <li >
                    <a href="templet">
                        <i class="ti-panel"></i>
                        <p>模版管理</p>
                    </a>
                </li>
              <li class="active">
                    <a href="checklist">
                        <i class="ti-pencil-alt2"></i>
                        <p>检查项设置</p>
                    </a>
                </li>
               
    	</div>
    </div>

    <div class="main-panel">
		<nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar bar1"></span>
                        <span class="icon-bar bar2"></span>
                        <span class="icon-bar bar3"></span>
                    </button>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">

                        <li>
                            <a href="#">
                                <i class="ti-settings"></i>
                                <p>设置</p>
                            </a>
                        </li>
                         <li>
                            <a href="logout" >
                                <i class="ti-panel"></i>
                                <p>退出</p>
                            </a>
                        </li>
						
                    </ul>

                </div>
            </div>
        </nav>


        <div class="content">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">编辑检查项</h4> <br />
                               <div class="card">
                           
                            <div class="content">
                            <% Checklist check = (Checklist)request.getAttribute("checklist"); %>                                 
                                    <div class="row">
                                        <div class="col-md-6">
                                            <div class="form-group">
                                                <label>检查项名称</label>
                                                <input type="text" id="itemname" class="form-control border-input" value=<%= check.getChecklist_name()%>>
                                            </div>
                                        </div>
                                       
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>检查时间</label>
                                                <input type="text" id="checktime" class="form-control border-input" value=<%= check.getChecktime() %>>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label>描述</label>
                                                <textarea rows="5" id="description" class="form-control border-input"><%= check.getDescription() %></textarea>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="text-center">
                                        <input type="button" value="确认修改" class="btn btn-info btn-fill btn-wd" onclick="submit(<%= check.getCid()%>)">
                                    </div>
                                    <div class="clearfix"></div>
                               
                            </div>

                           
                        </div>
                    </div>


</body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>

	<!--  Checkbox, Radio & Switch Plugins -->
	<script src="assets/js/bootstrap-checkbox-radio.js"></script>

	<!--  Charts Plugin -->
	<script src="assets/js/chartist.min.js"></script>

    <!--  Notifications Plugin    -->
    <script src="assets/js/bootstrap-notify.js"></script>

    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->
	<script src="assets/js/paper-dashboard.js"></script>

	<!-- Paper Dashboard DEMO methods, don't include it in your project! -->
	<script src="assets/js/demo.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){

           

          
        });
    </script>

</html>
