<!doctype html>
<%@page import="java.util.*"%>
<%@page import="model.Patient"%>
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
		function submit(patientusr){	
			
			//alert("in");
			
		    $.ajax({
                 type: "get",
                 url: "AddPatient",
                 data: {username: patientusr},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                 success: function(msg){
                	    alert(msg); window.location.href = "UserManage"}
                }); 
		}
	</script>

</head>
<body>

<div class="wrapper">

       <% String token=(String)session.getAttribute("token");
       if(token == null){
       		request.getRequestDispatcher("login").forward(request, response);}%>
     
	<div class="sidebar" data-background-color="white" data-active-color="danger">
    <!--
		Tip 1: you can change the color of the sidebar's background using: data-background-color="white | black"
		Tip 2: you can change the color of the active button using the data-active-color="primary | info | success | warning | danger"
	-->

    	<div class="sidebar-wrapper">
            <div class="logo">
                <a class="simple-text">
                    	医路相随
                </a>
            </div>

            <ul class="nav">
                <li class="active">
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
                <li>
                    <a href="templet">
                        <i class="ti-panel"></i>
                        <p>模版管理</p>
                    </a>
                </li>
                  <li>
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
                            <a href="logout">
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
                                             
                        <div class="card">
                            <div class="header">
                                <h4 class="title">所有病人</h4> <br />
                               <div class="text-right">
                               
                                <input type="text" placeholder="搜索关键字" >&nbsp
                                        <button  type="button" >搜索</button>
                                  
                                    </div>
                            </div>
                            <div class="content">

                                <ul class="list-unstyled team-members">
                                 <% ArrayList<Patient> list = (ArrayList<Patient>)request.getAttribute("list"); %>
                                 <%
                                    	if(list != null){
	                                    	Iterator iter = list.iterator(); 
											while(iter.hasNext()) { 									
												Patient p = (Patient)iter.next();
								 %>
                                            
                                            <li>
                                                <div class="row">
                                                    <div class="col-xs-3">
                                                        <div class="avatar" >
                                                            <img src="assets/img/faces/face-1.jpg" alt="Circle Image" class="img-circle img-no-padding img-responsive" >
                                                        </div>
                                                      
                                                    </div>
                                                    <div class="col-xs-6">
                                                      		   <%= p.getPatient_name() %> - <%= p.getSex() %>
                                                        <br />
                                                     <span><small>联系方式：<%= p.getPhone() %></small></span><br />
                                                         <span><small>证件号：<%= p.getIdCard() %></small></span><br />
                                                       
                                                    </div>
                                           			<div class="col-xs-3 text-right">               
                                             			<input type="button" value="添加" onclick="submit('<%= p.getUsername()%>')">
                                                    </div>

                                                </div>
                                            </li>
                                            
                                            <% }}%> 
                                           
                                        </ul>
                                      </div>
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

</html>
