package cn.edu.guet.lym.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import cn.edu.guet.lym.util.HttpClient;
import cn.edu.guet.lym.domain.Employee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class WechatApi {
	private static final Logger logger=LoggerFactory.getLogger(WechatApi.class);
	public static final String CORPID="wxb1c3c306c7bf95ff";
	private static final String CORPSECRET="Z6StVG5p89USLeKO73s-VRppegKTdqRxTkkZXAYFXK2Q6nky1kHgLVkirCvT8Es_";
	private static String accessToken="";
	public static Long oauth2_expires_in = 0L;
	
	
	/**
	 * 获取微信accessToken
	 * @return
	 */
	public static String getAccessToken() {
		if (System.currentTimeMillis() >= oauth2_expires_in) {
			String requestUrl="https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+CORPID+
					"&corpsecret="+CORPSECRET;
			try {
				String result = HttpClient.post(requestUrl);
				JSONObject jsonObject = JSONObject.parseObject(result);
				if(jsonObject.getString("access_token")!=null){
					accessToken=jsonObject.getString("access_token");
					oauth2_expires_in = System.currentTimeMillis()
							+ (jsonObject.getLong("expires_in") - 120) * 1000;
				}
				logger.info("accessToken="+accessToken);
			} catch (IOException e) {
				logger.error("请求微信accessToken异常",e);
			}
		}
		return accessToken;
	}
	/**
	 * 根据code获取成员信息
	 * @param access_token
	 * @param code
	 * @return
	 */
	public static JSONObject getUserInfo(String access_token, String code) {
		String requestUrl="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+
				access_token+"&code="+code;
		try {
			logger.info("请求微信userinfo url："+requestUrl);
			String result = HttpClient.post(requestUrl);
			logger.info("请求微信userinfo返回信息："+result);
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject!=null){
				return jsonObject;
			}
		} catch (IOException e) {
			logger.error("请求微信userinfo异常",e);
		}
		return null;
	}
	/**
	 * 使用user_ticket获取成员详情
	 * @param user_ticket
	 * @return
	 */
	public static Employee getUserDetail(String user_ticket) {
		String accessToken = getAccessToken();
		String requestUrl="https://qyapi.weixin.qq.com/cgi-bin/user/getuserdetail?access_token="+accessToken;
		Employee employee=new Employee();
		JSONObject params=new JSONObject();
		params.put("user_ticket", user_ticket);
		try {
			logger.info("请求入参:"+params.toString());
			String result = HttpClient.post(requestUrl,params.toString());
			logger.info("请求微信userinfoDetail返回信息："+result);
			JSONObject jsonObject = JSONObject.parseObject(result);
			if(jsonObject!=null){
				employee.setGender(jsonObject.getInteger("gender"));
				employee.setEmail(jsonObject.getString("email"));
				employee.setMobile(jsonObject.getString("mobile"));
				employee.setPosition(jsonObject.getString("position"));
				employee.setUserid(jsonObject.getString("userid"));
				employee.setName(jsonObject.getString("name"));
				employee.setAvatar(jsonObject.getString("avatar"));
				JSONArray jsonArray = jsonObject.getJSONArray("department");
				List<Integer> deptid =new ArrayList<>();
				for (int j = 0; j < jsonArray.size(); j++) {
					deptid.add(jsonArray.getInteger(j));
				}
				employee.setDepartment(deptid);
			}
		} catch (IOException e) {
			logger.error("请求微信userinfo异常",e);
		}
		return employee;
	}
	public static String getGender(Integer gender) {
		String sex="";
		switch (gender) {
		case 1:
			sex="男性";
			break;
		case 2:
			sex="女性";
			break;
		default:
			sex="未定义";
			break;
		}
		return sex;
	}
	
	/**
	 * 生成二维码
	 * @param scene_str
	 * @return
	 */
	public static String buildSweep(String scene_str){
		
		return "";
	}
	
	 public static void sendActivityMsg(String msg,int i,String userid){
		 String accessToken = getAccessToken();
		 String requestUrl="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+accessToken; 
		 JSONObject params=new JSONObject();
		 JSONObject text=new JSONObject();
		 params.put("touser",userid);
		 params.put("agentid", i);
		 params.put("msgtype", "text");
		 text.put("content", msg);
		 params.put("text", text);
		 params.put("safe", 0);
		 
		 try {
			 logger.info("请求入参:"+params.toString());
			 String result = HttpClient.post(requestUrl,params.toString());
			 logger.info("请求微信发送消息返回信息："+result);
			 //JSONObject jsonObject = JSONObject.parseObject(result);
		 } catch (Exception e) {
			 logger.error("请求微信发送消息异常",e);
		 }
		 
	 }
}
