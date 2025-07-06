package model;

public class Payment {

	private int invoiceID;
	private String invoiceDate;
	private double normalAmount;
	private double totalAmount;
	private double discount;
	private int usedloyalty;
	private Booking booking;
	public int getInvoiceID() {
		return invoiceID;
	}
	public void setInvoiceID(int invoiceID) {
		this.invoiceID = invoiceID;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public double getNormalAmount() {
		return normalAmount;
	}
	public void setNormalAmount(double normalAmount) {
		this.normalAmount = normalAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public int getUsedLoyalty() {
		return usedloyalty;
	}
	public void setUsedLoyalty(int usedloyalty) {
		this.usedloyalty = usedloyalty;
	}
	
}
