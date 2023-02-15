/*
    CLASS NAME      : AdultLess Class
    DESCRIPTION     : This class holds mainly the adult ID and inherits attributes from the User class
    SUBCLASS OF     : Subclass of User Class
    SUB-FUNCTION(s) : getAdultID(), getOccup(), setaid(), setpoccup(), getstayDays()
    AUTHOR          : BRENDAN DYLAN GAMPA ANAK JOSEPH DUSIT

*/
public class AdultLess extends User {

    private String AdultID;
    private String pastOccupation;

    AdultLess(String firstName, String lastName, String gender, int age, Shelter location, int stayDays, String AdultID,
            String pastOccupation) {
        super(firstName, lastName, gender, age, location, stayDays);
        this.AdultID = AdultID;
        this.pastOccupation = pastOccupation;
    }

    // accessors
    String getAdultID() {
        return AdultID;
    }

    String getOccup() {
        return pastOccupation;
    }

    // mutators
    public void setaid(String tempaid) {
        AdultID = tempaid;
    }

    public void setpocc(String temppocc) {
        pastOccupation = temppocc;
    }

}
