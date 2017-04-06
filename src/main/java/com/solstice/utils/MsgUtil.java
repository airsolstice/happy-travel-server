package com.solstice.utils;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

public class MsgUtil {

	public static void sendMsg( String text, String mobNum)
			throws Exception {
		  
        HttpClient client = new HttpClient();  
        PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");  
        // 在头文件中设置转码 
        post.addRequestHeader("Content-Type",  "application/x-www-form-urlencoded;charset=utf-8"); 
        // 注册的用户名  
        NameValuePair[] data = { new NameValuePair("Uid", "Solstice"), 
        		// 注册成功后,登录网站使用的密钥  
                new NameValuePair("Key", "8cd14da634348680ed5b"), 
                // 手机号码  
                new NameValuePair("smsMob", mobNum), 
                //设置短信内容    
                new NameValuePair("smsText", text) };
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
		
}
