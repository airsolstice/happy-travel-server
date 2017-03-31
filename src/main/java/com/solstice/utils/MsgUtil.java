package com.solstice.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class MsgUtil {
	/**
	 * 
	 * @param 内容
	 * @param 接收者
	 * @throws Exception
	 */
	public static void sendMsg( String text, String mobNum)
			throws Exception {
		  
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");  
        post.addRequestHeader("Content-Type",  "application/x-www-form-urlencoded;charset=utf-8");// 在头文件中设置转码  
        NameValuePair[] data = { new NameValuePair("Uid", "Solstice"), // 注册的用户名  
                new NameValuePair("Key", "8cd14da634348680ed5b"), // 注册成功后,登录网站使用的密钥  
                new NameValuePair("smsMob", mobNum), // 手机号码  
                new NameValuePair("smsText", text) };//设置短信内容    
        post.setRequestBody(data);  
        
        client.executeMethod(post);  
        Header[] headers = post.getResponseHeaders();  
        int statusCode = post.getStatusCode();  
        System.out.println("statusCode:" + statusCode);  
        for (Header h : headers) {  
            System.out.println(h.toString());  
        }  
        String result = new String(post.getResponseBodyAsString().getBytes(  
                "utf-8"));  
        System.out.println(result);  
        post.releaseConnection();  
	}
	
	@Test
	public void testMsg(){
		try {
			sendMsg("hello, this is a msg from smsWebChina", "18140049361");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
