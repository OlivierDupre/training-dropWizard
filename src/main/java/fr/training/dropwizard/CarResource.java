package fr.training.dropwizard;

import fr.training.dropwizard.model.Car;
import fr.training.dropwizard.model.Driver;
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
@Path("/cars") // Par picinpe/convention, on met au pluriel
@Produces(MediaType.APPLICATION_JSON) // Si on ne met rien, le serveur s'adapte en fonction de ce qui est présent dans le requête HTTP
public class CarResource {

    @GET
    public List<Car> allCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("2DPG", 4, 150));
        cars.add(new Car("Utopi-142", 2, 250));
        cars.add(new Car("37-DPKG", 40, 110));

        return cars;
    }

    @GET
    @Path("{id}")
    public Car findCar(@PathParam("id") String carId,
            @HeaderParam("Accept-Language") String acceptLanguage) {
        for (Car car : allCars()) {
            if (car.getId().equals(carId)) {
                car.setDriver(new Driver(acceptLanguage.split(",")[0], acceptLanguage.split(",")[2], 30));
                return car;
            }
        }

        return null;
    }
}
