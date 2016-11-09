 

import java.util.Date;
import java.util.List;
/*
import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.Election;
import edu.uga.cs.evote.entity.ElectionsOfficer;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.Issue;
import edu.uga.cs.evote.entity.PoliticalParty;
import edu.uga.cs.evote.entity.VoteRecord;
import edu.uga.cs.evote.entity.Voter;
*/

import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.impl.*;
import edu.uga.cs.evote.object.ObjectLayer;
import edu.uga.cs.evote.persistence.impl.*;

public class ObjectLayerImpl implements ObjectLayer
{
    PersistenceLayerImpl persistence = null;
    
    public ObjectLayerImpl()
    {
        this.persistence = null;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(): initialized" );
    }
    
    public ObjectLayerImpl( PersistenceLayerImpl persistence )
    {
        this.persistence = persistence;
        System.out.println( "ObjectLayerImpl.ObjectLayerImpl(persistence): initialized" );
    }
    
    /**
     * Create a new ElectionsOfficer object, given the set of initial attribute values.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user name (login name)
     * @param password the password
     * @param emailAddress the email address
     * @param address the address
     * @return a new ElectionsOfficer object instance with the given attribute values
     * @throws EVException in case either firstName, lastName, or userName is null
     */
    public ElectionsOfficerImpl createElectionsOfficer( String firstName, String lastName, String userName, 
                                                    String password, String emailAddress, String address ) throws EVException {
        ElectionsOfficerImpl eo = new ElectionsOfficerImpl(firstName, lastName, userName, password, emailAddress, address);
        eo.setPersistenceLayerImpl(persistence);
        return eo;
    }

    /**
     * Create a new ElectionsOfficer object with undefined attribute values.
     * @return a new ElectionsOfficer object instance
     */
    public ElectionsOfficerImpl createElectionsOfficer() {
        ElectionsOfficerImpl eo = new ElectionsOfficerImpl(null, null, null, null, null, null);
        eo.setId(-1);
        eo.setPersistenceLayerImpl(persistence);
        return eo;
    }
    
    /**
     * Return a List of ElectionsOfficer objects satisfying the search criteria given in the modelElectionsOfficer object.
     * @param modelElectionsOfficer a model ElectionsOfficer object specifying the search criteria
     * @return a List of the located ElectionsOfficer objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<ElectionsOfficerImpl> findElectionsOfficer( ElectionsOfficerImpl modelElectionsOfficer ) throws EVException {
        return persistence.restoreElectionsOfficer(modelElectionsOfficer);
    }
    
    /**
     * Store a given ElectionsOfficer object in persistent data store.
     * @param electionsOfficer the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeElectionsOfficer( ElectionsOfficerImpl electionsOfficer ) throws EVException {
        persistence.storeElectionsOfficer(electionsOfficer);
    }
    
    /**
     * Delete this ElectionsOfficer object.
     * @param electionsOfficer the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteElectionsOfficer( ElectionsOfficerImpl electionsOfficer ) throws EVException {
        persistence.deleteElectionsOfficer(electionsOfficer);
    }
    
    /**
     * Create a new Voter object, given the set of initial attribute values.
     * @param firstName the first name
     * @param lastName the last name
     * @param userName the user (login) name
     * @param password the password
     * @param emailAddress the email address
     * @param address the Voter's address
     * @param age the Voter's age
     * @return a new Voter object instance with the given attribute values
     * @throws EVException in case any of the String parameters is null or if age is not positive
     */
    public VoterImpl createVoter( String firstName, String lastName, String userName, String password, 
            String emailAddress, String address, int age ) throws EVException {
        VoterImpl voter = new VoterImpl(firstName, lastName, userName, password, emailAddress, address, age);
        voter.setPersistenceLayerImpl(persistence);
        return voter;
    }

    /**
     * Create a new Voter object with undefined attribute values.
     * @return a new Voter object instance
     */
    public VoterImpl createVoter() {
        VoterImpl voter = new VoterImpl(null, null, null, null, null, null, -1);
        voter.setId(-1);
        voter.setPersistenceLayerImpl(persistence);
        return voter;
    }
    
