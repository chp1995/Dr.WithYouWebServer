<!doctype html>
<%@page import="java.util.*"%>
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
    
    <!-- flotr2 - js lib for line chart -->

    
    <style type="text/css">
        #containerplot {
         height:500px;
         width:800px;
         margin: 8px auto}        
    </style>
    
    <script type="text/javascript">
		function add(usr){		
			
			//alert($('#select option:selected').val());
			
			// 检查date前后时间
			//var startDate = document.getElementById("startDate").value;
			//var endDate = document.getElementById("endDate").value;
			//if(startDate >= endDate || startDate == null || endDate == null)
			//{
			//	alert("请重新选择日期！");
			//	return;
			//}
			//alert(startDate + " " + endDate + " " + (startDate<endDate));
			
			
		    $.ajax({
                 type: "post",
                 url: "data",
                 data: {usr:usr,
                	   // startDate:startDate,
                	   // endDate:endDate,
                	    cid: $('#select option:selected').val()},//提交表单，相当于CheckCorpID.ashx?ID=XXX
                 contentType: "application/x-www-form-urlencoded; charset=utf-8", 
                 success: function(str){
                	 		//alert(json);
                	 	   var data = [];
                	       var json = eval('(' + str + ')'); 
                	       var maxX = 0, minY = 99999999, maxY = 0;
                	       for(var i = 0; i < json.length; i ++){ 
                	    	   //alert(json[i].value); 
                	    	   //alert(json[i].day); 
                	    	   var x = parseInt(json[i].day);
                	    	   var y = parseFloat(json[i].value);
                	    	   data.push([x, y]);
                	    	   if(x > maxX)
                	    		   maxX = x;
                	    	   if(y > maxY)
                	    		   maxY = y;
                	    	   if(y < minY)
                	    		   minY = y;
                	       } 
                	 		
                	       //alert(data);
                	       drawChart(data, maxX, minY - 10, maxY + 10);
                	    }
                }); 
		}
		
		function drawChart(array, maxX, minY, maxY){
			
			  // x轴准备
			  var xAxisArr = [];
			  for(var i = 1; i < maxX; i ++)
		      {
				  xAxisArr.push([i, "Day " + i]);		  
		      }
			  
			  var yAxisArr = [];
			  var diff = (maxY - minY) / 10;
			  for(var i = 0; i < 10; i ++)
			  {
				  yAxisArr.push([i+1, minY + i*diff + ""]);
			  }
			  //alert(yAxisArr);			  
			  
			  var data = [{ data : array, lines : { show : true }, points : { show : true }},];
			  
			  var container = document.getElementById("containerplot");
				
			  var option = {
			             colors: ['#00A8F0', '#C0D800', '#CB4B4B', '#4DA74D', '#9440ED'],  //线条的颜色
			             ieBackgroundColor:'#3ec5ff',                    //选中时的背景颜色
			             title:'用户周提交数据',               //标题
			             subtitle:'',                           //子标题
			             shadowSize:5,                                 //线条阴影
			             defaultType:'lines',                           //图表类型,可选值:bars,bubbles,candles,gantt,lines,markers,pie,points,radar
			             HtmlText:false,                                //是使用html或者canvas显示 true:使用html  false:使用canvas
			             fontColor:'#ff3ec5',                           //字体颜色
			             fontSize:7.5,                                  //字体大小
			             resolution:1,                                  //分辨率 数字越大越模糊
			             parseFloat:true,                               //是否将数据转化为浮点型
			           xaxis: {
			               //ticks:[[1,"一"],[2,"二"],[3,"三"],[4,"四"],[5,"五"],[6,"六"],[7,"七"]], // 自定义X轴
			               ticks: xAxisArr,
			               minorTicks: null,
			               showLabels:true,                             // 是否显示X轴刻度
			               showMinorLabels:false,
			               labelsAngle:0,                              //x轴文字倾斜角度
			               title:'日期',                                 //x轴标题
			               titleAngle:0,                                //x轴标题倾斜角度
			               noTicks:maxX,                                   //当使用自动增长时,x轴刻度的个数
			               minorTickFreq:null,                             //
			               tickFormatter: Flotr.defaultTickFormatter,   //刻度的格式化方式
			               tickDecimals:0,                              //刻度小数点后的位数
			               min:null,                                    //刻度最小值  X轴起点的值
			               max:null,                                    //刻度最大值
			               autoscale:true,
			               autoscaleMargin:0,
			               color:null,                             //x轴刻度的颜色
			               mode:'normal',
			               timeFormat:null,                            
			               timeMode:'UTC',                               //For UTC time ('local' for local time).
			               timeUnit:'year',                             //时间单位 (millisecond, second, minute, hour, day, month, year) 
			               scaling:'linear',                            //linear or logarithmic
			               base:Math.E,
			               titleAlign:'center',                         //标题对齐方式
			               margin:true
			           }, 
			           x2axis:{ 
			           },
			           yaxis:{
			                  //// => Y轴配置与X轴类似
			                //ticks: [ [-10,"-10"], [0,"0"], [10,"10"], [20,"20"], [30,"30"],[40,"40"] ],  
			                ticks: yAxisArr,
			                minorTicks: null,      // => format: either [1, 3] or [[1, 'a'], 3]
			                showLabels: true,      // => setting to true will show the axis ticks labels, hide otherwise
			                showMinorLabels: false,// => true to show the axis minor ticks labels, false to hide
			                labelsAngle: 15,        // => labels' angle, in degrees
			                title: '数据',           // => axis title
			                titleAngle: 90,        // => axis title's angle, in degrees
			                noTicks: maxY,            // => number of ticks for automagically generated ticks
			                minorTickFreq: null,   // => number of minor ticks between major ticks for autogenerated ticks
			                tickFormatter: Flotr.defaultTickFormatter, // => fn: number, Object -> string
			                tickDecimals: 'no',    // => no. of decimals, null means auto
			                min: null,             // => min. value to show, null means set automatically
			                max: null,             // => max. value to show, null means set automatically
			                autoscale: false,      // => Turns autoscaling on with true
			                autoscaleMargin: 0,    // => margin in % to add if auto-setting min/max
			                color: null,           // => The color of the ticks
			                scaling: 'linear',     // => Scaling, can be 'linear' or 'logarithmic'
			                base: Math.E,
			                titleAlign: 'center',
			                margin: true           // => Turn off margins with false
			           },
			           y2axis:{

			           },
			           grid: {
			                  color: '#545454',      // => 表格外边框和标题以及所有刻度的颜色
			                  backgroundColor: null, // => 表格背景颜色
			                  backgroundImage: null, // => 表格背景图片
			                  watermarkAlpha: 0.4,   // => 水印透明度
			                  tickColor: '#DDDDDD',  // => 表格内部线条的颜色
			                  labelMargin: 1,        // => margin in pixels
			                  verticalLines: true,   // => 表格内部是否显示垂直线条
			                  minorVerticalLines: null, // => whether to show gridlines for minor ticks in vertical dir.
			                  horizontalLines: true, // => 表格内部是否显示水平线条
			                  minorHorizontalLines: null, // => whether to show gridlines for minor ticks in horizontal dir.
			                  outlineWidth: 1,       // => 表格外边框的粗细
			                  outline : 'nsew',      // => 超出显示范围后的显示方式
			                  circular: false        // => 是否以环形的方式显示
			           },
			           mouse:{
			                  track: true,          // => 为true时,当鼠标移动到每个折点时,会显示折点的坐标
			                  trackAll: true,       // => 为true时,当鼠标在线条上移动时,显示所在点的坐标
			                  position: 'se',        // => 鼠标事件显示数据的位置 (default south-east)
			                  relative: false,       // => 当为true时,鼠标移动时,即使不在线条上,也会显示相应点的数据
			                  trackFormatter: Flotr.defaultTrackFormatter, // => formats the values in the value box
			                  margin: 5,             // => margin in pixels of the valuebox
			                  lineColor: '#FF3F19',  // => 鼠标移动到线条上时,点的颜色
			                  trackDecimals: 0,      // => 数值小数点后的位数
			                  sensibility: 2,        // => 值越小,鼠标事件越精确
			                  trackY: true,          // => whether or not to track the mouse in the y axis
			                  radius: 3,             // => radius of the track point
			                  fillColor: null,       // => color to fill our select bar with only applies to bar and similar graphs (only bars for now)
			                  fillOpacity: 0.4       // => o
			           }
			        };
			      
			     // Draw Graph
			     Flotr.draw(container, data, option);
			
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
                <a href="#" class="simple-text">
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
                    <a class="navbar-brand" >用户信息</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav navbar-right">
                    <!-- 
                        <li>
                            <a href="xiugaixinxi.html">
                                <i class="ti-settings"></i>
                                <p>修改信息</p>
                            </a>
                        </li>
                         <li>
                            <a href="xiugaitouxiang.html">
                                <i class="ti-panel"></i>
                                <p>修改头像</p>
                            </a>
                        </li>
                         <li>
                            <a href="xiugaimima.html">
                                <i class="ti-bell"></i>
                                <p>修改密码</p>
                            </a>
                        </li>  
                         -->
                         <li>
                            <a href="logout">
                                <p>退出</p>
                            </a>
                        </li>
                    </ul>

                </div>
            </div>
        </nav>

        <br><br>
        <!--  
         <div class="col-md-4">
           <div class="form-group">
                <label>开始时间</label>
                  <input type="date" id="startDate" class="form-control border-input">
           </div>
         </div>
          <div class="col-md-4">
               <div class="form-group">
                   <label>结束时间</label>
                       <input type="date" id="endDate" class="form-control border-input">
                </div>
          </div> -->
          
           
          <% Map map = (Map)request.getAttribute("map"); %>

           <div class="col-md-4">
                  <div class="form-group">
                      <label>检查项</label>
                 <select id="select" class="form-control border-input" style="position:absolute；left：400px">
                 
                 <% 

                    Set set = map.keySet();
                    Iterator<Integer> iter = set.iterator();  
					
					while (iter.hasNext()) {  
						int cid = iter.next();
				%>		
				    <option value ="<%= cid %>"><%= map.get(cid) %></option>
				
				<%} %>
                  </select>
                </div>
           </div>
           
           <% String usr = (String)request.getAttribute("usr"); %>
          
           <button style="margin:1px 0 0 650px" class="btn btn-info btn-fill btn-wd" onclick="add('<%= usr%>')">确认</button>
          
          
          </br></br> 
         
                                                                
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="header">

                                <h4 class="title">病人数据</h4>
                                <p class="category">一周变化</p>
                            </div>
                            <div id="containerplot"></div>
                        </div>
                    </div>
                </div>
            </div>
 
</body>

    <!--   Core JS Files   -->
    <script src="assets/js/jquery-1.10.2.js" type="text/javascript"></script>
	<script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
	
	<script src="assets/js/flotr2.js"></script>
    <script src="assets/js/flotr2.min.js"></script>

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
