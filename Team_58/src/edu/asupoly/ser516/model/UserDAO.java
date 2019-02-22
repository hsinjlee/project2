package edu.asupoly.ser516.model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * UserDAO is an interface with method to get user information.
 * 
 * @author Aditya Vikram
 * @version 1.3
 * @date 02/22/2019
 **/


public interface UserDAO {

	public List<UserVO> getUserInfo(String userName, String passWord) throws ClassNotFoundException, SQLException, IOException;
}
