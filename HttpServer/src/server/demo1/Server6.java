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
public class Server6 {
	ServerSocket serverSocket;
	public static String CRLF = "\r\n";
	public static String BLANK = " ";

	public static void main(String[] args) {
		Server6 server = new Server6();
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
			Servlet serv = new Servlet();
			Request req = new Request(socket.getInputStream());
			Response rep = new Response(socket.getOutputStream());

			serv.service(req, rep);

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
