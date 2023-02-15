/*
    CLASS NAME      : ChildLess Class
    DESCRIPTION     : This class holds mainly the homeless child information and inherits attributes from the User class
    SUBCLASS OF     : Subclass of User Class
    SUB-FUNCTION(s) : getOrphanStatus(), getChildID(), getParentName(), setChildID(), setpname(), setorphan()
    AUTHOR          : BRENDAN DYLAN GAMPA ANAK JOSEPH DUSIT
*/
public class ChildLess extends User {

    private String ChildID;
    private String parentName;
    private boolean OrphanStatus;

    ChildLess(String firstName, String lastName, String gender, int age, Shelter location, int stayDays, String ChildID,
            String parentName, boolean OrphanStatus) {
        super(firstName, lastName, gender, age, location, stayDays);
        this.ChildID = ChildID;
        this.parentName = parentName;
        this.OrphanStatus = OrphanStatus;
    }

    // accessors
    boolean getOrphanStatus() {
        return OrphanStatus;
    }

    String getChildID() {
        return ChildID;
    }

    String getParentName() {
        return parentName;
    }

    // mutators
    void setChildID(String ChildID) {
        this.ChildID = ChildID;
    }

    public void setpname(String temppname) {
        parentName = temppname;
    }

    public void setorphan(boolean temporphan) {
        OrphanStatus = temporphan;
    }
}
