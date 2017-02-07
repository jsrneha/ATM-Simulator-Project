import java.sql.* ; 
import java.awt.*;

import javax.swing.*; 
import java.awt.event.*;
public class Connector extends JFrame implements ActionListener{

	static final private String url ;
	static final private String user  ; 
	static final private String password  ; 
	static{
		
		url = "jdbc:mysql://localhost:3306/atm" ;
		user = "root" ;
		password = "akshat" ;
	}
	Connection conn = null; 
	Statement stm = null; 
	Connector(){
		
	try{	 
			Class.forName("com.mysql.jdbc.Driver");
		
			conn = DriverManager.getConnection(url, user, password) ;	
		
		}
		catch(Exception e){this.ErrorBox();}
	}
	JButton ok ; 
	JFrame Error ; 
	JPanel p1 ;
	JLabel l ; 
	void ErrorBox()
	{
		 Error = new JFrame("Error");
		 ok = new JButton("Ok");
		 ok.addActionListener(this);
		 p1 = new JPanel();
		 Error.setSize(500, 100);
		 Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 Error.setLocation(dim.width/2-Error.getSize().width/2, dim.height/2-Error.getSize().height/2);
		 Error.setDefaultCloseOperation(EXIT_ON_CLOSE);
		 l = new JLabel("Error in Database Connectivity.");
		 p1.add(l);
		 p1.add(ok);
		 Error.add(p1);
		 Error.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		// TODO Auto-generated method stub
		if(action.getSource()==ok)
		{
			Error.setVisible(false);
		}
	}
}	
	
		