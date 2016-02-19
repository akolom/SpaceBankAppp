package edu.mum.framework;

public interface IEntry {

	double getAmount();

	void setAmount(double amount);

	String getDate();

	void setDate(String date);

	String getDetail();

	void setDetail(String detail);

	void compute(IAccount account);

	boolean canSendMail(IAccount account);

}
