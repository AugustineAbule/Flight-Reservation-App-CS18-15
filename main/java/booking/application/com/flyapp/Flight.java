package booking.application.com.flyapp;

/**
 * Created by archie on 7/17/2018.
 */

public class Flight {
private String departTime;
private String departAirportCode;
private String departTimeStatus;
private String departTerminalGate;


    private String arrivalTime;
    private String arrivalAirportCode;
    private String arrivalTimeStatus;
    private String arraivalTerminalGate;


    public Flight(String departTime, String departAirportCode, String departTimeStatus, String departTerminalGate, String arrivalTime, String arrivalAirportCode, String arrivalTimeStatus, String arraivalTerminalGate) {
        this.departTime = departTime;
        this.departAirportCode = departAirportCode;
        this.departTimeStatus = departTimeStatus;
        this.departTerminalGate = departTerminalGate;
        this.arrivalTime = arrivalTime;
        this.arrivalAirportCode = arrivalAirportCode;
        this.arrivalTimeStatus = arrivalTimeStatus;
        this.arraivalTerminalGate = arraivalTerminalGate;
    }

    public String getDepartTime() {
        return departTime;
    }

    public String getDepartAirportCode() {
        return departAirportCode;
    }

    public String getDepartTimeStatus() {
        return departTimeStatus;
    }

    public String getDepartTerminalGate() {
        return departTerminalGate;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public String getArrivalTimeStatus() {
        return arrivalTimeStatus;
    }

    public String getArraivalTerminalGate() {
        return arraivalTerminalGate;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public void setDepartAirportCode(String departAirportCode) {
        this.departAirportCode = departAirportCode;
    }

    public void setDepartTimeStatus(String departTimeStatus) {
        this.departTimeStatus = departTimeStatus;
    }

    public void setDepartTerminalGate(String departTerminalGate) {
        this.departTerminalGate = departTerminalGate;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setArrivalAirportCode(String arrivalAirportCode) {
        this.arrivalAirportCode = arrivalAirportCode;
    }

    public void setArrivalTimeStatus(String arrivalTimeStatus) {
        this.arrivalTimeStatus = arrivalTimeStatus;
    }

    public void setArraivalTerminalGate(String arraivalTerminalGate) {
        this.arraivalTerminalGate = arraivalTerminalGate;
    }
}
