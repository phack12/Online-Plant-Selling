package plant.com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;


import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import plant.com.util.PlantDbConnection;


/**
 * Servlet implementation class Registration
 */
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("Username");
		String passWord = request.getParameter("Password");
		String email = request.getParameter("Email");
		String phone = request.getParameter("Phone");
		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
		/*
		pw.print("<h1>Welcome "+userName+"</h1><hr>");
		pw.print("<h1>Welcome "+passWord+"</h1><hr>");
		pw.print("<h1>Welcome "+email+"</h1><hr>");
		pw.print("<h1>Welcome "+phone+"</h1><hr>");
		*/
		pw.print("<h1>Welcome "+phone+"</h1><hr>");
		boolean isAdded = false;
		String errors = null;
		Connection con  = null;
		PreparedStatement pstmt = null;
	//	ResultSet rs  = null;
		try{
			con  = PlantDbConnection.getConnection();
			
			String sql ="insert into resistration(userName,passWord,email,phone)values"
					+"(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userName);
			pstmt.setString(2, passWord);
			pstmt.setString(3, email);
			pstmt.setString(4, phone);
			if(pstmt.executeUpdate()==1)
				isAdded = true;
			}//end while
			
		catch(SQLException e){
			errors = "DB Errors"+ e.getMessage();
		}finally{
			try {
				PlantDbConnection.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(errors!=null){
		request.setAttribute("errors", errors);
		request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else{
			errors = "succes fully Added";
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("login.jsp").forward(request, response);
			
		}
		
	}

}
