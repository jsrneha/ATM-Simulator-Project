
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import  javax.swing.*;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

	/*
 static HashMap<String,List<String>> transaction ;
 static Connector connect;
 static{
 		
	 List<String> list; 
	 HashMap<String , List<String>> transaction;
	  
    
    {
   	transaction =new HashMap<String,List<String>> ();
   	connect=new Connector();
   	try{
   		//int bal=0;
   		//String cardno;
   		connect.stm=connect.conn.createStatement();
   		String query ="select account.cardno,account.balance,account.type from account join atmcard on atmcard.cardno=account.cardno ;";
   		ResultSet rs;
   		rs = connect.stm.executeQuery(query);
   		Account acc =new Account();
   		while(rs.next()){
   			 list = new ArrayList<String>(); 
   			 acc.setBalance( rs.getInt("balance"));
   			 acc.setCardno(rs.getString("cardno"));
   			 acc.setType(rs.getString("type"));
   			 list.add(Integer.toString(acc.getBalance()));
   			 list.add(acc.getType());
   			 transaction.put(acc.getCardno(),list );
   		}
   		/*List<String> l =new ArrayList<String>();
   		l = transaction.get("254879526");
   		System.out.println(l.contains("Savings"));
   		System.out.println(l.toArray()[0].toString().length());*/
   /*	}
   	catch(Exception e){
   		System.out.println(e);
   	}
   }
   */
	

public class Transaction extends JFrame implements ActionListener
{
	
	
	 static HashMap<String,List<String>> transaction ;
	 static Connector connect;
	 static{
	 	try{
		transaction=new HashMap<String , List<String>> ();
	   	
	   	connect=new Connector();
	   	List<String> list = new ArrayList<String>();
	   	
	   		//int bal=0;
	   		//String cardno;
	   		connect.stm=connect.conn.createStatement();
	   		String query ="select account.cardno,account.balance,account.type from account join atmcard on atmcard.cardno=account.cardno ;";
	   		ResultSet rs;
	   		rs = connect.stm.executeQuery(query);
	   		Account acc =new Account();
	   		while(rs.next()){
	   			 list = new ArrayList<String>(); 
	   			 acc.setBalance( rs.getInt("balance"));
	   			 acc.setCardno(rs.getString("cardno"));
	   			 acc.setType(rs.getString("type"));
	   			 list.add(Integer.toString(acc.getBalance()));
	   			 list.add(acc.getType());
	   			 transaction.put(acc.getCardno(),list );
	   		}
	   		//List<String> l =new ArrayList<String>();
	   		//l = transaction.get("254879526");
	   		System.out.println(transaction);
	   		//System.out.println(l.toArray()[0].toString().length());*/
	   	}
	   	catch(Exception e){
	   		System.out.println(e);
	   	}
	   }
	   
	
	    JFrame change ; 
		JLabel jl1 , jl2 , jl3 ;
		JPanel jp1 , jp2 ,jp3 , jp4; 
		JTextField ammount ;
		JTextField type ;
		JButton Reset , withdraw , Exit, Tryagain , Back; 
		//private int amount;
		//private String account;
		//Transaction trns=new Transaction();
		Transaction()
		{
			//Account.createhm();
			Changepg();
		}
		
