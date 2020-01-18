package mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MessageProcess implements CommandProcess {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse respose) throws Throwable {
		request.setAttribute("message", "요청 파라미터로 명령어를 전달");
		return "/mvc/process.jsp";
	}
	
}
