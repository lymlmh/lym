package cn.edu.guet.lym.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Consts {
	@Value("${baseurl}")
	private String baseurl;
	public static final String ASSETS_TOKEN="A8888888888888888888A";
	public static final String ASSETS_ENCODINGAESKEY="A88888888888888888888888888888888888888888A";
	//public static final String BASE_URL="http://lmh.ngrok.cc/lym/";
	public static final String BASE_URL="http://www.liyuanming.me/lym/";
}
