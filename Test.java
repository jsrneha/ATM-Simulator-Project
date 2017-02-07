  /*import java.awt.*;  
import java.awt.event.*;  
import java.sql.*;

    public class Test
    {
    	
    	public static void main(String[] args)
    	{
    		try
    		{
    			Connector connect  = new Connector();
    			connect.stm = connect.conn.createStatement();
    			ResultSet rs;
    			String card="" ; 
    			rs=connect.stm.executeQuery("select cardno from account where accountno = 1002; ");
    			while(rs.next())
    			{
    				card = rs.getString("cardno");
    				
    			}
    			if(card!=null)
    				{
    				System.out.println("not avaialdlvsdv");
    				}
    			
    		}
    		catch(NullPointerException e)
    		{
    			System.out.println("null");
    		}
    		catch(Exception e)
    		{
    			System.out.println(e);
    		}
    		
    	}
    	
    	
    	
    	
    }
*/