package edu.uga.cs.evote.entity.impl;

import java.util.List;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.VoteRecord;
import edu.uga.cs.evote.entity.Voter;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class VoterImpl
extends Persistent
implements Voter {
	
	//attributes
	String firstName;
	String lastName;
	String userID;
	String password;
	String emailAddress;
	String address;
	String voterID;
	int age;
	ElectoralDistrict electoralDistrict;
	List<VoteRecord> voteRecord;
	
	//constructor
	public VoterImpl()
	{
		firstName = null;
		lastName = null;
		userID = null;
		password = null;
		emailAddress = null;
		address = null;
		voterID = null;
		age = -1;
		electoralDistrict = null;
		voteRecord = null;
	}
	
	public VoterImpl(
			String firstName,
			String lastName,
			String userID,
			String password,
			String emailAddress,
			String address,
			String voterID,
			int age,
			ElectoralDistrict electoralDistrict,
			List<VoteRecord> voteRecord)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.userID = userID;
		this.password = password;
		this.emailAddress = emailAddress;
		this.address = address;
		this.voterID = voterID;
		this.age = age;
		this.electoralDistrict = electoralDistrict;
		this.voteRecord = voteRecord;
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

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
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

	@Override
	public String getVoterId() {
		return voterID;
	}

	@Override
	public void setVoterId(String voterId) {
		this.voterID = voterId;
	}

	@Override
	public int getAge() {
		return age;
	}

	@Override
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public ElectoralDistrict getElectoralDistrict() throws EVException {
		return electoralDistrict;
	}

	@Override
	public void setElectoralDistrict(ElectoralDistrict electoralDistrict) throws EVException {
		this.electoralDistrict = electoralDistrict;
	}

	@Override
	public List<VoteRecord> getBallotVoteRecords() throws EVException {
		return voteRecord;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setUserName(String userName) {
		// TODO Auto-generated method stub
		
	}

}
