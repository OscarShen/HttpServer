package server.demo1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器，并启动 1、请求，并响应
 * 
 * @author Administrator
 *
 */
public class Server4 {
	ServerSocket serverSocket;
	public static String CRLF = "\r\n";
	public static String BLANK = " ";

	public static void main(String[] args) {
		Server4 server = new Server4();
		server.start();
	}

	/**
	 * 启动方法
	 */
	public void start() {
		try {
			serverSocket = new ServerSocket(8000);
			this.receive();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 接收客户端
	 */
	public void receive() {
		try {
			Socket socket = serverSocket.accept();

			byte[] data = new byte[20480];
			int len = socket.getInputStream().read(data);

			// 接收客户端的请求信息
			String requestInfo = new String(data, 0, len).trim();
			System.out.println(requestInfo);

			Response rep = new Response(socket);
			rep.println("<html><head><title>HTTP响应</title></head><body>Hello Tomcat!</body></html>");
			rep.pushToClient(200);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止客户端
	 */
	public void stop() {

	}
}
