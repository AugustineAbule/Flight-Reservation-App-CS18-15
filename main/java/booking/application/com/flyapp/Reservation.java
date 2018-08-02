package booking.application.com.flyapp;

/**
 * Created by archie on 7/17/2018.
 */

public class Reservation {

    private String Origin,Destination,DepartureTime,Type;

    public Reservation(String origin, String destination, String departureTime, String type) {
        Origin = origin;
        Destination = destination;
        DepartureTime = departureTime;
        Type = type;
    }

    public String getOrigin() {
        return Origin;
    }

    public String getDestination() {
        return Destination;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public String getType() {
        return Type;
    }

    public void setOrigin(String origin) {
        Origin = origin;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public void setType(String type) {
        Type = type;
    }
}
