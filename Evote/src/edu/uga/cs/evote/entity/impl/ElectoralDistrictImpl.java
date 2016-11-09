package edu.uga.cs.evote.entity.impl;

import java.util.List;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.Voter;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class ElectoralDistrictImpl
extends Persistent
implements ElectoralDistrict {

	//attributes
	String name;
	String zip;
	List<Voter> voters;
	List<Ballot> ballots;
	
	//constructors
	public ElectoralDistrictImpl()
	{
		name = null;
		zip = null;
		voters = null;
		ballots = null;
	}
	
	public ElectoralDistrictImpl(
			String name,
			String zip,
			List<Voter> voters,
			List<Ballot> ballots)
	{
		this.name = name;
		this.zip = zip;
		this.voters = voters;
		this.ballots = ballots;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String getZip() {
		return zip;
	}

	@Override
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public List<Voter> getVoters() throws EVException {
		return voters;
	}

	@Override
	public List<Ballot> getBallots() throws EVException {
		return ballots;
	}

	@Override
	public void addBallot(Ballot ballot) throws EVException {
		ballots.add(ballot);
	}

	@Override
	public void deleteBallot(Ballot ballot) throws EVException {
		ballots.remove(ballot);
	}

}
