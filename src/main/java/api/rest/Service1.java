package api.rest;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/service1")
@Singleton
public class Service1 extends RestBase {

    //see on http://localhost:2223/rest/service1/method1
    @GET
    @Path("method1")
    public String Method1() {
        return "service1.method1 result";
    }

    @GET
    @Path("method2")
    public String Method2() {
        return "service2.method2 result";
    }

}

