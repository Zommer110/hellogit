package board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction {	
	
	@Override
	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable {
		//����۰� �亯���� ����
		int num = 0, ref = 1, step = 0, depth = 0;
		try {
			if(request.getParameter("num") != null) {
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
