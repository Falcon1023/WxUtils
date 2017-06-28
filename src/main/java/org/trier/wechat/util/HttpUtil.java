package org.trier.wechat.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Http请求util
 */
public class HttpUtil {
    public static String doGet(String url){
        HttpClient httpClient= HttpClients.createDefault();
        HttpGet httpGet=new HttpGet(url);
        String result=null;
        try {
            HttpResponse httpResponse=httpClient.execute(httpGet);
            if(httpResponse.getStatusLine().getStatusCode()==200){
                HttpEntity entity=httpResponse.getEntity();
                result= EntityUtils.toString(entity,"UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doPostXml(String url,String xml){
        HttpClient httpClient= HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(url);
        String result=null;
        try {
            StringEntity stringEntity = new StringEntity(xml, Charset.forName("UTF-8"));
            stringEntity.setContentEncoding("UTF-8");
            // 发送xml格式的数据请求
            stringEntity.setContentType("application/xml");
            httpPost.setEntity(stringEntity);
            HttpResponse response=httpClient.execute(httpPost);
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity entity=response.getEntity();
                result= EntityUtils.toString(entity,"UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
