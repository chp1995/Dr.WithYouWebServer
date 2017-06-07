<!doctype html>
<%@page import="java.util.*"%>
<%@page import="model.Message"%>
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
    <link href="/css/font-awesome.min.css" rel="stylesheet">
    <link href='css/css.css' rel='stylesheet' type='text/css'>
    <link href="assets/css/themify-icons.css" rel="stylesheet">

    <style type="text/css">*{ margin:0; padding:0; border:0; outline:0; font-family:"Microsoft YaHei";}body{ font-size:12px;}#box{ width:200px; height:200px; background:orange; position:absolute; left:0; top:0;} .f-cb{ zoom:1;}.f-cb:after,.f-cb:before{ clear:both; content:""; display:table; height:0; }ul{ list-style:none;}.m{ width:700px; padding:20px; border:1px solid  #ccc; margin:50px auto; overflow:hidden;}.m1 { position:relative; }.m1:after,.m1:before { clear:both; content:""; display:table; height:0; margin-bottom:20px;}.m1 dt a{ width:60px; height:60px; background:#CCC; display:inline-block; text-align:center; line-height:60px; }.m1 dd{ max-width:274px; border:1px solid #ccc; border-radius:3px; padding:10px ;  background:#ccc; box-sizing:border-box; position:relative;}.m1 dd:before{ position:absolute; bottom:5px;  content:""; border-width:10px; border-style:solid; }.m1.he dd:before,.m1.vhe dd:before{ left:-20px; border-color:transparent #ccc  transparent transparent;   }.m1.me dd:before,.m1.vme dd:before{ right:-20px; border-color:transparent  transparent transparent  #ccc; }.m1.he dt { float:left; }.m1.he dd{ margin-left:20px; margin-top:20px ;float:left; }.m1.me dt { float:right; margin-left:20px;}.m1.me dd { float:right; margin-top:20px;}.m1.he dt a{ float:left; }.m1.he dt span{ display:block; float:left;  margin-right:-200px; color:#666; margin-left:20px;}.m1.me dt a{ float:right; }.m1.me dt span{ display:block; float:right;  margin-left:-200px; color:#666; margin-right:20px;}.m1.vhe dt a{ position:absolute; bottom:0; }.m1.vhe dt span{ padding-left:80px; }.m1.vhe dd{ margin-left:80px; margin-top:5px;} .m1.vme dt a{ position:absolute; bottom:0; right:0;}.m1.vme dt span{ padding-right:80px; text-align:right; display:block; }.m1.vme dd{ margin-right:80px; margin-top:5px; float:right;}a:link,a:visited {color: black;text-decoration: none;}a:hover,a:active {text-decoration: underline;}.m02{ width:318px; border:1px solid #999; margin-left:20px;  }.m2 li{ clear:both; display:table; padding-left:20px;}.m2{ width:168px;  }.m2 li a{float:left; max-width:168px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis; }.m2 li span{ float:left; padding-left:10px; margin-right:-100px;}</style><script src="js/jquery-1.11.1.js" type="text/javascript"></script>
    
    <script type="text/javascript">
		function send(pausr){	
					
		    $.ajax({
                 type: "post",
                 url: "sendMessage",
                 data: {pausr: pausr,
                	    message: $("#content").val()},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                 success: function(msg){
                	    alert(msg); location.reload();}
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
                      

        <% ArrayList<Message> list =  (ArrayList<Message>)request.getAttribute("list"); %>
        <% String docusr = (String)request.getAttribute("docusr"); %>
        <% String pausr = (String)request.getAttribute("pausr"); %>
        <div class="m">
        
        <%
           	if(list != null){
           		Iterator<Message> iter = list.iterator(); 
           		while(iter.hasNext()) {
           			Message message = iter.next();
           		
           	
        %>          
			 									
							    
               <%if(message.getSender().equals(docusr)) { %> <dl class="vme m1"> <% }else{ %><dl class="he m1"> <%} %>
               	<dt><a><%= message.getSender()%></a>
               	<span><%= message.getTime() %></span></dt>        
                <dd><%= message.getMessage() %></dd>    
               </dl>   
               
         <% }} %>	
              

                <textarea rows="5" class="form-control border-input" id="content"></textarea>    
                <br>
                <input type="button" style = " float:right" value = "发送" class="btn btn-info btn-fill btn-wd" onclick="send('<%= pausr%>')">    
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
