package edu.uga.cs.evote.test;

import java.sql.Connection;

import edu.uga.cs.evote.persistence.impl.DbUtils;
import edu.uga.cs.evote.object.impl.ObjectLayerImpl;
import edu.uga.cs.evote.persistence.impl.PersistenceLayerImpl;
import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.Ballot;
import edu.uga.cs.evote.entity.BallotItem;
import edu.uga.cs.evote.entity.Candidate;
import edu.uga.cs.evote.entity.Election;
import edu.uga.cs.evote.entity.ElectionsOfficer;
import edu.uga.cs.evote.entity.ElectoralDistrict;
import edu.uga.cs.evote.entity.Issue;
import edu.uga.cs.evote.entity.PoliticalParty;
import edu.uga.cs.evote.entity.Voter;
import edu.uga.cs.evote.entity.impl.ElectionsOfficerImpl;
import edu.uga.cs.evote.object.ObjectLayer;
import edu.uga.cs.evote.persistence.PersistenceLayer;


public class WriteTest {

	public static void main(String[] args) throws EVException {
		Connection conn = null;
        ObjectLayer objectLayer = null;
        PersistenceLayer persistence = null;
        
        // get a database connection
        try {
            conn = DbUtils.connect();
        } 
        catch (Exception seq) {
            System.err.println( "WriteTest: Unable to obtain a database connection" );
        }
        
        if( conn == null ) {
            System.out.println( "WriteTest: failed to connect to the database" );
            return;
        }
        
        // obtain a reference to the ObjectModel module      
        objectLayer = new ObjectLayerImpl();
        // obtain a reference to Persistence module and connect it to the ObjectModel        
        persistence = new PersistenceLayerImpl( conn, objectLayer ); 
        // connect the ObjectModel module to the Persistence module
        objectLayer.setPersistence( persistence );
        
        ElectionsOfficer joe =  objectLayer.createElectionsOfficer("druidDude", "abc@xyz.com");
        ElectionsOfficer bob =  objectLayer.createElectionsOfficer("druidDude2", "efg@xyz.com");
        ElectoralDistrict place = objectLayer.createElectoralDistrict("30606", "superAthens");
        Voter dee = objectLayer.createVoter("dee dee", 56, "30606", "u001");
        Voter dumb = objectLayer.createVoter("dumb dumb", 65, "30606", "u002");
        PoliticalParty right = objectLayer.createPoliticalParty("pp01", "right", "blue");
        PoliticalParty wrong = objectLayer.createPoliticalParty("pp02", "wrong", "orange");
        Ballot b1 = objectLayer.createBallot("b01", "30606", "druidDude", "BUY GIL", "yes");
        Ballot b2 = objectLayer.createBallot("b02", "30606", "druidDude2", "BUY PILLS", "yes");
        BallotItem bi1 = objectLayer.createBallotItem("i01","b01", 2);
        BallotItem bi2 = objectLayer.createBallotItem("i02","b01", 2);
        BallotItem bi3 = objectLayer.createBallotItem("i03","b01", 2);
        BallotItem bi4 = objectLayer.createBallotItem("i04","b01", 2);
        BallotItem bi5 = objectLayer.createBallotItem("i05","b01", 2);
        BallotItem bi6 = objectLayer.createBallotItem("i06","b01", 2);
        BallotItem bi7 = objectLayer.createBallotItem("i07","b02", 2);
        BallotItem bi8 = objectLayer.createBallotItem("i08","b02", 2);
        BallotItem bi9 = objectLayer.createBallotItem("i09","b02", 2);
        BallotItem bi10 = objectLayer.createBallotItem("i10","b02", 2);
        BallotItem bi11 = objectLayer.createBallotItem("i11","b02", 2);
        BallotItem bi12 = objectLayer.createBallotItem("i12","b02", 2);
        Issue i1 = objectLayer.createIssue("issue001", "i01", "Do You Like Pie?", "ANSWER THE QUESTION!", 1, 1);
        Issue i2 = objectLayer.createIssue("issue002", "i02", "Do you like cake?", "ANSWER THE QUESTION!", 1, 1);
        Issue i3 = objectLayer.createIssue("issue003", "i03", "Do you like cheese?", "ANSWER THE QUESTION!", 1, 1);
        Issue i4 = objectLayer.createIssue("issue004", "i07", "Do you like steak?", "ANSWER THE QUESTION!", 1, 1);
        Issue i5 = objectLayer.createIssue("issue005", "i08", "Do you like cake?", "ANSWER THE QUESTION!", 1, 1);
        Issue i6 = objectLayer.createIssue("issue006", "i09", "Do you like chips?", "ANSWER THE QUESTION!", 1, 1);
        Election e1 = objectLayer.createElection("Next Prez", "i04", (long)20160930, (long)20161030, "yes");
        Election e2 = objectLayer.createElection("#1 Fan", "i05", (long)20170930, (long)20171030, "no");
        Election e3 = objectLayer.createElection("Most Evil", "i06", (long)20180930, (long)20181030, "no");
        Election e4 = objectLayer.createElection("Poorest", "i10", (long)20160930, (long)20161030, "yes");
        Election e5 = objectLayer.createElection("Richest", "i11", (long)20170930, (long)20171030, "no");
        Election e6 = objectLayer.createElection("Smartest", "i12", (long)20180930, (long)20181030, "no");
        Candidate c1 = objectLayer.createCandidate("can01", "Next Prez", "D1", "Dude1", 1, "SomeGuy");
        Candidate c2 = objectLayer.createCandidate("can02", "Next Prez", "D1", "Dude2", 1, "SomeGuy");
        Candidate c3 = objectLayer.createCandidate("can03", "Next Prez", "D1", "Dude3", 0, "SomeGuy");
        Candidate c4 = objectLayer.createCandidate("can04", "#1 Fan", "NA", "Dude4", 1, "SomeGuy");
        Candidate c5 = objectLayer.createCandidate("can05", "#1 Fan", "NA", "Dude5", 1, "SomeGuy");
        Candidate c6 = objectLayer.createCandidate("can06", "#1 Fan", "NA", "Dude6", 0, "SomeGuy");
        Candidate c7 = objectLayer.createCandidate("can07", "Most Evil", "NA", "Dude7", 1, "SomeGuy");
        Candidate c8 = objectLayer.createCandidate("can08", "Most Evil", "NA", "Dude8", 1, "SomeGuy");
        Candidate c9 = objectLayer.createCandidate("can09", "Most Evil", "NA", "Dude9", 0, "SomeGuy");
        Candidate c10 = objectLayer.createCandidate("can10", "Poorest", "R1", "Dude10", 1, "SomeGuy");
        Candidate c11 = objectLayer.createCandidate("can11", "Poorest", "R1", "Dude11", 1, "SomeGuy");
        Candidate c12 = objectLayer.createCandidate("can12", "Poorest", "R1", "Dude12", 0, "SomeGuy");
        Candidate c13 = objectLayer.createCandidate("can13", "Richest", "NA", "Dude13", 1, "SomeGuy");
        Candidate c14 = objectLayer.createCandidate("can14", "Richest", "NA", "Dude14", 1, "SomeGuy");
        Candidate c15= objectLayer.createCandidate("can15", "Richest", "NA", "Dude15", 0, "SomeGuy");
        Candidate c16 = objectLayer.createCandidate("can16", "Smartest", "NA", "Dude16", 1, "SomeGuy");
        Candidate c17 = objectLayer.createCandidate("can17", "Smartest", "NA", "Dude17", 1, "SomeGuy");
        Candidate c18 = objectLayer.createCandidate("can18", "Smartest", "NA", "Dude18", 0, "SomeGuy");

        System.out.println(joe);
        if(joe == null){
        	System.out.println("Joe is Null");
        }
        try{
        	objectLayer.storeElectionsOfficer(joe);
        	objectLayer.storeElectionsOfficer(bob);
        	objectLayer.storeElectoralDistrict(place);
        	objectLayer.storeVoter(dee);
        	objectLayer.storeVoter(dumb);
        	objectLayer.storePoliticalParty(right);
        	objectLayer.storePoliticalParty(wrong);
        	objectLayer.storeBallot(b1);
        	objectLayer.storeBallot(b2);
        	objectLayer.storeBallotItem(bi1);
        	objectLayer.storeBallotItem(bi2);
        	objectLayer.storeBallotItem(bi3);
        	objectLayer.storeBallotItem(bi4);
        	objectLayer.storeBallotItem(bi5);
        	objectLayer.storeBallotItem(bi6);
        	objectLayer.storeBallotItem(bi7);
        	objectLayer.storeBallotItem(bi8);
        	objectLayer.storeBallotItem(bi9);
        	objectLayer.storeBallotItem(bi10);
        	objectLayer.storeBallotItem(bi11);
        	objectLayer.storeBallotItem(bi12);
        	objectLayer.storeIssue(i1);
        	objectLayer.storeIssue(i2);
        	objectLayer.storeIssue(i3);
        	objectLayer.storeIssue(i4);
        	objectLayer.storeIssue(i5);
        	objectLayer.storeIssue(i6);
        	objectLayer.storeElection(e1);
        	objectLayer.storeElection(e2);
        	objectLayer.storeElection(e3);
        	objectLayer.storeElection(e4);
        	objectLayer.storeElection(e5);
        	objectLayer.storeElection(e6);
        	objectLayer.storeCandidate(c1);
        	objectLayer.storeCandidate(c2);
        	objectLayer.storeCandidate(c3);
        	objectLayer.storeCandidate(c4);
        	objectLayer.storeCandidate(c5);
        	objectLayer.storeCandidate(c6);
        	objectLayer.storeCandidate(c7);
        	objectLayer.storeCandidate(c8);
        	objectLayer.storeCandidate(c9);
        	objectLayer.storeCandidate(c10);
        	objectLayer.storeCandidate(c11);
        	objectLayer.storeCandidate(c12);
        	objectLayer.storeCandidate(c13);
        	objectLayer.storeCandidate(c14);
        	objectLayer.storeCandidate(c15);
        	objectLayer.storeCandidate(c16);
        	objectLayer.storeCandidate(c17);
        	objectLayer.storeCandidate(c18);
        }
        
        catch( Exception e ) {
            e.printStackTrace();
        }
        
        System.out.println("All inputs added!");

	}

}