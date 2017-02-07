import java.util.ArrayList ; 
import java.sql.* ; 
import java.util.*;
public class Account {

	private int accountno ; 
	private String type ; 
	private String bankname ; 
	private int balance  ;
	private int atmissuestatus ;
	private String cardno;
	public Account()
	{
		
	}
	public Account(int accountno,String type,String bankname,int balance, int atmissuestatus, String cardno )
	{
		this.accountno = accountno ;
		this.type = type;
		this.bankname = bankname ; 
		this.cardno = cardno ; 
		this.atmissuestatus = atmissuestatus ; 
		this.balance = balance ;
	}
	
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public int getAccountno() {
		return accountno;
	}
	public void setAccountno(int accountno) {
		this.accountno = accountno;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getAtmissuestatus() {
		return atmissuestatus;
	}
	public void setAtmissuestatus(int atmissuestatus) {
		this.atmissuestatus = atmissuestatus;
	}
	public String toString()
	{
		return (this.accountno + " " + this.bankname + " " +this.balance + " " + this.atmissuestatus + " " + this.type);
	}

	 
}
