package be.abis.exercise.model;

public class Company{
	
	private String name;
	private String telephoneNumber;
	private String vatNr;
	private Address address;
		
	public Company() {
	}
	
	public Company(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getVatNr() {
		return vatNr;
	}
	public void setVatNr(String vatNr) {
		this.vatNr = vatNr;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String toString(){
		if (address != null) {
			return name + " in " + address.getTown();			
		} else {
			return name;						
		}
	}
	

}
