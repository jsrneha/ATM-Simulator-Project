import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class AdminFunctionality  implements ActionListener{

		 JFrame Function ;
	     JButton Block , Issue , View, CreateAccount  ,Exit ; 
	     JPanel p1 ; 
	     
	     AdminFunctionality(){
	    	 BufferedImage img = null;
	    	
	    	 try {
	  		    img = ImageIO.read(new File("/home/akshat/Downloads/adm.jpg"));
	  		} catch (IOException e) {
	  		    e.printStackTrace();
	  		}
	     	Image dimg = img.getScaledInstance(800, 700, Image.SCALE_SMOOTH);
	  		ImageIcon imageIcon = new ImageIcon(dimg);
	  	
	     Function = new JFrame("Welcome Admin" );
	     Function.setBounds(0,0,1500,720);
	     Function.setContentPane(new JLabel(imageIcon));
	     Function.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	     
	    Container cntr= Function.getContentPane();
	    cntr.setBackground(Color.GREEN);
	    cntr.setLayout(null);
	    Font f=new Font("Arial",Font.BOLD,30);
	    
	    GridLayout gl =new GridLayout(2,2,20,20);
    	
    	JPanel jp1 =new JPanel();
    	jp1.setBackground(Color.GRAY);
    	jp1.setLayout(gl);
    	jp1.setBounds(350, 150, 500, 500);
    	cntr.add(jp1);
    	
    	JLabel jl=new JLabel("PLEASE SELECT ANY ONE OPTION");
 	   // Font f=new Font("Arial",Font.BOLD,30);
 	    jl.setFont(f);
 	    jl.setBounds(200,0,1000,100);
 	    jl.setBackground(Color.YELLOW);
 	    
 	    Block = new JButton("Block ATM Card");
 	    Block.setBackground(Color.CYAN);
 		//Block.setSize(icon1.getIconWidth(), icon1.getIconHeight());
 		Block.addActionListener(this);
 		
 		Issue = new JButton("Issue new ATM Card");
 		Issue.setBackground(Color.ORANGE);
		Issue.addActionListener(this);
 		//Issue.setSize(icon4.getIconWidth(), icon4.getIconHeight());
 	
 		
 		CreateAccount = new JButton("Create Account");
 		CreateAccount.setBackground(Color.CYAN);
		CreateAccount.addActionListener(this);
 		//CreateAccount.setSize(icon3.getIconWidth(), icon3.getIconHeight());
 		
 		
 		//View = new JButton("View Daily Transaction");
		//View.addActionListener(this);
 		//View.setSize(icon5.getIconWidth(), icon5.getIconHeight());
 		 
	    
 		Exit = new JButton("Logout");
 		Exit.setBackground(Color.ORANGE);
  		//Exit.setSize(icon7.getIconWidth(), icon7.getIconHeight());
 		Exit.addActionListener(this);
		 
 		 cntr.add(jl);
 		 jp1.add(Issue);
 		 jp1.add(CreateAccount);
 		 //jp1.add(View);
 		 jp1.add(Block);
 		 jp1.add(Exit); 
 
		 Function.setVisible(true);
	 }


	
		public void actionPerformed(ActionEvent action) {
			
			if(action.getSource() == Exit)
			{
				Function.setVisible(false) ;
				new WelcomePage();
			}
			if(action.getSource() == View){}
			if(action.getSource() == CreateAccount){
				Function.setVisible(false);
				new CreateAccount() ;
			}
			
			if(action.getSource() == Issue){
				Function.setVisible(false) ; 
				new IssueCard();
				
			}
			
			if(action.getSource() == Block)
			{
				Function.setVisible(false);
				new Blockcard();
			}
			
		}

}

