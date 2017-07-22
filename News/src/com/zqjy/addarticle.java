package com.zqjy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.zqjy.db.MySQLDbHelper;

public class addarticle extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 720500912584647204L;

	/**
	 * Constructor of the object.
	 */
	public addarticle() {
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

		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String title = request.getParameter("arTitle");
		String mainText = request.getParameter("arMain");
		HttpSession session = request.getSession(true);
		Integer id = (Integer) session.getAttribute("id");
		System.out.println("title:"+title);
		System.out.println("mainText:"+mainText);
		System.out.println("id:"+id);
		PrintWriter out = response.getWriter();
		
		try {
			Connection conn = MySQLDbHelper.getConnection();
			Statement stmt = MySQLDbHelper.creStatement(conn);
			String sql = "INSERT INTO article(title,main,uid) VALUES('"+title+"','"+mainText+"',"+id+")";
			int res = MySQLDbHelper.executeUpdate(stmt, sql);
			System.out.println("res:"+res);
			out.print(JSON.toJSONString(res));
			out.flush();
			out.close();
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
