//hello
package edu.uga.cs.evote.persistence.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import edu.uga.cs.evote.object.ObjectLayer;
import edu.uga.cs.evote.persistence.PersistenceLayer;
import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.BallotItem;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.Election;
import edu.uga.cs.evote.entity.ElectionsOfficer;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.Issue;
import edu.uga.cs.evote.entity.PoliticalParty;
import edu.uga.cs.evote.entity.VoteRecord;
import edu.uga.cs.evote.entity.Voter;

public class PersistenceLayerImpl implements PersistenceLayer {
	
	 private ObjectLayer objectLayer = null;
	 private Connection   conn = null;
	    
	 public PersistenceLayerImpl( Connection conn, ObjectLayer objectLayer )
	 {
		 this.conn = conn;
		 this.objectLayer = objectLayer;
	 }

	@Override
	public List<ElectionsOfficer> restoreElectionsOfficer(ElectionsOfficer modelElectionsOfficer) throws EVException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void storeElectionsOfficer(ElectionsOfficer electionsOfficer) throws EVException {
		// TODO Auto-generated method stub
		 String               insertMembershipSql = "INSERT INTO electionOfficer (EOName, userID) VALUES ( ?, ? )";              
	        PreparedStatement    stmt = null;
	        int                  inscnt;
	        long                 membershipId;
	        
	        if( electionsOfficer.getEOName() == null || electionsOfficer.getuserID() == null )
	            throw new EVException( "MembershipManager.save: Attempting to save a Membership with no Person or Club defined" );
	        if( !electionsOfficer.getEOName().isPersistent() || !electionsOfficer.getuserID().isPersistent() )
	            throw new EVException( "MembershipManager.save: Attempting to save a Membership where either Person or Club are not persistent" );
	                              
	        try {
	            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
	            
	            stmt.setLong( 1, electionsOfficer.getEOName().getId() );
	            stmt.setLong( 2, electionsOfficer.getuserID().getId() );
	            
	            inscnt = stmt.executeUpdate();
	            
	            if( inscnt >= 1 ) {
	                String sql = "select last_insert_id()";
	                if( stmt.execute( sql ) ) { // statement returned a result

	                    // retrieve the result
	                    ResultSet r = stmt.getResultSet();

	                    // we will use only the first row!
	                    //
	                    while( r.next() ) {

	                        // retrieve the last insert auto_increment value
	                        membershipId = r.getLong( 1 );
	                        if( membershipId > 0 )
	                        	electionsOfficer.setId( membershipId ); // set this membership's db id (proxy object)
	                    }
	                }
	            }
	            else
	                throw new EVException( "MembershipManager.save: failed to save a Membership" );
	        }
	        catch( SQLException e ) {
	            e.printStackTrace();
	            throw new EVException( "MembershipManager.save: failed to save a Membership: " + e );
	        }
	}

