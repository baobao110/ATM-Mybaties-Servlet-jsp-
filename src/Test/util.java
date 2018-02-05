package Test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import AccountFlow.Account;
import BankCard.Card;
import service.Service;

public class util extends HttpServlet {
	
	private Service service=new Service();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String msg=req.getParameter("action");
		/*
		 * getParametr()�ǻ�ȡ�������ֵ�ķ���,�������ǻ�ȡaction�Ĳ���ֵ,����Parameterֻ��get����û��post����
		 */
		if(msg.equals("openAccount")) {
			openAccount(req,resp);
		}
		
		if(msg.equals("toOpenAccount")) {
			toOpenAccount(req,resp);
		}
		
		if(msg.equals("save")) {
			save(req,resp);
		}
		
		if(msg.equals("toSave")) {
			toSave(req,resp);
		}
		
		if(msg.equals("check")) {
			check(req,resp);
		}
		
		if(msg.equals("toCheck")) {
			toCheck(req,resp);
		}
		
		if(msg.equals("transfer")) {
			transfer(req,resp);
		}
		
		if(msg.equals("toTransfer")) {
			toTransfer(req,resp);
		}
		
		if(msg.equals("list")) {
			List(req,resp);
		}
		
		if (msg.equals("toList")) {
			toList(req, resp);
		}
		/*
		 * ���ݲ���ֵ�Ĳ�ͬ�����жϵ�����صķ���
		 */
	}
	/*
	 * get��post������:get�İ�ȫ�Ը�����Ϊget�����ڽ���ҳ����תʱ����url�д�����صĴ��ݲ�������post���ַ������ᰲȫ�Ը���
	 */
	private void openAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password=req.getParameter("password");
		Card card=service.open(password);
		req.setAttribute("card", card);//������ͨ����ֵ�Ե���ʽ�洢��ص���ֵ,����Attribute������set����Ҳ��get����������Ժ�Parameter��������
		req.getRequestDispatcher("/WEB-INF/pag/success.jsp").forward(req, resp);
		/*
		 * ������	req.getRequestDispatcher("��ַ").forward(req, resp)������ת����Ӧ��ҳ��,����ĵ�ַ�������Ĭ�ϵ�ַ�����·��
		 * ��������ǰ�˲��ֵĴ��붼д��webapp����,֮ǰ��MyEclipse�ǲ����ĵط����������ڸ㶮��
		 */
	}
	
	private void toOpenAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pag/OpenAccount.jsp").forward(req, resp);
	}
	
	private void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		service.save(number, password, money);
		Card card=service.GetCard(number);
		req.setAttribute("card", card);
		req.getRequestDispatcher("/WEB-INF/pag/success.jsp").forward(req, resp);
	}
	
	private void toSave(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pag/save.jsp").forward(req, resp);
	}
	
	private void transfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("OutNumber");
		int OutNumber=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		String c=req.getParameter("InNumber");
		int InNumber=Integer.parseInt(c);
		double money=Double.parseDouble(b);
		service.transfer(OutNumber, password, money, InNumber);
		Card card1=service.GetCard(OutNumber);
		Card card2=service.GetCard(InNumber);
		req.setAttribute("card1", card1);
		req.setAttribute("card2", card2);
		req.getRequestDispatcher("/WEB-INF/pag/success2.jsp").forward(req, resp);
	}
	
	private void toTransfer(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pag/transfer.jsp").forward(req, resp);
	}
	
	private void check(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a=req.getParameter("number");
		int number=Integer.parseInt(a);
		String password=req.getParameter("password");
		String b=req.getParameter("money");
		double money=Double.parseDouble(b);
		service.draw(number, password, money);
		Card card=service.GetCard(number);
		req.setAttribute("card", card);
		req.getRequestDispatcher("/WEB-INF/pag/success.jsp").forward(req, resp);
	}
	
	private void toCheck(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pag/check.jsp").forward(req, resp);
	}
	
	private void List(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String a= req.getParameter("number");
			int number=Integer.parseInt(a);
			String password = req.getParameter("password");
			 ArrayList<Account> list = service.List(number);
			req.setAttribute("flowInfo", list);
			req.setAttribute("number", number);
			req.setAttribute("password", password);
			req.getRequestDispatcher("/WEB-INF/pag/list.jsp").forward(req, resp);
		}
	
	private void toList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageName = req.getParameter("pageName");
		req.getRequestDispatcher("/WEB-INF/pag/" + pageName + ".jsp").forward(req, resp);
	
	}
	
	/*
	 * Servlet��������ǵ��ú�̨����ش�����к�̨�����о��ǻ�ȡ������ǰ��ҳ�����ز����Լ���ת��ַ
	 */
}
