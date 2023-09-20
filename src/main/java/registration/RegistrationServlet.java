package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		 
			 
				String uName = request.getParameter("name");
				String uEmail = request.getParameter("email");
				String uPass = request.getParameter("pass");
				String uMobile = request.getParameter("contact");
				
				RequestDispatcher dispatcher = null;
				Connection con = null;
				
				
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					
					con = DriverManager.getConnection("jdbc:mysql://localhost:3306/System_Users?useSSL=false","root","");
					PreparedStatement pst = con.prepareStatement("insert into users(UName,UPw,UEmail,UMobile) values (?,?,?,?)");
					pst.setString(1, uName);
					pst.setString(2, uEmail);
					pst.setString(3, uPass);
					pst.setString(4, uMobile);
					
					int rawCount = pst.executeUpdate();
					dispatcher = request.getRequestDispatcher("registration.jsp");
					
					if (rawCount > 0) {
						request.setAttribute("status", "Success");
						
					}
					else {
						request.setAttribute("status", "Fail");
					}
					
					dispatcher.forward(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					try {
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}

}
