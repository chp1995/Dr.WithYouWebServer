package androidServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import service.UserService;
import util.Token;

/**
 * Servlet implementation class AdLoginServlet
 */
@WebServlet("/adLogin")
public class AdLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 设置字符集编码
		request.setCharacterEncoding("UTF-8"); 
		
		// 获取用户名密码
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String token = Token.getToken();
		
		// 
		UserService service = new UserService();
		System.out.println(name + "   " + password);
		int id = service.adLogin(name, password, token);
		
		JSONObject json=new JSONObject();
		// 医生登陆成功
		if(id == 1)			
			json.put("result", "true");
		else
			json.put("result", "false");
		
		response.getWriter().write(json.toString());

	}

}
