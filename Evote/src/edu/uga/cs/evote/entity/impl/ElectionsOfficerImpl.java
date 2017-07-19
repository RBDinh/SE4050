package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.entity.ElectionsOfficer;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class ElectionsOfficerImpl
extends Persistent
implements ElectionsOfficer {

	//attributes
	//attributes
	private String firstName;
	private String lastName;
	private String userName;
	private String password;
	private String emailAddress;
	private String address;
	private String EOName;
	private String userID;
	
	//constructors
	public ElectionsOfficerImpl()
	{
		EOName = null;
		userID = null;
	}
	
	//constructors
	public ElectionsOfficerImpl(String EOName, String userID)
	{
		this.EOName = EOName;
		this.userID = userID;
	}
	
	public String getEOName() {
		return EOName;
	}

	public void setEOName(String eOName) {
		EOName = eOName;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
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


	public String getUserName() {
		return userID;
	}


	public void setUserName(String userID) {
		this.userID = userID;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

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

	@Override
	public String getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setState(String state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCounty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCounty(String county) {
		// TODO Auto-generated method stub
		
	}
}
