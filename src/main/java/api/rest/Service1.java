package api.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

@Path("/service1")
@Singleton
public class Service1 extends RestBase {

    //request http://localhost:2223/rest/service1/method1?p1=gokhan&p2=ercan
    @GET
    @Path("method1")
    public String Method1(@QueryParam("p1")String p1, @QueryParam("p2")String p2)
    {
        return p1 + " " + p2;
    }

    @GET
    @Path("method2")
    public String Method2() {
        return "service2.method2 result";
    }

}

