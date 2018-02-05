package BankCard;
import java.util.Date;

public class Card {
	private int id;
	private int number;
	private String password;
	private double money;
	private Date createtime;
	private Date modifytime;
	private int version;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getMony() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getModifytime() {
		return modifytime;
	}
	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Card [id=" + id + ", number=" + number + ", password=" + password + ", money=" + money + ", createtime="
				+ createtime + ", modifytime=" + modifytime + ", version=" + version + "]";
	}
	
//这里是根据数据库表建立的类和JDBC一样
	
}
