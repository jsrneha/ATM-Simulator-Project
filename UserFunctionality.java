import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class UserFunctionality implements ActionListener {
	JFrame function ;

	
	 JButton cashwdr , cashtnf ,cashdep,chngpin,balance,ministmt ,Exit ; 
	 JLabel jl1;
    JPanel jp1 ;
    BufferedImage img = null;
     
UserFunctionality(){
    	 
    	 ImageIcon icon1=new ImageIcon("/home/akshat/Downloads/wt2.jpg");
    	 ImageIcon icon2=new ImageIcon("/home/akshat/Downloads/changepin.png");
    	 ImageIcon icon3=new ImageIcon("/home/akshat/Downloads/images.png");
    	 ImageIcon icon4=new ImageIcon("/home/akshat/Downloads/transfer.png");
    	 ImageIcon icon5=new ImageIcon("/home/akshat/Downloads/bal.jpg");
    	 ImageIcon icon6=new ImageIcon("/home/akshat/Downloads/ms.jpg");
    	 ImageIcon icon7=new ImageIcon("/home/akshat/Downloads/back.png");
    	 
    	 
    	 try {
 		    img = ImageIO.read(new File("/home/akshat/Downloads/f.jpg"));
 		} catch (IOException e) {
 		    e.printStackTrace();
 		}
    	Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
 		ImageIcon imageIcon = new ImageIcon(dimg);
 		function =new JFrame("FUNCTIONALITY PAGE");
 		function.setContentPane(new JLabel(imageIcon));
    	 
    	//function =new JFrame("FUNCTIONALITY PAGE");
    	function.setBounds(0,0,1500,720);
    	function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	function.setVisible(true);
     
    	
    	Container cntr= function.getContentPane();
    	cntr.setBackground(Color.GREEN);
    	cntr.setLayout(null);
    	
    	GridLayout gl =new GridLayout(2,3,20,20);
    	
    	JPanel jp1 =new JPanel();
    	jp1.setBackground(Color.ORANGE);
    	jp1.setLayout(gl);
    	jp1.setBounds(350, 150, 500, 500);
    	cntr.add(jp1);
    	
 	     jl1=new JLabel("PLEASE SELECT ANY ONE OPTION");
 	     Font f=new Font("Arial",Font.BOLD,30);
 	     jl1.setFont(f);
 	     jl1.setBounds(200,0,1000,100);
 	     jl1.setBackground(Color.YELLOW);
 		cashwdr = new JButton(icon1);
 		cashwdr.setSize(icon1.getIconWidth(), icon1.getIconHeight());
 		cashwdr.addActionListener(this);
 		
 		cashtnf = new JButton(icon4);
 		cashtnf.setSize(icon4.getIconWidth(), icon4.getIconHeight());
 		cashtnf.addActionListener(this);
 		
 		cashdep = new JButton(icon3);
 		cashdep.setSize(icon3.getIconWidth(), icon3.getIconHeight());
 		cashdep.addActionListener(this);
 		
 		 balance = new JButton(icon5);
 		balance.setSize(icon5.getIconWidth(), icon5.getIconHeight());
 		 balance.addActionListener(this);
 		 
 		 //ministmt = new JButton(icon6);
 		//ministmt.setSize(icon6.getIconWidth(), icon6.getIconHeight());
 		 //ministmt.addActionListener(this);
 		 
 		 chngpin = new JButton(icon2);
 		 chngpin.setSize(icon2.getIconWidth(), icon2.getIconHeight());
 		 chngpin.addActionListener(this);
 		 Exit = new JButton(icon7);
 		Exit.setSize(icon7.getIconWidth(), icon7.getIconHeight());
		 Exit.addActionListener(this);
 		 
		 cntr.add(jl1);
 		 jp1.add(cashwdr);
 		 jp1.add(cashtnf);
 		 jp1.add(cashdep);
 		 jp1.add(balance);
 		 //jp1.add(ministmt);
 		 jp1.add(chngpin);
 		 jp1.add(Exit); 
 		 
 		//function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 		 //function.add(jp1);
 		 
     }
	
     public void actionPerformed(ActionEvent action){
    	 if(action.getSource() == Exit)
			{
				function.setVisible(false) ;
				new WelcomePage();
			}
			if(action.getSource() == cashwdr){
				function.setVisible(false) ;
				new Transaction();
			}
			
			if(action.getSource() == cashdep){
				function.setVisible(false);
				new Deposit();
				
			}
			
			if(action.getSource() == chngpin)
			{
				function.setVisible(false);
			//	System.out.println(Session.cardno);
				//System.out.println(Session.pin);
				 new ChangePin();
			}
			if(action.getSource() == cashtnf){
				
				function.setVisible(false);
				new Transfer();
				
			}
			
			if(action.getSource() == balance){
				//function.setVisible(false);
				new BalanceEnquiry();
				
			}
			
			
    	 
     }
}
