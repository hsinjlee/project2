package Team76.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

/**
 * This class GradeDbUtil contains Model in MVC. 
 * Author: Hsin-Jung Lee 
 * Version: 3
 */
public class GradeDbUtil {

	private DataSource dataSource;

	public GradeDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}

	public List<Grade> getGrades() throws Exception {

		List<Grade> grades = new ArrayList<>();

		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;

		try {

			connection = dataSource.getConnection();

			String sql = "SELECT * FROM grade";

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);

			while (resultSet.next()) {

				int studentId = resultSet.getInt("studentID");
				int quizId = resultSet.getInt("QuizId");
				String quizTitle = resultSet.getString("quiztitle");
				String studentName = resultSet.getString("studentName");
				String grade = resultSet.getString("grade");

				Grade tempGrade = new Grade(studentId, quizId, quizTitle, 
						studentName, grade);

				grades.add(tempGrade);
			}
			return grades;
		} finally {

			close(connection, statement, resultSet);
		}
	}

	private void close(Connection connection, Statement statement, 
			ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connection != null) {
				connection.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
