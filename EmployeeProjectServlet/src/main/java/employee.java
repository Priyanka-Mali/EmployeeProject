

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/employee")
public class employee extends HttpServlet {
	
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
			
			if(conn!=null)
			{
				PreparedStatement ps=conn.prepareStatement("insert into reg values(?,?,?,?,?)");
				ps.setString(1,id);
				ps.setString(2,fname);
				ps.setString(3, lname);
				ps.setString(4,email);
				ps.setString(5, address);
				
				int val=ps.executeUpdate();
				
				if(val>0)
				{
					out.println("Registarion done");
				}
				else
				{
					out.println("Registarion failed");
				}
			}
		}
		catch(Exception ex){
			out.println(ex);
		}
	}

	
	

}