package edu.mum.framework;

public abstract class Entry implements IEntry {
	private double amount;
	private String date;
	private String detail; // withdraw or deposite
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "Entry [amount=" + amount + ", date=" + date + ", detail=" + detail + "]";
	}
	
	
	

}
