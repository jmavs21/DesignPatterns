package behavioural.state;

public class Exercise {
    public static void main(String[] args) {
        DirectionService service = new DirectionService(new Walking());
        service.getEta();
        service.getDirection();
        service.setTravelMode(new Bicycling());
        service.getEta();
        service.getDirection();
        service.setTravelMode(new Driving());
        service.getEta();
        service.getDirection();
    }
}

interface TravelMode {
    int getEta();
    int getDirection();
}

class DirectionService {
    private TravelMode travelMode;
    public DirectionService(TravelMode travelMode) {
        this.travelMode = travelMode;
    }
    public int getEta() {
        return travelMode.getEta();
    }
    public int getDirection() {
        return travelMode.getDirection();
    }
    public TravelMode getTravelMode() {
        return travelMode;
    }
    public void setTravelMode(TravelMode travelMode) {
        this.travelMode = travelMode;
    }
}

class Walking implements TravelMode {
    @Override
    public int getEta() {
        System.out.println("Calculating ETA (walking)");
        return 4;
    }
    @Override
    public int getDirection() {
        System.out.println("Calculating Direction (walking)");
        return 4;
    }
}

class Bicycling implements TravelMode {
    @Override
    public int getEta() {
        System.out.println("Calculating ETA (bicycling)");
        return 2;
    }
    @Override
    public int getDirection() {
        System.out.println("Calculating Direction (bicycling)");
        return 2;
    }
}

class Driving implements TravelMode {
    @Override
    public int getEta() {
        System.out.println("Calculating ETA (driving)");
        return 1;
    }
    @Override
    public int getDirection() {
        System.out.println("Calculating Direction (driving)");
        return 1;
    }
}


