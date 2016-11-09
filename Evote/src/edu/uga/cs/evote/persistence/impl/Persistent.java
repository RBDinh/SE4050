package edu.uga.cs.evote.persistence.impl;

import edu.uga.cs.evote.persistence.Persistable;
import edu.uga.cs.evote.persistence.PersistenceLayer;


public abstract class Persistent 
    implements Persistable
{
    private long id;
    private static PersistenceLayer persistencaLayer;
    
    public Persistent()
    {
        this.id = -1;
    }
    
    public Persistent( long id )
    {
        this.id = id;
    }
    
    public long getId()
    {
        return id;
    }

    public void setId( long id )
    {
        this.id = id;
    }

    public boolean isPersistent()
    {
        return id >= 0;
    }

    public static PersistenceLayer getPersistencaLayer()
    {
        return persistencaLayer;
    }

    public static void setPersistencaLayer(PersistenceLayer pLayer)
    {
        persistencaLayer = pLayer;
    }
}
