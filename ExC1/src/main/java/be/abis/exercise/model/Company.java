package be.abis.exercise.model;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Company{
	
	@NotEmpty( message="Please enter the company name")
	private String name;
	
	private String telephoneNumber;
	
	private String vatNr;
	
	@NotNull
	@Valid
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
