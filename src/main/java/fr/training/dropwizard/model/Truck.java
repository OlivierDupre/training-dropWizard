package fr.training.dropwizard.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author shuttle
 */
@XmlRootElement
public class Truck {

    String id;
    int nbPassenger, maxSpeed;
    Driver driver;

    public Truck() {
    }

    public Truck(String id, int nbPassenger, int maxSpeed) {
        this.id = id;
        this.nbPassenger = nbPassenger;
        this.maxSpeed = maxSpeed;
    }

    public Truck(String id, int nbPassenger, int maxSpeed, Driver driver) {
        this.id = id;
        this.nbPassenger = nbPassenger;
        this.maxSpeed = maxSpeed;
        this.driver = driver;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNbPassenger() {
        return nbPassenger;
    }

    public void setNbPassenger(int nbPassenger) {
        this.nbPassenger = nbPassenger;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Truck{" + "id=" + id + ", nbPassenger=" + nbPassenger + ", maxSpeed=" + maxSpeed + '}';
    }
}
