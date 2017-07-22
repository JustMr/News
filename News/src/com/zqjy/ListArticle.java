package com.zqjy;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.zqjy.bean.Article;
import com.zqjy.db.MySQLDbHelper;

public class ListArticle extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -940585662987819861L;

	/**
	 * Constructor of the object.
	 */
	public ListArticle() {
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
		List<Article> list = new ArrayList<Article>();
		
		Connection conn = MySQLDbHelper.getConnection();
		Statement stmt = MySQLDbHelper.creStatement(conn);
		
		String sql = "SELECT * FROM article";
		ResultSet rs = MySQLDbHelper.executeQuery(stmt, sql);
		
		PrintWriter out = response.getWriter();
		
		try {
			while (rs.next()) {
				Article article = new Article();
				article.setId(rs.getInt(1));
				article.setTitle(rs.getString(2));
				article.setMain(rs.getString(3));
				article.setUid(rs.getInt(4));
				list.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.print(JSON.toJSONString(list));
		
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
