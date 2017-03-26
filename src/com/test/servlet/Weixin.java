package com.test.servlet;
 
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
 
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 









import org.dom4j.DocumentException;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.test.po.TextMessage;
import com.test.util.MsgUtil;
@WebServlet("/wxopenid/WeiXin")
public class Weixin extends HttpServlet {
    String sToken = "Kmvqet4J2N";//这个Token是随机生成，但是必须跟企业号上的相同
    String sCorpID = "wxe10a9a80eb785b66";//这里是你企业号的CorpID
    String sEncodingAESKey = "eVmjkzXDFWxN1nlsy0hO8TBvjm5tPmLoTlaJfekLE9t";//这个EncodingAESKey是随机生成，但是必须跟企业号上的相同
    /**
     * 确认请求来自微信服务器
     * @throws IOException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
         
        // 微信加密签名 
        String sVerifyMsgSig = request.getParameter("msg_signature");
        // 时间戳
        String sVerifyTimeStamp = request.getParameter("timestamp");
        // 随机数
        String sVerifyNonce = request.getParameter("nonce");
        // 随机字符串
        String sVerifyEchoStr = request.getParameter("echostr");
        String sEchoStr; //需要返回的明文
        PrintWriter out = response.getWriter();  
        WXBizMsgCrypt wxcpt;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
            // 验证URL成功，将sEchoStr返回
            out.print(sEchoStr); 
            System.out.println(sEchoStr);
        } catch (AesException e1) {
            e1.printStackTrace();
        }
    }
 
    /**
     * 处理微信服务器发来的消息
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();  
		//获取PostData
    	try {
    		ServletInputStream inputStream = request.getInputStream(); 
    		String s;
    		StringBuffer sb=new StringBuffer();
    		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    		while ((s = in.readLine()) != null) {
    			sb.append(s);
    		}
    		String postdata=sb.toString();
			//对收到消息进行解密
    		WXBizMsgCrypt wxbmc;
			wxbmc=new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			String msgSignature=request.getParameter("msg_signature");
			String timeStamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			String msg_mingwen=wxbmc.DecryptMsg(msgSignature, timeStamp, nonce, postdata);//msg_mingwen为解密后的消息
			String message=null;
			Map<String, String>	map=MsgUtil.stringXmlToMap(msg_mingwen);//将msg_mingwen转换成MAP格式 便于处理
			String msgType=map.get("MsgType");
			String toUserName=map.get("ToUserName");
			String fromUserName=map.get("FromUserName");
			String content=map.get("Content");
			if ("text".equals(msgType)) {
				TextMessage textMessage=new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setContent("您发送的以下信息我已收到:"+content);
				textMessage.setCreateTime(System.currentTimeMillis()/1000);
				textMessage.setMsgType("text");
				message=wxbmc.EncryptMsg(MsgUtil.textMessageToXml(textMessage), timeStamp, nonce);
			}
			if ("image".equals(msgType)) {
				TextMessage textMessage=new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setContent("你发送了一张图片，可我还是不太懂哦");
				textMessage.setAgentID("6");
				textMessage.setCreateTime(1348831860);
				textMessage.setMsgType("text");
				message=wxbmc.EncryptMsg(MsgUtil.textMessageToXml(textMessage), timeStamp, nonce);
			}
			out.print(message);
	
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (AesException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	finally{
    		out.close();
    	}
    }
 
}