package server.demo1;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器，并启动 1、请求，并响应
 * 
 * @author Administrator
 *
 */
public class Server5 {
	ServerSocket serverSocket;
	public static String CRLF = "\r\n";
	public static String BLANK = " ";

	public static void main(String[] args) {
		Server5 server = new Server5();
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

			InputStream is = socket.getInputStream();
			Request req = new Request(is);

			Response rep = new Response(socket);
			rep.println("<html><head><title>HTTP响应</title>");
			rep.println("</head><body>");
			rep.println("欢迎：").println(req.getParameter("uname")).println("回来");
			rep.println("</body></html>");
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
