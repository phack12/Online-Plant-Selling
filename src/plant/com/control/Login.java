package plant.com.control;
import plant.com.bo.User;
import plant.com.util.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userName = request.getParameter("Username");
		String passWord = request.getParameter("Password");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result =null;
		try {
			con = PlantDbConnection.getConnection();
			String sql = "select *from resistration where username=? and password =?";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				result = "Successfully logged"
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
