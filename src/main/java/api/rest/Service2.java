package api.rest;

import javax.inject.Singleton;
import javax.ws.rs.Path;

@Path("/service2")
@Singleton
public class Service2 extends RestBase {

    @Path("method1")
    public String Method1() {
        return "service2.method1 result";
    }

    @Path("method2")
    public String Method2() {
        return "service2.method2 result";
    }

}

