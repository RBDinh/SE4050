package edu.uga.cs.evote.entity.impl;

import java.util.Date;
import java.util.List;

import edu.uga.cs.evote.persistence.impl.Persistent;
import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.BallotItem;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.VoteRecord;

public class BallotImpl 
extends Persistent
implements Ballot {

	//ballot attributes
	long ballotID;
	String zip;
	String EOName;
	String name;
	Date openDate;
	Date closeDate;
	ElectoralDistrict ED;
	
	//constructors
	public BallotImpl()
	{
		ballotID = -1;
		zip = null;
		EOName = null;
		name = null;
		openDate = null;
		closeDate = null;
		ED = null;
		ballotItems = null;
		voteRecord = null;
	}
	
	public BallotImpl(long ballotID,	String zip,
			String EOName,
			String name,
			Date openDate,
			Date closeDate,
			ElectoralDistrict ED,
			List<BallotItem> ballotItems,
			List<VoteRecord> voteRecord )
	{
		this.ballotID = ballotID;
		this.zip = zip;
		this.EOName = EOName;
		this.name = name;
		this.openDate = openDate;
		this.closeDate = closeDate;
		this.ED = ED;
		this.ballotItems = ballotItems;
		this.voteRecord = voteRecord;
	}

	//methods
	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long getBallotID() {
		return ballotID;
	}

	@Override
	public void setBallotID(long ballotID) {
		this.ballotID = ballotID;
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
	public String getEOName() {
		return EOName;
	}

	@Override
	public void setEOName(String EOName) {
		this.EOName = EOName;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String Name) {
		this.name = Name;
	}

	@Override
	public Date getOpenDate() {
		return openDate;
	}

	@Override
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	@Override
	public Date getCloseDate() {
		return closeDate;
	}

	@Override
	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	@Override
	public ElectoralDistrict getElectoralDistrict() throws EVException {
		return ED;
	}

	@Override
	public void setElectoralDistrict(ElectoralDistrict electoralDistrict) throws EVException {
		this.ED = electoralDistrict;
	}

	@Override
	public List<BallotItem> getBallotItems() throws EVException {
		return ballotItems;
	}

	@Override
	public void addBallotItem(BallotItem ballotItem) throws EVException {
		ballotItems.add(ballotItem);
	}

	@Override
	public void deleteBallotItem(BallotItem ballotItem) throws EVException {
		ballotItems.remove(ballotItem);
	}

	@Override
	public List<VoteRecord> getVoterVoteRecords() throws EVException {
		return voteRecord;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setId(long id) {
		// TODO Auto-generated method stub
		
	}

}
