package service;


import action.InfoAction;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by lyn on 16-5-21.
 */
@Path("/")
public class InfoServiceREST {
    @EJB(name="InfoAction")
    private InfoAction infoAction;

    @GET
    @Path("/showData/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    public String showData(@PathParam("username") String username)
    {
        String out=infoAction.showInfo(username);
        return out;
    }


}
