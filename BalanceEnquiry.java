import java.sql.ResultSet ; 
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.*;
import java.util.*;

import javax.swing.JOptionPane;
public class BalanceEnquiry {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	
	BalanceEnquiry(){
		balance();
		}
	
	 private void balance(){
		String card=Session.cardno ; 
 		//System.out.println(card);
 		
 		
	 	int bal  = 0; 
	 	try{
	 		Connector connect;
	 		connect=new Connector();
	 		connect.stm=connect.conn.createStatement();
	 		String query ="select balance from account where cardno = \"" + card + "\" ; ";
	 		//System.out.println(query);
	 		ResultSet rs ;
	 		rs=connect.stm.executeQuery(query);
	 		while(rs.next()){
	 		bal = rs.getInt("balance");}
	 		}catch(Exception e){
	 			System.out.println(e);
	 		}	
	 	
	 	if(bal>0){
	 		JOptionPane.showMessageDialog(null,"Available Balance = "+ bal + " Date  = " + dateFormat.format(cal.getTime()));
	 		
	 							//swing page with date and time
	 			}
	 	else
	 	{
	 		JOptionPane.showMessageDialog(null, "Insufficient Balance");
	 	}
 		}
		
		
	}
	
	


