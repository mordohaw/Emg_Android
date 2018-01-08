package williammordohay.localisationapp.Timetables;

/**
 * Created by William on 08/01/2018.
 */

public class TimeObject {
    private String stopId,stopName;
    private int scheduledArrival,scheduledDeparture,realtimeArrival,realtimeDeparture,arrivalDelay,departureDelay,serviceDay,tripId;
    private boolean timepoint, realtime;

    public TimeObject(String stopId, String stopName, int scheduledArrival, int scheduledDeparture, int realtimeArrival, int realtimeDeparture, int arrivalDelay, int departureDelay, int serviceDay, int tripId, boolean timepoint, boolean realtime) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.scheduledArrival = scheduledArrival;
        this.scheduledDeparture = scheduledDeparture;
        this.realtimeArrival = realtimeArrival;
        this.realtimeDeparture = realtimeDeparture;
        this.arrivalDelay = arrivalDelay;
        this.departureDelay = departureDelay;
        this.serviceDay = serviceDay;
        this.tripId = tripId;
        this.timepoint = timepoint;
        this.realtime = realtime;
    }

    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public int getScheduledArrival() {
        return scheduledArrival;
    }

    public void setScheduledArrival(int scheduledArrival) {
        this.scheduledArrival = scheduledArrival;
    }

    public int getScheduledDeparture() {
        return scheduledDeparture;
    }

    public void setScheduledDeparture(int scheduledDeparture) {
        this.scheduledDeparture = scheduledDeparture;
    }

    public int getRealtimeArrival() {
        return realtimeArrival;
    }

    public void setRealtimeArrival(int realtimeArrival) {
        this.realtimeArrival = realtimeArrival;
    }

    public int getRealtimeDeparture() {
        return realtimeDeparture;
    }

    public void setRealtimeDeparture(int realtimeDeparture) {
        this.realtimeDeparture = realtimeDeparture;
    }

    public int getArrivalDelay() {
        return arrivalDelay;
    }

    public void setArrivalDelay(int arrivalDelay) {
        this.arrivalDelay = arrivalDelay;
    }

    public int getDepartureDelay() {
        return departureDelay;
    }

    public void setDepartureDelay(int departureDelay) {
        this.departureDelay = departureDelay;
    }

    public int getServiceDay() {
        return serviceDay;
    }

    public void setServiceDay(int serviceDay) {
        this.serviceDay = serviceDay;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public boolean isTimepoint() {
        return timepoint;
    }

    public void setTimepoint(boolean timepoint) {
        this.timepoint = timepoint;
    }

    public boolean isRealtime() {
        return realtime;
    }

    public void setRealtime(boolean realtime) {
        this.realtime = realtime;
    }
}
