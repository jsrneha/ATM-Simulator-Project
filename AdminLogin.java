
import javax.imageio.ImageIO;
import javax.swing.*;

import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class AdminLogin extends JFrame implements ActionListener{
	
    static HashMap<String , String> adminlogin; 
    static Connector connect;
    static{ // 
    	 adminlogin = new HashMap<String , String>();
		 connect  = new Connector();
    	 //System.out.println("here");
		try{
			
			
			connect.stm = connect.conn.createStatement();
			String query  = "select * from admin ;"; 
			ResultSet rs;
			rs = connect.stm.executeQuery(query);
			Admin a = new Admin ();
			while(rs.next()){
				
				a.setAdminid(rs.getString("adminid"));
				a.setPassword(rs.getString("password"));
				adminlogin.put(a.getAdminid(), a.getPassword());
				
			}
	}
		catch(Exception e){
			System.out.println("Exception Occured.");
		}
	}

	JFrame login , Error ; 
	JLabel l1 , l2 , l3 ;
	JPanel p1 , p2 ,p3 , p4; 
	JTextField id ;
	JPasswordField password ; 
	JButton Reset , Proceed , Exit, Tryagain , exit ;
	AdminLogin()
	{
		
		LoginPage();
		
	}
	private void Errorbox()
	{
		Error = new JFrame("Error");
		Error.setSize(500,500);
		l3 = new JLabel("The credentials you have entered are wrong.");
		p3 = new JPanel();
		p4 = new JPanel();
		p3.add(l3);
		Tryagain = new JButton("Try Again");
		exit = new JButton("Exit");
		p4.add(Tryagain);
		p4.add(exit);
		Error.add(p3,new BorderLayout().CENTER);
		Error.add(p4 , new BorderLayout().SOUTH);
		Tryagain.addActionListener(this);
		exit.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
		//Error.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Error.setVisible(true) ;
	}
	private void  LoginPage()
	{
		BufferedImage img = null;
		 try {
	 		    img = ImageIO.read(new File("/home/akshat/Downloads/admin.jpg"));
	 		} catch (IOException e) {
	 		    e.printStackTrace();
	 		}
	    Image dimg = img.getScaledInstance(1000, 900, Image.SCALE_SMOOTH);
	 	ImageIcon imageIcon = new ImageIcon(dimg);
	 	

		login = new JFrame ("LOGIN PAGE");
		login.setBounds(0,0,1500,720);
		login.setContentPane(new JLabel(imageIcon));
		//login.setDefaultCloseOperation(EXIT_ON_CLOSE);
		login.setVisible(true);
		
		Container cntr= login.getContentPane();
   	cntr.setBackground(Color.WHITE);
   	cntr.setLayout(null);
   	BorderLayout b=new BorderLayout();
   	cntr.setLayout(b);
   	Font f=new Font("Arial",Font.BOLD,20);
   	 
		
		id = new JTextField(20);
		password = new JPasswordField(20);
		
		l1 = new JLabel("ADMIN ID");
		l1.setFont(f);
		l2 = new JLabel("Password");
		l2.setFont(f);
		Reset  = new JButton("Reset");
		Proceed  = new JButton("Proceed");
		Exit = new JButton ("Exit");
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		Proceed.addActionListener(this);
		p1 = new JPanel();
		p1.setBackground(Color.CYAN);
		
		p2 = new JPanel();
		p2.setBackground(Color.CYAN);
		
		p1.add(l1);
		p1.add(id);
		p1.add(l2);
		p1.add(password);
		p2.add(Reset);
		p2.add(Proceed);
		p2.add(Exit);
		//cntr.add(label);
		cntr.add(p1,BorderLayout.PAGE_START);
		cntr.add(p2,BorderLayout.PAGE_END);
		
	}

	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == Reset)
		{
			id.setText("");
			password.setText("");
		}
		if(action.getSource() == Exit)
		{
			login.setVisible(false);
			new WelcomePage();
		}
		if(action.getSource() == Proceed)
		{
			String adminid  = id.getText() ; 
			String adminpassword = String.valueOf(password.getPassword());
			//System.out.println(adminid.length());
			//System.out.println(adminlogin);
			if(adminid.equals("") || adminpassword.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				id.setText(""); password.setText("");
			}
			else{
				
			verify(adminid , adminpassword);
			}
		}
		if(action.getSource() == Tryagain)
		{
			Error.setVisible(false);
			LoginPage();
		}
		if(action.getSource() == exit)
		{
			Error.setVisible(false);
			new WelcomePage();
		}
	}
	
	void verify(String adminid , String adminpassword)
	{
		try{
		
		if(adminlogin.get(adminid).equals(adminpassword))
		{
				login.setVisible(false) ; 
				new AdminFunctionality();
		}
		else
		{
			login.setVisible(false);
			Errorbox();
		}
	}
		catch(Exception e)
		{
			login.setVisible(false);
			Errorbox() ; 
		}
	}
	
}
