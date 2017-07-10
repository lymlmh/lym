package cn.edu.guet.lym.util;

//封装操作成功后的信息的
public class ResultJson {
	private String msg;
	private boolean success=false;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ResultJson(String msg, boolean success) {
		this.msg = msg;
		this.success = success;
	}
	public ResultJson() {
	}
	public ResultJson(boolean success) {
		this.success = success;
	}
}
