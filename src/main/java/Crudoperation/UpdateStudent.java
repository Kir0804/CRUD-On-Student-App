package Crudoperation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updatelink")
public class UpdateStudent extends HttpServlet {
Connection con ;		
	
	@Override
	public void init() throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/1eja7","root", "sql@123");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		//fetch the data from html
				
				String id = req.getParameter("studentid");
				String  newstream = req.getParameter("studentstream");
				
			//parsing
				int sid = Integer.parseInt(id);
				
				PreparedStatement pstmt=null;
				
				String query="update stud_info set  stream=? where sid=?";
				
				try {
					pstmt=con.prepareStatement(query);
					pstmt.setString(1, newstream);
					pstmt.setInt(2,sid);
				
					int count = pstmt.executeUpdate();
					
					PrintWriter pw = resp.getWriter();
					pw.print("<h1 style color:green>"+count+" Record Updated Sucessfully</h1>");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}

