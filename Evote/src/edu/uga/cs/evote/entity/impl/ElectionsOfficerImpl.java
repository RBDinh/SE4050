package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.entity.ElectionsOfficer;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class ElectionsOfficerImpl
extends Persistent
implements ElectionsOfficer {

	//attributes
	String firstName;
	String lastName;
	String userName;
	String password;
	String emailAddress;
	String address;
	
	//constructors
	public ElectionsOfficerImpl()
	{
		firstName = null;
		lastName = null;
		userName = null;
		password = null;
		emailAddress = null;
		address = null;
	}
	
	//constructors
	public ElectionsOfficerImpl(
			String firstName,
			String lastName,
			String userName,
			String password,
			String emailAddress,
			String address)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.emailAddress = emailAddress;
		this.address = address;
	}
	
	//methods
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getUserName() {
		return userName;
	}

	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}
}
