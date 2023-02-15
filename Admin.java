/*
    CLASS NAME      : Admin Class
    DESCRIPTION     : This class holds mainly the admin ID and password of the system to be used for login and also inherits from the Person class.
    SUBCLASS OF     : Subclass of Person Class
    SUB-FUNCTION(s) : getAdminID(), getAdminPass(), setAdminID(), setAdminPass()
    AUTHOR          : IESKANDAR ZULQARNAIN BIN GHAZALI 

*/
public class Admin extends Person {
    private String adminID;
    private String adminPass;

    Admin(String firstName, String lastName, String gender, int age, String adminID, String adminPass) {
        super(firstName, lastName, gender, age);
        this.adminID = adminID;
        this.adminPass = adminPass;
    }

    // accessor
    String getAdminID() {
        return adminID;
    }

    String getAdminPass() {
        return adminPass;
    }

    // muttator
    void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    void setAdminPass(String adminPass) {
        this.adminPass = adminPass;
    }
}
