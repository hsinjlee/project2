package Team76.Controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class GradeControllerServlet
 */
@WebServlet("/GradeControllerServlet")
public class GradeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private GradeDbUtil gradeDbUtil;
	
	@Resource(name="jdbc/ser516p2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
			gradeDbUtil = new GradeDbUtil(dataSource);
		}
		catch (Exception exception) {
			throw new ServletException(exception);
		}
	}

	
	protected void doGet
	(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			
//			listGrades(request, response);
			List <Grade> grades = gradeDbUtil.getGrades();
			
			
			request.setAttribute("GRADE_LIST", grades);
			
			
			RequestDispatcher dispatcher 
			= request.getRequestDispatcher("/list-grades.jsp");
			dispatcher.forward(request, response);
			
		}
		catch (Exception exception) {
			throw new ServletException(exception);
		}
		
	}

//	private void listGrades
//	(HttpServletRequest request, HttpServletResponse response) 
//			throws Exception {
//		
//		List <Grade> grades = gradeDbUtil.getGrades();
//		
//		
//		request.setAttribute("GRADE_LIST", grades);
//		
//		
//		RequestDispatcher dispatcher 
//		= request.getRequestDispatcher("/list-grades.jsp");
//		dispatcher.forward(request, response);
//		
//	}

}
