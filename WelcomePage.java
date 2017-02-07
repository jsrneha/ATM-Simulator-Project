
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
public class WelcomePage extends JFrame implements ActionListener{

	JFrame welcome ; 
	JButton b1 ,b2,b3 ;
	BufferedImage img = null;
	public WelcomePage()
	{
		ImageIcon icon1=new ImageIcon("/home/akshat/Downloads/ul.jpg");
		ImageIcon icon2=new ImageIcon("/home/akshat/Downloads/i.png");
		try {
		    img = ImageIO.read(new File("/home/akshat/Downloads/ATM.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(800, 800, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		welcome = new JFrame("LNMIIT ATM SERVICE");
		welcome.setContentPane(new JLabel(imageIcon));
		welcome.setBounds(0,0,1500,700);
    	
    	
		
    	Container cntr= welcome.getContentPane();
    	cntr.setBackground(Color.WHITE);
    	cntr.setLayout(null);
    	BorderLayout b=new BorderLayout();
    	cntr.setLayout(b);
    	Font f=new Font("Arial",Font.BOLD,40);
    	
    	

		b1 = new JButton(icon2);//admin button
		b1.setSize(icon2.getIconWidth(),icon2.getIconHeight());
		b1.addActionListener(this);
		b2 = new JButton(icon1);//user login button
		b2.setSize(icon1.getIconWidth(),icon1.getIconHeight());
		b2.addActionListener(this);
		
		JLabel label=new JLabel();
		label.setText("WELCOME TO LNMIIT ATM SYSTEM");
		label.setFont(f);
		Cursor cur= new Cursor(Cursor.HAND_CURSOR);

    	JPanel p1 = new JPanel();
		p1.setBackground(Color.ORANGE);
		
		JPanel p2 = new JPanel();
		p2.setSize(100,100);
		p2.setBackground(Color.BLACK);
	
		
		 p1.add(label);
		 p2.add(b1);
		 p2.add(b2);
		 

		b1.setCursor(cur);
		b2.setCursor(cur);
		cntr.add(p1,BorderLayout.PAGE_START);
		cntr.add(p2,BorderLayout.PAGE_END);
		welcome.setVisible(true);
		welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	
	}
	public void actionPerformed(ActionEvent e){  
		if(e.getSource()==b1)
		{
			new AdminLogin(); 
			welcome.setVisible(false);
		    
		}
		if(e.getSource()==b2)
		{
			welcome.setVisible(false); 
			new Userlogin();
			
		}
		}  
}
