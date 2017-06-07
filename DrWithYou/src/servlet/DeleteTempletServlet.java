package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChecklistService;
import service.TempletService;

/**
 * Servlet implementation class DeleteTempletServlet
 */
@WebServlet("/deleteTemplet")
public class DeleteTempletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTempletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		int tid = Integer.parseInt(request.getParameter("id"));
		System.out.println("delete tid = " + tid);
		
		response.setCharacterEncoding("UTF-8");
	
		// 删除模板
		// 1. 删除templet中模板
		// 2. 删除user和templet对应
		// 3. 删除templet和checkpoint对应
		TempletService service = new TempletService();
		service.deletePatientTemplet(tid);             // 删除所有该模板和病人关系
		service.deleteTempletCheck(tid);
		String message = service.deleteTemplet(tid);   // 删除模板
		response.getWriter().write(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
