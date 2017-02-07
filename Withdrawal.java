import  javax.swing.*;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
public class Withdrawal implements ActionListener{

	JFrame change ; 
	JLabel jl1 , jl2 , jl3 ;
	JPanel jp1 , jp2 ,jp3 , jp4; 
	JTextField amount ;
	JTextField type ;
	JButton Reset , withdraw , Exit, Tryagain , Back; 
	 
	Withdrawal()
	{
		Changepg();
	}
	private void Changepg(){
		
		change =new JFrame();
		change.setBounds(100,100,1000,500);
		change.setTitle("Withdraw");
		
		amount=new JTextField(20);
		type=new JTextField(20);
		
		jl1=new JLabel("Amount");
		jl2=new JLabel("Account Type");
		
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
		jp1.add(amount);
		jp1.add(jl2);
		jp1.add(type);
		
		jp2.add(withdraw);
		jp2.add(Reset);
		jp2.add(Exit);
		jp2.add(Back);
		
		change.add(jp1,new BorderLayout().CENTER);
		change.add(jp2,new BorderLayout().SOUTH);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		change.setLocation(dim.width/2-change.getSize().width/2, dim.height/2-change.getSize().height/2);
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == Reset)
		{
			amount.setText("");
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
			if(amount.equals("") || type.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				amount.setText(""); type.setText("");
			}
			else{
					int amnt = Integer.parseInt(amount.getText());
					String typ = type.getText(); 
					//change(amnt , typ);
		}
		if(action.getSource() == Back)
		{
			change.setVisible(false);
			new UserFunctionality();
		}
	}

	}	
}
