/*
    CLASS NAME      : Person Class
    DESCRIPTION     : This class holds the information of the person which is also the SUPERCLASS of the Admin, Volunteer, and User class.
    SUB-FUNCTION(s) : getfName(), getlName(), getName(), getGender(), getAge(),  getSaveName(), setFirstName(), setLastName(), setgender(), setAge()
    AUTHOR          :  AUM JEEVAN A/L NIRANGKAR                

*/
public class Person {
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected int age;

    Person(String firstName, String lastName, String gender, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    // accessor
    protected String getfName() {
        return firstName;
    }

    protected String getlName() {
        return lastName;
    }

    String getName() {
        return firstName + " " + lastName;
    }

    String getGender() {
        return gender;
    }

    int getAge() {
        return age;
    }

    String getSaveName() {
        return firstName + ", " + lastName;
    }

    // mutator
    void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    void setlastName(String lastName) {
        this.lastName = lastName;
    }

    void setgender(String gender) {
        this.gender = gender;
    }

    void setAge(int tempaid) {
        this.age = tempaid;
    }

}