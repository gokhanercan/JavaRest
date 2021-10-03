package api.host;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.FilterMapping;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.joda.time.DateTime;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/*
Jetty Host Service for API
- Hala exception detaylarını alamıyoruz. Jersey App'in provider'larına erişim CustomExceptionMapper ile 500 hatalarını saklamasına engel olmamız gerekiyor. Jetty'ye hata detayı gelmiyor.
*/
public class ApiHost {

    public static void main(String[] args) throws Exception {

        //Built Context
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        SetCrossOriginAccess(context);

        //Configure Servlets
        ServletHolder servlet1 = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/rest/*");
        servlet1.setInitParameter("jersey.config.server.provider.packages","api.rest");

        //Run server
        Server jettyServer = new Server(2223);
        jettyServer.setHandler(context);

        try {
            Log("Starting Jetty Server...");      //for detailed embedded configuration options: http://www.eclipse.org/jetty/documentation/9.3.x/embedding-jetty.html
            jettyServer.start();
            Log("Jetty started. Browse http://localhost:2223/rest/service1/test");
            //jettyServer.dumpStdErr();
            jettyServer.join();
        }
        catch (Exception ex){
            Log("Jetty encounter an error: " + ex);
        }
        finally {
            jettyServer.destroy();
            Log("Jetty has been destroyed.");
        }
    }

    protected static void Log(String string){
        String datestr = DateTime.now().toString("HH:mm:ss");
        System.out.println(datestr + " - " +  string);
    }

    private static void SetCrossOriginAccess(ServletContextHandler context){
        FilterHolder holder = new FilterHolder(CrossOriginFilter.class);
        holder.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        holder.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        holder.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,HEAD");
        holder.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        holder.setName("cross-origin");
        FilterMapping fm = new FilterMapping();
        fm.setFilterName("cross-origin");
        fm.setPathSpec("*");
        context.addFilter(holder,fm.getPathSpecs()[0], EnumSet.of(DispatcherType.REQUEST));
    }

}
