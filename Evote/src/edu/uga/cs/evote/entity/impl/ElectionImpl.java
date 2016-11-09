package edu.uga.cs.evote.entity.impl;

import java.sql.Date;
import java.util.List;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.Election;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class ElectionImpl
extends Persistent
implements Election {

	//attributes
	String electionName;
	Date startDate, endDate;
	int voteCount;
	Ballot ballot;
	String office;
	Boolean isPartisan;
	
	//constructors
	public ElectionImpl()
	{
		super(-1);
		electionName = null;
//		itemID = null;
		voteCount = -1;
		ballot = null;
		office = null;
		isPartisan = null;
	}
	
	//constructors
	public ElectionImpl(String electionName, String itemID,
			int voteCount, Ballot ballot, String office, boolean isPartisan, List<Candidate> candidates)
	{
		super(-1);
		this.voteCount = voteCount;
//		this.itemID = itemID;
		this.ballot = ballot;
		this.office = office;
		this.isPartisan = isPartisan;
		this.candidates = candidates;
	}
	
	//methods
	
	public String getElectionName() {
		return electionName;
	}
	
	public void setElectionName( String electionName ) {
		this.electionName = electionName;
	}
	
//	public String getItemID() {
//		return itemID;
//	}
//	
//	public void setItemID( String itemID ) {
//		this.itemID = itemID;
//	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate( Date date ) {
		this.startDate = date;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate( Date date ) {
		this.endDate = date;
	}
	
	@Override
	public int getVoteCount() {
		return voteCount;
	}

	@Override
	public void setVoteCount(int voteCount) throws EVException {
		this.voteCount = voteCount;
	}
	
	@Override
	public void addVote() {
		voteCount++;
	}

	@Override
	public Ballot getBallot() throws EVException {
		return ballot;
	}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		this.ballot = ballot;
	}

	@Override
	public String getOffice() {
		return office;
	}

	@Override
	public void setOffice(String office) {
		this.office = office;
	}

	@Override
	public boolean getIsPartisan() {
		return isPartisan;
	}

	@Override
	public void setIsPartisan(boolean isPartisan) {
		this.isPartisan = isPartisan;
	}

	@Override
	public List<Candidate> getCandidates() throws EVException {
		return candidates;
	}

	@Override
	public void addCandidate(Candidate candidate) throws EVException {
		this.candidates.add(candidate);
	}

	@Override
	public void deleteCandidate(Candidate candidate) throws EVException {
		this.candidates.remove(candidate);
	}

}
