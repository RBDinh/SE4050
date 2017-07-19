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

	String voterName;
	int age;
	String zip;
	String userID;
	
	String firstName;
	String lastName;
	String password;
	String emailAddress;
	String address;
	String voterID;
	ElectoralDistrict electoralDistrict;
	List<VoteRecord> voteRecord;
	private String county;
	
	//constructor
	public VoterImpl()
	{
		zip = null;
		voterName = null;
		firstName = null;
		lastName = null;
		userID = null;
		password = null;
		emailAddress = null;
		address = null;
		voterID = null;
		age = 0;
		electoralDistrict = null;
		voteRecord = null;
	}
	
	public VoterImpl(String voterName, int age, String zip, String userID)
			
	{
		this.voterName = voterName;
		this.age = age;
		this.zip = zip;
		this.userID = userID;
		
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getVoterID() {
		return voterID;
	}

	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	public List<VoteRecord> getVoteRecord() {
		return voteRecord;
	}

	public void setVoteRecord(List<VoteRecord> voteRecord) {
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
	public String getEmail() {
		return emailAddress;
	}

	@Override
	public void setEmail(String emailAddress) {
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

	@Override
	public void setZip(String zip) {
		// TODO Auto-generated method stub
		this.zip = zip;
	}

	@Override
	public String getZip() {
		// TODO Auto-generated method stub
		return zip;
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
		return county;
	}

	@Override
	public void setCounty(String county) {
		this.county = county;
	}

}
