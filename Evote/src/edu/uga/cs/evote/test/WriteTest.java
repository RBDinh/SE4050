package edu.uga.cs.evote.test;

import java.sql.Connection;

import edu.uga.cs.evote.persistence.impl.DbUtils;
import edu.uga.cs.evote.object.impl.ObjectLayerImpl;
import edu.uga.cs.evote.persistence.impl.PersistenceLayerImpl;
import edu.uga.cs.evote.EVException;
import edu.uga.cs.evote.entity.impl.ElectionsOfficerImpl;
import edu.uga.cs.evote.object.ObjectLayer;
import edu.uga.cs.evote.persistence.PersistenceLayer;


public class WriteTest {

	public static void main(String[] args) {
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
        //objectLayer.setPersistence( persistence );
        
        ElectionsOfficerImpl joe = new ElectionsOfficerImpl("Randy", "Dinh", "druidDude", "pass", "abc@xyz.com", "678 Fart St.");
        
        try{
        	objectLayer.storeElectionsOfficer(joe);
        }
        
        catch( Exception e ) {
            e.printStackTrace();
        }

	}

}
