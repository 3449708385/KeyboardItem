import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.util.EntityUtils;

import com.yishenxiao.commons.utils.Base64Utils;
import com.yishenxiao.commons.utils.BaseUrlUtils;
import com.yishenxiao.commons.utils.HttpClientUtils;
import com.yishenxiao.commons.utils.HttpUtils;
import com.yishenxiao.commons.utils.MD5Utils;
import com.yishenxiao.commons.utils.WeiboShortLink;

public class Test01 {
   /*public static void main(String[] args){
	   //System.out.println(System.currentTimeMillis());
	   List<String> str = new ArrayList<String>();
	   str.add("111");
	   str.add("222");
	   System.out.println(str);
   }*/
	public static void main(String[] args) throws Exception {
		//System.out.println(WeiboShortLink.sinaShortUrl("http://open.weibo.com/#_loginLayer_1522663153331"));
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		System.out.println(list.remove("2"));
		System.out.println(list.toString());
		/*Map<String,Object> paraterMap = new HashMap<String, Object>();
		paraterMap.put("access_token", long_url);
		paraterMap.put("action", "long2short");
		paraterMap.put("long_url", "https://www.bejson.com/");
		
		
		String url = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";
		String params = "";
		String str = HttpClientUtils.httpPostWithJSON(url, params);
		System.out.println(str);*/
	/*	Map<String,Object> paraterMap = new HashMap<String, Object>();
		String httpurl = "http://www.mynb8.com/api/sina";
		String appkey = "801f432e85f89724a43f171aeae23e81";
		String long_url = URLEncoder.encode("https://blog.csdn.net/qq_14974975/article/details/78396151", "GBK");
		String sign = MD5Utils.getMd5(appkey+MD5Utils.getMd5(long_url).toLowerCase()).toLowerCase();
		paraterMap.put("long_url", long_url);
		paraterMap.put("appkey", appkey);
		paraterMap.put("sign", sign);
		String str = HttpClientUtils.getSendRequest(httpurl, paraterMap);
		str=str.replace("\\/", "/");
		System.out.println(str);*/
		/*String baseStr = BaseUrlUtils.getImageStr("http://gosmallapp.com/ide/2.jpg?x-oss-process=style/xiaoyixiao");
		if(baseStr.equals("fail")){
			return;
		}
		//System.out.println(baseStr);
		//BaseUrlUtils.generateImage(baseStr, "D:/mgp/108.jpg");
	    String host = "https://dm-51.data.aliyun.com";
	    String path = "/rest/160601/ocr/ocr_idcard.json";
	    String method = "POST";
	    String appcode = "14ecaa62ce9f4762a7a3e663feb2d339";
	    Map<String, String> headers = new HashMap<String, String>();
	    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
	    headers.put("Authorization", "APPCODE " + appcode);
	    //根据API的要求，定义相对应的Content-Type
	    headers.put("Content-Type", "application/json; charset=UTF-8");
	    Map<String, String> querys = new HashMap<String, String>();
	    String bodys = "{\"image\":\""+baseStr+"\",\"configure\":{\"side\":\"back\"}}";// #身份证正反面类型: face/back


	    try {
	    	*//**
	    	* 重要提示如下:
	    	* HttpUtils请从
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
	    	* 下载
	    	*
	    	* 相应的依赖请参照
	    	* https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
	    	*//*
	    	String temp = null;
	    	HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
	    	System.out.println(response.toString());
	    	if(response != null && response.getStatusLine().getStatusCode()==200){
	          HttpEntity entity = response.getEntity();
	          temp=EntityUtils.toString(entity,"UTF-8");
	          System.out.println(temp);
	        }else{
	        	temp=null;
	        }
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }*/
		//new一个URL对象  
     /*   URL url = new URL("http://img5.xiazaizhijia.com/walls/20160708/1440x900_2f172c09d079701.jpg");  
        //打开链接  
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
        //设置请求方式为"GET"  
        conn.setRequestMethod("GET");  
        //超时响应时间为5秒  
        conn.setConnectTimeout(5 * 1000);  
        //通过输入流获取图片数据  
        InputStream inStream = conn.getInputStream();  
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性  
        byte[] data = readInputStream(inStream);  
        //new一个文件对象用来保存图片，默认保存当前工程根目录  
        File imageFile = new File("D:/mgp/2.jpg");  
        //创建输出流  
        FileOutputStream outStream = new FileOutputStream(imageFile);  
        //写入数据  
        outStream.write(data);  
        //关闭输出流  
        outStream.close();  */
	}
	
	 public static byte[] readInputStream(InputStream inStream) throws Exception{  
	        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
	        //创建一个Buffer字符串  
	        byte[] buffer = new byte[1024];  
	        //每次读取的字符串长度，如果为-1，代表全部读取完毕  
	        int len = 0;  
	        //使用一个输入流从buffer里把数据读取出来  
	        while( (len=inStream.read(buffer)) != -1 ){  
	            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度  
	            outStream.write(buffer, 0, len);  
	        }  
	        //关闭输入流  
	        inStream.close();  
	        //把outStream里的数据写入内存  
	        return outStream.toByteArray();  
	    }  
}
