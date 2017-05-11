package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.TempletService;

/**
 * Servlet implementation class SaveTempletServlet
 */
@WebServlet("/saveTemplet")
public class SaveTempletServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveTempletServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		// 获取json
		int id = Integer.parseInt(request.getParameter("id"));
		String templetname = request.getParameter("templetname");
		String suitable = request.getParameter("suitable");
		String description = request.getParameter("description");
		String array = request.getParameter("array");
		System.out.println("save templet " + id + "\t" + templetname + "\t" + suitable + "\t" + description);
		System.out.println("checkbox array " + array);
		
		// 添加模板检查相关系
		TempletService service = new TempletService();
		service.deleteTempletCheck(id);         // 删除模板所有检查项
		JSONArray data = JSONArray.fromObject(array);
		for(int i = 0; i < data.size(); i ++){
		     String temp =  (String)data.get(i);
//		     System.out.println("temp = " + temp);
		     int index = Integer.parseInt(temp);
		     service.addTempletCheck(id, index);  // 添加模板和检查项关系	     
		}
		
		// 获取token
		String token = (String)request.getSession().getAttribute("token");
		if(token == "" || token == null)
			request.getRequestDispatcher("/login").forward(request, response);
		
		String message = service.saveTemplet(id, templetname, suitable, description);
		
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
