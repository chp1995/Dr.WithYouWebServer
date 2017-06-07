package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Message;
import service.MessageService;
import service.UserService;

/**
 * Servlet implementation class MessageServlet
 */
@WebServlet("/message")
public class MessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// token -> username
		String username = new UserService().getUsername(token);  // 医生用户名
		if(username == "" || username == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// 获取病人应户名
		String pausr = request.getParameter("pa");  // 病人用户名
		System.out.println("message patient usr = " + pausr);
		
		// 获取对话列表（按时间顺序）
		MessageService service = new MessageService();
		ArrayList<Message> list = service.getMessages(username, pausr);
		
		request.setAttribute("list", list);
		request.setAttribute("pausr", pausr);
		request.setAttribute("docusr", username);
		request.getRequestDispatcher("/message.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
