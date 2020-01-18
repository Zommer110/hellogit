package mvc;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//���ɾ�� ���ɾ� ó�� Ŭ������ ������ ����
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	//���ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ �о Map��ü�� commandMap�� ����
	//���ɾ�� ó��Ŭ������ ���εǾ� �ִ� properties ������ Command.properties����
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		//web.xml���� propertyConfig�� �ش��ϴ� init-param�� ���� �о��
		String props = config.getInitParameter("propertyConfig");
		//���ɾ�� ó��Ŭ������ ���������� ������ Properties ��ü ����
		Properties pr = new Properties(); //Ű ���� value�� ��� String
		String path = config.getServletContext().getRealPath("/WEB-INF");
		FileInputStream f = null;
		try {
			//Command.properties ������ ������ �о��
			f = new FileInputStream(new File(path,props));
			//Command.properties ������ ������ Properties ��ü�� ����
			pr.load(f); //�ε��ؼ� FileInputStream�� �ָ� �����ϰ� ����� ������� ���� ���� �о �����Ѵ�. 
		} catch (IOException e) {
			throw new ServletException(e);
		} finally {
			if(f != null) try { f.close(); } catch(IOException ex) {}
		}
		//Iterator ��ü�� Enumeration ��ü�� Ȯ���Ų ������ ��ü
		Iterator<Object> keyIter = pr.keySet().iterator(); //Ű ���鸸 �ټ����� ���ڿ��� ����
		//��ü�� �ϳ��� ������ �� ��ü������ Properties ��ü�� ����� ��ü�� ����
		while(keyIter.hasNext()) { //������ �� �� ���� �ֳ�? ����� ��.
			String command = (String)keyIter.next(); // ex> /mvc/messge.do�� ����
			String className = pr.getProperty(command); // ex> mvc.messageProcess �̸��� ��� ���� ��.
			try {//�ش� ���ڿ��� Ŭ������ �����.
				Class commandClass = Class.forName(className);//Ŭ������ ã�ƶ�! �׸��� Ŭ������ �ν��ϴ� ��
				Object commandInstance = commandClass.newInstance();//�ش�Ŭ������ ��ü�� ���� = Ŭ������ �ν��� �� ��¥ ��ü�� ����� ��(Object)
				//Map��ü�� commandMap�� ��ü ����
				commandMap.put(command, commandInstance); //Map�� Ű���� ��ü�� �����Ѵ�. �̰��� ���������̱� ������ init�� ������ ����ִ�. 
				//�׷��� �츮�� �� Map�� ��� �̿��ؼ� ��ü�� �޾ƿ��µ� �� ���� �̱��� �����̶� ����� ���� �� �� �ִ�. �̹� ��ü�� ����� ���� �װ��� �� ������ �������� ���̴�. 
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
	
	//������� ��û�� �м��ؼ� �ش� �۾��� ó��
	private void requestPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String view = null;
		CommandProcess com = null; //Action�� �ش���
		try {
			String command = request.getRequestURI(); // /myWeb/mvc/message.do
			if(command.indexOf(request.getContextPath()) == 0) { // myWeb�̳� 
				command = command.substring(request.getContextPath().length());// /mvc/message.do�� �߶�� �̰��� ���ɾ�
			}
			com = (CommandProcess)commandMap.get(command); //Ű��(���ɾ�)�� �ִϱ� ��ü�� ���´�. �θ������� ����ȯ�� ���ش�.
			view = com.requestPro(request, response);
		} catch (Throwable e) {
			throw new ServletException(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}
}