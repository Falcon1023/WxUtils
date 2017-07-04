package org.trier.wechat.pojo.auth;

import java.io.Serializable;

/**
 * 请求token返回的数据
 */
public class Token implements Serializable {
    private String access_token;
    private int expires_in;
    private long record_time;
    private String refresh_token;
    private String openid;
    private String scope;
    private int errcode;
    private String errmsg;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public long getRecord_time() {
        return record_time;
    }

    public void setRecord_time(long record_time) {
        this.record_time = record_time;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "access_token:" + access_token + "\n"
                + "expires_in:" + expires_in + "\n"
                + "record_time:" + record_time + "\n"
                + "refresh_token:" + refresh_token + "\n"
                + "openid:" + openid + "\n"
                + "scope:" + scope + "\n"
                + "errcode:" + errcode + "\n"
                + "errmsg:" + errmsg;
    }
}
