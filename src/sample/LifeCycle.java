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
        System.out.println("LifeServlet의 생성자 호출됨.....");
    }

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("init() 호출됨.....");
	}

	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("destory() 호출됨.....");
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service() 호출됨.....");
	}//원래는 서비스가 doGet이랑 doPost를 분기해주는데 오버라이딩 한 상황에서 안에 분기하는 코드를 쓰지 않았고 그래서 분기 되지 않는다.

}
