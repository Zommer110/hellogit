package mvcMem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;

public class LoginProcAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentDAO dao = StudentDAO.getInstance();
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		int check = dao.loginCheck(id, pass);
		if(check == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("loginID", id);
		} else if(id != null && pass != null) {
			request.setAttribute("check", check);
		}
		return new ActionForward("member.mdo?cmd=login",false);
	}
	
}
