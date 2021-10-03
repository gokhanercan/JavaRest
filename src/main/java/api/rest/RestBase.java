package api.rest;

import org.joda.time.DateTime;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

public class RestBase {

    @GET
    @Path("test")
    public String test() throws IOException {
        //Log("Test ping.");
        return "working";
    }

    public void Log(String message) {

        String datestr = DateTime.now().toString("HH:mm:ss");
        String finalMsg = datestr + " - " +  message;
        System.out.println(finalMsg);

    }

}
