package sample;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LifeCycle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LifeCycle() {
        super();
        System.out.println("LifeServlet�� ������ ȣ���.....");
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init() ȣ���.....");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destory() ȣ���.....");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service() ȣ���.....");
	}//������ ���񽺰� doGet�̶� doPost�� �б����ִµ� �������̵� �� ��Ȳ���� �ȿ� �б��ϴ� �ڵ带 ���� �ʾҰ� �׷��� �б� ���� �ʴ´�.

}
