package plant.com.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import plant.com.bo.FLower;
import plant.com.util.PlantDbConnection;

/**
 * Servlet implementation class ShowAllHerbs
 */
public class ShowAllHerbs extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<FLower> list = new ArrayList<FLower>();
		//fill using jdbc
		Connection con  = null;
		Statement stmt = null;
		ResultSet rs  = null;
		try{
			con  = PlantDbConnection.getConnection();
			stmt  = con.createStatement();
			//join we need product and its cat
			//String sql ="select * from product p ,product_cat c where p.cat_id=c.id";
			//String sql ="select * from product ";
			String sql ="select *from herbs";
			rs  = stmt.executeQuery(sql);
			while(rs.next()){
				
				FLower f = new FLower();
				f.setFlowerId(rs.getString(1));
				f.setFlowerName(rs.getString(2));
				f.setFlowerDesc(rs.getString(3));
				f.setFlowerImage(rs.getString(4));
				
				
				//cat
				//add into list
				list.add(f);
			}//end while
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				PlantDbConnection.closeConnection(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
 request.setAttribute("allherbs", list);		
 request.getRequestDispatcher("herbs.jsp").forward(request, response);		
		
		

	}



	}


