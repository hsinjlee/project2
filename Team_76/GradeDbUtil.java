package Team76.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import javax.sql.DataSource;

public class GradeDbUtil {

	private DataSource dataSource;

	public GradeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Grade> getGrades() throws Exception {

		List<Grade> grades = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;

//		Class.forName("com.mysql.jdbc.Driver");
//
//        // connect way #1
//        String url1 = "jdbc:mysql://localhost:3306/ser516p2/";
//        String user = "root";
//        String password = "";
//
//        myConn = DriverManager.getConnection(url1, user, password);
//        if (myConn != null) {
//            System.out.println("Connected to the database test1");
//        }
		
		
//		String userID = request.getParameter("userID");
//		String driver = "com.mysql.jdbc.driver";
//		String url = "jdbc:mysql://localhost:3306/";
//		String database = "ser516p2";
//		String user = "root";
//		String password = "1hsinjung!";
//		try {
//			Class.forName(driver);
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		

		try {

			myConn = dataSource.getConnection();

			String sql = "SELECT * FROM grade";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			while (myRs.next()) {

				int studentId = myRs.getInt("studentID");
				int quizId = myRs.getInt("QuizId");
				String quizTitle = myRs.getString("quiztitle");
				String studentName = myRs.getString("studentName");
				String grade = myRs.getString("grade");

				Grade tempGrade = new Grade(studentId, quizId, quizTitle, 
						studentName, grade);

				grades.add(tempGrade);
			}

			return grades;
		} finally {

			close(myConn, myStmt, myRs);
		}
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				myStmt.close();
			}

			if (myConn != null) {
				myConn.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
