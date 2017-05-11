package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import service.ChecklistService;

/**
 * Servlet implementation class SavePatientChecklistServlet
 */
@WebServlet("/savePatientChecklist")
public class SavePatientChecklistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePatientChecklistServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setCharacterEncoding("UTF-8");
		String message = "修改成功！";
		
		String array = request.getParameter("array");
		String patientName = request.getParameter("username");
		
		// 删除病人的所有检查项
		ChecklistService service = new ChecklistService();
		int deleresult = service.deletePatientChecklist(patientName);
//		if(deleresult == 0){
//			message = "修改失败！";
//			response.getWriter().write(message);
//			return;
//		}
		
		// 保存病人和检查项关系
		JSONArray data = JSONArray.fromObject(array);
		for(int i = 0; i < data.size(); i ++){
		     String temp =  (String)data.get(i);
		     int index = Integer.parseInt(temp);
		     System.out.println("add patient checklist\t" + patientName + "\t" + index);
		     int result = service.addPatientChecklist(patientName, index); 
		     if(result != 1){
		    	 message = "修改失败！";
		    	 break;
		     }
		}
		
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
