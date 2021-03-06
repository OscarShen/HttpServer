package server.demo4;

import java.util.ArrayList;
import java.util.List;

/**
 * 用来存mapping
 * 
 * @author ruiyao.shen
 *
 */
public class Mapping {
	private String name;
	private List<String> urlPattern;

	public Mapping() {
		urlPattern = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUrlPattern() {
		return urlPattern;
	}

	public void setUrlPattern(List<String> urlPattern) {
		this.urlPattern = urlPattern;
	}

}
