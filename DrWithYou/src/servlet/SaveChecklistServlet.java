package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.ChecklistService;

/**
 * Servlet implementation class SaveChecklistServlet
 */
@WebServlet("/saveChecklist")
public class SaveChecklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveChecklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("id"));
		String itemname = request.getParameter("itemname");
		String checktime = request.getParameter("checktime");
		String description = request.getParameter("description");
		System.out.println("save " + id + "\t" + itemname + "\t" + checktime + "\t" + description);
		
		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		// 设置编码
		response.setCharacterEncoding("UTF-8");
		
		String message = new ChecklistService().saveChecklist(id, itemname, checktime, description);
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
