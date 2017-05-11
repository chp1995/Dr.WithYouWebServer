package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Checklist;
import model.Templet;
import service.ChecklistService;
import service.TempletService;
import service.UserService;

/**
 * Servlet implementation class EditTemplet
 */
@WebServlet("/editTemplet")
public class EditTempletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTempletServlet() {
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
		
		// token -> username
		String username = new UserService().getUsername(token);
		if(username == "" || username == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// 获取id
		int tid = Integer.parseInt(request.getParameter("id"));	
		System.out.println("edit templet tid = " + tid);
		
		// 获取模板信息
		TempletService service = new TempletService();
		Templet templet = service.getTemplet(tid);
		request.setAttribute("templet", templet);
		
		// 获取检查项信息
		ChecklistService chservice = new ChecklistService();
		ArrayList<Checklist> list = chservice.getCheckList(username);
		request.setAttribute("list", list);
		
		// 获取模板检查项
		ArrayList<Integer> checklist = service.getTempletCheck(tid);
		request.setAttribute("checklist", checklist);
		
		request.getRequestDispatcher("/editTemplet.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
