package edu.asupoly.ser516.controller;

import edu.asupoly.ser516.model.CourseVO;
import edu.asupoly.ser516.model.UserVO;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "StudentHome", urlPatterns = "/StudentHome")
public class StudentHomeServlet extends HttpServlet
{
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res)
    {
        try
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String hostName = "showtimefinder.database.windows.net"; // update me
            String dbName = "ser516_db"; // update me
            String user = "scrum_mates@showtimefinder"; // update me
            String password = "Azure@Cloud"; // update me
            String url = String.format(
                "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                hostName, dbName, user, password);
            Connection connection = null;
            connection = DriverManager.getConnection(url);

            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            /*
            PreparedStatement query;
            query = connection.prepareStatement("select * from [dbo].[Course] A " +
                                                " join [dbo].[UserCourseMApping] B" +
                                                " on A.courseId = B.courseId" + " where B.userId = ?");
            query.setInt(1, 8);//student.getUserId());

            ResultSet resultData = query.executeQuery();
            */
            
            PreparedStatement query2;
            query2 = connection.prepareStatement("select * from [dbo].[UserDetails] " +
                                                " where userId = ?");
            query2.setInt(1, 8);

            ResultSet userData = query2.executeQuery();
            
            String userName = "";
            while(userData.next())
                userName = userData.getString("firstname");
            UserVO userVO = new UserVO(userName, "", 1, true, "", "", 8);
            
            //System.out.println(userData);

            //List<CourseVO> list = new ArrayList<>();

            /*
            while (resultData.next()) {
                    int courseId = resultData.getInt("courseId");
                    String courseName = resultData.getString("courseName");
                    String courseNumber = resultData.getString("courseNumber");
                    CourseVO course = new CourseVO(courseName, courseNumber, courseId);
                    list.add(course);
            }

            HashMap<Integer, String> course = new HashMap<>();
            for (int i = 0; i < list.size(); i++)
                    course.put(list.get(i).getCourseId(), list.get(i).getCourseName());

            //session.setAttribute("CourseHashMap", course);
            */
            HttpSession session = req.getSession();
            session.setAttribute("UserVO", userVO);
            

            res.sendRedirect(req.getContextPath() + "/studentHome.ftl");

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse res)
    {
        UserVO student = (UserVO) req.getAttribute("UserVO");
        HttpSession session = req.getSession();
        session.setAttribute("studentFirstName", student.getFirstname());

        System.out.println("studentName: " + session.getAttribute("studentFirstName"));

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            String hostName = "showtimefinder.database.windows.net"; // update me
            String dbName = "ser516_db"; // update me
            String user = "scrum_mates@showtimefinder"; // update me
            String password = "Azure@Cloud"; // update me
            String url = String.format(
                "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;"
                                + "hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                hostName, dbName, user, password);
            Connection connection = null;
            connection = DriverManager.getConnection(url);
            
            String schema = connection.getSchema();
            System.out.println("Successful connection - Schema: " + schema);

            PreparedStatement query;
            query = connection.prepareStatement("select * from [dbo].[Course] A " +
                                                " join [dbo].[UserCourseMApping] B" +
                                                " on A.courseId = B.courseId" + " where B.userId = ?");
            query.setInt(1, student.getUserId());

            ResultSet resultData = query.executeQuery();

            List<CourseVO> list = new ArrayList<>();

            while (resultData.next()) {
                    int courseId = resultData.getInt("courseId");
                    String courseName = resultData.getString("courseName");
                    String courseNumber = resultData.getString("courseNumber");
                    CourseVO course = new CourseVO(courseName, courseNumber, courseId);
                    list.add(course);
            }

            HashMap<Integer, String> course = new HashMap<>();
            for (int i = 0; i < list.size(); i++)
                    course.put(list.get(i).getCourseId(), list.get(i).getCourseName());

            session.setAttribute("CourseHashMap", course);

            System.out.println(req.getContextPath() + "/studentHome.ftl");

            res.sendRedirect(req.getContextPath() + "/studentHome.ftl");
            
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}