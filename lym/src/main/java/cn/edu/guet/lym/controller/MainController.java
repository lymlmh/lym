package cn.edu.guet.lym.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.guet.lym.util.Consts;
import cn.edu.guet.lym.util.WXBizMsgCrypt;
import cn.edu.guet.lym.util.WXBizMsgCryptManager;
import cn.edu.guet.lym.util.WechatApi;

@Controller
@RequestMapping("main")
public class MainController {
	private static final Logger logger=LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value="weixin",method = RequestMethod.GET)
	public void index(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			/*String msg_signature=URLDecoder.decode(request.getParameter("msg_signature"),"UTF-8");
			String timestamp=URLDecoder.decode(request.getParameter("timestamp"),"UTF-8");
			String nonce=URLDecoder.decode(request.getParameter("nonce"),"UTF-8");
			String echostr=URLDecoder.decode(request.getParameter("echostr"),"UTF-8");*/
			String msg_signature=request.getParameter("msg_signature");
			String timestamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			String echostr=request.getParameter("echostr");
			System.out.println("msg_signature="+msg_signature+"|timestamp="+timestamp+"|nonce="+nonce+
					"|echostr="+echostr);
			String sEchoStr="";
			//WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Consts.ASSETS_TOKEN, Consts.ASSETS_ENCODINGAESKEY, WechatApi.CORPID);
			//sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp,timestamp, echostr);
			 WXBizMsgCrypt wxcpt = WXBizMsgCryptManager.getChatWXBizMsgCrypt(); 
			 sEchoStr = wxcpt.VerifyURL(msg_signature, timestamp, nonce, echostr);  
			response.getWriter().print(sEchoStr);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	@RequestMapping(value="weixin",method = RequestMethod.POST)
	public void doPost(HttpServletRequest request,HttpServletResponse response){
		try {
			request.setCharacterEncoding("utf-8");
			String msg_signature=request.getParameter("msg_signature");
			String timestamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			//System.out.println("msg_signature="+msg_signature+"|timestamp="+timestamp+"|nonce="+nonce);
			String sEchoStr="";
			String postData="";
			String rquestResult = getRquestResult(request);
			//System.out.println(rquestResult);
			postData = getTextForXml(rquestResult,"Encrypt");
			//System.out.println(postData);
			//Object[] extract = XMLParse.extract(rquestResult);
			WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(Consts.ASSETS_TOKEN, Consts.ASSETS_ENCODINGAESKEY, WechatApi.CORPID);
			sEchoStr = wxcpt.DecryptMsg(msg_signature, timestamp, nonce, postData);
			//System.out.println(sEchoStr);
			
			String FromUserName = getTextForXml(sEchoStr,"FromUserName");
			String returnMsg = returnMsg(FromUserName);
			//System.out.println("returnMsg"+returnMsg);
			String encryptMsg = wxcpt.EncryptMsg(returnMsg, "", getNonceStr());
			PrintWriter writer = response.getWriter();
			writer.write(encryptMsg);
			writer.flush();
			writer.close();
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	public String getNonceStr() {
		Random ran = new Random();
		String ranNumStr = ran.nextInt(100000000) + "";
		String encode = Base64.encodeBase64String(ranNumStr.getBytes());
		return org.apache.commons.lang.StringUtils.trim(encode);
	}
		public String getTextForXml(String sEchoStr,String xmlName) throws DocumentException{
			Document parseText = DocumentHelper.parseText(sEchoStr);
			List<Element> subEls = parseText.getRootElement().elements();
			String postData="";
			for(Element subEl : subEls){
				String text = subEl.getTextTrim();
				if(subEl.getName().equals(xmlName)){
					if(StringUtils.hasText(text)){
						postData=text;
					}
				}
			}
			return postData;
		}
		public String returnMsg(String FromUserName){
			String msg="<xml>"+
			   "<ToUserName><![CDATA[%s]]></ToUserName>"+
			   "<FromUserName><![CDATA[%s]]></FromUserName>"+
			   "<CreateTime>%s</CreateTime>"+
			   "<MsgType><![CDATA[news]]></MsgType>"+
			   "<ArticleCount>1</ArticleCount>"+
			   "<Articles>"+
			       "<item>"+
			           "<Title><![CDATA[%s]]></Title>"+ 
			           "<Description><![CDATA[%s]]></Description>"+
			           "<PicUrl><![CDATA[%s]]></PicUrl>"+
			           "<Url><![CDATA[%s]]></Url>"+
			       "</item>"+
			   "</Articles>"+
			"</xml>";
			return String.format(msg, FromUserName,WechatApi.CORPID,System.currentTimeMillis(),
					"daohfaof","doahfa",Consts.BASE_URL+"style/pic02.png",Consts.BASE_URL+"404.jsp");
		}
	//解析请求中的数据为字符串
		public String getRquestResult(HttpServletRequest request) {
			try {
				InputStream inStream = request.getInputStream();
				ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = inStream.read(buffer)) != -1) {
					outSteam.write(buffer, 0, len);
				}
				outSteam.close();
				inStream.close();
				String resultStr = new String(outSteam.toByteArray(), "utf-8");
				return resultStr;
			} catch (Exception e) {
				logger.error("解析扫码支付回调地址异常", e);
			}
			return null;
		}

	
	@RequestMapping("sweep")
	public void sweep(){
		String scene_str="http://icmcc.mail.10086.cn/MailOffice/wap/unlogin/index";
		WechatApi.buildSweep(scene_str);
	}
}
