package inter;

import java.sql.Connection;

import org.apache.ibatis.annotations.Param;

import BankCard.Card;

public interface CardDAO {
	public Card GetCard(@Param("number") int number);
	/*
	 * 这里的DAO层和以前JDBC时不一样，这里作为接口，只需要定义方法就可以,同时需要注意这里的@Param()这里是给参数加注解
	 */
	public int modifyPassword(@Param("number")int number,@Param("password")String password);//多个参数要起别名
	public int modifyMoney(@Param("number")int number,@Param("money")double money);
	public int open(Card card);
	/*
	 * 这里的DAO层操作的是数据库表类所以在Mybaties中和JDBC一样要根据数据库建立相应的类，一张表一个类,DAO层同样是操作下面的数据库表类，和JDBC不同是这里DAO层是接口只需要定义
	 * 方法但不需要实现,对于数据库的操作写在Card.xml中同样用SQL语句
	 */
}
