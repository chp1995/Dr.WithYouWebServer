package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TempletService;
import service.UserService;

/**
 * Servlet implementation class AddTempletServlet
 */
@WebServlet("/addTemplet")
public class AddTempletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTempletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		request.getRequestDispatcher("/addTemplet.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// token -> username
		String username = new UserService().getUsername(token);
		if(username == "" || username == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// 获取信息
		String templetname = request.getParameter("templetname");
		String suitable = request.getParameter("suitable");
		String description = request.getParameter("description");
		System.out.println("addtemplete\t" + templetname + "\t" + suitable + "\t" + description);
		
		// 新建模板
		response.setCharacterEncoding("UTF-8");
		String message = new TempletService().addTemplet(username, templetname, suitable, description);
		response.getWriter().write(message);
		
	}

}
