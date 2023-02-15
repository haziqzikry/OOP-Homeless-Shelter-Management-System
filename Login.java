/*
    CLASS NAME      : Login Class
    DESCRIPTION     : This class holds information about the login of the system and enables the user to login into to the system.
    SUB-FUNCTION(s) : getUserType(), WelcomeMessage(), readUserInput(), 
    AUTHOR          : BRENDAN DYLAN GAMPA ANAK JOSEPH DUSIT

*/

import java.util.*;

public class Login {
    private String username;
    private String password;
    private char userType;

    Scanner in1 = new Scanner(System.in);
    Scanner in2 = new Scanner(System.in);

    char getUserType() {
        return Character.toUpperCase(userType);
    }

    void WelcomeMessage() {
        System.out.println("<< LOGIN SECTION >>");
        System.out.println("----------------------");
        System.out.println("A - Admin Login");
        System.out.println("V - Volunteer Login");
        System.out.println("----------------------");
        System.out.println();
    }

    // reads user type
    void readUserInput(ArrayList<Admin> ad, ArrayList<Volunteer> vol) throws Exception {
        boolean test = false;

        System.out.print("Select user type: ");
        userType = in1.next().charAt(0);

        System.out.println();

        // validate user type
        while (Character.toUpperCase(userType) != 'A' && Character.toUpperCase(userType) != 'V') {
            System.out.println("The entered user type is not available. Please input again.");
            System.out.println();
            System.out.print("Select user type: ");
            userType = in1.next().charAt(0);
        }

        in1.nextLine();

        // check size of arraylist
        if (Character.toUpperCase(userType) == 'A') {
            if (ad.size() == 0) {
                System.out.println("There is no admin account. Please register admin account first.");
                System.out.println();
                System.exit(0);
            }
        } else if (Character.toUpperCase(userType) == 'V') {
            if (vol.size() == 0) {
                System.out.println("There is no volunteer account. Please register volunteer account first.");
                System.out.println();
                System.exit(0);
            }
        }

        // validate username && password
        do {
            System.out.print("Username: ");
            username = in1.next();
            System.out.print("Password: ");
            password = in2.next();
            System.out.println();

            if (Character.toUpperCase(userType) == 'A') {
                for (Admin a : ad) {
                    if (a.getAdminID().equals(username) && a.getAdminPass().equals(password)) {
                        System.out.println("Admin Login successful!");
                        test = true;
                        break;
                    }
                }
            } else if (Character.toUpperCase(userType) == 'V') {
                for (Volunteer v : vol) {
                    if (v.getVolunteerID().equals(username) && v.getVolunteerPass().equals(password)) {
                        System.out.println("Volunteer Login successful!");
                        test = true;
                        break;
                    }
                }
            }
            if (!test) {
                System.out.println("Login failed. Please try again.");
                System.out.println();
            }

            in1.nextLine();
            in2.nextLine();

        } while (!test);

        TestHomeless.SystemPause();
    }
}