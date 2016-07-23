package server.demo1;

import java.io.IOException;
import java.net.Socket;

import util.CloseUtil;

/**
 * 一个响应与请求 对应一个Dispatcher对象
 * 
 * @author ruiyao.shen
 *
 */
public class Dispatcher implements Runnable {
	private Socket socket;
	private Request req;
	private Response rep;
	private int code = 0;

	public Dispatcher(Socket socket) {
		this.socket = socket;
		this.code = 200;
		try {
			req = new Request(socket.getInputStream());
			rep = new Response(socket.getOutputStream());
		} catch (IOException e) {
			this.code = 500;
			return;
		}
	}

	@Override
	public void run() {
		Servlet serv = new Servlet();
		serv.service(req, rep);
		try {
			rep.pushToClient(code);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			rep.pushToClient(500);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CloseUtil.closeSocket(socket);
	}

}
