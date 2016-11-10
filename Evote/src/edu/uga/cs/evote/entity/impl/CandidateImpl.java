package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.Election;
import edu.uga.cs.evote.entity.PoliticalParty;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class CandidateImpl extends Persistent implements Candidate {

	//attributes
	String choiceID;
	String electionName;
	int voteCount;
	String title;
	Election election = null;
	PoliticalParty politicalParty = null;
	String description;
	String partyID;
	
	//constructors
	public CandidateImpl()
	{
		super(-1);
		choiceID = null;
		electionName = null;
		voteCount = -1;
	}
	
	public CandidateImpl(String choiceID, String electionName, String partyID, String title, int voteCount, String description)
	{
		super(-1);
		this.choiceID = choiceID;
		this.electionName = electionName;
		this.voteCount = voteCount;
		this.partyID = partyID;
		this.title = title;
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}

	@Override
	public String getChoiceID() {
		return choiceID;
	}

	@Override
	public void setChoiceID(String choiceID) {
		this.choiceID = choiceID;
	}
	
	@Override	
	public String getName() {
		return electionName;
	}

	@Override
	public void setName(String name) {
		this.electionName = name;
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
	public Election getElection() throws EVException {
		return election;
	}

	@Override
	public void setElection(Election election) throws EVException {
		this.election = election;
	}

	@Override
	public PoliticalParty getPoliticalParty() throws EVException {
		return politicalParty;
	}

	@Override
	public void setPoliticalParty(PoliticalParty politicalParty) throws EVException {
		this.politicalParty = politicalParty;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public void setElectionName(String electionName) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setpartyID(String partyID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTitle(String title) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getElectionName() {
		// TODO Auto-generated method stub
		return electionName;
	}

	@Override
	public String getPartyID() {
		// TODO Auto-generated method stub
		return partyID;
	}

}
