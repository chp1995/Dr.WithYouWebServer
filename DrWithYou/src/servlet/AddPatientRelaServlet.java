package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.PatientService;

/**
 * Servlet implementation class AddPatientRelaServlet
 */
@WebServlet("/AddPatient")
public class AddPatientRelaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPatientRelaServlet() {
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
		String patientusr = request.getParameter("username");		
		System.out.println("patientusr = " + patientusr);
		System.out.println("token = " + token);
		
		PatientService service = new PatientService();
		boolean result = service.addPatient(token, patientusr);
		String message = "";
		if(result)
			message = "添加成功";
		else
			message = "添加失败";
		System.out.println("message = " + message);
		
		// 设置编码
		response.setCharacterEncoding("UTF-8");
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
