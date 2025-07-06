package model;

public class Customer {
	
	private int CustID;
	private String CustPass;
	private String FirstName;
	private String LastName;
	private String ContactNo;
	private String EmailAddress;
	private int Loyaltypoint;
	
	public Customer()
	{}
	
	public int getCustID() {
		return CustID;
	}
	public void setCustID(int custID) {
		CustID = custID;
	}
	public String getCustPass() {
		return CustPass;
	}
	public void setCustPass(String custPass) {
		CustPass = custPass;
	}
	public String getFirstName() {
		return FirstName;
	}
	public void setFirstName(String firstName) {
		FirstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getContactNo() {
		return ContactNo;
	}
	public void setContactNo(String contactNo) {
		ContactNo = contactNo;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}

	public int getLoyaltypoint() {
		return Loyaltypoint;
	}

	public void setLoyaltypoint(int loyaltypoint) {
		Loyaltypoint = loyaltypoint;
	}


}
