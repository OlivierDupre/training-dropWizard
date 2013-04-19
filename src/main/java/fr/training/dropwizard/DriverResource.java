package fr.training.dropwizard;

import fr.training.dropwizard.model.Driver;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author shuttle
 */
//@Produces(MediaType.APPLICATION_JSON)
public class DriverResource {

    String vehicleId;

    public DriverResource(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    @GET
    public List<Driver> allDrivers() {
        List<Driver> drivers = new ArrayList<>();

        if (vehicleId.startsWith("C")) {
            drivers.add(new Driver("Fanny", "Dudu", 30));
            drivers.add(new Driver("Olivier", "Dudu", 29));
        } else {
            drivers.add(new Driver("JL", "Dudu", 33));
            drivers.add(new Driver("Denis", "Dudu", 32));
        }

        return drivers;
    }

    @GET
    @Path("{driverFirstName}")
    public Driver retrieveDriver(@PathParam("driverFirstName") String firstName) {
        return new Driver(firstName, firstName, 80);
    }
}
