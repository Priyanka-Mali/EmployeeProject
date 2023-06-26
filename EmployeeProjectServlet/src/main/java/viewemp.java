

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewemp")
public class viewemp extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String id=req.getParameter("id");
		String fname=req.getParameter("fname");
		String lname=req.getParameter("lname");
		String email=req.getParameter("email");
		String address=req.getParameter("address");
		
		try {
			com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(d);
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/priyanka","root","root");
			
			PreparedStatement ps=conn.prepareStatement("select *from reg");
			ResultSet rs=ps.executeQuery();
			
			out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> ID </td>");
            out.println("<td> Firstname </td>");
            out.println("<td> Lastname </td>");
            out.println("<td> Email </td>");
            out.println("<td> Address </td>");
            out.println("<td> Edit </td>");
            out.println("<td> Delete </td>");
            
            out.println("</tr>");
            
			while(rs.next())
			{
				out.println("<tr>");
	            out.println("<td>"  + rs.getString("id")      +  "</td>");
	            out.println("<td>"  + rs.getString("fname")   +  "</td>");  
	            out.println("<td>"  + rs.getString("lname")   +  "</td>");  
	            out.println("<td>"  + rs.getString("email")   +  "</td>");  
	            out.println("<td>"  + rs.getString("address") +  "</td>");  
	            
	            out.println("<td>"  + "<a href='editreturn?id=" + rs.getString("id")  + "'> Edit </a>" + "</td>");
	            out.println("<td>"  + "<a href='deleteemp?id=" +  rs.getString("id")  + "'> Delete </a>" + "</td>");
	            out.println("</tr>");
	      	}
			out.println("</table>");
		}
		catch(Exception ex){
			out.println(ex);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
