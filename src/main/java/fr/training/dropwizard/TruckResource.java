package fr.training.dropwizard;

import fr.training.dropwizard.model.Driver;
import fr.training.dropwizard.model.Truck;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author shuttle
 */
@Path("/trucks") // Par principe/convention, on met au pluriel
@Produces(MediaType.APPLICATION_JSON) // Si on ne met rien, le serveur s'adapte en fonction de ce qui est présent dans le requête HTTP
public class TruckResource {

    @GET
    public List<Truck> allTrucks() {
        List<Truck> trucks = new ArrayList<>();

        trucks.add(new Truck("Volvo", 10, 100));
        trucks.add(new Truck("Mercedes", 20, 200));
        trucks.add(new Truck("Renault", 5, 50));

        return trucks;
    }

    @GET
    @Path("{id}")
    public Truck findTruck(@PathParam("id") String truckId,
            @HeaderParam("Accept-Language") String acceptLanguage) {
        for (Truck truck : allTrucks()) {
            if (truck.getId().equals(truckId)) {
                truck.setDriver(new Driver("Mister", "Dudu", 45));
                return truck;
            }
        }

        return null;
    }
    
    @Path("{vehicleId}/drivers")
    public DriverResource manageDrivers(@PathParam("vehicleId") String id){
        return new DriverResource(id);
    }
}