    /**
     * Return a List of Voter objects satisfying the search criteria given in the modelVoter object.
     * @param modelVoter a model Voter object specifying the search criteria
     * @return a List of the located Voter objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<VoterImpl> findVoter( VoterImpl modelVoter ) throws EVException {
        return persistence.restoreVoter(modelVoter);
    }
    
    /**
     * Store a given Voter object in persistent data store.
     * @param voter the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeVoter( VoterImpl voter ) throws EVException {
        persistence.storeVoter(voter);
    }
    
    /**
     * Delete this Voter object.
     * @param voter the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteVoter( VoterImpl voter ) throws EVException {
        persistence.deleteVoter(voter);
    }
 
    /**
     * Create a new PoliticalParty object, given the set of initial attribute values.
     * @param name the name of the political party
     * @return a new PoliticalParty object instance with the given attribute values
     * @throws EVException in case name is null
     */
    public PoliticalPartyImpl createPoliticalParty( String name ) throws EVException {
        PoliticalPartyImpl politicalParty = new PoliticalPartyImpl(name);
        persistence.setPoliticalParty(politicalParty);
        return politicalParty;
    }

    /**
     * Create a new PoliticalParty object with undefined attribute values.
     * @return a new PoliticalParty object instance
     */
    public PoliticalPartyImpl createPoliticalParty() {
        PoliticalPartyImpl politicalParty = new PoliticalPartyImpl(null);
        politicalParty.setId(-1);
        persistence.setPoliticalParty(politicalParty);
        return politicalParty;
    }

    /**
     * Return a List of PoliticalParty objects satisfying the search criteria given in the modelPoliticalParty object.
     * @param modelPoliticalParty a model PoliticalParty object specifying the search criteria
     * @return a List of the located PoliticalParty objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<PoliticalPartyImpl> findPoliticalParty( PoliticalPartyImpl modelPoliticalParty ) throws EVException {
        return persistence.restorePoliticalParty(modelPoliticalParty);
    }
    
    /**
     * Store a given PoliticalParty object in persistent data store.
     * @param politicalParty the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storePoliticalParty( PoliticalPartyImpl politicalParty ) throws EVException {
        persistence.storePoliticalParty(politicalParty);
    }
    
    /**
     * Delete this PoliticalParty object.
     * @param politicalParty the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deletePoliticalParty( PoliticalPartyImpl politicalParty ) throws EVException {
        persistence.storePoliticalParty(politicalParty);
    }

    /**
     * Create a new ElectoralDistrict object, given the set of initial attribute values.
     * @param name the name of the political party
     * @return a new ElectoralDistrict object instance with the given attribute values
     * @throws EVException in case name is null
     */
    public ElectoralDistrictImpl createElectoralDistrict( String name ) throws EVException {
        ElectoralDistrictImpl electoralDistrict = new ElectoralDistrictImpl(name);
        persistence.setElectoralDistrict(electoralDistrict);
        return electoralDistrict;
    }

    /**
     * Create a new ElectoralDistrict object with undefined attribute values.
     * @return a new ElectoralDistrict object instance
     */
    public ElectoralDistrictImpl createElectoralDistrict() {
        ElectoralDistrictImpl electoralDistrict = new ElectoralDistrictImpl(null);
        electoralDistrict.setId(-1);
        persistence.setElectoralDistrict(electoralDistrict);
        return electoralDistrict;
    }

    /**
     * Return a List of ElectoralDistrict objects satisfying the search criteria given in the modelElectoralDistrict object.
     * @param modelElectoralDistrict a model ElectoralDistrict object specifying the search criteria
     * @return a List of the located ElectoralDistrict objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<ElectoralDistrictImpl> findElectoralDistrict( ElectoralDistrictImpl modelElectoralDistrict ) throws EVException {
        return persistence.restoreElectoralDistrict(modelElectoralDistrict);
    }
    
    /**
     * Store a given ElectoralDistrict object in persistent data store.
     * @param electoralDistrict the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeElectoralDistrict( ElectoralDistrictImpl electoralDistrict ) throws EVException {
        persistence.storeElectoralDistrict(electoralDistrict);
    }
    
    /**
     * Delete this ElectoralDistrict object.
     * @param electoralDistrict the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteElectoralDistrict( ElectoralDistrictImpl electoralDistrict ) throws EVException {
        persistence.deleteElectoralDistrict(electoralDistrict);
    }

    /**
     * Create a new Ballot object, given the set of initial attribute values.
     * @param openDate the date when the ballot should be open for voting
     * @param closeDate the date when the ballot should be closed for voting
     * @param approved indication if the ballot has been approved
     * @param electoralDistrict the electoral district of this ballot
     * @return a new Ballot object instance with the given attribute values
     * @throws EVException in case any of the arguments are null or if the electoralDistrict is not persistent
     */
    public BallotImpl createBallot( Date openDate, Date closeDate, boolean approved, ElectoralDistrictImpl electoralDistrict ) throws EVException {
        BallotImpl ballot = new BallotImpl(openDate, closeDate, approved, electoralDistrict);
        persistence.setBallot(ballot);
        return ballot;
    }

