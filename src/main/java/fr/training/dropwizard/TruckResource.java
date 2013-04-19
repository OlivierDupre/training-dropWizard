package fr.training.dropwizard;

import fr.training.dropwizard.model.Driver;
import fr.training.dropwizard.model.Truck;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author shuttle
 */
@Path("/trucks") // Par principe/convention, on met au pluriel
@Consumes(MediaType.APPLICATION_JSON)
public class TruckResource {

    /**
     * http://localhost:8080/trucks?page=8
     * 
     * @param myPage
     * @return 
     */
    @GET
    public List<Truck> allTrucks(@QueryParam("page") int myPage) {
        List<Truck> trucks = new ArrayList<>();

        trucks.add(new Truck("Volvo", 10 * myPage, 100));
        trucks.add(new Truck("Mercedes", 20 * myPage, 200));
        trucks.add(new Truck("Renault", 5 * myPage, 50));

        return trucks;
    }

    /**
     * HOW TO.
     *
     * Use it: curl -H "Accept: application/json"
     * http://localhost:8080/trucks/Mercedes
     * <br/>
     * Result expected:
     * {"id":"Mercedes","nbPassenger":20,"maxSpeed":200,"driver":{"firstName":"Mister","lastName":"Dudu","age":45}}
     *
     * @param truckId
     * @param acceptLanguage
     * @return
     */
    @GET
    @Path("{id}")
    public Truck findTruck(@PathParam("id") String truckId,
            @HeaderParam("Accept-Language") String acceptLanguage) {
        for (Truck truck : allTrucks(1)) {
            if (truck.getId().equals(truckId)) {
                truck.setDriver(new Driver("Mister", "Dudu", 45));
                return truck;
            }
        }

        return null;
    }

    @Path("{vehicleId}/drivers")
    public DriverResource manageDrivers(@PathParam("vehicleId") String id) {
        return new DriverResource(id);
    }

    /**
     * HOW TO.
     *
     * Use : curl -H "Accept: application/json" -H "Content-Type:
     * application/json" -d
     * '{"id":"Merco","nbPassenger":20,"maxSpeed":200,"driver":{"firstName":"Mister","lastName":"Dudu","age":45}}'
     * http://localhost:8080/trucks/
     *
     * @param truck
     * @return
     */
//    @POST
//    public Truck createTruck(Truck truck) {
//        // em.persist(truck)
//        System.out.printf("Truck : %s\n", truck);
//
//        truck.setId(truck.getId() + " modified");
//
//        return truck;
//    }
    /**
     * HOW TO.
     *
     * Use : curl -X POST -H "Accept: application/json" -H "Content-Type:
     * application/json" -d
     * '{"id":"Merco","nbPassenger":20,"maxSpeed":200,"driver":{"firstName":"Mister","lastName":"Dudu","age":45}}'
     * http://localhost:8080/trucks/ -v
     *
     * @param truck
     * @return
     */
    @POST
    public Response createTruck(Truck truck) {
        // em.persist(truck)
        System.out.printf("Truck : %s\n", truck);

        truck.setId(truck.getId() + "_modified");

        Response.ResponseBuilder rb = Response.created(URI.create(truck.getId()));
        rb.lastModified(Calendar.getInstance().getTime());
        rb.entity(truck); //BODY de a requête HTTP --> le JSON/XML/Text affiché

        return rb.build();
    }
}
