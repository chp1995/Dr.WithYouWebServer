<!doctype html>
<%@page import="java.util.*"%>
<%@page import="model.Patient"%>
<%@page import="model.Templet"%>
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
		function edit(patientusr){	
			
			//alert($('#select_k1 option:selected').text());
			alert($('#' + patientusr + ' option:selected').val());
			
			$.ajax({
                type: "post",
                url: "editPatientTemplet",
                data: {patientusr: patientusr,
                	  tid : $('#' + patientusr + ' option:selected').val()},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                success: function(msg){
               	    alert(msg); window.location.href = "patientCheckpoint"}
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
                
                <li>
                    <a href="UserManage">
                        <i class="ti-user"></i>
                        <p>病人管理</p>
                    </a>
                </li>
                 <li>
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
               
           
				
            </ul>
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
                        <li class="dropdown">
                              <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <i class="ti-bell"></i>
                                    <p class="notification">4</p>
                                    <p>消息</p>
                                    <b class="caret"></b>
                              </a>
                              <ul class="dropdown-menu">
                                <li><a href="#">消息 1</a></li>
                                <li><a href="#">消息 2</a></li>
                                <li><a href="#">消息 3</a></li>
                                <li><a href="#">消息 4</a></li>
                                <li><a href="#">全部消息</a></li>
                              </ul>
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
                                <h4 class="title">病情设置列表</h4>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-striped">
                                    <thead>
                                    	<th>姓名</th>
                                    	<th>证件号</th>
                                    	<th>疾病选择</th>	
                                    </thead>
                                    
                                    <tbody>
                                    
                                    <% ArrayList<Patient> list = (ArrayList<Patient>)request.getAttribute("list"); %>
                                    <% HashMap<String, String> map = (HashMap<String, String>)request.getAttribute("map"); %>
                                    <% ArrayList<Templet> templetList = (ArrayList<Templet>)request.getAttribute("templetList"); %>
                                	<%
                                    	if(list != null){
	                                    	Iterator iter = list.iterator(); 
											while(iter.hasNext()) { 									
												Patient p = (Patient)iter.next();
									 %>
                                        <tr>
                                        	<td><%= p.getPatient_name()%></td>
                                        	<td><%= p.getIdCard()%></td>
                                        	<td>
                                                <select name="select" id="<%= p.getUsername()%>" class="xla_k">
                                                
                                                   <option value="选择品牌" <% if(map.get(p.getUsername()).equals("null")) { %> selected="selected" <% } %>>选择病情</option>
                                                   
                                                   <% 
                                                    if(templetList != null){
           	                                    	Iterator temIter = templetList.iterator(); 
        											while(temIter.hasNext()) { 									
        												Templet t = (Templet)temIter.next(); %>
                                                   <option value=<%= t.getTid()%> <% if(map.get(p.getUsername()).equals(t.getTemplet_name())){ %> selected="selected" <% } %>><%= t.getTemplet_name() %></option>
                                                   <% }} %>
                                                </select></td>
                                           <td><a href="patientChecklistDetail?username=<%= p.getUsername()%>"><button  type='button'>细化检查项</button></a></td>
                                           <td><input type="button" aria-hidden="true" class="close" value="√" onclick="edit('<%= p.getUsername() %>')"></td>
                                        </tr>
                                        
                                      <%}} %>
                                        	
                                        	     
                                    </tbody>
                                </table>
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
