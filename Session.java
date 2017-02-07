

public class Session {
	
	 static  String  cardno;
	  static String pin;
	 //private static Session ses;
	
	 
   public void setcardno(String card){
		this.cardno= card;
		
	}
   
	public  String getcardno(){
		 return cardno;
	 }
	
	public String getpin(){
		return pin;
	}
	 public void setpin(String pin){
		 this.pin =pin;
	 }
	 /*public  void setSession(Session s){
		 ses=s;
	 }
	 public static Session  getSession(){
		 return ses;
	 }*/

}
