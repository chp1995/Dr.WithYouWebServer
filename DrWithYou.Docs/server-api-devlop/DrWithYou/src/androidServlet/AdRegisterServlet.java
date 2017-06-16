package androidServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

<<<<<<< HEAD
=======
import net.sf.json.JSONObject;

>>>>>>> master
import service.UserService;

/**
 * Servlet implementation class AdRegisterServlet
 */
@WebServlet("/adRegister")
public class AdRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取用户名密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 
		UserService service = new UserService();
		String message = service.register(username, password);
		
		// 返回提示
<<<<<<< HEAD
		request.setAttribute("message", message);
		request.getRequestDispatcher("/register.jsp").forward(request, response);
=======
		JSONObject json=new JSONObject();
		json.put("message", message);
		response.getWriter().write(json.toString());
>>>>>>> master
	}

}