	@Override
	public void deleteElectionsOfficer(ElectionsOfficer electionsOfficer) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM club WHERE EOName = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !electionsOfficer.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, electionsOfficer.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }

	}

	@Override
	public List<Voter> restoreVoter(Voter modelVoter) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT voterName, age, zip, userID FROM voter";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Voter>   voters = new ArrayList<Voter>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelVoter != null ) {
            if( modelVoter.getVoterName() >= 0 ) // id is unique, so it is sufficient to get a person
                query.append( " where voterName = " + modelVoter.getVoterName() );
           
        }
        try {
            stmt = conn.createStatement();
		 if( stmt.execute( query.toString() ) ) { 
		    String voterName;
		    int age;
		    String zip;
		    String userID;
		    Voter nextVoter = null;
			ResultSet rs = stmt.getResultSet();

		    while( rs.next() ) {
			voterName = rs.getString( 1 );
			age = rs.getInt( 2 );
			zip = rs.getString( 3 );
			userID = rs.getString( 4 );

			nextVoter = objectLayer.createVoter();
			nextVoter.setVoterName(voterName);
			nextVoter.setAge(age);
			nextVoter.setZip(zip);
			nextVoter.setUserID(userID);

			voters.add(nextVoter);
		    }
		 }
            
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		
		return voters;
	}

	@Override
	public void storeVoter(Voter voter) throws EVException {
		// TODO Auto-generated method stub
		String               insertMembershipSql = "INSERT INTO voter (voterName, age, zip, userID) VALUES ( ?, ?, ?, ? )";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( voter.getVoterName() == null || voter.getAge() == null || voter.getZip() == null || voter.getUserID() == null)
            throw new EVException( "MembershipManager.save: Attempting to save a Membership with no Person or Club defined" );
        if( !voter.getVoterName().isPersistent() || !voter.getAge().isPersistent() || !voter.getZip().isPersistent() || !voter.getUserID().isPersistent())
            throw new EVException( "MembershipManager.save: Attempting to save a Membership where either Person or Club are not persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setLong( 1, voter.getVoterName() );
            stmt.setLong( 2, voter.getAge() );
            stmt.setLong( 3, voter.getZip() );
            stmt.setLong( 4, voter.getUserID() );
            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	voter.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "MembershipManager.save: failed to save a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "MembershipManager.save: failed to save a Membership: " + e );
        }
	}

	@Override
	public void deleteVoter(Voter voter) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM voter WHERE voterName = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !voter.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, voter.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }

	}

	@Override
	public List<Ballot> restoreBallot(Ballot modelBallot) throws EVException {
		// TODO Auto-generated method stub
		 	String       sqlQuery = "SELECT ballotID, zip, EOName, bName, approved FROM ballot";
	        Statement    stmt = null;
	        StringBuffer query = new StringBuffer( 100 );
	        StringBuffer condition = new StringBuffer( 100 );
	        List<Ballot>   ballots = new ArrayList<Ballot>();
		
	        condition.setLength( 0 );
	         query.append( sqlQuery );
	        if( modelBallot != null ) {
	            if( modelBallot.getBallotID() >= 0 ) // id is unique, so it is sufficient to get a person
	                query.append( " where ballotID = " + modelBallot.getBallotID() );
	           
	        }
	        
	        try {
                stmt = conn.createStatement();
                long ballotID;
            	String zip;
            	String EOName;
            	String name;
            	Date openDate;
            	Date closeDate;
            	Ballot nextBallot = null;
            	
		if( stmt.execute( query.toString() ) ) {
			ResultSet rs = stmt.getResultSet();

			// retrieve the retrieved clubs
			while( rs.next() ) {
				ballotID = rs.getLong( 1 );
				zip = rs.getString( 2 );
				EOName = rs.getString( 3 );
				name = rs.getString( 4 );
				openDate = rs.getDate( 5 );
				closeDate = rs.getDate( 6 );

				nextBallot = objectLayer.createBallot();
				nextBallot.setBallotID(ballotID);
				nextBallot.setZip(zip);
				nextBallot.setEOName(EOName);
				nextBallot.setName(name);
				nextBallot.setOpenDate(openDate);
				nextBallot.setCloseDate(closeDate);

				ballots.add( nextBallot );
			}
		}
            }
	        
	        catch( Exception e ) {      // just in case...
	            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
	        }

		return ballots;
	}

	@Override
	public void storeBallot(Ballot ballot) throws EVException {
		// TODO Auto-generated method stub
		String               insertMembershipSql = "INSERT INTO ballot (ballotID, zip, EOName, bName, approved) VALUES ( ?, ?, ?, ?, ? )";              
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( ballot.getBallotID() == null || ballot.getZip() == null || ballot.getEOName() == null || ballot.getbName() == null, || ballot.getApproved() == null)
            throw new EVException( "MembershipManager.save: Attempting to save a Membership with no Person or Club defined" );
        if( !ballot.getBallotID().isPersistent() || !ballot.getZip().isPersistent() || !ballot.getEOName().isPersistent() || !ballot.getbName().isPersistent() || !ballot.getApproved().isPersistent())
            throw new EVException( "MembershipManager.save: Attempting to save a Membership where either Person or Club are not persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setLong( 1, ballot.getBallotID() );
            stmt.setNString( 2, ballot.getZip() );
            stmt.setNString( 3, ballot.getEOName() );
            stmt.setLong( 4, ballot.getbName() );
            stmt.setLong( 5, ballot.getApproved() );

            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	ballot.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "MembershipManager.save: failed to save a Membership" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "MembershipManager.save: failed to save a Membership: " + e );
        }
	}

	@Override
	public void deleteBallot(Ballot ballot) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM ballot WHERE ballotID = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !ballot.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, ballot.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }

	}

	@Override
	public List<Candidate> restoreCandidate(Candidate modelCandidate) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT choiceID, electionName, partyID, title, voteCount, description FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Candidate>   candidates = new ArrayList<Candidate>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelCandidate != null ) {
            if( modelCandidate.getChoiceID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where ballotID = " + modelCandidate.getChoiceID() );
           
        }
        
        try {
		stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String choiceID;
			String electionName;
			String partyID;
			String title;
			int voteCount;
			String description;
			Candidate nextCandidate = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			choiceID = rs.getString(1);
			electionName = rs.getString(2);
			partyID = rs.getString(3);
			title = rs.getString(4);
			voteCount = rs.getInt(5);
			description = rs.getString(6);

			nextCandidate = objectLayer.createCandidate();
			nextCandidate.setChoiceID(choiceID);
			nextCandidate.setElectionName(electionName);
			nextCandidate.setpartyID(partyID);
			nextCandidate.setTitle(title);
			nextCandidate.setVoteCount(voteCount);
			nextCandidate.setDescription(description);

			candidates.add(nextCandidate);
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return candidates;
	}

	@Override
	public void storeCandidate(Candidate candidate) throws EVException {
		// TODO Auto-generated method stub
		String               insertMembershipSql = "INSERT INTO candidate "
				+ "(choiceID, electionName, partyID, title, voteCount, description) "
				+ "VALUES ( ?, ?, ?, ?, ?, ?)";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( candidate.getName() == null )
            throw new EVException( "PersistenceLayer.save: Attempting to save a Ballot with no ballotId" );
        if( candidate.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to save a ballot that isn't persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setString( 1, candidate.getChoiceID() );
            stmt.setLong( 2, candidate.getElection().getId() );
            stmt.setLong( 3, candidate.getPoliticalParty().getId() );
            stmt.setString( 4, candidate.getName() );
            stmt.setInt( 5, candidate.getVoteCount() );
            stmt.setString( 6, candidate.getDescription() );

            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	candidate.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to save a Candidate" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to save a Candidate: " + e );
        }


	}

	@Override
	public void deleteCandidate(Candidate candidate) throws EVException {
		// TODO Auto-generated method stub

		String               deleteClubSql = "DELETE FROM candidate WHERE choiceID = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !candidate.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, candidate.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public List<Election> restoreElection(Election modelElection) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT electionName, itemID, startDate, endDate, isPartisan FROM election";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Election>   elections = new ArrayList<Election>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelElection != null ) {
            if( modelElection.getElectionName() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where id = " + modelElection.getElectionName() );
           
        }
        
        try {
		
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    String electionName;
		    String itemID;
			Date startDate;
			Date endDate;
			Boolean isPartisan;
			Election nextElection = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			electionName = rs.getString(1);
			itemID = rs.getString(2);
			startDate = rs.getDate(3);
			endDate = rs.getDate(4);
			isPartisan = rs.getBoolean(5);

			nextElection.setElectionName(electionName);
			nextElection.setItemID(itemID);
			nextElection.setStartDate(startDate);
			nextElection.setEndDate(endDate);
			nextElection.setIsPartisan(isPartisan);

			elections.add(nextElection);

		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
		return elections;
	}

	@Override
	public void storeElection(Election election) throws EVException {
		// TODO Auto-generated method stub
		String               insertMembershipSql = "INSERT INTO election "
				+ "(electionName, itemID, startDate, endDate, isPartisan) "
				+ "VALUES ( ?, ?, ?, ?, ?)";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( election.getElectionName() == null )
            throw new EVException( "PersistenceLayer.save: Attempting to save a election with no name" );
        if( election.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to save an election that isn't persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setString( 1, election.getElectionName() );
            stmt.setLong( 2, election.getId() );
            stmt.setDate( 3, election.getStartDate() );
            stmt.setDate( 4, election.getEndDate() );
            stmt.setBoolean( 5, election.getIsPartisan() );

            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	election.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to save an Election" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to save an Election: " + e );
        }


	}

	@Override
	public void deleteElection(Election election) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM candidate WHERE electionName = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !election.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, election.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }

	}

	@Override
	public List<ElectoralDistrict> restoreElectoralDistrict(ElectoralDistrict modelElectoralDistrict)
			throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT zip, districtName FROM electoralDistrict";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<ElectoralDistrict>   electionDistricts = new ArrayList<ElectoralDistrict>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelElectoralDistrict != null ) {
            if( modelElectoralDistrict.getZip() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where zip = " + modelElectoralDistrict.getZip() );
           
        }
        
        try {
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    String zip;
		    String districtName;
		    ElectoralDistrict nextElectoralDistrict = null;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			zip = rs.getString(1);
			districtName = rs.getString(2);

			nextElectoralDistrict.setZip(zip);
			nextElectoralDistrict.setDistrictName(districtName);

			electionDistricts.add(nextElectoralDistrict);
		    }
		}
        }
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return electionDistricts;
	}

	@Override
	public void storeElectoralDistrict(ElectoralDistrict electoralDistrict) throws EVException {
		// TODO Auto-generated method stub
		String               insertMembershipSql = "INSERT INTO electionDistrict "
				+ "(zip, districtName) "
				+ "VALUES ( ?, ? )";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( electoralDistrict.getName() == null )
            throw new EVException( "PersistenceLayer.save: Attempting to save a electionDistrict with no name" );
        if( electoralDistrict.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to save an electionDistrict that isn't persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setString( 1, electoralDistrict.getZip() );
            stmt.setString( 2, electoralDistrict.getName() );

            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	electoralDistrict.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to save an Electoral District" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to save an Electoral District: " + e );
        }

	}

	@Override
	public void deleteElectoralDistrict(ElectoralDistrict electoralDistrict) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM electoralDistrict WHERE zip = ?";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !electoralDistrict.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, electoralDistrict.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }

	}

	@Override
	public List<Issue> restoreIssue(Issue modelIssue) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT issueID, itemID, questionTitle, description, yesCount, noCount FROM issue";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Issue>   issues = new ArrayList<Issue>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelIssue != null ) {
            if( modelIssue.getIssueID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where issueID = " + modelIssue.getIssueID() );
           
        }
        
        try {
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    String issueID;
		    String itemID;
		    String questionTitle;
		    String description;
		    int yesCount;
		    int noCount;
		    Issue nextIssue = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			issueID = rs.getString(1);
			itemID = rs.getString(2);
			questionTitle = rs.getString(3);
			description = rs.getString(4);
			yesCount = rs.getInt(5);
			noCount = rs.getInt(6);

			nextIssue.setIssueID(issueID);
			nextIssue.setItemID(itemID);
			nextIssue.setQuestion(questionTitle);
			nextIssue.setDescription(description);
			nextIssue.setYesCount(yesCount);
			nextIssue.setNoCount(noCount);

			issues.add(nextIssue);
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
        
		return issues;
	}

	@Override
	public void storeIssue(Issue issue) throws EVException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteIssue(Issue issue) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM issue WHERE issueID = ";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !issue.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, issue.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public List<PoliticalParty> restorePoliticalParty(PoliticalParty modelPoliticalParty) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT partyID, partyName, color FROM politicalParty";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<PoliticalParty>   politicalParties = new ArrayList<PoliticalParty>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelPoliticalParty != null ) {
            if( modelPoliticalParty.getPartyID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where partyID = " + modelPoliticalParty.getPartyID() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String partyID;
		    String partyName;
		    String color;
		    PoliticalParty nextPoliticalParty = null;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			partyID = rs.getString(1);
			partyName = rs.getString(2);
			color = rs.getString(3);

			nextPoliticalParty.setPartyID(partyID);
			nextPoliticalParty.setPartyName(partyName);
			nextPoliticalParty.setColor(color);

			politicalParties.add(nextPoliticalParty);
		    }
	}
            
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
		return politicalParties;
	}

	@Override
	public void storePoliticalParty(PoliticalParty politicalParty) throws EVException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletePoliticalParty(PoliticalParty politicalParty) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM politicalParty WHERE partyID = ";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !politicalParty.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, politicalParty.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
			
	}

	@Override
	public List<VoteRecord> restoreVoteRecord(VoteRecord modelVoteRecord) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT recordID, voterID, ballotID, date FROM voteRecord";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<VoteRecord>   voteRecords = new ArrayList<VoteRecord>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( modelVoteRecord != null ) {
            if( modelVoteRecord.getRecordID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where recordID = " + modelVoteRecord.getRecordID() );
           
        }
        
        try {
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    String recordID;
		    String voterID;
		    String ballotID;
		    Date date;
		    VoteRecord nextVoteRecord = null;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			recordID = rs.getString(1);
			voterID = rs.getString(2);
			ballotID = rs.getString(3);
			date = rs.getDate(4);

			nextVoteRecord.setRecordID(recordID);
			nextVoteRecord.setVoterID(voterID);
			nextVoteRecord.setBallotID(ballotID);
			nextVoteRecord.setDate(date);
			voteRecords.add(nextVoteRecord);
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return voteRecords;
	}

	@Override
	public void storeVoteRecord(VoteRecord voteRecord) throws EVException {
		// TODO Auto-generated method stub

		String               insertMembershipSql = "INSERT INTO voteRecord "
				+ "(recordID, voterName, ballotID, date) "
				+ "VALUES ( ?, ?, ?, ?)";
        PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( voteRecord.getRecordID() == null )
            throw new EVException( "PersistenceLayer.save: Attempting to save a vote Record with no ID" );
        if( voteRecord.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to save a vote Record that isn't persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( insertMembershipSql );
            
            stmt.setString( 1, voteRecord.getRecordID() );
            stmt.setString( 2, voteRecord.getVoter().getFirstName() +
            		" " + voteRecord.getVoter().getLastName() );
            stmt.setLong( 3, voteRecord.getBallot().getId() );
            stmt.setDate( 4, (Date) voteRecord.getDate() );


            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	voteRecord.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to save a vote Record" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to save a vote Record: " + e );
        }


	}

	@Override
	public void deleteVoteRecord(VoteRecord voteRecord) throws EVException {
		// TODO Auto-generated method stub
		String               deleteClubSql = "DELETE FROM voteRecord WHERE recordID = ";              
        PreparedStatement    stmt = null;
        int                  inscnt;
             
        if( !voteRecord.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteClubSql );         
            stmt.setLong( 1, voteRecord.getId() );
            inscnt = stmt.executeUpdate();          
            if( inscnt == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public void storeBallotIncludesBallotItem(Ballot ballot, BallotItem ballotItem) throws EVException {
		// TODO Auto-generated method stub
		storeBallot(ballot);
		for(int i=0; i<ballot.getBallotItems().size(); i++)
		{
			storeBallotItem(ballot.getBallotItems().get(i));
		}
	}

	@Override
	public Ballot restoreBallotIncludesBallotItem(BallotItem ballotItem) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT ballotID, zip, EOName, bName, approved FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
    	Ballot nextBallot = null;

        condition.setLength( 0 );
         query.append( sqlQuery );
        if( ballotItem != null ) {
            if( ballotItem.getBallotID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where  ballotID = " + ballotItem.getBallotID() );
           
        }
        
        try {
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    long ballotID;
			String zip;
			String EOName;
			String name;
			Date openDate;
			Date closeDate;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			ballotID = rs.getLong( 1 );
			zip = rs.getString( 2 );
			EOName = rs.getString( 3 );
			name = rs.getString( 4 );
			openDate = rs.getDate( 5 );
			closeDate = rs.getDate( 6 );

			nextBallot = objectLayer.createBallot();
			nextBallot.setBallotID(ballotID);
			nextBallot.setZip(zip);
			nextBallot.setEOName(EOName);
			nextBallot.setName(name);
			nextBallot.setOpenDate(openDate);
			nextBallot.setCloseDate(closeDate);

		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }

	return nextBallot;
		
		return null;
	}

	@Override
	public List<BallotItem> restoreBallotIncludesBallotItem(Ballot ballot) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT itemID, ballotID, voteCount FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
    	List<BallotItem> ballotItems = new ArrayList<BallotItem>();

        condition.setLength( 0 );
         query.append( sqlQuery );
        if( ballot != null ) {
            if( ballot.getBallotID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where  ballotID = " + ballot.getBallotID() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String itemID;
		    String ballotID;
		    int voteCount;
		    BallotItem nextBallotItem = null;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			itemID = rs.getString(1);
			ballotID = rs.getString(2);
			voteCount = rs.getInt(3);

			nextBallotItem.setItemID(itemID);
			nextBallotItem.setBallotID(ballotID);
			nextBallotItem.setVoteCount(voteCount);
			ballotItems.add(nextBallotItem);
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
		return ballotItems;
	}

	@Override
	public void deleteBallotIncludesBallotItem(Ballot ballot, BallotItem ballotItem) throws EVException {
		// TODO Auto-generated method stub
		String               deleteBallotItemSql = "DELETE FROM ballotItem WHERE ballotItemID = ?";
	    String               deleteBallotSql = "DELETE FROM ballot WHERE ballotID = ?";
        PreparedStatement    stmt = null;
        PreparedStatement    stmt2 = null;
        int                  inscnt, inscnt2;
             
        if( !ballot.isPersistent() || !ballotItem.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteBallotItemSql );
            stmt2 = (PreparedStatement) conn.prepareStatement( deleteBallotSql );
            stmt.setLong( 1, ballotItem.getId() );
            stmt2.setLong( 1, ballot.getId() );
            inscnt = stmt.executeUpdate();
            inscnt2 = stmt2.executeUpdate();
            if( inscnt == 1 && inscnt2 == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public void storeCandidateIsCandidateInElection(Candidate candidate, Election election) throws EVException {
		String               updateCandidateSql = "UPDATE candidate "
				+ "SET electionName = ? "
				+ "WHERE choiceID = ?";
		
		PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( candidate.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to update a candidate that isn't persistent" );
        
        if( election.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to update an election that isn't persistent" );
                              
        try {
            stmt = (PreparedStatement) conn.prepareStatement( updateCandidateSql );
            
            stmt.setString( 1, election.getElectionName() );
            stmt.setString( 2, candidate.getChoiceID() );
            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	voteRecord.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to update a candidate" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to update a candidate: " + e );
        }

	}


	@Override
	public Election restoreCandidateIsCandidateInElection(Candidate candidate) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT electionName, itemID, startDate, endDate, isPartisan FROM election";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
    	Election nextElection = null;
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( candidate != null ) {
            if( candidate.getElectionName() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where electionName = " + candidate.getElectionName() );
           
        }
        
        try {
            stmt = conn.createStatement();
	if( stmt.execute( query.toString() ) ) {
		    String electionName;
		    String itemID;
			Date startDate;
			Date endDate;
			Boolean isPartisan;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			electionName = rs.getString(1);
			itemID = rs.getString(2);
			startDate = rs.getDate(3);
			endDate = rs.getDate(4);
			isPartisan = rs.getBoolean(5);

			nextElection.setElectionName(electionName);
			nextElection.setItemID(itemID);
			nextElection.setStartDate(startDate);
			nextElection.setEndDate(endDate);
			nextElection.setIsPartisan(isPartisan);


		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
		return nextElection;
	}
		

	@Override
	public List<Candidate> restoreCandidateIsCandidateInElection(Election election) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT choiceID, electionName, partyID, title, voteCount, description FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Candidate>   candidates = new ArrayList<Candidate>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( election != null ) {
            if( election.getElectionName() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where electionName = " + election.getElectionName() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String choiceID;
			String electionName;
			String partyID;
			String title;
			int voteCount;
			String description;
			Candidate nextCandidate = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			choiceID = rs.getString(1);
			electionName = rs.getString(2);
			partyID = rs.getString(3);
			title = rs.getString(4);
			voteCount = rs.getInt(5);
			description = rs.getString(6);

			nextCandidate = objectLayer.createCandidate();
			nextCandidate.setChoiceID(choiceID);
			nextCandidate.setElectionName(electionName);
			nextCandidate.setpartyID(partyID);
			nextCandidate.setTitle(title);
			nextCandidate.setVoteCount(voteCount);
			nextCandidate.setDescription(description);

			candidates.add(nextCandidate);
		    }
		}	
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return candidates;
	}

	@Override
	public void deleteCandidateIsCandidateInElection(Candidate candidate, Election election) throws EVException {
		// TODO Auto-generated method stub
		String               deleteCandidateSql = "DELETE FROM candidate WHERE choiceID = ?";
		String               deleteElectionSql = "DELETE FROM election WHERE electionNameID = ?";
        PreparedStatement    stmt = null;
        PreparedStatement    stmt2 = null;
        int                  inscnt, inscnt2;
             
        if( !candidate.isPersistent() || !election.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteCandidateSql );
            stmt2 = (PreparedStatement) conn.prepareStatement( deleteElectionSql );
            stmt.setLong( 1, candidate.getId() );
            stmt2.setLong( 1, election.getId() );
            inscnt = stmt.executeUpdate();
            inscnt2 = stmt2.executeUpdate();
            if( inscnt == 1 && inscnt2 == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public void storeElectoralDistrictHasBallotBallot(ElectoralDistrict electoralDistrict, Ballot ballot)
			throws EVException {
		// TODO Auto-generated method stub
		storeElectoralDistrict(electoralDistrict);
		storeBallot(ballot);
	}

	@Override
	public ElectoralDistrict restoreElectoralDistrictHasBallotBallot(Ballot ballot) throws EVException {
		// TODO Auto-generated method stub
		String       selectClubSql = "SELECT zip, districtName FROM electoralDistrict";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        ElectoralDistrict nextElectoralDistrict = null;
	
        condition.setLength( 0 );
         query.append( selectPersonSql );
        if( ballot != null ) {
            if( ballot.getZip() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where zip = " + ballot.getZip() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String zip;
		    String districtName;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			zip = rs.getString(1);
			districtName = rs.getString(2);

			nextElectoralDistrict.setZip(zip);
			nextElectoralDistrict.setDistrictName(districtName);

		    }
		} 
        }
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return nextElectoralDistrict;
	}

	@Override
	public List<Ballot> restoreElectoralDistrictHasBallotBallot(ElectoralDistrict electoralDistrict)
			throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT ballotID, zip, EOName, bName, approved FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Ballot>   ballots = new ArrayList<Ballot>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( electoralDistrict != null ) {
            if( electoralDistrict.getZip() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where zip = " + electoralDistrict.getZip() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    long ballotID;
			String zip;
			String EOName;
			String name;
			Date openDate;
			Date closeDate;
			Ballot nextBallot = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			ballotID = rs.getLong( 1 );
			zip = rs.getString( 2 );
			EOName = rs.getString( 3 );
			name = rs.getString( 4 );
			openDate = rs.getDate( 5 );
			closeDate = rs.getDate( 6 );

			nextBallot = objectLayer.createBallot();
			nextBallot.setBallotID(ballotID);
			nextBallot.setZip(zip);
			nextBallot.setEOName(EOName);
			nextBallot.setName(name);
			nextBallot.setOpenDate(openDate);
			nextBallot.setCloseDate(closeDate);

			ballots.add( nextBallot );
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }

	return ballots;
	}

	@Override
	public void deleteElectoralDistrictHasBallotBallot(ElectoralDistrict electoralDistrict, Ballot ballot)
			throws EVException {
		// TODO Auto-generated method stub
		String               deleteBallotSql = "DELETE FROM ballot WHERE ballotID = ?";
		String               deleteElectoralDistrictSql = "DELETE FROM electoralDistrict WHERE zip = ?";
        PreparedStatement    stmt = null;
        PreparedStatement    stmt2 = null;
        int                  inscnt, inscnt2;
             
        if( !ballot.isPersistent() || !electoralDistrict.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteBallotSql );
            stmt2 = (PreparedStatement) conn.prepareStatement( deleteElectoralDistrictSql );
            stmt.setLong( 1, ballot.getId() );
            stmt2.setLong( 1, electoralDistrict.getId() );
            inscnt = stmt.executeUpdate();
            inscnt2 = stmt2.executeUpdate();
            if( inscnt == 1 && inscnt2 == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public void storeCandidateIsMemberOfPoliticalParty(Candidate candidate, PoliticalParty politicalParty)
			throws EVException {
		// TODO Auto-generated method stub
		storeCandidate(candidate);
		storePoliticalParty(politicalParty);
	}

	@Override
	public PoliticalParty restoreCandidateIsMemberOfPoliticalParty(Candidate candidate) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT partyID, partyName, color FROM politicalParty";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        PoliticalParty nextPoliticalParty = null;
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( candidate != null ) {
            if( candidate.getPartyID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where partyID = " + candidate.getPartyID() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String partyID;
		    String partyName;
		    String color;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			partyID = rs.getString(1);
			partyName = rs.getString(2);
			color = rs.getString(3);

			nextPoliticalParty.setPartyID(partyID);
			nextPoliticalParty.setPartyName(partyName);
			nextPoliticalParty.setColor(color);

		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
		return nextPoliticalParty;
	}

	@Override
	public List<Candidate> restoreCandidateIsMemberOfPoliticalParty(PoliticalParty politicalParty) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT choiceID, electionName, partyID, title, voteCount, description FROM ballot";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Candidate>   candidates = new ArrayList<Candidate>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( politicalParty != null ) {
            if( politicalParty.getPartyID() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where partyID = " + politicalParty.getPartyID() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String choiceID;
			String electionName;
			String partyID;
			String title;
			int voteCount;
			String description;
			Candidate nextCandidate = null;

			ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			choiceID = rs.getString(1);
			electionName = rs.getString(2);
			partyID = rs.getString(3);
			title = rs.getString(4);
			voteCount = rs.getInt(5);
			description = rs.getString(6);

			nextCandidate = objectLayer.createCandidate();
			nextCandidate.setChoiceID(choiceID);
			nextCandidate.setElectionName(electionName);
			nextCandidate.setpartyID(partyID);
			nextCandidate.setTitle(title);
			nextCandidate.setVoteCount(voteCount);
			nextCandidate.setDescription(description);

			candidates.add(nextCandidate);
		    }
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return candidates;
	}

	@Override
	public void deleteCandidateIsMemberOfElection(Candidate candidate, PoliticalParty politicalParty)
			throws EVException {
		// TODO Auto-generated method stub
		String               deleteCandidateSql = "DELETE FROM candidate WHERE choiceID = ?";
		String               deletePoliticalPartySql = "DELETE FROM politicalParty WHERE partyID = ?";
        PreparedStatement    stmt = null;
        PreparedStatement    stmt2 = null;
        int                  inscnt, inscnt2;
             
        if( !candidate.isPersistent() || !politicalParty.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteCandidateSql );
            stmt2 = (PreparedStatement) conn.prepareStatement( deletePoliticalPartySql );
            stmt.setLong( 1, candidate.getId() );
            stmt2.setLong( 1, politicalParty.getId() );
            inscnt = stmt.executeUpdate();
            inscnt2 = stmt2.executeUpdate();
            if( inscnt == 1 && inscnt2 == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}

	@Override
	public void storeVoterBelongsToElectoralDistrict(Voter voter, ElectoralDistrict electoralDistrict)
			throws EVException {
		// TODO Auto-generated method stub
		String               updateCandidateSql = "UPDATE voter "
				+ "SET zip = ? "
				+ "WHERE userID = ?";
		
		PreparedStatement    stmt = null;
        int                  inscnt;
        long                 membershipId;
        
        if( voter.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to update a voter that isn't persistent" );

        if( electoralDistrict.isPersistent())
            throw new EVException( "PersistenceLayer.save: Attempting to update a voter that isn't persistent" );

        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( updateCandidateSql );
            
            stmt.setString( 1, electoralDistrict.getZip() );
            stmt.setString( 2, voter.getUserID() );
            
            inscnt = stmt.executeUpdate();
            
            if( inscnt >= 1 ) {
                String sql = "select last_insert_id()";
                if( stmt.execute( sql ) ) { // statement returned a result

                    // retrieve the result
                    ResultSet r = stmt.getResultSet();

                    // we will use only the first row!
                    //
                    while( r.next() ) {

                        // retrieve the last insert auto_increment value
                        membershipId = r.getLong( 1 );
                        if( membershipId > 0 )
                        	voteRecord.setId( membershipId ); // set this membership's db id (proxy object)
                    }
                }
            }
            else
                throw new EVException( "PersistenceLayer.save: failed to update a voter" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "PersistenceLayer.save: failed to update a voter: " + e );
        }

	}


	@Override
	public ElectoralDistrict restoreVoterBelongsToElectoralDistrict(Voter voter) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT zip, districtName FROM electoralDistrict";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        ElectoralDistrict nextElectoralDistrict = null;
        
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( voter != null ) {
            if( voter.getZip() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where zip = " + voter.getZip() );
           
        }
        
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String zip;
		    String districtName;

		    ResultSet rs = stmt.getResultSet();

		    // retrieve the retrieved clubs
		    while( rs.next() ) {
			zip = rs.getString(1);
			districtName = rs.getString(2);

			nextElectoralDistrict.setZip(zip);
			nextElectoralDistrict.setDistrictName(districtName);

		    }
		}
        }
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		return nextElectionDistrict;
	}

	@Override
	public List<Voter> restoreVoterBelongsToElectoralDistrict(ElectoralDistrict electoralDistrict) throws EVException {
		// TODO Auto-generated method stub
		String       sqlQuery = "SELECT voterName, age, zip, userID FROM voter";
        Statement    stmt = null;
        StringBuffer query = new StringBuffer( 100 );
        StringBuffer condition = new StringBuffer( 100 );
        List<Voter>   voters = new ArrayList<Voter>();
	
        condition.setLength( 0 );
         query.append( sqlQuery );
        if( electoralDistrict != null ) {
            if( electoralDistrict.getZip() != null ) // id is unique, so it is sufficient to get a person
                query.append( " where zip = " + electoralDistrict.getZip() );
           
        }
        try {
            stmt = conn.createStatement();
		if( stmt.execute( query.toString() ) ) {
		    String voterName;
		    int age;
		    String zip;
		    String userID;
		    Voter nextVoter = null;
			ResultSet rs = stmt.getResultSet();

		    while( rs.next() ) {
			voterName = rs.getString( 1 );
			age = rs.getInt( 2 );
			zip = rs.getString( 3 );
			userID = rs.getString( 4 );

			nextVoter = objectLayer.createVoter();
			nextVoter.setVoterName(voterName);
			nextVoter.setAge(age);
			nextVoter.setZip(zip);
			nextVoter.setUserID(userID);

			voters.add(nextVoter);
		    }
            
		}
        }
        
        catch( Exception e ) {      // just in case...
            throw new EVException( "ClubManager.restore: Could not restore persistent Club objects; Root cause: " + e );
        }
        
		
		return voters;
	}

	@Override
	public void deleteVoterBelongsToElection(Voter voter, ElectoralDistrict electoralDistrict) throws EVException {
		// TODO Auto-generated method stub
		String               deleteVoterSql = "DELETE FROM voter WHERE voterName = ?";
		String               deleteElectoralDistrictSql = "DELETE FROM electoralDistrict WHERE zip = ?";
        PreparedStatement    stmt = null;
        PreparedStatement    stmt2 = null;
        int                  inscnt, inscnt2;
             
        if( !voter.isPersistent() || !electoralDistrict.isPersistent() ) // is the Club object persistent?  If not, nothing to actually delete
            return;
        
        try {
            stmt = (PreparedStatement) conn.prepareStatement( deleteVoterSql );
            stmt2 = (PreparedStatement) conn.prepareStatement( deleteElectoralDistrictSql );
            stmt.setLong( 1, voter.getId() );
            stmt2.setLong( 1, electoralDistrict.getId() );
            inscnt = stmt.executeUpdate();
            inscnt2 = stmt2.executeUpdate();
            if( inscnt == 1 && inscnt2 == 1 ) {
                return;
            }
            else
                throw new EVException( "ClubManager.delete: failed to delete a Club" );
        }
        catch( SQLException e ) {
            e.printStackTrace();
            throw new EVException( "ClubManager.delete: failed to delete a Club: " + e );        }
	}
	}

}
