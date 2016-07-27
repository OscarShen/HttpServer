package server.demo4;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class WebApp {
	private static ServeletContext context;
	static {
		try {
			// 获取解析工厂
			SAXParserFactory factory = SAXParserFactory.newInstance();
			// 获取解析器
			SAXParser sax = factory.newSAXParser();
			// 指定xml+处理器
			WebHandler web = new WebHandler();
			sax.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("server/demo4/web.xml"), web);

			// 将list 转成map
			context = new ServeletContext();
			Map<String, String> servlet = context.getServlet();
			for (Entity entity : web.getEntityList()) {
				servlet.put(entity.getName(), entity.getClazz());
			}

			Map<String, String> mapping = context.getMapping();
			for (Mapping mapp : web.getMappingList()) {
				List<String> urls = mapp.getUrlPattern();
				for (String url : urls) {
					mapping.put(url, mapp.getName());
				}
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

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
