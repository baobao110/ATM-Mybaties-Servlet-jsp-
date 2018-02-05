package service;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import AccountFlow.Account;
import BankCard.Card;
import base.DataBase;
import inter.AccountDAO;
import inter.CardDAO;

public class Service {
	
	public Card open(String password) {
		SqlSession session=DataBase.open(false);//这里开启事务,这里和JDBC那里一样需要手动的提交事务所以设置为false,true为自动提交，false为手动提交，默认为false手动提交
	try {
		CardDAO dao=session.getMapper(CardDAO.class);//获取Card.xml中的类
		Card card=new Card();
		card.setNumber(DataBase.CreateNumber());
		card.setPassword(password);
		card.setMoney(0);
		if(dao.open(card)==1) {//这里调用Card.xml类中的SQL语句
			return card;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();//这里如果程序执行出错就需要回滚，这里调用session的rollback()方法
		}
		session.close();//最后关闭session会话
		return null;
	}
	public void save(int number,String password,double money)  {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao1=session.getMapper(CardDAO.class);
			Card card=dao1.GetCard(number);
			if(null==card) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!password.equals(card.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				return;
			}
			double x=card.getMony();
			money+=x;
			if(dao1.modifyMoney(number, money)!=0) {
				System.out.println("存钱成功");	
			}
			Account account=new Account();
			account.setNumber(number);
			account.setMoney(money);
			account.setType(1);
			account.setDescription("存钱");
			AccountDAO dao2=session.getMapper(AccountDAO.class);
			dao2.add(account);
			System.out.println("流水账产生");
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			}
		session.close();
		}
		
	
	public void draw(int number,String password,double money) {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao1=session.getMapper(CardDAO.class);
			Card card=dao1.GetCard(number);
			if(null==card) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!password.equals(card.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				return;
			}
			double x=card.getMony();
			if(money>x) {
				System.out.println("金额不足");
				return;
			}
			x-=money;
			if(dao1.modifyMoney(number, x)!=0) {
				System.out.println("取钱成功");	
			}
			Account account=new Account();
			account.setNumber(number);
			account.setMoney(money);
			account.setType(1);
			account.setDescription("取钱");
			AccountDAO dao2=session.getMapper(AccountDAO.class);
			dao2.add(account);
			System.out.println("流水账产生");
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			}
		session.close();
	}
	
	public void transfer(int OutNumber,String password,double money,int InNumber) {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao1=session.getMapper(CardDAO.class);
			Card card1=dao1.GetCard(OutNumber);
			if(null==card1) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!password.equals(card1.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(money<0) {
				System.out.println("金额小于零");
				return;
			}
			double x=card1.getMony();
			if(money>x) {
				System.out.println("金额不足");
				return;
			}
			x-=money;
			if(dao1.modifyMoney(OutNumber, x)!=0) {
				System.out.println("取钱成功");	
			}
			Account account1=new Account();
			account1.setNumber(OutNumber);
			account1.setMoney(money);
			account1.setType(1);
			account1.setDescription("取钱");
			AccountDAO dao2=session.getMapper(AccountDAO.class);
			dao2.add(account1);
			System.out.println("流水账产生");
			CardDAO dao3=session.getMapper(CardDAO.class);
			Card card2=dao3.GetCard(InNumber);
			if(null==card2) {
				System.out.println("账号或者密码不存在");
				return;
			}
			double y=card2.getMony();
			money+=y;
			if(dao3.modifyMoney(InNumber, money)!=0) {
				System.out.println("转入成功");	
			}
			Account account2=new Account();
			account2.setNumber(InNumber);
			account2.setMoney(money);
			account2.setType(1);
			account2.setDescription("存钱");
			AccountDAO dao4=session.getMapper(AccountDAO.class);
			dao4.add(account2);
			System.out.println("流水账产生");
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			}
		session.close();
		}
	
	public void ChangePassword(int number,String password,String changePassword) {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao=session.getMapper(CardDAO.class);
			Card a=dao.GetCard(number);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(!password.equals(a.getPassword())) {
				System.out.println("账号或者密码不存在");
				return;
			}
			if(dao.modifyPassword(number, changePassword)!=0) {
				System.out.println("密码修改成功");
			}
			session.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		session.close();
	}
	
	public ArrayList<Account> List(int number) {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao=session.getMapper(CardDAO.class);
			Card a=dao.GetCard(number);
			if(null==a) {
				System.out.println("账号不存在");
				return null;
			}
			AccountDAO dao2=session.getMapper(AccountDAO.class);
			ArrayList<Account>x=dao2.List(number);
			return x;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
		}
		session.close();
		return null;
	}
	
	public Card GetCard(int number) {
		SqlSession session=DataBase.open(false);
		try {
			CardDAO dao=session.getMapper(CardDAO.class);
			Card a=dao.GetCard(number);
			if(null==a) {
				System.out.println("账号或者密码不存在");
				return null;
			}
			else {
				return a;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			session.rollback();
			}
		session.close();
		return null;
		}
	}