    /**
     * Create a new Ballot object with undefined attribute values.
     * @return a new Ballot object instance
     */
    public BallotImpl createBallot() {
        BallotImpl ballot = new BallotImpl(null, null, false, null);
        ballot.setId(-1);
        persistence.setBallot(ballot);
        return ballot;
    }

    /**
     * Return a List of Ballot objects satisfying the search criteria given in the modelBallot object.
     * @param modelBallot a model Ballot object specifying the search criteria
     * @return a List of the located Ballot objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<BallotImpl> findBallot( BallotImpl modelBallot ) throws EVException {
        return persistence.restoreBallot(modelBallot);
    }
    
    /**
     * Store a given Ballot object in persistent data store.
     * @param ballot the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeBallot( BallotImpl ballot ) throws EVException {
        persistence.storeBallot(ballot);
    }
    
    /**
     * Delete this Ballot object.
     * @param ballot the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteBallot( BallotImpl ballot ) throws EVException {
        persistence.deleteBallot(ballot);
    }

    /**
     * Create a new Candidate object, given the set of initial attribute values.
     * @param name the name of the candidate
     * @param politicalParty the political party the candidate belongs to; it may be null for non partisan elections
     * @param election the election for which this candidate is running
     * @return a new Candidate object instance with the given attribute values
     * @throws EVException in case either the name or the politicalParty are null
     */
    public CandidateImpl createCandidate( String name, PoliticalPartyImpl politicalParty, ElectionImpl election ) throws EVException {
        CandidateImpl candidate = new CandidateImpl(name, politicalParty, election);
        persistence.setCandidate(candidate);
        return candidate;
    }

    /**
     * Create a new Candidate object with undefined attribute values.
     * @return a new Candidate object instance
     */
    public CandidateImpl createCandidate() {
        CandidateImpl candidate = new CandidateImpl(null, null, null);
        candidate.setId(-1);
        persistence.setCandidate(candidate);
        return candidate;
    }

    /**
     * Return a List of Candidate objects satisfying the search criteria given in the modelCandidate object.
     * @param modelCandidate a model Candidate object specifying the search criteria
     * @return a List of the located Candidate objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<CandidateImpl> findCandidate( CandidateImpl modelCandidate ) throws EVException {
        return persistence.restoreCandidate(modelCandidate);
    }
    
    /**
     * Store a given Candidate object in persistent data store.
     * @param candidate the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeCandidate( CandidateImpl candidate ) throws EVException {
        persistence.storeCandidate(candidate);
    }
    
    /**
     * Delete this Candidate object.
     * @param candidate the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteCandidate( CandidateImpl candidate ) throws EVException {
        persistence.deleteCandidate(candidate);
    }

    /**
     * Create a new Issue object, given the set of initial attribute values.
     * @param question the question of the this issue
     * @return a new Issue object instance with the given attribute value
     * @throws EVException in case question is null
     */
    public IssueImpl createIssue( String question ) throws EVException {
        IssueImpl issue = new IssueImpl(question);
        persistence.setIssue(issue);
        return issue;
    }

    /**
     * Create a new Issue object with undefined attribute values.
     * @return a new Issue object instance
     */
    public IssueImpl createIssue() {
        IssueImpl issue = new IssueImpl(null);
        issue.setId(-1);
        persistence.setIssue(issue);
        return issue;
    }

