package hms.models;

public class Nurse {
	
	private String name;
	private String phone_number;
	private String pager_number;
	private String email_address;
	private String address;
	private String sin;
	private char gender;
	private int salary;
	
	
	public Nurse(String name, String phone_number, String pager_number, String email_address, String address, String sin, char gender, int salary) {
		this.name = name;
		this.phone_number = phone_number;
		this.pager_number = pager_number;
		this.email_address = email_address;
		this.address = address;
		this.sin = sin;
		this.gender = gender;
		this.salary = salary;
	}
}
