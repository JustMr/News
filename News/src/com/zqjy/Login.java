package com.zqjy;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zqjy.db.MySQLDbHelper;

public class Login extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -721078120626050791L;

	/**
	 * Constructor of the object.
	 */
	public Login() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username").trim();
		String userpwd = request.getParameter("pwd").trim();
		HttpSession session = request.getSession(true);
		System.out.println("username:"+username);
		System.out.println("pwd:"+userpwd);
		
		try {
			Connection conn = MySQLDbHelper.getConnection();
			Statement stmt = MySQLDbHelper.creStatement(conn);
			String sql = "SELECT id FROM customer WHERE name='"+username+"' AND password= '"+userpwd+"'";
			ResultSet rs = MySQLDbHelper.executeQuery(stmt, sql);
			if(rs.next()) {
				Integer userid = rs.getInt("id");
				System.out.println("id:"+userid);
				
				MySQLDbHelper.close(rs);
				MySQLDbHelper.close(stmt);
				MySQLDbHelper.close(conn);
				session.setAttribute("id", userid);
				response.sendRedirect("managerhome.jsp");
			}else {
				response.sendRedirect("error.html");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.sendRedirect("error.html");
		}
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
