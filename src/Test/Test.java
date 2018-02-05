package Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import AccountFlow.Account;
import BankCard.Card;
import base.DataBase;
import inter.AccountDAO;
import inter.CardDAO;
import service.Service;

public class Test {
	public static void main(String[] args) {
		Service a=new Service();
		a.open("343");
		//a.ChangePassword(756045, "34343434","111111");
		//a.save(756045, "34343434",20);
		//a.List(756045);
		//a.transfer(756045, "111111",20,3322);
	}
	
}

	
/*
 * Mybaties的最后Test语句程序比较固定,具体的格式要求见Mybaties。text文件中的中文文档
 */
