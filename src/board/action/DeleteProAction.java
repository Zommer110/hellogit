package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.BoardDAO;

public class DeleteProAction implements CommandAction {

	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		request.setCharacterEncoding("utf-8");
		int num = Integer.parseInt(request.getParameter("num"));
		String pageNum = request.getParameter("pageNum");
		String pass = request.getParameter("pass");
		BoardDAO dbPro = BoardDAO.getInstance();
		int check = dbPro.deleteArticle(num, pass);
		//해당 뷰에서 사용할 속성
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("check", new Integer(check));
		return "/board/deletePro.jsp";
	}

}
