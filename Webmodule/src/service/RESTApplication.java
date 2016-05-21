package service;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lyn on 16-5-21.
 */
@ApplicationPath("/InfoServiceREST")
public class RESTApplication extends Application{
    private Set<Class<?>> classes=new HashSet<>();
    public RESTApplication()
    {
        classes.add(InfoServiceREST.class);
    }

    @Override
    public Set<Class<?>> getClasses()
    {
        return classes;
    }

}
