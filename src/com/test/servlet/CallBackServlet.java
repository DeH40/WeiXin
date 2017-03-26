package com.test.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.test.po.AccessToken;
import com.test.po.OAuth;
import com.test.po.OpenID;
import com.test.util.AuthUtil;
@WebServlet("/wxopenid/callback")
public class CallBackServlet extends HttpServlet {

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
		String codeString=request.getParameter("code");
		//PrintWriter out = response.getWriter();
	
		String APIurl="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CROPID&corpsecret=SECRET";
		String url=APIurl.replace("CROPID", AuthUtil.CropID).replace("SECRET", AuthUtil.Secret);
		AccessToken at=AuthUtil.doAccessToken(url);	
		String AccessToken=at.getAccesstoken();
		OAuth oa=AuthUtil.doGetOpenID(AccessToken,codeString);
		//out.print(oa.getOpenId());
		String OpenID="oa is null";
		String userid="oa is null";
		if (oa!=null) {
			 OpenID=oa.getOpenId();
			 userid = oa.getUserId();
			 OpenID=AuthUtil.userID2openID(userid, AccessToken);
		}
		
		
			request.getSession().setAttribute("opid", OpenID);
			response.sendRedirect("logInfo.jsp");
			
		
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

		
	}

}
