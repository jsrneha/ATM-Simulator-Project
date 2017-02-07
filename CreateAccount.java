import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class CreateAccount implements ActionListener{
	
	 
    
	User a = new User();
	Account acc = new  Account();
	
    CreateAccount()
    {
    	credentials();
    }
    
    JFrame User ;
    JLabel userid , username , address , birthdate , phone ,bankname ; 
    JButton Create , Reset , Exit ; 
    JPanel p  ,p1;
    JRadioButton Saving ,  Current ; 
    ButtonGroup accounttype ;
    JTextField Userid , Username , Address , Birthdate , Phone , Bankname; 
    
    void credentials()
    {
    	BufferedImage img = null;
    	
   	 try {
 		    img = ImageIO.read(new File("/home/akshat/Downloads/adm.jpg"));
 		} catch (IOException e) {
 		    e.printStackTrace();
 		}
    	Image dimg = img.getScaledInstance(800, 700, Image.SCALE_SMOOTH);
 		ImageIcon imageIcon = new ImageIcon(dimg);
 	
 		
    	User = new JFrame("Create Account");
    	
    	User.setBounds(0,0,1500,720);
    	User.setBackground(Color.CYAN);
    	//Container cntr= User.setContentPane(new JLabel(ImageIcon));
    	p = new JPanel() ; 
    	p1 = new JPanel();
    	
    	Create = new JButton("Create");
    	Reset = new JButton("Reset") ;
    	Exit = new JButton("Exit") ;

    	Saving = new JRadioButton("Savings", true);
    	Current = new JRadioButton("Current",false);
    	 
    	 ButtonGroup accounttype = new ButtonGroup();
         accounttype.add(Saving);
         accounttype.add(Current);

         
    	Create.addActionListener(this);
    	Reset.addActionListener(this);
    	Exit.addActionListener(this);
    	
    	userid = new JLabel("Userid") ;
    	username = new JLabel("Username") ;
    	address = new JLabel("Address") ;
    	birthdate = new JLabel("Birthdate (yyyy-mm-dd)") ;
    	phone = new JLabel("Phone number") ;
    	bankname = new JLabel("Bankname") ;
    
    	Userid = new JTextField(20);
    	Username = new JTextField(20);
    	Address = new JTextField(20);
    	Birthdate = new JTextField(20);
    	Phone = new JTextField(20);
    	Bankname = new JTextField(15);
    	
    	p.add(userid);p.add(Userid);
    	p.add(username);p.add(Username);
    	p.add(address);p.add(Address);
    	p.add(birthdate);p.add(Birthdate);
    	p.add(phone) ; p.add(Phone) ;
    	p.add(bankname);p.add(Bankname);
    	p.add(Saving);
    	p.add(Current);
    	
    	p1.add(Create);
    	p1.add(Exit);
    	p1.add(Reset);
    	
    	
    	User.add(p , new BorderLayout().CENTER);
    	User.add(p1 , new BorderLayout().SOUTH);
    	
    	Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		User.setLocation(dim.width/2-User.getSize().width/2, dim.height/2-User.getSize().height/2);
    	User.setVisible(true);
    }

	
	public void actionPerformed(ActionEvent action) {
	
		if(action.getSource() == Reset)
		{
			Userid.setText("");
			Username.setText("");
			Address.setText("");
			Birthdate.setText("");
			Phone.setText("");
		}

		if(action.getSource()==Exit)
		{
			User.setVisible(false);
			new AdminFunctionality();
		}
		if(action.getSource()==Create)
		{
			if(Userid.getText().equals("")||Username.getText().equals("")||Address.getText().equals("")||Birthdate.getText().equals("")||Phone.getText().equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill in all the fields.") ;
				Userid.setText("");
				Username.setText("");
				Address.setText("");
				Birthdate.setText("");
				Phone.setText("");
			}
			else
			{
				String type = "";
				if(Saving.isEnabled()){type = "Savings" ;  }
				if(Current.isEnabled()){type = "Current" ;}
				a.setUserid(Userid.getText());
				a.setName(Username.getText());
				a.setAddress(Address.getText());
				a.setPhone(Phone.getText());
				a.setBirthdate(Birthdate.getText());
				verify(type);
			}
		}
		
			
	}
	
	private void verify(String type)
	{
		//System.out.println(Blockcard.blocking); 
		if(Blockcard.blocking.containsValue(a.getUserid()))
		{
			JOptionPane.showMessageDialog(null, "User ID is not unique.");
			Userid.setText("");
			Username.setText("");
			Address.setText("");
			Birthdate.setText("");
			Phone.setText("");
			Bankname.setText("");
		}
		else
		{
		    Connector connect = new Connector();
			String query = "select max(accountno) from account ; ";
			try{
				ResultSet rs ; 
				connect.stm = connect.conn.createStatement() ; 
				rs =connect.stm.executeQuery(query);
				while(rs.next())
				{
					acc.setAccountno(rs.getInt("max(accountno)")+1);
				}
				acc.setType(type);
				acc.setBankname(Bankname.getText());
				//Assigning new account number . 
				/*System.out.println(acc.getAccountno());
				System.out.println(acc.getType());
				System.out.println(acc.getBankname());*/
				/*System.out.println(a.getName());
				System.out.println(a.getBirthdate());
				System.out.println(a.getAddress());
				System.out.println(a.getPhone());
				System.out.println(a.getUserid());*/
				String insertaccount = "insert into account(accountno , atmissuestatus , bankname ,type , balance ) values (" + acc.getAccountno() + ", 0 , \"" + acc.getBankname() + "\" ,\"" + acc.getType() + "\", 500 );";   // 500 the minimum balance to create accoutn and initially no atmcard is issued.     

				String insertuser = "insert into user values( \"" + a.getUserid() + "\" , \"" + a.getName() + "\" , \"" + a.getAddress() + "\", \"" + a.getBirthdate() + "\" ,\"" + a.getPhone() + "\" , " + acc.getAccountno() + ");"; 
				
				connect.stm.executeUpdate(insertaccount);
				connect.stm.executeUpdate(insertuser);
				Blockcard.blocking.put( Integer.toString(acc.getAccountno()),a.getUserid() );
				JOptionPane.showMessageDialog(null, "Account Successfully Created. Account no = " + acc.getAccountno()) ;
				Userid.setText("");
				Username.setText("");
				Address.setText("");
				Birthdate.setText("");
				Phone.setText("");
				Bankname.setText("");
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
		
	}
}
