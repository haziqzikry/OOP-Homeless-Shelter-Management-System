/*
    CLASS NAME      : Volunteer Class
    DESCRIPTION     : This class holds information of volunteers such as the volunteer ID and password of the system to be used for login and also inherits from the Person class.
    SUBCLASS OF     : Subclass of Person Class
    SUB-FUNCTION(s) : getVolunteerID(), getVolunteerPass(), getWorkingLocation(), setVolunteerID(), setVolunteerPass(), setworkingLocation()
    AUTHOR          : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK

*/
public class Volunteer extends Person {
    private Shelter shelter;
    private String volunteerID;
    private String volunteerPass;

    Volunteer(String firstName, String lastName, String gender, int age, String volunteerID, String volunteerPass,
            Shelter workingLocation) {
        super(firstName, lastName, gender, age);
        this.volunteerID = volunteerID;
        this.volunteerPass = volunteerPass;
        this.shelter = workingLocation;
    }

    // accessors
    String getVolunteerID() {
        return volunteerID;
    }

    String getVolunteerPass() {
        return volunteerPass;
    }

    String getWorkingLocation() {
        return shelter.getshelName();
    }

    // mutators
    void setvolunteerID(String volunteerID) {
        this.volunteerID = volunteerID;
    }

    void setvolunteerPass(String volunteerPass) {
        this.volunteerPass = volunteerPass;
    }

    void setworkingLocation(Shelter workingLocation) {
        this.shelter = workingLocation;
    }
}
