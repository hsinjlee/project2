package edu.asupoly.ser516.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.asupoly.ser516.model.CourseDAOBean;
import edu.asupoly.ser516.model.CourseVO;
import edu.asupoly.ser516.model.UserVO;

/**
 * Class ProfessorHome Servlet is a controller that routes the user to Professor
 * Home after login.
 * 
 * @author shivamverma
 * @version 1.2
 * @date 02/21/2019
 **/

public class ProfessorHomeServlet extends HttpServlet{
	
	private static Logger log = Logger.getLogger(ProfessorHomeServlet.class.getName());

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {

		UserVO userVO = (UserVO) req.getAttribute("UserVO");
		
		HttpSession session = req.getSession();
		
		session.setAttribute("userVO", userVO);
		
		try {
			CourseDAOBean courseBean = new CourseDAOBean();
			if(courseBean.equals(null)) {
				
			}
			
			List<CourseVO> courseVO = courseBean.getCourseAssignedToProfessor(userVO);
			if(courseVO.isEmpty()) {
				session.setAttribute("displayMessage", "No Courses have been assigned to the professor");
				log.info("No Courses have been assigned to the professor.");
			}
			
			HashMap<Integer, String> course = new HashMap<>();
			for (int i = 0; i < courseVO.size(); i++)
				course.put(courseVO.get(i).getCourseId(), courseVO.get(i).getCourseName());
			session.setAttribute("CourseHashMap", course);
			res.sendRedirect(req.getContextPath() + "/professorHome.ftl");
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
}