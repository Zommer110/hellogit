package board.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.CommandProcess;

public class ControllerAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//명령어와 명령어 처리 클래스를 쌍으로 저장
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	//명령어와 처리클래스가 매핑되어 있는 properties 파일을 읽어서 Map객체인 commandMap에 저장
	//명령어와 처리클래스가 매핑되어 있는 properties 파일은 Command.properties파일
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		//web.xml에서 propertyConfig에 해당하는 init-param의 값을 읽어옴
		String props = config.getInitParameter("propertyConfig");
		//명령어와 처리클래스의 매핑정보를 저장할 Properties 객체 생성
		Properties pr = new Properties(); //키 값과 value가 모두 String
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream f = null;
		try {
			//Command.properties 파일의 내용을 읽어옴
			f = new FileInputStream(new File(path,props));
			//Command.properties 파일의 정보를 Properties 객체에 저장
			pr.load(f); //로드해서 FileInputStream을 주면 파일하고 연결된 내용들을 한줄 한줄 읽어서 저장한다. 
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if(f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Iterator 객체는 Enumeration 객체를 확장시킨 개념의 객체
		Iterator<Object> keyIter = pr.keySet().iterator(); //키 값들만 줄세워서 문자열로 저장
		//객체를 하나씩 꺼내서 그 객체명으로 Properties 객체에 저장된 객체에 접근
		while(keyIter.hasNext()) { //다음에 줄 선 것이 있나? 물어보는 것.
			String command = (String)keyIter.next(); // ex> /mvc/messge.do가 저장
			String className = pr.getProperty(command); // ex> mvc.messageProcess 이름을 얻어 오는 것.
			try {//해당 문자열을 클래스로 만든다.
				Class commandClass = Class.forName(className);//클래스를 찾아라! 그리고 클래스로 인식하는 것
				Object commandInstance = commandClass.newInstance();//해당클래스의 객체를 생성 = 클래스를 인식한 후 진짜 객체를 만드는 것(Object)
				//Map객체인 commandMap에 객체 저장
				commandMap.put(command, commandInstance); //Map에 키값과 객체를 저장한다. 이것은 전역변수이기 때문에 init이 끝나도 살아있다. 
				//그래서 우리가 이 Map을 계속 이용해서 객체를 받아오는데 잘 보면 싱글톤 패턴이랑 비슷한 것을 볼 수 있다. 이미 객체를 만들어 놓고 그것을 쓸 때마다 빌려오는 것이다. 
			} catch (ClassNotFoundException e) {
				throw new ServletException(e);
			} catch (InstantiationException e) {
				throw new ServletException(e);
			} catch (IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		requestPro(request, response);
	}
	
	//사용자의 요청을 분석해서 해당 작업을 처리
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = null;
		CommandProcess com = null; //Action에 해당함
		try {
			String command = request.getRequestURI(); // /myWeb/mvc/message.do
			if(command.indexOf(request.getContextPath()) == 0) { // myWeb이냐 
				command = command.substring(request.getContextPath().length());// /mvc/message.do를 잘라라 이것이 명령어
			}
			com = (CommandProcess)commandMap.get(command); //키값(명령어)를 주니까 객체가 나온다. 부모형으로 형변환도 해준다.
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

}