    /**
     * Return a List of Issue objects satisfying the search criteria given in the modelIssue object.
     * @param modelIssue a model Issue object specifying the search criteria
     * @return a List of the located Issue objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<IssueImpl> findIssue( IssueImpl modelIssue ) throws EVException {
        return persistence.restoreIssue(modelIssue);
    }
    
    /**
     * Store a given Issue object in persistent data store.
     * @param issue the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeIssue( IssueImpl issue ) throws EVException {
        persistence.storeIssue(issue);
    }
    
    /**
     * Delete this Issue object.
     * @param issue the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteIssue( IssueImpl issue ) throws EVException {
        persistence.deleteIssue(issue);
    }

    /**
     * Create a new Election object, given the set of initial attribute values.
     * @param office the office this Election is for
     * @param isPartisan indication if this Election is partisan
     * @return a new Election object instance with the given attribute value
     * @throws EVException in case question is null
     */
    public ElectionImpl createElection( String office, boolean isPartisan ) throws EVException {
        ElectionImpl election = new ElectionImpl(office, isPartisan);
        persistence.setElection(election);
        return election;
    }

    /**
     * Create a new Election object with undefined attribute values.
     * @return a new Election object instance
     */
    public ElectionImpl createElection() {
        ElectionImpl election = new ElectionImpl(null, false);
        election.setId(-1);
        persistence.setElection(election);
        return election;
    }

    /**
     * Return a List of Election objects satisfying the search criteria given in the modelElection object.
     * @param modelElection a model Election object specifying the search criteria
     * @return a List of the located Election objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<ElectionImpl> findElection( ElectionImpl modelElection ) throws EVException {
        return persistence.restoreElection(modelElection);
    }
    
    /**
     * Store a given Election object in persistent data store.
     * @param election the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeElection( ElectionImpl election ) throws EVException {
        persistence.storeElection(election);
    }
    
    /**
     * Delete this Election object.
     * @param election the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteElection( ElectionImpl election ) throws EVException {
        persistence.deleteElection(election);
    }
    
    /**
     * Create a new VoteRecord object, given the set of initial attribute values.
     * @param ballot the Ballot for which a vote has been cast
     * @param voter the Voter who cast a vote
     * @param date the Date when the vote has been cast
     * @return a new VoteRecord object instance with the given attribute value
     * @throws EVException in case either of the arguments is null
     */
    public VoteRecordImpl createVoteRecord( BallotImpl ballot, VoterImpl voter, Date date ) throws EVException {
        VoteRecordImpl voteRecord = new VoteRecordImpl(ballot, voter, date);
        persistence.setVoteRecord(voteRecord);
        return voteRecord;
    }

    /**
     * Create a new VoteRecord object with undefined attribute values.
     * @return a new VoteRecord object instance
     */
    public VoteRecordImpl createVoteRecord() {
        VoteRecordImpl voteRecord = new VoteRecordImpl(null, null, null);
        voteRecord.setId(-1);
        persistence.setVoteRecord(voteRecord);
        return voteRecord;
    }

    /**
     * Return a List of VoteRecord objects satisfying the search criteria given in the modelVoteRecord object.
     * @param modelVoteRecord a model VoteRecord object specifying the search criteria
     * @return a List of the located VoteRecord objects
     * @throws EVException in case there is a problem with the retrieval of the requested objects
     */
    public List<VoteRecordImpl> findVoteRecord( VoteRecordImpl modelVoteRecord ) throws EVException {
        return persistence.restoreVoteRecord(modelVoteRecord);
    }
    
    /**
     * Store a given VoteRecord object in persistent data store.
     * @param voteRecord the object to be persisted
     * @throws EVException in case there was an error while persisting the object
     */
    public void storeVoteRecord( VoteRecordImpl voteRecord ) throws EVException {
        persistence.storeVoteRecord(voteRecord);
    }
    
    /**
     * Delete this VoteRecord object.
     * @param voteRecord the object to be deleted.
     * @throws EVException in case there is a problem with the deletion of the object
     */
    public void deleteVoteRecord( VoteRecordImpl voteRecord ) throws EVException {
        persistence.deleteVoteRecord(voteRecord);
    }
}