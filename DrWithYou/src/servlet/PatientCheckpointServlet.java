package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import model.Templet;
import service.PatientService;
import service.TempletService;
import service.UserService;

/**
 * Servlet implementation class PatientCheckpointServlet
 */
@WebServlet("/patientCheckpoint")
public class PatientCheckpointServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PatientCheckpointServlet() {
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
		
		// 获取病人list
		PatientService service = new PatientService();
		ArrayList<Patient> list = service.getDoctorPatients(username);
		
		// 获取模板list
		TempletService temService = new TempletService();
		ArrayList<Templet> templetList = temService.getTempletList(username);
		
		// 获取病人对应模板
		HashMap<String, String> map = temService.getUserTemplet(list);
		
		request.setAttribute("list", list);
		request.setAttribute("templetList", templetList);
		request.setAttribute("map", map);
		
		request.getRequestDispatcher("/patientCheckpoint.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
