package cn.edu.guet.lym.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.edu.guet.lym.domain.Department;
import cn.edu.guet.lym.domain.Employee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AddressBookApi {
	private static final Logger logger=LoggerFactory.getLogger(AddressBookApi.class);
	
	/**
	 * 创建部门
	 * @param d
	 * @return
	 */
	public static JSONObject createDept(Department d){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+
				WechatApi.getAccessToken();
		JSONObject params=new JSONObject();
		params.put("name", d.getName());
		params.put("parentid", d.getParentid());
		params.put("order", d.getSequence());
		params.put("id", d.getId());
		try {
			String result = HttpClient.post(reqUrl, params.toString());
			logger.info("创建部门返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			return resultJson;
		} catch (Exception e) {
			logger.info("创建部门异常",e);
		}
		return null;
	}
	/**
	 * 更新部门
	 * @param d
	 * @return
	 */
	public static JSONObject updateDept(Department d){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+
				WechatApi.getAccessToken();
		JSONObject params=new JSONObject();
		params.put("name", d.getName());
		
		params.put("order", d.getSequence());
		params.put("id", d.getId());
		if(d.getId()==1)
		{
			//params.put("parentid", d.getParentid());
		}
		else{
			params.put("parentid", d.getParentid());
		}
		JSONObject resultJson=new JSONObject();
		try {
			String result = HttpClient.post(reqUrl, params.toString());
			logger.info("更新部门返回信息："+result);
			resultJson = JSONObject.parseObject(result);
		} catch (Exception e) {
			logger.info("更新部门异常",e);
		}
		return resultJson;
	}
	
	/**
	 * 发送消息
	 * 
	 * 
	 */
/*	public static JSONObject sendmsg(String msg){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN"+
				WechatApi.getAccessToken();
		JSONObject params=new JSONObject();
		//JSONObject texts=new JSONObject();
		//texts.put("content", msg);
		params.put("msgtype", "text");
		params.put("agentid", "7");
		params.put("content", msg);
		JSONObject resultJson=new JSONObject();
		String group1data = "{\"filter\":{\"is_to_all\":false,\"agentid\":\"7\"},\"text\":{\"content\":\"群发消息测试\"},\"msgtype\":\"text\"}\";";
		try {
			String result = HttpClient.post(reqUrl, params.toString());
			logger.info("更新部门返回信息："+result);
			resultJson = JSONObject.parseObject(result);
		} catch (Exception e) {
			logger.info("更新部门异常",e);
		}
		return resultJson;
	}*/
	
	/**
	 * 删除部门
	 * @param id
	 * @return
	 */
	public static JSONObject deleteDept(String id){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+
				WechatApi.getAccessToken()+"&id="+id;
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("删除部门返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				return resultJson;
			}
		} catch (Exception e) {
			logger.info("删除部门异常",e);
		}
		return null;
	}
	/**
	 * 获取部门列表
	 * @param id	部门ID 1为根部门
	 * @return
	 */
	public static List<Department> listDept(String id){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+WechatApi.getAccessToken()+"&id="+id;
		List<Department> list=new ArrayList<>();
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("获取部门列表返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				JSONArray jsonArray = resultJson.getJSONArray("department");
				for (int i = 0; i < jsonArray.size(); i++) {
					Department d=new Department();
					JSONObject jsonObject = jsonArray.getJSONObject(i);
					d.setName(jsonObject.getString("name"));
					d.setId(jsonObject.getInteger("id"));
					d.setSequence(jsonObject.getInteger("order"));
					d.setParentid(jsonObject.getInteger("parentid"));
					list.add(d);
				}
			}
		} catch (Exception e) {
			logger.info("获取部门列表异常",e);
		}
		return list;
	}
	/**
	 * 创建成员
	 * @param e
	 * @return
	 */
	public static boolean createUser(Employee e){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+
				WechatApi.getAccessToken();
		JSONObject params=new JSONObject();
		params.put("userid", e.getUserid());
		params.put("name", e.getName());
		params.put("department", e.getDepartment());
		params.put("position", e.getPosition());
		params.put("mobile", e.getMobile());
		params.put("gender", e.getGender());
		params.put("email", e.getEmail());
		//params.put("weixinid", e.getWeixinid());
		try {
			String result = HttpClient.post(reqUrl, params.toString());
			logger.info("创建成员返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				return true;
			}
		} catch (Exception e1) {
			logger.info("创建成员异常",e1);
		}
		return false;
	}
	/**
	 * 更新成员
	 * @param e
	 * @return
	 */
	public static boolean updateUser(Employee e){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+
				WechatApi.getAccessToken();
		JSONObject params=new JSONObject();
		params.put("userid", e.getUserid());
		params.put("name", e.getName());
		params.put("department", e.getDepartment());
		params.put("position", e.getPosition());
		params.put("mobile", e.getMobile());
		params.put("gender", e.getGender());
		params.put("email", e.getEmail());
		//params.put("weixinid", e.getWeixinid());
		try {
			logger.info("更新成员请求信息："+params.toString());
			String result = HttpClient.post(reqUrl, params.toString());
			logger.info("更新成员返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				return true;
			}
		} catch (Exception e1) {
			logger.info("更新成员异常",e1);
		}
		return false;
	}
	/**
	 * 删除成员
	 * @param userid
	 * @return
	 */
	public static JSONObject daleteUser(String userid){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token=ACCESS_TOKEN&userid=USERID";
		reqUrl=reqUrl.replace("ACCESS_TOKEN", WechatApi.getAccessToken())
				.replace("USERID", userid);
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("删除成员返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				return resultJson;
			}
		} catch (Exception e1) {
			logger.info("删除成员异常",e1);
		}
		return null;
	}
	/**
	 * 获取成员
	 * @param userid
	 * @return
	 */
	public static Employee getUser(String userid){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+
				WechatApi.getAccessToken()+"&userid="+userid;
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("获取成员返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				Employee e=new Employee();
				e.setUserid(resultJson.getString("userid"));
				e.setName(resultJson.getString("name"));
				JSONArray jsonArray = resultJson.getJSONArray("department");
				List<Integer> deptid =new ArrayList<>();
				for (int j = 0; j < jsonArray.size(); j++) {
					deptid.add(jsonArray.getInteger(j));
				}
				e.setDepartment(deptid);
				e.setPosition(resultJson.getString("position"));
				e.setMobile(resultJson.getString("mobile"));
				e.setGender(Integer.valueOf(resultJson.getString("gender")));
				e.setEmail(resultJson.getString("email"));
				//e.setWeixinid(resultJson.getString("weixinid"));
				e.setAvatar(resultJson.getString("avatar"));
				e.setStatus(resultJson.getInteger("status"));
				return e;
			}
		} catch (Exception e1) {
			logger.info("获取成员异常",e1);
		}
		return null;
	}
	/**
	 * 获取部门所有成员(简单信息)
	 * @param department_id	 部门id 
	 * @param fetch_child	1/0：是否递归获取子部门下面的成员 
	 * @param status	 	0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4 
	 * @return
	 */
	public static List<Employee> getUserSimpleList(String department_id,String fetch_child ,
			int[] status ){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";
		reqUrl=reqUrl.replace("ACCESS_TOKEN", WechatApi.getAccessToken())
				.replace("DEPARTMENT_ID", department_id)
				.replace("FETCH_CHILD", fetch_child)
				.replace("STATUS", status.toString());
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("获取部门成员返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				List<Employee> list=new ArrayList<>();
				JSONArray userlist = resultJson.getJSONArray("userlist");
				for (int i = 0; i < userlist.size(); i++) {
					JSONObject jsonObject = userlist.getJSONObject(i);
					Employee e=new Employee();
					e.setUserid(jsonObject.getString("userid"));
					e.setName(jsonObject.getString("name"));
					JSONArray jsonArray = jsonObject.getJSONArray("department");
					List<Integer> deptid =new ArrayList<>();
					for (int j = 0; j < jsonArray.size(); j++) {
						deptid.add(jsonArray.getInteger(j));
					}
					e.setDepartment(deptid);
					list.add(e);
				}
				return list;
			}
		} catch (Exception e1) {
			logger.info("获取部门成员异常",e1);
		}
		return null;
	}
	
	/**
	 * 获取部门所有成员(详情)
	 * @param department_id	 部门id 
	 * @param fetch_child	1/0：是否递归获取子部门下面的成员 
	 * @param status	 	0获取全部成员，1获取已关注成员列表，2获取禁用成员列表，4获取未关注成员列表。status可叠加，未填写则默认为4 
	 * @return
	 */
	public static List<Employee> getUserList(String department_id,String fetch_child ,
			int[] status ){
		String reqUrl="https://qyapi.weixin.qq.com/cgi-bin/user/list?access_token=ACCESS_TOKEN&department_id=DEPARTMENT_ID&fetch_child=FETCH_CHILD&status=STATUS";
		reqUrl=reqUrl.replace("ACCESS_TOKEN", WechatApi.getAccessToken())
				.replace("DEPARTMENT_ID", department_id)
				.replace("FETCH_CHILD", fetch_child)
				.replace("STATUS", status.toString());
		try {
			String result = HttpClient.get(reqUrl);
			logger.info("获取部门成员详情返回信息："+result);
			JSONObject resultJson = JSONObject.parseObject(result);
			if(resultJson.getInteger("errcode")==0){
				List<Employee> list=new ArrayList<>();
				JSONArray userlist = resultJson.getJSONArray("userlist");
				for (int i = 0; i < userlist.size(); i++) {
					JSONObject jsonObject = userlist.getJSONObject(i);
					Employee e=new Employee();
					e.setUserid(jsonObject.getString("userid"));
					e.setName(jsonObject.getString("name"));
					JSONArray jsonArray = jsonObject.getJSONArray("department");
					List<Integer> deptid =new ArrayList<>();
					for (int j = 0; j < jsonArray.size(); j++) {
						deptid.add(jsonArray.getInteger(j));
					}
					e.setDepartment(deptid);
					e.setPosition(jsonObject.getString("position"));
					e.setMobile(jsonObject.getString("mobile"));
					e.setGender(Integer.valueOf(jsonObject.getString("gender")));
					e.setEmail(jsonObject.getString("email"));
					e.setWeixinid(jsonObject.getString("weixinid"));
					e.setAvatar(jsonObject.getString("avatar"));
					e.setStatus(jsonObject.getInteger("status"));
					list.add(e);
				}
				return list;
			}
		} catch (Exception e1) {
			logger.info("获取部门成员详情异常",e1);
		}
		return null;
	}
	
/*	public void send(String msg)
	{
		try {
			WechatApi.sendActivityMsg(msg,7);
		} catch (Exception e) {
			logger.error("删除角色异常",e);
		}
	}*/
}
