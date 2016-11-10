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
	String itemID;
	Long startDate, endDate;
	Ballot ballot;
	String isPartisan;
	List<Candidate> candidates = null;
	Candidate candidate = null;
	
	//constructors
	public ElectionImpl()
	{
		super(-1);
		electionName = null;
		itemID = null;
		startDate = null;
		endDate = null;
		ballot = null;
		isPartisan = null;
	}
	
	//constructors
	public ElectionImpl(String electionName, String itemID, Long startDate, Long endDate, String isPartisan)
	{
		super(-1);
		this.electionName = electionName;
		this.itemID = itemID;
		this.startDate = startDate;
		this.endDate = endDate;
		this.isPartisan = isPartisan;
	}
	
	//methods
	
	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getElectionName() {
		return electionName;
	}
	
	public void setElectionName( String electionName ) {
		this.electionName = electionName;
	}
	
	public Long getStartDate() {
		return startDate;
	}
	
	public void setStartDate( Long date ) {
		this.startDate = date;
	}
	
	public Long getEndDate() {
		return endDate;
	}
	
	public void setEndDate( Long date ) {
		this.endDate = date;
	}
	
	
	@Override
	public void setVoteCount(int voteCount) throws EVException {
		
	}
	
	@Override
	public void addVote() {
		
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
	public String getIsPartisan() {
		return isPartisan;
	}

	@Override
	public void setIsPartisan(String isPartisan) {
		this.isPartisan = isPartisan;
	}

	@Override
	public List<Candidate> getCandidates() throws EVException {
            if( isPersistent() ) {
            	candidates = getPersistencaLayer().restoreCandidateIsCandidateInElection( this );
            }
		return candidates;
	}

	@Override
	public void addCandidate(Candidate candidate) throws EVException {
		if( candidate  == null ){
			if( isPersistent() ) {
				getPersistencaLayer().storeCandidateIsCandidateInElection(candidate, this);
			}
		}
	}

	@Override
	public void deleteCandidate(Candidate candidate) throws EVException {
		if( candidate  == null ){
			getPersistencaLayer().deleteCandidateIsCandidateInElection(candidate, this);
		}
	}

	@Override
	public int getVoteCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getBallotID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBallotID(String ballotID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setStartDate(java.util.Date startDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndDate(java.util.Date endDate) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setIsPartisan(Boolean isPartisan) {
		// TODO Auto-generated method stub
		
	}

	
}
