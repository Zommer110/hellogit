package mvc.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.control.ActionForward;

public class IndexAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("IndexActionÀÇ execute() ¼öÇàµÊ!");
		return new ActionForward("index.jsp",false);
	}	
}
