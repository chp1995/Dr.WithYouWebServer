/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.72
 * Generated at: 2017-04-19 05:29:01 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userManagement_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!doctype html>\r\n");
      out.write("<html lang=\"en\">\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta charset=\"utf-8\" />\r\n");
      out.write("\t<link rel=\"apple-touch-icon\" sizes=\"76x76\" href=\"assets/img/apple-icon.png\">\r\n");
      out.write("\t<link rel=\"icon\" type=\"image/png\" sizes=\"96x96\" href=\"assets/img/favicon.png\">\r\n");
      out.write("\t<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />\r\n");
      out.write("\r\n");
      out.write("\t<title>å»è·¯ç¸é</title>\r\n");
      out.write("\r\n");
      out.write("\t<meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0' name='viewport' />\r\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width\" />\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("    <!-- Bootstrap core CSS     -->\r\n");
      out.write("    <link href=\"assets/css/bootstrap.min.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("    <!-- Animation library for notifications   -->\r\n");
      out.write("    <link href=\"assets/css/animate.min.css\" rel=\"stylesheet\"/>\r\n");
      out.write("\r\n");
      out.write("    <!--  Paper Dashboard core CSS    -->\r\n");
      out.write("    <link href=\"assets/css/paper-dashboard.css\" rel=\"stylesheet\"/>\r\n");
      out.write("\r\n");
      out.write("    <!--  CSS for Demo Purpose, don't include it in your project     -->\r\n");
      out.write("    <link href=\"assets/css/demo.css\" rel=\"stylesheet\" />\r\n");
      out.write("\r\n");
      out.write("    <!--  Fonts and icons     -->\r\n");
      out.write("    <link href=\"http://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css\" rel=\"stylesheet\">\r\n");
      out.write("    <link href='https://fonts.googleapis.com/css?family=Muli:400,300' rel='stylesheet' type='text/css'>\r\n");
      out.write("    <link href=\"assets/css/themify-icons.css\" rel=\"stylesheet\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("<div class=\"wrapper\">\r\n");
      out.write("\t<div class=\"sidebar\" data-background-color=\"white\" data-active-color=\"danger\">\r\n");
      out.write("\r\n");
      out.write("    <!--\r\n");
      out.write("\t\tTip 1: you can change the color of the sidebar's background using: data-background-color=\"white | black\"\r\n");
      out.write("\t\tTip 2: you can change the color of the active button using the data-active-color=\"primary | info | success | warning | danger\"\r\n");
      out.write("\t-->\r\n");
      out.write("\r\n");
      out.write("    \t<div class=\"sidebar-wrapper\">\r\n");
      out.write("            <div class=\"logo\">\r\n");
      out.write("                <a class=\"simple-text\">\r\n");
      out.write("                    å»è·¯ç¸é\r\n");
      out.write("                </a>\r\n");
      out.write("            </div>\r\n");
      out.write("\r\n");
      out.write("            <ul class=\"nav\">\r\n");
      out.write("                <li class=\"active\">\r\n");
      out.write("                    <a href=\"bingren.html\">\r\n");
      out.write("                        <i class=\"ti-user\"></i>\r\n");
      out.write("                        <p>çäººç®¡ç</p>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                 <li >\r\n");
      out.write("                    <a href=\"bingqing.html\">\r\n");
      out.write("                        <i class=\"ti-view-list-alt\"></i>\r\n");
      out.write("                        <p>çæç®¡ç</p>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"moban.html\">\r\n");
      out.write("                        <i class=\"ti-panel\"></i>\r\n");
      out.write("                        <p>æ¨¡çç®¡ç</p>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("                  <li>\r\n");
      out.write("                    <a href=\"jianchaxiang.html\">\r\n");
      out.write("                        <i class=\"ti-pencil-alt2\"></i>\r\n");
      out.write("                        <p>æ£æ¥é¡¹è®¾ç½®</p>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("               \r\n");
      out.write("                <li>\r\n");
      out.write("                    <a href=\"notifications.html\">\r\n");
      out.write("                        <i class=\"ti-bell\"></i>\r\n");
      out.write("                        <p>æçæ¶æ¯</p>\r\n");
      out.write("                    </a>\r\n");
      out.write("                </li>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("        \r\n");
      out.write("    \t</div>\r\n");
      out.write("    </div>\r\n");
      out.write("\r\n");
      out.write("    <div class=\"main-panel\">\r\n");
      out.write("\t\t<nav class=\"navbar navbar-default\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"navbar-header\">\r\n");
      out.write("                    <button type=\"button\" class=\"navbar-toggle\">\r\n");
      out.write("                        <span class=\"sr-only\">Toggle navigation</span>\r\n");
      out.write("                        <span class=\"icon-bar bar1\"></span>\r\n");
      out.write("                        <span class=\"icon-bar bar2\"></span>\r\n");
      out.write("                        <span class=\"icon-bar bar3\"></span>\r\n");
      out.write("                    </button>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"collapse navbar-collapse\">\r\n");
      out.write("                    <ul class=\"nav navbar-nav navbar-right\">\r\n");
      out.write("\r\n");
      out.write("                        <li>\r\n");
      out.write("                            <a href=\"#\">\r\n");
      out.write("                                <i class=\"ti-settings\"></i>\r\n");
      out.write("                                <p>è®¾ç½®</p>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("                        <li class=\"dropdown\">\r\n");
      out.write("                              <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\r\n");
      out.write("                                    <i class=\"ti-bell\"></i>\r\n");
      out.write("                                    <p class=\"notification\">4</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<p>æ¶æ¯</p>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<b class=\"caret\"></b>\r\n");
      out.write("                              </a>\r\n");
      out.write("                              <ul class=\"dropdown-menu\">\r\n");
      out.write("                                <li><a href=\"#\">æ¶æ¯ 1</a></li>\r\n");
      out.write("                                <li><a href=\"#\">æ¶æ¯ 2</a></li>\r\n");
      out.write("                                <li><a href=\"#\">æ¶æ¯ 3</a></li>\r\n");
      out.write("                                <li><a href=\"#\">æ¶æ¯ 4</a></li>\r\n");
      out.write("                                <li><a href=\"#\">å¨é¨æ¶æ¯</a></li>\r\n");
      out.write("                              </ul>\r\n");
      out.write("                        </li>\r\n");
      out.write("                         <li>\r\n");
      out.write("                            <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">\r\n");
      out.write("                                <i class=\"ti-panel\"></i>\r\n");
      out.write("                                <p>éåº</p>\r\n");
      out.write("                            </a>\r\n");
      out.write("                        </li>\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("                    </ul>\r\n");
      out.write("\r\n");
      out.write("                </div>\r\n");
      out.write("            </div>\r\n");
      out.write("        </nav>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("        <div class=\"content\">\r\n");
      out.write("            <div class=\"container-fluid\">\r\n");
      out.write("                <div class=\"row\">\r\n");
      out.write("                                             \r\n");
      out.write("                        <div class=\"card\">\r\n");
      out.write("                            <div class=\"header\">\r\n");
      out.write("                                <h4 class=\"title\">çäººç®¡çåè¡¨</h4> <br />\r\n");
      out.write("                               <div class=\"text-right\">\r\n");
      out.write("                                <a href=\"tianjiabingren.html\">\r\n");
      out.write("                                        <button   class=\"btn btn-info btn-fill btn-wd\" >æ·»å çäºº</button>\r\n");
      out.write("                                    </a>\r\n");
      out.write("                                    </div>\r\n");
      out.write("                            </div>\r\n");
      out.write("                            <div class=\"content\">\r\n");
      out.write("\r\n");
      out.write("                                <ul class=\"list-unstyled team-members\">\r\n");
      out.write("                                            <li>\r\n");
      out.write("                                                 <button type=\"button\" aria-hidden=\"true\" class=\"close\">Ã</button>\r\n");
      out.write("\r\n");
      out.write("                                                <div class=\"row\">\r\n");
      out.write("                                                    <div class=\"col-xs-3\">  \r\n");
      out.write("                                                         <a href=\"data.html\">\r\n");
      out.write("                                                        <div class=\"avatar\">\r\n");
      out.write("                                                            <img src=\"assets/img/faces/face-0.jpg\" alt=\"Circle Image\" class=\"img-circle img-no-padding img-responsive\">\r\n");
      out.write("                                                        </div></a>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                    <div class=\"col-xs-6\">\r\n");
      out.write("                                                        éç¿ è± - å¥³\r\n");
      out.write("                                                        <br />\r\n");
      out.write("                                                        <span><small>èç³»æ¹å¼ï¼123456</small></span><br />\r\n");
      out.write("                                                         <span><small>è¯ä»¶å·ï¼123456</small></span><br />\r\n");
      out.write("                                                        <span><small>çææè¿°ï¼ååååååååååååå</small></span>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                   \r\n");
      out.write("                                                    <div class=\"col-xs-3 text-right\"></br>\r\n");
      out.write("\r\n");
      out.write("                                                       <a href=\"aaaaaaaaaaaaa.html\">\r\n");
      out.write("                                                        <btn class=\"btn btn-sm btn-success btn-icon\"><i class=\"fa fa-envelope\"></i></btn></a>\r\n");
      out.write("                                                    </div></a>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </li>\r\n");
      out.write("\r\n");
      out.write("                                            <li>\r\n");
      out.write("                                                 <button type=\"button\" aria-hidden=\"true\" class=\"close\">Ã</button>\r\n");
      out.write("                                                <div class=\"row\">\r\n");
      out.write("                                                    <div class=\"col-xs-3\">\r\n");
      out.write("                                                        <div class=\"avatar\" >\r\n");
      out.write("                                                            <img src=\"assets/img/faces/face-1.jpg\" alt=\"Circle Image\" class=\"img-circle img-no-padding img-responsive\" >\r\n");
      out.write("                                                        </div>\r\n");
      out.write("                                                      \r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                    <div class=\"col-xs-6\">\r\n");
      out.write("                                                         çå¤§å - ç·\r\n");
      out.write("                                                        <br />\r\n");
      out.write("                                                     <span><small>èç³»æ¹å¼ï¼123456</small></span><br />\r\n");
      out.write("                                                         <span><small>è¯ä»¶å·ï¼123456</small></span><br />\r\n");
      out.write("                                                        <span><small>çææè¿°ï¼ååååååååååååå</small></span>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                                    <div class=\"col-xs-3 text-right\"></br>\r\n");
      out.write("                                                        <btn class=\"btn btn-sm btn-success btn-icon\"><i class=\"fa fa-envelope\"></i></btn>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </li>\r\n");
      out.write("                                            <li>\r\n");
      out.write("                                                 <button type=\"button\" aria-hidden=\"true\" class=\"close\">Ã</button>\r\n");
      out.write("                                                <div class=\"row\">\r\n");
      out.write("                                                    <div class=\"col-xs-3\">\r\n");
      out.write("                                                        <div class=\"avatar\">\r\n");
      out.write("                                                            <img src=\"assets/img/faces/face-3.jpg\" alt=\"Circle Image\" class=\"img-circle img-no-padding img-responsive\">\r\n");
      out.write("                                                        </div>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                    <div class=\"col-xs-6\">\r\n");
      out.write("                                                        æå°å° - å¥³\r\n");
      out.write("                                                        <br />\r\n");
      out.write("                                                        <span><small>èç³»æ¹å¼ï¼123456</small></span><br />\r\n");
      out.write("                                                         <span><small>è¯ä»¶å·ï¼123456</small></span><br />\r\n");
      out.write("                                                        <span><small>çææè¿°ï¼ååååååååååååå</small></span>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("\r\n");
      out.write("                                                    <div class=\"col-xs-3 text-right\"></br>\r\n");
      out.write("                                                        <btn class=\"btn btn-sm btn-success btn-icon\"><i class=\"fa fa-envelope\"></i></btn>\r\n");
      out.write("                                                    </div>\r\n");
      out.write("                                                </div>\r\n");
      out.write("                                            </li>\r\n");
      out.write("                                        </ul>\r\n");
      out.write("                                      </div>\r\n");
      out.write("                                   </div>\r\n");
      out.write("                                </div>\r\n");
      out.write("                           </div>\r\n");
      out.write("\r\n");
      out.write("               \r\n");
      out.write("      \r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("\r\n");
      out.write("    <!--   Core JS Files   -->\r\n");
      out.write("    <script src=\"assets/js/jquery-1.10.2.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\t<script src=\"assets/js/bootstrap.min.js\" type=\"text/javascript\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<!--  Checkbox, Radio & Switch Plugins -->\r\n");
      out.write("\t<script src=\"assets/js/bootstrap-checkbox-radio.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<!--  Charts Plugin -->\r\n");
      out.write("\t<script src=\"assets/js/chartist.min.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!--  Notifications Plugin    -->\r\n");
      out.write("    <script src=\"assets/js/bootstrap-notify.js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!--  Google Maps Plugin    -->\r\n");
      out.write("    <script type=\"text/javascript\" src=\"https://maps.googleapis.com/maps/api/js\"></script>\r\n");
      out.write("\r\n");
      out.write("    <!-- Paper Dashboard Core javascript and methods for Demo purpose -->\r\n");
      out.write("\t<script src=\"assets/js/paper-dashboard.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t<!-- Paper Dashboard DEMO methods, don't include it in your project! -->\r\n");
      out.write("\t<script src=\"assets/js/demo.js\"></script>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("        $(document).ready(function(){\r\n");
      out.write("\r\n");
      out.write("           \r\n");
      out.write("\r\n");
      out.write("            $.notify({\r\n");
      out.write("                icon: 'ti-gift',\r\n");
      out.write("                message: \"æ¬¢è¿æ¨åå° <b>å»è·¯ç¸éï¼å»çç«¯</b>\"\r\n");
      out.write("\r\n");
      out.write("            },{\r\n");
      out.write("                type: 'success',\r\n");
      out.write("                timer: 4000\r\n");
      out.write("            });\r\n");
      out.write("\r\n");
      out.write("        });\r\n");
      out.write("    </script>\r\n");
      out.write("\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}