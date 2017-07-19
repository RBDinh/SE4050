package edu.uga.cs.evote.entity;

import java.sql.Date;
import java.util.List;

import edu.uga.cs.evote.EVException;

public interface Election 
    extends BallotItem
{
    /** Return the office for which this election is held.
     * @return the office of this election
     */
    //public String getOffice();
    
    /** Set the new office for which this election is held.
     * @param office the new office for this election
     */
   // public void setOffice( String office );
    
    /** Return true if this is a partisan election and false otherwise.
     * @return partisan status of this election
     */
    public String getIsPartisan();
    
    /** Set the new partisan status of this election.
     * @param isPartisan the new partisan status
     */
    public void setIsPartisan( String isPartisan );
    
    /** Return a list of the candidates for this election.
     * @return the list of the candidates
     * @throws EVException in case there is a problem with traversing a link to the requested objects
     */
    public List<Candidate> getCandidates() throws EVException;
    
    /** Add a candidate for this election.
     * @param candidate the candidate to be added
     * @throws EVException in case there is a problem with setting a link to the requested object
     */
    public void addCandidate( Candidate candidate ) throws EVException;
    
    /** Remove the candidate from the candidates for this election.
     * @param candidate to be removed
     * @throws EVException in case there is a problem with removing the link to the requested object
     */
    public void deleteCandidate( Candidate candidate ) throws EVException;
    
	public String getElectionName();
	
	public void setElectionName( String electionName );
	
	public String getItemID();
	
	public void setItemID( String itemID );
	
	public Date getStartDate();

	public void setStartDate( Date startDate );
	
	public Date getEndDate();
	
	public void setEndDate( Date date );


	public void setIsPartisan(Boolean isPartisan);
	
}
