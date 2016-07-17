package util;

import java.io.Closeable;
import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class CloseUtil {
	/**
	 * 使用泛型方法关闭IO流
	 * 
	 * @param io
	 */
	@SafeVarargs
	public static <T extends Closeable> void closeIO(T... io) {
		for (Closeable temp : io) {
			try {
				if (null != null) {
					temp.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void closeSocket(ServerSocket socket) {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeSocket(Socket socket) {
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void closeSocket(DatagramSocket socket) {
		if (socket != null) {
			socket.close();
		}
	}
}
