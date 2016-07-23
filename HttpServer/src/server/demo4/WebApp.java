package server.demo4;

import java.util.Map;

public class WebApp {
	private static ServeletContext context;
	static {
		context = new ServeletContext();

		Map<String, String> mapping = context.getMapping();
		mapping.put("/login", "login");
		mapping.put("/log", "login");
		mapping.put("/reg", "register");

		Map<String, String> servlet = context.getServlet();
		servlet.put("login", "server.demo3.LoginServlet");
		servlet.put("register", "server.demo3.RegisterServlet");
	}

	public static Servlet getServlet(String url)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (null == url || (url = url.trim()).equals("")) {
			return null;
		}
		// return context.getServlet().get(context.getMapping().get(url));
		// 根据字符串（完整路径）创建对象
		String name = context.getServlet().get(context.getMapping().get(url));
		Class<?> clazz = Class.forName(name);
		return (Servlet) clazz.newInstance();
	}
}
