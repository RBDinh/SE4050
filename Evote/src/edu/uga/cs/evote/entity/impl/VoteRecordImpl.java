package edu.uga.cs.evote.entity.impl;

import java.util.Date;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.VoteRecord;
import edu.uga.cs.evote.entity.Voter;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class VoteRecordImpl
extends Persistent
implements VoteRecord {

	//attributes
	String recordID;
	Date date;
	Voter voter;
	Ballot ballot;
	
	//constructor
	public VoteRecordImpl()
	{
		recordID = null;
		date = null;
		voter = null;
		ballot = null;
	}
	
	public VoteRecordImpl(
			String recordID,
			Date date,
			Voter voter,
			Ballot ballot)
	{
		this.recordID = recordID;
		this.date = date;
		this.voter = voter;
		this.ballot = ballot;
	}

	@Override
	public String getRecordID() {
		return recordID;
	}

	@Override
	public void setRecordID( String recordID ) {
		this.recordID = recordID;
	}
	
	@Override
	public Date getDate() {
		return date;
	}

	@Override
	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public Voter getVoter() throws EVException {
		return voter;
	}

	@Override
	public void setVoter(Voter voter) throws EVException {
		this.voter = voter;
	}

	@Override
	public Ballot getBallot() throws EVException {
		return ballot;
	}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		this.ballot = ballot;
	}

}
