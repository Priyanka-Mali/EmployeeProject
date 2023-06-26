

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

@WebServlet("/editreturn")
public class editreturn extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		res.setContentType("text/html");
		PrintWriter out=res.getWriter();
		
		String id=req.getParameter("id");
		
		try {
			
		com.mysql.cj.jdbc.Driver d=new com.mysql.cj.jdbc.Driver();
		DriverManager.registerDriver(d);
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/priyanka","root","root");
		
		PreparedStatement ps=conn.prepareStatement("select *from reg where id=?");
		ps.setString(1,id);
		
		ResultSet rs=ps.executeQuery();
		
		while(rs.next()) {
			 out.print("<form action='editemployee' method='Get'");
             out.print("<table");
            
             out.print("<tr> <td>EmpID</td>   <td> <input type='text' name ='id'  id='id' value='"  + rs.getString("id")+"'/> </td> </tr><br>");
             out.print("<tr> <td>Firstname</td>  <td> <input type='text' name ='fname'  id='fname' value='" + rs.getString("fname") + "'/> </td> </tr><br>");
             out.print("<tr> <td>Lastname</td>   <td> <input type='text' name ='lname' id='lname' value='" + rs.getString("lname") + "'/> </td> </tr><br>");
             out.print("<tr> <td>Email</td>   <td> <input type='text' name ='email' id='email' value='" + rs.getString("email") + "'/> </td> </tr><br>");
             out.print("<tr> <td>Address</td>   <td> <input type='text' name ='address' id='address' value='" + rs.getString("address") + "'/> </td> </tr><br>");
             out.print("<tr> <td colspan ='2'> <input type='submit'  value= 'Edit & Save'/> </td> </tr>");
             out.print("</table");
             out.print("</form");
		}
		}
		catch(Exception ex)
		{
			out.println("Record Failed");
		}
	}

}
