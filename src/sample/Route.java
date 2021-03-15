package sample;

public class Route {

    int routeid;
    int tollfee;
    String startingpoint;
    String destination;
    int traveltime;
    String intervalpoint;

    public Route() {
    }

    public Route(int routeid, int tollfee, String startingpoint, String destination, int traveltime, String intervalpoint) {
        this.routeid = routeid;
        this.tollfee = tollfee;
        this.startingpoint = startingpoint;
        this.destination = destination;
        this.traveltime = traveltime;
        this.intervalpoint = intervalpoint;
    }

    public int getRouteid() {
        return routeid;
    }

    public void setRouteid(int routeid) {
        this.routeid = routeid;
    }

    public int getTollfee() {
        return tollfee;
    }

    public void setTollfee(int tollfee) {
        this.tollfee = tollfee;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTraveltime() {
        return traveltime;
    }

    public void setTraveltime(int traveltime) {
        this.traveltime = traveltime;
    }

    public String getIntervalpoint() {
        return intervalpoint;
    }

    public void setIntervalpoint(String intervalpoint) {
        this.intervalpoint = intervalpoint;
    }
}
