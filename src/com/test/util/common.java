package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServlet;

import com.google.gson.Gson;
import com.test.po.AccessToken;
import com.test.po.OAuth;

@SuppressWarnings("serial")
public class common extends HttpServlet {
	
	
	private static String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }
		public static String getToken(String CropID,String Secret) throws MalformedURLException, IOException {
			Gson gson=new Gson();
			String url="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+CropID+"&corpsecret="+Secret;
			InputStream is = new URL(url).openStream(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
		    AccessToken at=gson.fromJson(jsonText, AccessToken.class);
		    return at.getAccesstoken();
			      			
		}
		public static String getOpenID(String AccessToken,String Code) throws MalformedURLException, IOException {
			Gson gson=new Gson();
			String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+AccessToken+"&code="+Code;
			InputStream is = new URL(url).openStream(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			OAuth oa=gson.fromJson(jsonText, OAuth.class);
			return oa.getOpenId();
		}
		public static String getCode(String CropID,String RedirectURI) throws MalformedURLException, IOException {
			Gson gson=new Gson();
			String APIurl="https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE#wechat_redirect";
			String url=APIurl.replace("CORPID", CropID).replace("REDIRECT_URI", RedirectURI);
			InputStream is = new URL(url).openStream(); 
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
		    AccessToken at=gson.fromJson(jsonText, AccessToken.class);
		    return at.getAccesstoken();
			      			
		}
}
