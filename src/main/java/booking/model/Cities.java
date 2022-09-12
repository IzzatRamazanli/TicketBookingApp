package booking.model;

public enum Cities {
    KIEV("KBP"),
    BAKU("GYD"),
    PARIS("CDG"),
    LONDON("LHR"),
    ISTANBUL("SAW"),
    ROME("FCO"),
    MADRID("MAD"),
    QATAR("DIA"),
    DUBAI("DXB"),
    ANKARA("ESB"),
    BOSTON("BOS"),
    TOKIO("HND"),
    OSLO("OSL"),
    COPENHAGEN("CPH");


    private final String airportCode;

    Cities(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }
}
