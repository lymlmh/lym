package cn.edu.guet.lym.util;

import java.util.HashMap;
import java.util.Map;

public class WXBizMsgCryptManager {
	public static final String sToken = "A8888888888888888888A";  
    /** 
     * replace the sCorpID to your corp id 
     */  
    public static final String sCorpID = "wxb1c3c306c7bf95ff";  
    public static final String sEncodingAESKey = "A88888888888888888888888888888888888888888A";  
      
    private static Map<String, WXBizMsgCrypt> crypts = new HashMap<String, WXBizMsgCrypt>();  
  
    public static WXBizMsgCrypt getChatWXBizMsgCrypt(){  
        return getWXBizMsgCrypt(sToken, sEncodingAESKey, sCorpID);  
    }  
  
    public static WXBizMsgCrypt getWXBizMsgCrypt(String token, String encodingAesKey, String corpId) {  
        WXBizMsgCrypt wxcpt = null;  
        String key = corpId + "-" + token;  
        if (crypts.containsKey(key)) {  
            wxcpt = crypts.get(key);  
        } else {  
            try {  
                wxcpt = new WXBizMsgCrypt(token, encodingAesKey, corpId);     
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return wxcpt;  
    }  
}
