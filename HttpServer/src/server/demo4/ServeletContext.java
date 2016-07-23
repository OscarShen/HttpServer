package server.demo4;

import java.util.HashMap;
import java.util.Map;

/**
 * 上下文
 * 
 * @author ruiyao.shen
 *
 */
public class ServeletContext {
	// 为每一个servlet取个别名
	// login-->server.demo3.LoginServelet
	private Map<String, String> servlet;
	// url-->login
	// /log-->login
	// /log-->login
	private Map<String, String> mapping;

	public Map<String, String> getServlet() {
		return servlet;
	}

	public void setServlet(Map<String, String> servlet) {
		this.servlet = servlet;
	}

	public Map<String, String> getMapping() {
		return mapping;
	}

	public void setMapping(Map<String, String> mapping) {
		this.mapping = mapping;
	}

	public ServeletContext() {
		servlet = new HashMap<>();
		mapping = new HashMap<>();
	}
}
