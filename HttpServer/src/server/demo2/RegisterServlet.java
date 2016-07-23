package server.demo2;

public class RegisterServlet extends Servlet {

	@Override
	public void doGet(Request req, Response rep) throws Exception {

	}

	@Override
	public void doPost(Request req, Response rep) throws Exception {
		rep.println("<html><head><title>返回注册</title>");
		rep.println("</head><body>");
		rep.println("你的用户为：").println(req.getParameter("uname")).println("回来");
		rep.println("</body></html>");
	}

}