		private void Changepg(){
			
					BufferedImage img = null;
					change =new JFrame();
					try {
							img = ImageIO.read(new File("/home/akshat/Downloads/withdraw .jpg"));
						} catch (IOException e) {
							e.printStackTrace();
						}
					Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
					ImageIcon imageIcon = new ImageIcon(dimg);
					
					
					change.setBounds(0,0,1500,720);
					change.setTitle("Withdraw");
					change.setContentPane(new JLabel(imageIcon));
					change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					change.setVisible(true);
					
					Container cntr= change.getContentPane();
			    	cntr.setBackground(Color.WHITE);
			    	cntr.setLayout(null);
			    	BorderLayout b=new BorderLayout();
			    	cntr.setLayout(b);
			    	Font f=new Font("Arial",Font.BOLD,20);
			    	 
				
					ammount=new JTextField(20);
					type=new JTextField(20);
					
					
					jl1=new JLabel("Amount");
					jl2=new JLabel("Account Type");
					jl1.setFont(f);
					jl2.setFont(f);
					
					Reset  = new JButton("Reset");
					withdraw  = new JButton("Withdraw");
					Exit = new JButton ("Logout");
					Back = new JButton ("Back");
					
					Exit.addActionListener(this);
					Reset.addActionListener(this);
					withdraw.addActionListener(this);
					Back.addActionListener(this);
					
					jp1=new JPanel();
					jp2=new JPanel();
					 
					jp1.add(jl1);
					jp1.add(ammount);
					jp1.add(jl2);
					jp1.add(type);
					
					jp2.add(withdraw);
					jp2.add(Reset);
					jp2.add(Exit);
					jp2.add(Back);
					
					cntr.add(jp1,BorderLayout.PAGE_START);
					cntr.add(jp2,BorderLayout.PAGE_END);
					

					//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
					//change.setLocation(dim.width/2-change.getSize().width/2, dim.height/2-change.getSize().height/2);
					
				}
				
			
			public void actionPerformed(ActionEvent action) {
				if(action.getSource() == Reset)
				{
					ammount.setText("");
					type.setText("");
				}
				if(action.getSource() == Exit)
				{
					Session.cardno = "" ;  
					Session.pin = "" ;
					change.setVisible(false);
					new WelcomePage();
				}
				if(action.getSource() == withdraw)
				{
					if(ammount.getText().equals("") || type.getText().equals(""))
					{
						JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
						ammount.setText(""); type.setText("");
					}
					else{
							int amnt = Integer.parseInt(ammount.getText());
							String typ = type.getText(); 
							change(amnt , typ);
				}
				}
				if(action.getSource() == Back)
				{
					change.setVisible(false);
					new UserFunctionality();
				}
			}	
 public void withdraw(int amount,String acctype) throws InsufficientbalanceException, AccountException
	   {
	 
		 List<String> l =new ArrayList<String>();
 		 l =transaction.get(Session.cardno);
 		//System.out.println(l.toArray()[0]);
		 if(l.toArray()[1].toString().equals(acctype))
		 {
			 int balance = Integer.parseInt(l.toArray()[0].toString()) ;
			 if(amount< balance)
			 {
				 try{
				 balance -= amount;
				 connect.stm = connect.conn.createStatement();
				 String query1 = "update account set balance =  " + balance + " where cardno = \"" + Session.cardno + "\" ;" ;
				 
				 connect.stm.executeUpdate(query1);// Updating the database.
				 l.set(0, Integer.toString(balance)) ;// Updating the transaction hashmap.
				// Transaction table to be updated.
				 ammount.setText(""); type.setText("");
				 JOptionPane.showMessageDialog(null, "Withdraw Successful !! , Available Balance = " + balance );
				 //System.out.println(Transaction.transaction);
				 }
				 catch(Exception e)
				 	{
				 		System.out.println(e);
				 	}
			 }
			 else
			 {
				 int  needs = amount - balance;
				 throw new InsufficientbalanceException(needs);
			 }
		 }
		 else
		 {
			throw new  AccountException( "Sorry, account type mismatches !");
			
		 }
	   }
 
	
  public void change (int bal,String type)
  {
	try{
			withdraw(bal,type);
		}
	catch(InsufficientbalanceException e){
		JOptionPane.showMessageDialog(null, "Sorry, but you are short of Rs. " + e.getAmount());
		
	}
	catch( AccountException e){
		JOptionPane.showMessageDialog(null, e.toString() );
		}
	}

 }


class AccountException extends Exception
{
	private String  detail;
	AccountException(String a) 
	{
		detail = a; 
	}
public String toString() 
	{
	return detail;
	}
}

 class InsufficientbalanceException extends Exception
{
   private int amount;
   public InsufficientbalanceException(int  amount)
   {
      this.amount = amount;
   } 
   public double getAmount()
   {
      return amount;
   }
}
