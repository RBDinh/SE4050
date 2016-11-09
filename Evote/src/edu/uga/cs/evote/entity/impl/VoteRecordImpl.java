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
	String voterName;
	String ballotID;
	Date date;
	Voter voter;
	Ballot ballot;
	
	//constructor
	public VoteRecordImpl()
	{
        super( -1 );
		recordID = null;
		voterName = null;
		ballotID = null;
		date = null;
		voter = null;
		ballot = null;
	}
	
	public VoteRecordImpl(
			String recordID,
			String voterName,
			String ballotID,
			Date date
			)
	{
		this.recordID = recordID;
		this.voterName = voterName;
		this.ballotID = ballotID;
		this.date = date;
	}

	public String getVoterName() {
		return voterName;
	}

	public void setVoterName(String voterName) {
		this.voterName = voterName;
	}

	public String getBallotID() {
		return ballotID;
	}

	public void setBallotID(String ballotID) {
		this.ballotID = ballotID;
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
		if(ballot == null){
			ballot = getPersistencaLayer().restoreBallotIncludesBallotItem(this);
			throw new EVException( "This ballot object is not persistent" );
		}
		
		return ballot;
		}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		this.ballot = ballot;
	}

}
