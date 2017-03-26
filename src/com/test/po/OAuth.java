package com.test.po;

public class OAuth {
	private String UserId;
	private String DeviceId;
	private String OpenId;
	private String errcode;
	private String errmsg;
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getDeviceId() {
		return DeviceId;
	}
	public void setDeviceId(String deviceId) {
		DeviceId = deviceId;
	}
	public String getOpenId() {
		return OpenId;
	}
	public void setOpenId(String openId) {
		OpenId = openId;
	}
	public String getErrcode() {
		return errcode;
	}
	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	
}
