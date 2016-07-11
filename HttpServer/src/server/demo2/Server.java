package server.demo2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器，并启动
 * 
 * @author Administrator
 *
 */
public class Server {
	ServerSocket serverSocket;

	public static void main(String[] args) {
		Server server = new Server();
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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void stop() {

	}
}
