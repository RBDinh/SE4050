package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.BallotItem;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class BallotItemImpl extends Persistent implements BallotItem {
	
	int voteCount;
	Ballot ballot;

	//constructors
	public BallotItemImpl()
	{
		super(-1);
		voteCount = -1;
		ballot = null;
	}
	
	public BallotItemImpl(int voteCount, Ballot ballot)
	{
		super(-1);
		this.voteCount = voteCount;
		this.ballot = ballot;
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
		return ballot;
	}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		this.ballot = ballot;
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

	@Override
	public boolean isPersistent() {
		// TODO Auto-generated method stub
		return false;
	}

}
