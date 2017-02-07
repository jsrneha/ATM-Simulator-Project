import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ChangePin extends JFrame implements ActionListener 
{	
	JFrame change , Error ; 
	JLabel jl1 , jl2 , jl3 ;
	JPanel jp1 , jp2 ,jp3 , jp4; 
	JPasswordField newpassd; ;
	JPasswordField oldpassd ;
	JButton Reset , changee , Exit, Tryagain , exit;

	ChangePin(){
		//System.out.println(Session.cardno);
		Changepg();
	}
	
	private void Errorbox()
	{
		Error = new JFrame("Error");
		Error.setSize(500,500);
		jl3 = new JLabel("The credentials you have entered are wrong.");
		jp3 = new JPanel();
		jp4 = new JPanel();
		jp3.add(jl3);
		Tryagain = new JButton("Try Again");
		exit = new JButton("Exit");
		jp4.add(Tryagain);
		jp4.add(exit);
		Error.add(jp3,new BorderLayout().CENTER);
		Error.add(jp4 , new BorderLayout().SOUTH);
		Tryagain.addActionListener(this);
		exit.addActionListener(this);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
		Error.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Error.setVisible(true) ;
	}
	
	private void Changepg(){
		
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("/home/akshat/Downloads/chpass.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		change =new JFrame();
		change.setBounds(0,0,1500,700);
		change.setContentPane(new JLabel(imageIcon));
		change.setTitle("CHANGE PIN.....  ");
		
		Container cntr= change.getContentPane();
    	cntr.setBackground(Color.WHITE);
    	cntr.setLayout(null);
    	BorderLayout b=new BorderLayout();
    	cntr.setLayout(b);
    	Font f=new Font("Arial",Font.BOLD,20);
		
		newpassd=new JPasswordField(20);
		oldpassd=new JPasswordField(20);
		
		jl1=new JLabel("Old Password");
		jl2=new JLabel("New Password");

		jl1.setFont(f);
		jl2.setFont(f);
		
		Reset  = new JButton("Reset");
		changee  = new JButton("Change");
		Exit = new JButton ("Exit");
		
		Exit.addActionListener(this);
		Reset.addActionListener(this);
		changee.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		 
		jp1.add(jl1);
		jp1.add(oldpassd);
		jp1.add(jl2);
		jp1.add(newpassd);
		
		jp2.add(changee);
		jp2.add(Reset);
		jp2.add(Exit);
		
		cntr.add(jp1, BorderLayout.PAGE_START);
		cntr.add(jp2, BorderLayout.PAGE_END);
	
		//Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		//change.setLocation(dim.width/2-change.getSize().width/2, dim.height/2-change.getSize().height/2);
		change.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		change.setVisible(true);
	}
public void actionPerformed(ActionEvent action) {
	if(action.getSource() == Reset)
	{
		oldpassd.setText("");
		newpassd.setText("");
	}
	if(action.getSource() == Exit)
	{
		Session.cardno = "" ;  
		Session.pin = "" ;
		change.setVisible(false);
		new WelcomePage();
	}
	if(action.getSource() == changee)
	{
		String old  =String.valueOf(oldpassd.getPassword());
		String newpd = String.valueOf(newpassd.getPassword());
		if(old.equals("") || newpd.equals(""))
		{
			JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
			oldpassd.setText(""); newpassd.setText("");
		}
		else
			{changepassd(old,newpd);}
	}
	if(action.getSource() == exit)
	{
		change.setVisible(false);
		new UserFunctionality();
	}
}

 void changepassd(String old,String newpin){
 		 
 		//System.out.println(card);
 		if(Userlogin.userlogin.get(Session.cardno).equals(old))
 		{
	 		
	 		try{
	 			Connector connect;
		 		connect=new Connector();
	 			connect.stm=connect.conn.createStatement();
	 			String query ="update atmcard set pin = \"" + newpin + "\" where cardno = \"" + Session.cardno + "\" ; ";
	 			//System.out.println(query);
	 			connect.stm.executeUpdate(query);
	 			//System.out.println(query);
	 			Userlogin.userlogin.put(Session.cardno,newpin ) ;//Updating the hashmap with newpin .
	 			JOptionPane.showMessageDialog(null, "Pin Successfully Changed.") ;
	 			oldpassd.setText("");
	 			newpassd.setText("");
	 			
	 		}
	 		catch(Exception e){
	 			System.out.println(e);
	 		}	
 	
 		}
 		else
 		{
 			JOptionPane.showMessageDialog(null, "Old pin is incorrect.") ;
 			oldpassd.setText("");
			newpassd.setText("");
 		}
 	}
	 
}