import java.util.*;
import java.util.List; 
import javax.imageio.ImageIO;
import javax.swing.*;

import java.sql.ResultSet ; 
import java.text.*;
import java.util.Date;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

	public class IssueCard implements ActionListener {
		
		IssueCard()
		{
			display() ; 
			
		}
		
		JFrame Block , Error, Success , Issue ,Allready;
		JTextField account,id ;
		JPanel p1 ,p2,p3,p4; 
		JLabel l1 , l2 , l3 ;
		JButton back , proceed , reset , ok  ; 
		
		void Errorbox()
		{
			Error = new JFrame("Error");
			Error.setSize(500,500);
			p3 = new JPanel();
			ok = new JButton("Okay");
			ok.addActionListener(this);
			l3 = new JLabel("The values you entered do not match.");
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
		    Error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			p3.add(l3);
			p3.add(ok);
			Error.add(p3);
			Error.setVisible(true);
			
		}
		void display()
		{
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("/home/akshat/Downloads/isuue.png"));
			} catch (IOException e) {
					System.out.println("aks");
				e.printStackTrace();
			}
			Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			Issue = new JFrame("Issue ATM Card");
			Issue.setBounds(0,0,1500,700);
			Issue.setContentPane(new JLabel(imageIcon));
		    Issue.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		    Container cntr= Issue.getContentPane();
	    	cntr.setBackground(Color.WHITE);
	    	cntr.setLayout(null);
	    	BorderLayout b=new BorderLayout();
	    	cntr.setLayout(b);
	    	Font f=new Font("Arial",Font.BOLD,20);
			
			account  = new JTextField(20);
			id = new JTextField(20);
			l1 = new JLabel("Enter account number");
			p1 = new JPanel();
			p2 = new JPanel();
			l2 = new JLabel("Enter associated user id");
			back = new JButton("Back");
			proceed = new JButton("Proceed");
			reset = new JButton("Reset");
			back.addActionListener(this);
			proceed.addActionListener(this);
			reset.addActionListener(this);
			p2.add(back);
			p2.add(proceed);
			p2.add(reset);
			p1.add(l1);
			p1.add(account);
			p1.add(l2);
			p1.add(id);
			cntr.add(p1,BorderLayout.PAGE_START);
			cntr.add(p2,BorderLayout.PAGE_END);
			Issue.setVisible(true);
			
		}
	Random r  = new Random();
	int generateatm()
	{
		 return (r.nextInt(900000000) + 100000000);
	}
	int generatepin()
	{
		
		return (r.nextInt(9000) + 1000) ; 
	}
	int generatecvv()
	{
		return (r.nextInt(900) + 100) ;
	}
	String generateexpirydate()
	{
		Date today = new Date();
		Date changed  = new Date();
		Calendar c   = Calendar.getInstance() ; 
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println(dateFormat.format(today));
		today = c.getTime() ; 
		c.add(Calendar.YEAR, 10) ;
		changed = c.getTime() ; 
		return (dateFormat.format(changed));
		
	}
	void verify(String userid , String accountno)
	{
		try{
				
			if(Blockcard.blocking.get(accountno).equals(userid))
				{
				//System.out.println("found");
						try{
							
							Connector connect  = new Connector();
							connect.stm = connect.conn.createStatement() ; 
							String already = "select * from atmcard where accountno = " + accountno + " ; " ;
							ResultSet rs  = connect.stm.executeQuery(already);
							if(!rs.next())
						{		
							AtmCard a = new AtmCard();
							a.setPin(Integer.toString(generatepin()));
							a.setCvv(generatecvv()) ;
							a.setBlockstatus(0);
							a.setCardno(Integer.toString(generateatm()));
							String Query2 = "insert into atmcard values( \"" + a.getCardno() + "\" , \"" + a.getPin() + "\", " + a.getCvv() + ", \"" + generateexpirydate() + "\" ," + a.getBlockstatus() + ", " + accountno + "  ); " ; 					
							String Query3 = "update account set atmissuestatus =  1 where accountno = " + accountno + " ;" ; 
							String Query1 = "update account set cardno = \"" + a.getCardno() + "\" where accountno = " + accountno + " ;" ;  
							
							String Query4 = "select * from account where cardno =  \"" + a.getCardno() + "\" ;" ;
							
							connect.stm.executeUpdate(Query2) ; 
							connect.stm.executeUpdate(Query3) ;
							connect.stm.executeUpdate(Query1);
							Account acc = new Account() ; 
							ResultSet rs1 = connect.stm.executeQuery(Query4);
							System.out.println(a.getCardno());
							while(rs1.next())
							{
								acc.setType(rs1.getString("type"));
								acc.setBalance(rs1.getInt("balance"));
							}
							
							Userlogin.userlogin.put(a.getCardno(),a.getPin()); // Updating the hashmap.
							List<String> l = new ArrayList<String> (); 
							
							l.add(Integer.toString(acc.getBalance()));
							l.add(acc.getType());
							Transaction.transaction.put(a.getCardno(),l); // Updating transaction hashmap.
							JOptionPane.showMessageDialog(null, "Card Successfully issued. Card Number : " + a.getCardno() + " Pin = " + a.getPin());
							id.setText(""); account.setText("");
						}	
							else{
								JOptionPane.showMessageDialog(null, "Card already issued to the User.") ;
								id.setText(""); account.setText("");
								
							} 
							//System.out.println(Query2);
							//System.out.println(Query3);
							//System.out.println(Query1);
						}
						
						catch(Exception e){
							//verify(userid , accountno) ; 
							System.out.println(e);
						}
				}
			else{
				Errorbox();
			}
		}
		
		catch(Exception e)
		{
				
			Errorbox();
		}
		
		
	}

	//@Override
	public void actionPerformed(ActionEvent action) {
		
		if(action.getSource() == reset)
		{
			id.setText("");
			account.setText("");
		}
		if(action.getSource() == back)
		{
			    Issue.setVisible(false);
				new AdminFunctionality();
		}
		if(action.getSource() == proceed)
		{
			String userid = id.getText() ;
			String accountno = account.getText();
			if(userid.equals("") || accountno.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				id.setText(""); account.setText("");
			}
			else
				{verify(userid , accountno);}
			
		}
		if(action.getSource() == ok )
		{
			Error.setVisible(false);
			Issue.setVisible(false);
			display();
		}	
	}
}
