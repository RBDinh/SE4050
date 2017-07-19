package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.entity.User;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class UserImpl
extends Persistent
implements User {
	
	//attributes
	String firstName;
	String lastName;
	String userID;
	String password;
	String email;
	String address;
	String county;
	String state;
	
	//constructors
	public UserImpl()
	{
		firstName = null;
		lastName = null;
		userID = null;
		password = null;
		email = null;
		address = null;
		county = null;
		state = null;
	}
	
	public UserImpl(
			String userID,
			String password,
			String firstName,
			String lastName,
			String address,
			String state,
			String county,
			String email)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.email = email;
		this.address = address;
		this.county = county;
		this.email = email;
		this.state = state;
	}

	
	
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
		return userID;
	}

	@Override
	public void setUserName(String userName) {
		this.userID = userName;
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
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getState() {
		return state;
	}
	
	public void setState( String state) {
		this.state = state;
	}
	
	public String getCounty() {
		return county;
	}
	
	public void setCounty (String county) {
		this.county = county;
	}

}
