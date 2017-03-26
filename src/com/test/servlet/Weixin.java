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
    String sToken = "Kmvqet4J2N";//���Token��������ɣ����Ǳ������ҵ���ϵ���ͬ
    String sCorpID = "wxe10a9a80eb785b66";//����������ҵ�ŵ�CorpID
    String sEncodingAESKey = "eVmjkzXDFWxN1nlsy0hO8TBvjm5tPmLoTlaJfekLE9t";//���EncodingAESKey��������ɣ����Ǳ������ҵ���ϵ���ͬ
    /**
     * ȷ����������΢�ŷ�����
     * @throws IOException 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
         
        // ΢�ż���ǩ�� 
        String sVerifyMsgSig = request.getParameter("msg_signature");
        // ʱ���
        String sVerifyTimeStamp = request.getParameter("timestamp");
        // �����
        String sVerifyNonce = request.getParameter("nonce");
        // ����ַ���
        String sVerifyEchoStr = request.getParameter("echostr");
        String sEchoStr; //��Ҫ���ص�����
        PrintWriter out = response.getWriter();  
        WXBizMsgCrypt wxcpt;
        try {
            wxcpt = new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
            sEchoStr = wxcpt.VerifyURL(sVerifyMsgSig, sVerifyTimeStamp,sVerifyNonce, sVerifyEchoStr);
            // ��֤URL�ɹ�����sEchoStr����
            out.print(sEchoStr); 
            System.out.println(sEchoStr);
        } catch (AesException e1) {
            e1.printStackTrace();
        }
    }
 
    /**
     * ����΢�ŷ�������������Ϣ
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();  
		//��ȡPostData
    	try {
    		ServletInputStream inputStream = request.getInputStream(); 
    		String s;
    		StringBuffer sb=new StringBuffer();
    		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
    		while ((s = in.readLine()) != null) {
    			sb.append(s);
    		}
    		String postdata=sb.toString();
			//���յ���Ϣ���н���
    		WXBizMsgCrypt wxbmc;
			wxbmc=new WXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);
			String msgSignature=request.getParameter("msg_signature");
			String timeStamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			String msg_mingwen=wxbmc.DecryptMsg(msgSignature, timeStamp, nonce, postdata);//msg_mingwenΪ���ܺ����Ϣ
			String message=null;
			Map<String, String>	map=MsgUtil.stringXmlToMap(msg_mingwen);//��msg_mingwenת����MAP��ʽ ���ڴ���
			String msgType=map.get("MsgType");
			String toUserName=map.get("ToUserName");
			String fromUserName=map.get("FromUserName");
			String content=map.get("Content");
			if ("text".equals(msgType)) {
				TextMessage textMessage=new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setContent("�����͵�������Ϣ�����յ�:"+content);
				textMessage.setCreateTime(System.currentTimeMillis()/1000);
				textMessage.setMsgType("text");
				message=wxbmc.EncryptMsg(MsgUtil.textMessageToXml(textMessage), timeStamp, nonce);
			}
			if ("image".equals(msgType)) {
				TextMessage textMessage=new TextMessage();
				textMessage.setFromUserName(toUserName);
				textMessage.setToUserName(fromUserName);
				textMessage.setContent("�㷢����һ��ͼƬ�����һ��ǲ�̫��Ŷ");
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