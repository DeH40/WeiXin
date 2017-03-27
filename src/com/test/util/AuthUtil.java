package com.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.test.po.AccessToken;
import com.test.po.OAuth;
import com.test.po.OpenID;
import com.test.po.OpenID2;

public class AuthUtil {
	public static final String CropID="wxe10a9a80eb785b66";
	public static final String Secret="dX45nt8Y-QGdgK5sz-twU8UwaHgZPlLst0N_f3HukY89T1QTa1lrnjMpXMlxm8Rf";
	public static AccessToken doAccessToken(String url) throws ClientProtocolException, IOException{
		Gson jo=new Gson();
		AccessToken at=null;
		@SuppressWarnings("deprecation")
		DefaultHttpClient client =new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		HttpResponse response=client.execute(httpGet);
		HttpEntity entity=response.getEntity();
		String result;
		if (entity!=null) {
			result=EntityUtils.toString(entity,"UTF-8");
			System.out.println(result);
			at = jo.fromJson(result, AccessToken.class);
		}
		httpGet.releaseConnection();
		return at;	
	}
	public static OAuth doGetOpenID (String AccessToken,String codeString) throws ClientProtocolException, IOException {
		OAuth oa = null;
		Gson jo=new Gson();
		String url="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+AccessToken+"&code="+codeString;
		@SuppressWarnings("deprecation")
		DefaultHttpClient client =new DefaultHttpClient();
		HttpGet httpGet=new HttpGet(url);
		HttpResponse response=client.execute(httpGet);
		HttpEntity entity=response.getEntity();
		if (entity!=null) {
			String result=EntityUtils.toString(entity,"UTF-8");
			System.out.println(result);
			oa = jo.fromJson(result,OAuth.class);
		}
		
		return oa;
	}
	public static String userID2openID(String userid,String AccessToken) {
		String openidString="";
		OpenID oid=new OpenID();
		oid.setUserid(userid);
		Gson gson=new Gson();
		String json=gson.toJson(oid);
		String jsonString=post(json, AccessToken);
		OpenID2 oid2=new OpenID2();
		System.out.println(jsonString);
		oid2=gson.fromJson(jsonString, OpenID2.class);
		openidString=oid2.getOpenid();
		return openidString;
	}
    public static String post(String json,String AccessToken) {
    	String URL="https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token="+AccessToken;
        @SuppressWarnings("deprecation")
		HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(URL);
        post.setHeader("Content-Type", "application/json");
        post.addHeader("Authorization", "Basic YWRtaW46");
        String result = "";
        
        try {

            StringEntity s = new StringEntity(json, "utf-8");
            s.setContentEncoding((Header) new BasicHeader(HTTP.CONTENT_TYPE,
                    "application/json"));
            post.setEntity(s);

            // 发送请求
            HttpResponse httpResponse = client.execute(post);

            // 获取响应输入流
            InputStream inStream = httpResponse.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    inStream, "utf-8"));
            StringBuilder strber = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
                strber.append(line + "\n");
            inStream.close();

            result = strber.toString();
           

        } catch (Exception e) {
            System.out.println("请求异常");
            throw new RuntimeException(e);
        }

        return result;
    }

}

