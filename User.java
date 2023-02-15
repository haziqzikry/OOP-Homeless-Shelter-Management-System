/*
    CLASS NAME      : User Class
    DESCRIPTION     : Inherits from the class Person. This class is used to create a User object.
    SUBCLASS OF     : Subclass of Person Class
    SUB-FUNCTION(s) : getLocation(), getStayDays(), setLocation(), setStaydays()
    AUTHOR          : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK

*/

public class User extends Person {
    private Shelter location;
    protected int stayDays;

    User(String firstName, String lastName, String gender, int age, Shelter location, int stayDays) {
        super(firstName, lastName, gender, age);
        this.location = location;
        this.stayDays = stayDays;
    }

    // accessors
    String getLocation() {
        return location.getshelName();
    }

    int getStayDays() {
        return stayDays;
    }

    // mutators
    void setStaydays(int tempstayDays) {
        stayDays = tempstayDays;
    }

    void setLocation(Shelter location) {
        this.location = location;
    }
}
