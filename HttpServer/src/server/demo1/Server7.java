package server.demo1;

import java.io.IOException;
import java.net.ServerSocket;

import util.CloseUtil;

/**
 * 创建服务器，并启动 1、请求，并响应
 * 
 * @author Administrator
 *
 */
public class Server7 {
	ServerSocket serverSocket;
	public static String CRLF = "\r\n";
	public static String BLANK = " ";
	private boolean isShutdown = false;

	public static void main(String[] args) {
		Server7 server = new Server7();
		server.start();
	}

	/**
	 * 启动方法
	 */
	public void start() {
		start(8000);
	}

	/**
	 * 指定端口的启动方法
	 */
	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
			this.receive();
		} catch (IOException e) {
			stop();
		}
	}

	/**
	 * 接收客户端
	 */
	public void receive() {
		try {
			while (!isShutdown) {
				new Thread(new Dispatcher(serverSocket.accept())).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止客户端
	 */
	public void stop() {
		isShutdown = true;
		CloseUtil.closeSocket(serverSocket);
	}
}
