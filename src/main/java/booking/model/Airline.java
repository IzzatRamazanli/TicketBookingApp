package booking.model;

public enum Airline {
    EMIRATES_AIRLINES("EK"),
    QATAR_AIRWAYS("QR"),
    BRAVO_AIRWAYS("BA"),
    AZAL("J2"),
    TURKISH_AIRLINES("TK"),
    SKY_UP("PQ"),
    ALASKA_AIRLINES("AS"),
    BRITISH_AIRWAYS("BA");

    private final String code;

    Airline(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
