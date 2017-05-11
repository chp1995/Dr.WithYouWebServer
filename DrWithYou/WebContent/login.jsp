<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
    <meta charset="utf-8" />
    <!-- <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">  -->
    <title>医路相随－医生端</title>
        
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Login and Registration Form with HTML5 and CSS3" />
    <meta name="keywords" content="html5, css3, form, switch, animation, :target, pseudo-class" />
    <meta name="author" content="Codrops" />
    <link rel="shortcut icon" href="../favicon.ico">
    <link rel="stylesheet" type="text/css" href="css/demo.css" />
    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
    
     <script type="text/javascript">
		function login(){					
			 $.ajax({
                 type: "post",
                 url: "login",
                 data: {username: $("#username").val(),
             	        password: $("#password").val()},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                 success: function(msg){
                	 if(msg == "登录失败！")
                		 alert(msg); 
                	 else
                		 window.location.href = "UserManage";}
                }); 
		}
	
	</script>       
</head>
<body>
<div class="container">
    <!-- Codrops top bar -->
    <div class="codrops-top">

        <div class="clr"></div>
    </div><!--/ Codrops top bar -->
    <header>
        <h1>医路相随 <span>医生端</span></h1>

    </header>
    <section>
        <div id="container_demo" >
            <!-- hidden anchor to stop jump http://www.css3create.com/Astuce-Empecher-le-scroll-avec-l-utilisation-de-target#wrap4  -->
            <a class="hiddenanchor" id="toregister"></a>
            <a class="hiddenanchor" id="tologin"></a>
            <div id="wrapper">
                <div id="login" class="animate form">
                        <h1>Log in</h1>
                        <p>
                            <label data-icon="u" > 用户名 </label>
                            <input id="username" required="required" type="text" placeholder="请输入您的用户名"/>
                        </p>
                        <p>
                            <label data-icon="p"> 密码 </label>
                            <input id="password" required="required" type="password" placeholder="********" />
                        </p>
                        
                        <p class="keeplogin">
                            <input type="checkbox" id="loginkeeping"/>
                            <label for="loginkeeping">Keep me logged in</label>
                        </p>

                        <p><input type="button" value="登录" onclick="login()"/> </p>
                </div>
            </div>
        </div>
    </section>
</div>
</body>
</html>