package edu.uga.cs.evote.entity.impl;

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.Issue;
import edu.uga.cs.evote.persistence.impl.Persistent;

public class IssueImpl
extends Persistent
implements Issue {

	//attributes
	String issueID;
	String itemID;
	String description;
	String questionTitle;
	int yesCount, noCount;
	Ballot ballot = null;
	BallotImpl ballotItem = null;
	
	//constructors
	public IssueImpl()
	{
		itemID = null;
		description = null;
		questionTitle = null;
		yesCount = -1;
		noCount = -1;
	}
	
	//constructors
	public IssueImpl(String issueID, String itemID, String questionTitle, String description, int yesCount, int noCount)
	{
		this.issueID = issueID;
		this.itemID = itemID;
		this.description = description;
		this.questionTitle = questionTitle;
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
	public String getQuestion() {
		return questionTitle;
	}

	@Override
	public void setQuestion(String questionTitle) {
		this.questionTitle = questionTitle;
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
	}

	@Override
	public void addNoVote() {
		noCount++;
	}

	@Override
	public String getBallotID() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getVoteCount() {
		return yesCount + noCount;
	}

	@Override
	public void setVoteCount(int voteCount) throws EVException {
		// TODO Auto-generated method stub
	}

	@Override
	public void addVote() {
		// TODO Auto-generated method stub
	}

	@Override
	public Ballot getBallot() throws EVException {
		// TODO Auto-generated method stub
		ballotItem.getPersistencaLayer().restoreBallotItemIncludesIssue(this);
		
		return ballot;
	}

	@Override
	public void setBallot(Ballot ballot) throws EVException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBallotID(String ballotID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getIssueID() {
		// TODO Auto-generated method stub
		return issueID;
	}

	@Override
	public void setIssueID(String issueID) {
		// TODO Auto-generated method stub
		this.issueID = issueID;
	}

	@Override
	public void setNoCount(int noCount) {
		// TODO Auto-generated method stub
		
	}

}
