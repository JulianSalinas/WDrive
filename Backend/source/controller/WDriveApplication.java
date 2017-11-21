package controller;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class WDriveApplication extends Application{

    @Override
    public Set<Class<?>> getClasses() {
        HashSet hashSet = new HashSet<Class<?>>();
        hashSet.add( HelloWorld.class );
        return hashSet;
    }

}