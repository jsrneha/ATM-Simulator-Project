
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Deposit extends JFrame implements ActionListener
{
	
	JFrame change ; 
	JLabel jl1 , jl2 , jl3 ;
	JPanel jp1 , jp2 ,jp3 , jp4; 
	JTextField ammount ;
	JTextField type ;
	JButton Reset , deposit , Exit, Tryagain , Back; 
	
	Deposit(){
		Changepg();
	}
	private void Changepg(){
		
		BufferedImage img = null;
		change =new JFrame();
		try {
				img = ImageIO.read(new File("/home/akshat/Downloads/dp.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		
		change.setBounds(0,0,1500,700);
		change.setTitle(" CASH DEPOSIT ");
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
		
		Reset  = new JButton("Reset");
		deposit  = new JButton("Deposit");
		Exit = new JButton ("Logout");
		Back = new JButton ("Back");
		
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		deposit.addActionListener(this);
		Back.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jl1.setFont(f);
		jl2.setFont(f); 
		
		jp1.add(jl1);
		jp1.add(ammount);
		jp1.add(jl2);
		jp1.add(type);
		
		jp2.add(deposit);
		jp2.add(Reset);
		jp2.add(Exit);
		jp2.add(Back);
		
		cntr.add(jp1,BorderLayout.PAGE_START);
		cntr.add(jp2,BorderLayout.PAGE_END);
		
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
		if(action.getSource() == deposit)
		{
			if(ammount.getText().equals("") || type.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				ammount.setText(""); type.setText("");
			}
			else{
					int amnt = Integer.parseInt(ammount.getText());
					String typ = type.getText(); 
					cashdeposit(amnt , typ);
		}}
		if(action.getSource() == Back)
		{
			change.setVisible(false);
			new UserFunctionality();
		}
	}

	public void depositcash(int amount,String acctype) throws  MaximumbalanceException, AccountExceptions
	   {
		 List<String> l =new ArrayList<String>();
		 l =Transaction.transaction.get(Session.cardno);
		 
		//System.out.println(l.toArray()[0]);
		 if(l.toArray()[1].toString().equals(acctype))
		 {
			 int balance = Integer.parseInt(l.toArray()[0].toString()) ;
			 if(amount< 50000)
			 {
				 try{
					 Connector connect = new Connector();
					 balance += amount;
					 connect.stm = connect.conn.createStatement();
					 String query1 = "update account set balance =  " + balance + " where cardno = \"" + Session.cardno + "\" ;" ;
					 //String query2 = "insert into ";
					 connect.stm.executeUpdate(query1);// Updating the database.
					 l.set(0, Integer.toString(balance)) ;// Updating the hashmap.
					// Transaction table to be updated.
					 ammount.setText(""); type.setText("");
					 JOptionPane.showMessageDialog(null, "Deposit Successful !! , Available Balance = " + balance );
					 System.out.println(Transaction.transaction);
					 }
					 catch(Exception e)
					 	{
					 		System.out.println(e);
					 	}
			 }
			 else
			 {
				 
				 throw new MaximumbalanceException(amount);
			 }
		 }
		 else
		 {
			throw new  AccountExceptions( "Sorry, account type mismatches !");
		 }
	   }
	
	 public void cashdeposit (int bal,String type)
	  {
		try{
				depositcash(bal,type);
			}
		catch( MaximumbalanceException e){
			JOptionPane.showMessageDialog(null , "Cannot deposit " + e.getAmount() + " at one go." + "Limit = " + "Rs. 50000");
		}
		catch( AccountExceptions e){
			JOptionPane.showMessageDialog(null,"Account type does not match.");
			}
		}
	
}
class AccountExceptions extends Exception
{
	private String  detail;
	AccountExceptions(String a) 
	{
		detail = a; 
	}
public String toString() 
	{
	return detail;
	}
}

 class MaximumbalanceException extends Exception
{
   private int amount;
   public  MaximumbalanceException(int  amount)
   {
      this.amount = amount;
   } 
   public double getAmount()
   {
      return amount;
   }
}



