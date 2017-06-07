package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import service.UserService;
import util.AppException;
import util.Token;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 设置编码
		request.setCharacterEncoding("UTF-8"); 
		
		// 获取用户信息
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("login\t" + name + "\t" + password);
		String token = Token.getToken();
		
		// 
		UserService service = new UserService();
		System.out.println(name + "   " + password);
		int id = service.login(name, password, token);
		
		String message = "";
		// 处理
		if(id == 1){
			// session保存token
			HttpSession session = request.getSession();
			session.setAttribute("token", token);
			
			// 重定向
			message = "登录成功！";
		}
		else
			message = "登录失败！";
		
		System.out.println("login message = " + message);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(message);
		
	}

}
