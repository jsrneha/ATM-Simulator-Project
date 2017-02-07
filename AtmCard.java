
public class AtmCard {

	private String cardno ; 
	private int cvv ; 
	private String pin ; 
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	// Expiry date to be included .
	private int blockstatus ;
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	public int getBlockstatus() {
		return blockstatus;
	}
	public void setBlockstatus(int blockstatus) {
		this.blockstatus = blockstatus;
	} 
}
