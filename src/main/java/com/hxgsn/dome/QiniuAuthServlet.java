package com.hxgsn.dome;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class authServlet
 */
@WebServlet("/qiniuAuthServlet")
public class QiniuAuthServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		final QiniuAuth qiniuAuth = new QiniuAuth();
		final PrintWriter out = response.getWriter();
		final String token = qiniuAuth.getUpToken();
		out.append("{\"uptoken\":\"" + token + "\"}");
	}

	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

}
