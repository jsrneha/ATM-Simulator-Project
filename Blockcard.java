import javax.imageio.ImageIO;
import javax.swing.*;
import java.sql.*;
import java.util.HashMap;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;
	
public class Blockcard implements ActionListener{

	static HashMap<String , String> blocking; 
    static Connector connect;
    static{ // Hashmap for atmcard blocking.
    	 blocking = new HashMap<String , String>();
		 connect  = new Connector();
    	// System.out.println("here");
		try{
			connect.stm = connect.conn.createStatement();
			String query  = "select * from user;"; 
			ResultSet rs;
			rs = connect.stm.executeQuery(query);
			Account a  = new Account ();
			User b = new User() ;
			while(rs.next()){
				
				a.setAccountno(rs.getInt("accountno"));
				b.setUserid( rs.getString("userid"));
				blocking.put(Integer.toString(a.getAccountno()),b.getUserid());
			}
			
			//System.out.println(blocking); 
	}
		catch(Exception e){
			System.out.println(e);
		}
	}
    Blockcard()
	{
		display();
	}
	JFrame Block , Error;
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
			img = ImageIO.read(new File("/home/akshat/Downloads/b.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image dimg = img.getScaledInstance(1400, 900, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		Block = new JFrame("Block ATM Card");
		Block.setBounds(0,0,1500,700);
		Block.setContentPane(new JLabel(imageIcon));
	    Block.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    Container cntr= Block.getContentPane();
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
		cntr.add(p1, BorderLayout.PAGE_START);
		cntr.add(p2, BorderLayout.PAGE_END);
		Block.setVisible(true);
		
	}
	
	void verify(String userid , String  accountno)
	{
		
		
	try{
			if(blocking.get(accountno).equals(userid))
			{
				try{
				Connector connect  = new Connector();
				connect.stm = connect.conn.createStatement();
				String query2 = "update atmcard set blockstatus = 1 where accountno = " + Integer.parseInt(accountno) + " ;"  ;
				System.out.println("here"); 
				connect.stm.executeUpdate(query2);
				id.setText("");
				account.setText("");
				JOptionPane.showMessageDialog(null, "Card successfully Blocked.") ;
				connect.conn.close();
				}
				catch (Exception e)
				{
					
					//System.out.println("Exception");
					Block.setVisible(false);
					Errorbox();
				}
			}
			else{	
				Errorbox();
			}
		}
	catch(Exception e){
				Block.setVisible(false);
				Errorbox();
			}
		}
	public void actionPerformed(ActionEvent action) {
		if(action.getSource() == reset)
		{
			id.setText("");
			account.setText("");
		}
		if(action.getSource() == back)
		{
				Block.setVisible(false);
				new AdminFunctionality();
		}
		if(action.getSource()==proceed)
		{
			String userid  = id.getText() ;
			String accountno = account.getText();
			if(userid.equals("") || accountno.equals(""))
			{
				
				JOptionPane.showMessageDialog(null, "Please fill both the fields.") ;
				id.setText("") ; 
				account.setText("");
			
			}
			
			else
				{verify(userid , accountno);}
			
		}
		if(action.getSource() == ok )
		{
			Error.setVisible(false);
			Block.setVisible(false);
			display();
		}
	}
		
}
