package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.BallotItem;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class BallotItemImpl extends Persistent implements BallotItem {
	
	String itemID;
	String ballotID;
	int voteCount;
	Ballot ballot;

	//constructors
	public BallotItemImpl()
	{
		super(-1);
		itemID = null;
		ballotID = null;
		voteCount = -1;
		ballot = null;
	}
	
	public BallotItemImpl(String itemID, String ballotID, int voteCount)
	{
		super(-1);
		this.itemID = itemID;
		this.ballotID = ballotID;
		this.voteCount = voteCount;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getBallotID() {
		return ballotID;
	}

	public void setBallotID(String ballotID) {
		this.ballotID = ballotID;
	}

	//methods
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
		if(ballot == null){
				ballot = getPersistencaLayer().restoreBallotIncludesBallotItem(this);
		}
			return ballot;
	}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		this.ballot = ballot;
	}

}
