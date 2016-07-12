package server.demo1;

//import java.io.BufferedReaderder;
import java.io.BufferedWriter;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * 创建服务器，并启动 1、请求，并响应
 * 
 * @author Administrator
 *
 */
public class Server3 {
	ServerSocket serverSocket;
	public static String CRLF = "\r\n";
	public static String BLANK = " ";

	public static void main(String[] args) {
		Server3 server = new Server3();
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

	// private BufferedReader getReader(Socket socket) throws IOException {
	// return new BufferedReader(new
	// InputStreamReader(socket.getInputStream()));
	// }

	private BufferedWriter getWriter(Socket socket) throws IOException {
		return new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
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

			// 响应
			StringBuilder responseContext = new StringBuilder();
			responseContext.append("<html><head><title>HTTP响应</title></head><body>Hello Tomcat!</body></html>");

			StringBuilder response = new StringBuilder();
			// 1)HTTP协议版本、状态代码、描述
			response.append("HTTP/1.1").append(BLANK).append("200").append(BLANK).append("OK").append(CRLF);
			// 2)响应头(ResponseHead)
			response.append("Server:Oscar Server/0.1").append(CRLF);
			response.append("Date:").append(new Date()).append(CRLF);
			response.append("Content-type:text/html;charset=UTF8").append(CRLF);
			// 正文长度，字节长度
			response.append("Content-Length:").append(responseContext.toString().getBytes().length).append(CRLF);
			// 3)正文之前
			response.append(CRLF);
			// 4)正文
			response.append(responseContext);

			BufferedWriter bw = getWriter(socket);
			bw.write(response.toString());
			bw.flush();
			bw.close();

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
