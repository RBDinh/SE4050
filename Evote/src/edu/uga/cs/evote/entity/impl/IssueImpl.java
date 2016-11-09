package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.Issue;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class IssueImpl
extends Persistent
implements Issue {

	//attributes
	String itemID;
	String description;
	int voteCount;
	Ballot ballot;
	String question;
	int yesCount, noCount;
	
	//constructors
	public IssueImpl()
	{
		itemID = null;
		description = null;
		voteCount = -1;
		ballot = null;
		question = null;
		yesCount = -1;
		noCount = -1;
	}
	
	//constructors
	public IssueImpl(
			String itemID,
			String description,
			int voteCount,
			Ballot ballot,
			String question,
			int yesCount,
			int noCount)
	{
		this.itemID = itemID;
		this.description = description;
		this.voteCount = voteCount;
		this.ballot = ballot;
		this.question = question;
		this.yesCount = yesCount;
		this.noCount = noCount;
	}
	
	//methods
	public String getItemID() {
		return itemID;
	}
	
	public void setItemID( String itemID ) {
		this.itemID = itemID;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription( String description ) {
		this.description = description;
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
	public String getQuestion() {
		return question;
	}

	@Override
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public int getYesCount() {
		return yesCount;
	}

	@Override
	public void setYesCount(int yesCount) throws EVException {
		this.yesCount = yesCount;
	}

	@Override
	public int getNoCount() {
		return noCount;
	}

	@Override
	public void addYesVote() {
		yesCount++;
		voteCount++;
	}

	@Override
	public void addNoVote() {
		noCount++;
		voteCount++;
	}

}
