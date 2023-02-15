/*
    CLASS NAME      : Shelter Class
    DESCRIPTION     : This class holds the shelter information
    SUB-FUNCTION(s) : getshelName(), getCity(), getState(), setshelName(), setCity(), setState()
    AUTHOR          : IESKANDAR ZULQARNAIN BIN GHAZALI 

*/
public class Shelter {
    private String shelName, city, state;

    Shelter() {
    }

    Shelter(String shelName, String city, String state) {
        this.shelName = shelName;
        this.city = city;
        this.state = state;
    }

    // accessors
    String getshelName() {
        return shelName;
    }

    String getCity() {
        return city;
    }

    String getState() {
        return state;
    }

    // mutators
    void setshelName(String shelName) {
        this.shelName = shelName;
    }

    void setCity(String city) {
        this.city = city;
    }

    void setState(String state) {
        this.state = state;
    }
}