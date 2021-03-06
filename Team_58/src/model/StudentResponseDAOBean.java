package model;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

import controller.ProfessorHomeServlet;
/**
 * Class StudentResponseDAOBean with the following method:
 * 	a. update studentResponse score based on quizId after grade quiz command
 * @author akashkadam
 * @version 1.0
 * 
 */
public class StudentResponseDAOBean implements StudentResponseDAO {

	private static Logger log = Logger.getLogger(ProfessorHomeServlet.class.getName());
	
	private static Properties dbProperties = new Properties();

	public StudentResponseDAOBean() throws IOException {
		dbProperties.load(ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties"));
	}
	@Override
	public void updateStudentResponse(int quizId) {


		Connection connection = null;
		PreparedStatement query = null;

		try {
			
			connection = ConnectionFactory.getConnection();
			query = connection.prepareStatement(dbProperties.getProperty("updateStudentResponse"));
			query.setInt(1,quizId);
			query.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			log.info(e.getMessage());
		}
	}

}
