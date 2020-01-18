package mvcMem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;

public class DeleteProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		StudentDAO dao = StudentDAO.getInstance();
		HttpSession session = request.getSession();
		String loginID = (String)session.getAttribute("loginID");
		String pass = request.getParameter("pass");
		int result = dao.deleteMember(loginID, pass);
		if(result != 0) {
			session.invalidate();
		}
		request.setAttribute("result", result);
		return new ActionForward("/mvcMem/deleteProc.jsp",false);
	}
	
}
