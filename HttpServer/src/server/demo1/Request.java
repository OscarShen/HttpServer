package server.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装request
 * 
 * @author ruiyao.shen
 *
 */
public class Request {
	// 请求方式
	private String method;
	// 请求资源
	private String url;
	// 请求参数
	private Map<String, List<String>> parameterMapValue;

	// 内部
	public static final String CRLF = "\r\n";
	private InputStream is;
	private String requestInfo;

	public Request() {
		method = "";
		url = "";
		parameterMapValue = new HashMap<>();
		requestInfo = "";
	}

	public Request(InputStream is) {
		this();
		this.is = is;
		try {
			byte[] data = new byte[20480];
			int len = is.read(data);
			requestInfo = new String(data, 0, len);
			parseRequestInfo();
		} catch (IOException e) {
			return;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("\n".getBytes().length);
		System.out.println("\r\n".getBytes().length);
		System.out.println("a".getBytes().length);
		System.out.println("你".getBytes().length);
	}

	// 分析头信息
	private void parseRequestInfo() {
		if (null == requestInfo || (requestInfo = requestInfo.trim()).equals("")) {
			return;
		}

		/**
		 * 从信息的首行分解出：请求方式、请求路径、请求参数（get可能存在） GET
		 * /index.html?uname=asf&pwd=sdfsdf HTTP/1.1
		 * 
		 * 如果为post方式，请求参数可能在最后正文中
		 */
		String paramString = "";// 接收请求参数

		// 1、获取请求方式
		String firstLine = requestInfo.substring(0, requestInfo.indexOf(CRLF));
		int idx = requestInfo.indexOf("/");// /的位置
		this.method = firstLine.substring(0, idx).trim();
		String urlStr = firstLine.substring(idx, firstLine.indexOf("HTTP/")).trim();
		if (this.method.equalsIgnoreCase("post")) {
			this.url = urlStr;
			paramString = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
		} else if (this.method.equalsIgnoreCase("get")) {
			if (urlStr.contains("?")) {// 是否存在参数
				String[] urlArray = urlStr.split("\\?");
				this.url = urlArray[0];
				paramString = urlArray[1];
			}
		}

		// 2、将请求参数封装到map中
	}
}
