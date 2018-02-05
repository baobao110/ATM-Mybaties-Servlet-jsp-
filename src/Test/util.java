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
		 * getParametr()是获取请求参数值的方法,在这里是获取action的参数值,对于Parameter只有get方法没有post方法
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
		 * 根据参数值的不同进行判断调用相关的方法
		 */
	}
	/*
	 * get和post的区别:get的安全性更低因为get方法在进行页面跳转时会在url中带有相关的传递参数但是post这种方法不会安全性更高
	 */
	private void openAccount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String password=req.getParameter("password");
		Card card=service.open(password);
		req.setAttribute("card", card);//这里是通过键值对的形式存储相关的数值,对于Attribute属性有set方法也有get方法这里可以和Parameter参数区别
		req.getRequestDispatcher("/WEB-INF/pag/success.jsp").forward(req, resp);
		/*
		 * 这里用	req.getRequestDispatcher("地址").forward(req, resp)方法跳转到相应的页面,这里的地址是相对于默认地址的相对路径
		 * 所以属于前端部分的代码都写在webapp下面,之前用MyEclipse是不理解的地方在这里终于搞懂了
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
	 * Servlet类的作用是调用后台的相关代码进行后台处理还有就是获取和设置前端页面的相关参数以及跳转地址
	 */
}
