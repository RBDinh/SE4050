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
	String districtName;
	String zip;
	List<Voter> voters;
	List<Ballot> ballots;
	
	//constructors
	public ElectoralDistrictImpl()
	{
		districtName = null;
		zip = null;

	}
	
	public ElectoralDistrictImpl(String zip, String districtName)
	{
		this.districtName = districtName;
		this.zip = zip;
	
	}
	
	@Override
	public String getName() {
		return districtName;
	}

	@Override
	public void setName(String name) {
		this.districtName = name;
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

	@Override
	public void setDistrictName(String districtName) {
		// TODO Auto-generated method stub
		this.districtName = districtName;
	}

}
