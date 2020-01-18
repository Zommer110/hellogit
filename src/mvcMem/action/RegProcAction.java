package mvcMem.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvcMem.control.ActionForward;
import mvcMem.model.StudentDAO;
import mvcMem.model.StudentVo;

public class RegProcAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		StudentDAO dao = StudentDAO.getInstance();
		StudentVo vo = new StudentVo(
				request.getParameter("id"),
				request.getParameter("pass"),
				request.getParameter("name"),
				request.getParameter("phone1"),
				request.getParameter("phone2"),
				request.getParameter("phone3"),
				request.getParameter("email"),
				request.getParameter("zipcode"),
				request.getParameter("address1"),
				request.getParameter("address2"));
		boolean flag = dao.memberInsert(vo);
		request.setAttribute("flag", flag);
		return new ActionForward("/mvcMem/regProc.jsp",false);
	}
	
}
