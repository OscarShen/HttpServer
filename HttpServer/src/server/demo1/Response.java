package server.demo1;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

import util.CloseUtil;

public class Response {
	// 两个常量
	public static String CRLF = "\r\n";
	public static String BLANK = " ";
	// 流
	private BufferedWriter bw;
	// 正文
	private StringBuilder content;
	// 存储头信息
	private StringBuilder headInfo;
	// 存储正文长度
	private int len;

	public Response() {
		headInfo = new StringBuilder();
		len = 0;
		content = new StringBuilder();
	}

	public Response(OutputStream os) {
		this();
		bw = new BufferedWriter(new OutputStreamWriter(os));
	}

	public Response(Socket socket) {
		this();
		try {
			bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (IOException e) {
			headInfo = null;
		}
	}

	/**
	 * 构建信息
	 * 
	 * @param info
	 * @return
	 */
	public Response println(String info) {
		content.append(info).append(CRLF);
		len += (info + CRLF).getBytes().length;
		return this;
	}

	/**
	 * 推送到客户端
	 * 
	 * @param code
	 * @throws IOException
	 */
	void pushToClient(int code) throws IOException {
		if (headInfo == null) {
			code = 500;
		}
		createHeadInfo(code);
		// 头信息+分隔符
		bw.append(headInfo);
		// 正文
		bw.append(content);
		bw.flush();
	}

	public void close() {
		CloseUtil.closeIO(bw);
	}

	/**
	 * 构建响应头
	 */
	private void createHeadInfo(int code) {
		// 1)HTTP协议版本、状态代码、描述
		headInfo.append("HTTP/1.1").append(BLANK).append(code).append(BLANK);
		switch (code) {
		case 200:
			headInfo.append("OK");
			break;
		case 404:
			headInfo.append("NOT FOUND");
			break;
		case 505:
			headInfo.append("SEVER ERROR");
			break;
		}
		headInfo.append(CRLF);
		// 2)响应头(ResponseHead)
		headInfo.append("Server:Oscar Server/0.1").append(CRLF);
		headInfo.append("Date:").append(new Date()).append(CRLF);
		headInfo.append("Content-type:text/html;charset=UTF8").append(CRLF);
		// 正文长度，字节长度
		headInfo.append("Content-Length:").append(len).append(CRLF);
		headInfo.append(CRLF);
		System.out.println(headInfo);
	}
}
