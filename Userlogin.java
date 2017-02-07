
import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Userlogin extends JFrame implements ActionListener{
		
		//Session s = new Session();
		static Map<String , String> userlogin; 
	    static Connector connect;
	    static {
	    	userlogin =new HashMap<String,String> ();
	    	connect=new Connector();
	    	try{
	    		
	    		connect.stm=connect.conn.createStatement();
	    		String query ="select * from atmcard ;";
	    		ResultSet rs;
	    		rs = connect.stm.executeQuery(query);
	    		AtmCard c=new AtmCard();
	    		while(rs.next()){
	    			c.setCardno(rs.getString("cardno"));
	    			c.setPin(rs.getString("pin"));
	    			userlogin.put(c.getCardno(),c.getPin());
	    			
	    		}
	    		System.out.println(userlogin);
	    	}
	    	catch(Exception e){
	    		System.out.println("Exception occured");
	    	}
	    }
	 
	    JFrame login , Error ; 
		JLabel l1 , l2 , l3 ;
		JPanel p1 , p2 ,p3 , p4; 
		JTextField cardno ;
		JPasswordField password ; 
		JButton Reset , Proceed , Exit, Tryagain , exit ;
		Userlogin(){
			Loginpage();
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
			Error.setDefaultCloseOperation(EXIT_ON_CLOSE);
			Error.setVisible(true) ;
		}
		private void Loginpage(){
			BufferedImage img = null;
			 ImageIcon icon1=new ImageIcon("/home/akshat/Downloads/pr.png");
	    	 ImageIcon icon2=new ImageIcon("/home/akshat/Downloads/R.jpg");
	    	 ImageIcon icon3=new ImageIcon("/home/akshat/Downloads/e.jpg");
			 try {
		 		    img = ImageIO.read(new File("/home/akshat/Downloads/lnm.png"));
		 		} catch (IOException e) {
		 		    e.printStackTrace();
		 		}
		    Image dimg = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		 	ImageIcon imageIcon = new ImageIcon(dimg);
		 	
	
			login = new JFrame ("LOGIN PAGE");
			login.setBounds(0,0,1500,720);
			login.setContentPane(new JLabel(imageIcon));
			
			
			Container cntr= login.getContentPane();
	    	cntr.setBackground(Color.WHITE);
	    	cntr.setLayout(null);
	    	BorderLayout b=new BorderLayout();
	    	cntr.setLayout(b);
	    	Font f=new Font("Arial",Font.BOLD,20);
	    	 

			cardno = new JTextField(20);
			password = new JPasswordField(20);
			
			l1 = new JLabel("ATM Card_NO");
			l1.setFont(f);
			l2 = new JLabel("Password");
			l2.setFont(f);
			Reset  = new JButton(icon2);
			Reset.setSize(icon2.getIconWidth(),icon2.getIconHeight());
			Proceed  = new JButton(icon1);
			Proceed.setSize(icon1.getIconWidth(),icon1.getIconHeight());
			Exit = new JButton (icon3);
			Exit.setSize(icon3.getIconWidth(),icon3.getIconHeight());
			Exit.addActionListener(this);
			Reset.addActionListener(this);
			Proceed.addActionListener(this);
			p1 = new JPanel();
			p1.setBackground(Color.CYAN);
			
			p2 = new JPanel();
			p2.setBackground(Color.BLACK);
			
			p1.add(l1);
			p1.add(cardno);
			p1.add(l2);
			p1.add(password);
			p2.add(Reset);
			p2.add(Proceed);
			p2.add(Exit);
		
			cntr.add(p1,BorderLayout.PAGE_START);
			cntr.add(p2,BorderLayout.PAGE_END);
			
			login.setDefaultCloseOperation(EXIT_ON_CLOSE);
			login.setVisible(true);
		
			
			
		}
		public void actionPerformed(ActionEvent action) {
			if(action.getSource() == Reset)
			{
				cardno.setText("");
				password.setText("");
			}
			if(action.getSource() == Exit)
			{
				login.setVisible(false);
				new WelcomePage();
			}
			if(action.getSource() == Proceed)
			{	
				//System.out.println(userlogin);
				
				Session.cardno = cardno.getText();
				Session.pin = String.valueOf(password.getPassword());
				if(Session.cardno.equals("") || Session.pin.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
					cardno.setText(""); password.setText("");
				}
				else
					{verify(Session.cardno,Session.pin);}
				
			}
			if(action.getSource() == Tryagain)
			{
				Error.setVisible(false);
				Loginpage();
			}
			if(action.getSource() == exit)
			{
				Error.setVisible(false);
				new WelcomePage();
			}
		}
		
		void verify(String cardno , String password)
		{
			try{
			
		
			if(userlogin.get(cardno).equals(password))
			{
					login.setVisible(false) ; 
					new UserFunctionality();
			}
			else
			{
				login.setVisible(false);
				Errorbox();
				//System.out.println("not exist");
			}
		}
			catch(Exception e)
			{
				login.setVisible(false);
				Errorbox() ; 
				//System.out.println(e);
			}
		}		
}