package com.test.po;

public class PictureMessage {
	private String ToUserName;
	private String FromUserName;
	private String PicUrl;
	private static String MsgType="image";
	private String MediaId;
	private String MsgId;
	private String AgentID;
	private long CreateTime ;
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public static String getMsgType() {
		return MsgType;
	}
	public static void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getAgentID() {
		return AgentID;
	}
	public void setAgentID(String agentID) {
		AgentID = agentID;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	
	
}
