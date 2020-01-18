package sample;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParam extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String company;
	private String manager;
	private String tel;
	private String email;    
	
    public InitParam() {
        super();
        // TODO Auto-generated constructor stub
    }    

	@Override
	public void init() throws ServletException {
		System.out.println("�ʱ�ȭ �޼��� �����");
		//ServletContext�� �ʱ� �Ķ���� �� �б�
		company = getServletContext().getInitParameter("company");
		manager = getServletContext().getInitParameter("manager");
		//ServletConfig�� �ʱ� �Ķ���� �� �б�
		tel = getServletConfig().getInitParameter("tel");
		email = getServletConfig().getInitParameter("email");
	}
	
	protected void processRequest(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<body>");
			out.println("<li>ȸ��� : " + company + "</li>");
			out.println("<li>����� : " + manager + "</li>");
			out.println("<li>��ȭ��ȣ : " + tel + "</li>");
			out.println("<li>�̸��� : " + email + "</li>");
			out.println("</body>");
			out.println("</html>");
		} finally {
			
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

}
