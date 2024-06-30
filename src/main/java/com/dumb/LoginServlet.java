package com.dumb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.SQLException;

import com.dumb.dao.DabaseOperation;
import com.dumb.dao.UserRepository;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect(request.getContextPath() + "/login.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Connection con=null; 
		try {
			con = DabaseOperation.dbConnect();
		}catch(SQLException e ){
			e.printStackTrace();
		}
			
		if(UserRepository.authenticateUser(username, password, con)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("bookissue.html");
		}
		else	
			response.sendRedirect("login.html");
		
	}

}
