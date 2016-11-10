package edu.uga.cs.evote.entity.impl;

import java.util.List;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.PoliticalParty;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class PoliticalPartyImpl
extends Persistent
implements PoliticalParty {
	
	//attributes
	String partyID;
	String partyName;
	String color;
	List<Candidate> candidates;
	
	//constructors
	public PoliticalPartyImpl(String partyID, String partyName, String color)
	{
		this.partyID = partyID;
		this.partyName = partyName;
		this.color = color;
	}
	
	public PoliticalPartyImpl()
	{
		color = null;
		partyID = null;
		partyName = null;
		candidates = null;
	}
	
	
	@Override
	public String getPartyID() {
		return partyID;
	}

	@Override
	public void setPartyID(String partyID) {
		this.partyID = partyID;
	}
	
	@Override
	public String getName() {
		return partyName;
	}

	@Override
	public void setName(String name) {
		this.partyName = name;
	}
	
	@Override
	public String getColor() {
		return color;
	}

	@Override
	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public List<Candidate> getCandidates() throws EVException {
		return candidates;
	}

	@Override
	public void setPartyName(String partyName) {
		// TODO Auto-generated method stub
		this.partyName = partyName;
	}

	public String getPartyName(){
		
		return partyName;
	}
}
