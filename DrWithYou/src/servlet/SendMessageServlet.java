package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MessageService;
import service.UserService;

/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/sendMessage")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendMessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		// 获取用户名
		String username = new UserService().getUsername(token);
		System.out.println("username = " + username);
		if(username == "")
			request.getRequestDispatcher("/login").forward(request, response);
		
		// 获取病人用户名
		String pausr = request.getParameter("pausr");
		// 获取内容
		String message = request.getParameter("message");
				
		MessageService service = new MessageService();
		int result = service.sendMessage(username, pausr, message);
		String alert = "";
		if(result == 0)
			alert = "发送失败";
		else
			alert = "发送成功";
		
		// 设置编码
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(alert);
	}

}
