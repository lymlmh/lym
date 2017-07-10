package cn.edu.guet.lym.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.helpers.LogLog;


/**
 * Http请求类
 * 
 * <p>静态类，方便使用，无连接池<p>
 * <p>如需更丰富的功能、更高的性能，请使用apache开源组件<a href="http://hc.apache.org/">httpcomponents-client</a></p>
 * <p>包含post、get两种方式</p>
 * @author qiulw
 * @version V4.0.0 2011.11.29
 */
public class HttpClient {
	/**
	 * 发送HTTP请求，返回响应文本
	 * @param url 请求地址
	 * @param params post参数，格式：param1=a&param2=b
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @param isPost 是否使用POST方式，true 使用POST，false 使用GET
	 * @param requestHeaders 设置请求头信息
	 * @return HTTP响应文本
	 * @throws IOException
	 */
	public static String send(String url,
			                  String params,
			                  String charsetName,
			                  boolean isPost,
			                  Map<String, String> requestHeaders) throws IOException {
		URL client = new URL(url);
		HttpURLConnection con = (HttpURLConnection) client.openConnection();		
		con.setConnectTimeout(30000);
		con.setReadTimeout(30000);
		if(StringUtils.isEmpty(charsetName))
			charsetName = "UTF-8";
		con.setRequestProperty("Accept-Charset", charsetName);
		con.setRequestProperty("Content-Type", "text/xml");
		if(requestHeaders!=null && !requestHeaders.isEmpty()){
			for (String key : requestHeaders.keySet()) {
				con.setRequestProperty(key, requestHeaders.get(key));
			}
		}
		
		con.setDoInput(true);
		con.setUseCaches(false);
		
		if(isPost){
			con.setDoOutput(true);
			con.setRequestMethod("POST");
		}else{
			con.setRequestMethod("GET");
		}		
		con.setInstanceFollowRedirects(true);
		//con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");	
		//设置post参数
		if(isPost && StringUtils.isNotEmpty(params)){			
			con.setRequestProperty("Content-Length", String.valueOf(params.getBytes(charsetName).length));
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),charsetName);
//			con.setRequestProperty("Content-Length", String.valueOf(params.getBytes().length));
//			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
			out.write(params);
			out.flush();
			out.close();
		}				
		con.connect();
		
		//读取返回内容		
		InputStream input = con.getInputStream();
		return readString(input,charsetName);
	}
	/**
	 * 发送HTTP请求，返回响应文本
	 * <p>通过子线程执行，不堵塞主线程</p>
	 * @param url
	 * @param params
	 * @param charsetName
	 * @param isPost
	 * @param requestHeaders
	 */
	public static void sendAsync(String url,String params,String charsetName,boolean isPost,Map<String, String> requestHeaders){
		AsyncHttpSender thread = new AsyncHttpSender();
		thread.setUrl(url);
		thread.setParams(params);
		thread.setCharsetName(charsetName);
		thread.setRequestHeaders(requestHeaders);
		thread.start();
	}
	/**
	 * 使用post方式提交http请求，并返回响应内容
	 * @param url 请求地址
	 * @param params post参数，格式：param1=a&param2=b
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @return http响应内容
	 * @throws IOException
	 */
	public static String post(String url, String params,String charsetName) throws IOException {		
		return send(url, params, charsetName, true,null);
	}
	/**
	 * 使用post方式提交http请求，并返回响应内容
	 * @param url 请求地址
	 * @param params post参数，格式：param1=a&param2=b
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @return http响应内容
	 * @throws IOException
	 */
	public static String post(String url, String params) throws IOException {
		return post(url,params,null);
	}
	/**
	 * 使用post方式提交http请求，并返回响应内容
	 * @param url 请求地址
	 * @param params post参数，格式：param1=a&param2=b
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @return http响应内容
	 * @throws IOException
	 */
	public static String post(String url) throws IOException {
		return post(url,null,null);
	}
	/**
	 * 使用get方式提交http请求，并返回响应内容
	 * @param url 请求地址
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @return http响应内容
	 * @throws IOException
	 */
	public static String get(String url,String charsetName) throws IOException{
		return send(url, null, StringUtils.isEmpty(charsetName) ? "UTF-8" : charsetName, false,null);
	}
	/**
	 * 使用get方式提交http请求，并返回响应内容
	 * <p>通过子线程执行，不堵塞主线程</p>
	 * @param url 请求地址
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @throws IOException
	 */
	public static void getAsync(String url,String charsetName){
		sendAsync(url, null, StringUtils.isEmpty(charsetName) ? "UTF-8" : charsetName, false,null);
	}
	/**
	 * 使用get方式提交http请求，并返回响应内容
	 * @param url 请求地址
	 * @param charsetName 用来读取返回内容的字符集编码，默认使用UTF-8
	 * @return http响应内容
	 * @throws IOException
	 */
	public static String get(String url) throws IOException{
		return get(url,null);
	}		

	/**
	 * 读取流中的字符串
	 * @param in 输入流
	 * @param charsetName 字符集
	 * @return 输入流中读取出来的字符串
	 * @throws IOException
	 */
	public static String readString(InputStream in,String charsetName) throws IOException{
		StringBuffer sb=new StringBuffer();
		BufferedReader br = new BufferedReader(new InputStreamReader(in,charsetName));
		String line = "";
		while((line=br.readLine())!=null)
			sb.append(line);
		br.close();
		return sb.toString();
	}
}
class AsyncHttpSender extends Thread
{
	String url;
	String params;
	String charsetName;
	boolean isPost;
	Map<String, String> requestHeaders;	

	public void setUrl(String url) {
		this.url = url;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public void setCharsetName(String charsetName) {
		this.charsetName = charsetName;
	}

	public void setPost(boolean isPost) {
		this.isPost = isPost;
	}

	public void setRequestHeaders(Map<String, String> requestHeaders) {
		this.requestHeaders = requestHeaders;
	}

	public void run(){
		try{
		URL client = new URL(url);
		HttpURLConnection con = (HttpURLConnection) client.openConnection();		
		con.setConnectTimeout(30000);
		con.setReadTimeout(30000);
		if(StringUtils.isEmpty(charsetName))
			charsetName = Charset.defaultCharset().name();
		con.setRequestProperty("Accept-Charset", charsetName);
		con.setRequestProperty("Content-Type", "text/xml");
		if(requestHeaders!=null && !requestHeaders.isEmpty()){
			for (String key : requestHeaders.keySet()) {
				con.setRequestProperty(key, requestHeaders.get(key));
			}
		}		
		con.setDoInput(true);
		con.setUseCaches(false);
		
		if(isPost){
			con.setDoOutput(true);
			con.setRequestMethod("POST");
		}else{
			con.setRequestMethod("GET");
		}		
		con.setInstanceFollowRedirects(true);
		//设置post参数
		if(isPost && StringUtils.isNotEmpty(params)){			
			con.setRequestProperty("Content-Length", String.valueOf(params.getBytes(charsetName).length));
			OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream(),charsetName);
			out.write(params);
			out.flush();
			out.close();
		}				
		con.connect();		
		
//		//读取返回内容		
//		InputStream input = con.getInputStream();
//		StringBuffer sb=new StringBuffer();
//		BufferedReader br = new BufferedReader(new InputStreamReader(in,charsetName));
//		String line = "";
//		while((line=br.readLine())!=null)
//			sb.append(line);
//		br.close();
//		sb.toString();
		}catch(Exception ex){
			LogLog.error("AsyncHttpSender error", ex);			
		}
	}

};
