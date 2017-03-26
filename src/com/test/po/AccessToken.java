package com.test.po;

public class AccessToken {
  private String access_token;
  private long expiresin;
  private String errcode;
  private String errmsg;
public String getAccesstoken() {
	return access_token;
}
public void setAccesstoken(String accesstoken) {
	this.access_token = accesstoken;
}
public long getExpiresin() {
	return expiresin;
}
public void setExpiresin(long expiresin) {
	this.expiresin = expiresin;
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
