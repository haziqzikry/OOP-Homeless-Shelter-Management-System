/*
        SECJ2154 - 07 OBJECT-ORIENTED PROGRAMMING

        HOMELESS SHELTER MANAGEMENT SYSTEM [GROUP KeJumpz]
        ==================================================

        This program is a simple system that allows the user to manage a shelter.

        LECTURER: DR. NORSHYAM BINTI IDRIS          7321

        TEAM MEMBER LIST:
        [1] AUM JEEVAN A/L NIRANGKAR                A20EC0017
        [2] BRENDAN DYLAN GAMPA ANAK JOSEPH DUSIT   A20EC0021
        [3] IESKANDAR ZULQARNAIN BIN GHAZALI        A20EC0046
        [4] MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK  A20EC0079
        
        CLASS NAME      : TestHomeless
        DESCRIPTION     : This class is used to test the functionality of the program.
        AUTHOR          : ALL TEAM MEMBER
*/

import java.util.*;
import java.io.*;
import java.time.*;

public class TestHomeless {
    LocalDate date = LocalDate.now();
    static ArrayList<Admin> admin = new ArrayList<Admin>();
    static ArrayList<Volunteer> volunteer = new ArrayList<Volunteer>();
    static ArrayList<BasicNeeds> bNeeds = new ArrayList<BasicNeeds>();
    static ArrayList<AdultLess> adult = new ArrayList<AdultLess>();
    static ArrayList<ChildLess> child = new ArrayList<ChildLess>();
    static ArrayList<Shelter> shelter = new ArrayList<Shelter>();

    static int ad = 0;
    static int vol = 0;
    static int bn = 0;

    static Scanner sc = new Scanner(System.in);
    static Scanner in1 = new Scanner(System.in);
    static Scanner in2 = new Scanner(System.in);

    static int choiceA; // choice adult CASE 2 volunteer menu
    static int choiceA2; // choice adult CASE 2 volunteer menu
    static int choiceC; // choice child CASE 2 volunteer menu
    static int choiceC2; // choice child CASE 2 volunteer menu
    static int choose = 0; // general selection for services
    static int chooseBneed; // general selection for basic needs
    static int choiceB; // choice basic needs CASE 2 volunteer menu
    static char category; // choose category of homeless people
    static boolean opstatus; // orphan status
    static boolean adminOpt; // admin option

    public static void main(String[] args) throws Exception {
        readFile();
        boolean test = false;

        Login l = new Login();
        Registration Reg = new Registration();

        do {
            Clrscr();
            System.out.println("<< HELLO AND WELCOME TO HOMELESS SHELTER MANAGEMENT SYSTEM >>");
            printLine();
            System.out.println("1 - Login");
            System.out.println("2 - Register New User");
            System.out.println("0 - Terminated");
            printLine();
            System.out.println();
            System.out.print("Option :: ");
            choose = sc.nextInt();
            switch (choose) {
                // login
                case 1:
                    Clrscr();
                    l.WelcomeMessage();
                    l.readUserInput(admin, volunteer);

                    // user type home page
                    if (l.getUserType() == 'A') {
                        do {
                            Clrscr();
                            adminOpt = false;
                            System.out.println("<<< SERVICES AVAILABLE >>>");
                            printLine();
                            System.out.println("[1] Manage Shelter");
                            System.out.println("[2] Manage Volunteer");
                            System.out.println("[3] Manage Basic Needs");
                            System.out.println("[4] Logout");
                            printLine();

                            System.out.print("Option :: ");
                            choose = sc.nextInt();
                            switch (choose) {
                                // manage shelter
                                case 1:
                                    ManageShelter();
                                    adminOpt = true;
                                    break;
                                // manage volunteer
                                case 2:
                                    if (volunteer.size() == 0) {
                                        System.out.println();
                                        System.out.println("No volunteer registered");
                                    } else {
                                        manageVolunteer();
                                    }
                                    adminOpt = true;
                                    break;
                                // manage basic needs
                                case 3:
                                    managebasicneed();
                                    adminOpt = true;
                                    break;
                                // logout
                                case 4:
                                    System.out.println("Returning back to main Page!");
                                    adminOpt = false;
                                    break;
                                default:
                                    System.out.println("Invalid Choice! Please Select Again!");
                                    adminOpt = true;
                                    break;
                            }
                            SystemPause();
                        } while (adminOpt);
                    } else if (l.getUserType() == 'V') {

                        do {
                            Clrscr();
                            System.out.println("<<< SERVICES AVAILABLE >>>");
                            printLine();
                            System.out.println("[1] Add Homeless People");
                            System.out.println("[2] Update Homeless People");
                            System.out.println("[3] Delete Homeless People");
                            System.out.println("[4] Display Homeless People List");
                            System.out.println("[5] Logout");
                            printLine();

                            System.out.print("Option :: ");
                            choose = sc.nextInt();

                            switch (choose) {
                                case 1:
                                    // add homeless people
                                    Clrscr();
                                    addHomeless();
                                    SystemPause();
                                    break;
                                case 2:
                                    // update homeless people
                                    Clrscr();
                                    updateHomeless();
                                    SystemPause();
                                    break;
                                case 3:
                                    // delete homeless people
                                    Clrscr();
                                    deleteHomeless();
                                    SystemPause();
                                    break;
                                case 4:
                                    // display homeless people list
                                    Clrscr();
                                    displayHomelessPeople();
                                    SystemPause();
                                    break;
                                case 5:
                                    System.out.println();
                                    System.out.println("You are logging out");
                                    break;
                                default:
                                    System.out.println("Sorry! The services selected is not available!");
                                    break;
                            }
                        } while (choose >= 1 && choose <= 4);
                    }
                    test = true;
                    break;
                case 2:
                    // registration
                    Clrscr();
                    Reg.WelcomeMessage();
                    registerNewUser();
                    test = true;
                    break;
                case 0:
                    // exit system
                    System.out.println();
                    System.out.println("Thank you for using our system");
                    test = false;
                    break;
                default:
                    System.out.println();
                    System.out.println("INVALID CHOICE!");
                    test = true;
                    break;
            }
            SystemPause();
        } while (test);
        sc.close();
        in1.close();
        in2.close();

    }

    /*
     * FUNCTION NAME : registerNewUser
     * DESCRIPTION : this function is used to register new admin or volunteer
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    public static void registerNewUser() throws Exception {
        String username, password, fname, lname, gender;
        int age;
        char userType;

        Shelter workloca;
        int choose = 0;
        boolean test = false;
        do {
            test = false;
            System.out.print("Select user type: ");
            userType = in1.next().charAt(0);
            in1.nextLine();
            if (Character.toUpperCase(userType) != 'A' && Character.toUpperCase(userType) != 'V') {
                System.out.println("The entered user type is not available. Please input again.");
                System.out.println();
                test = true;
            }
        } while (test);

        System.out.println();
        System.out.print("Username    :: ");
        username = in1.nextLine();
        System.out.print("Password    :: ");
        password = in2.nextLine();
        System.out.print("First name  :: ");
        fname = in1.nextLine();
        System.out.print("Last name   :: ");
        lname = in2.nextLine();
        System.out.print("Gender      :: ");
        gender = in1.nextLine();
        System.out.print("Age         :: ");
        age = in1.nextInt();

        if (Character.toUpperCase(userType) == 'V') {
            System.out.println();
            TestHomeless.displayLocation();
            do {
                System.out.print("Option :: ");
                choose = sc.nextInt();

            } while (choose < 1 || choose > shelter.size());
            workloca = shelter.get(choose - 1);

            // send to volunteer class
            volunteer.add(new Volunteer(fname, lname, gender, age, username, password, workloca));
        } else if (Character.toUpperCase(userType) == 'A') {

            // send to admin class
            admin.add(new Admin(fname, lname, gender, age, username, password));
        }

        // update file
        saveFile();

    }

    /*
     * FUNCTION NAME : reducebasicneeds
     * DESCRIPTION : function for reduce basic needs upon sign up
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void reducebasicneeds() {
        for (int i = 0; i < bNeeds.size(); i++) {
            bNeeds.get(i).decrementQuantity();
            System.out.println("You are eligible for 1 " + bNeeds.get(i).getBasicName() + ".");
        }
    }

    /*
     * FUNCTION NAME : managebasicneed
     * DESCRIPTION : function for managing basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void managebasicneed() throws Exception {
        boolean test;
        do {
            Clrscr();
            test = false;
            System.out.println("<<< SERVICES AVAILABLE >>>");
            printLine();
            System.out.println("[1] Check Balance");
            System.out.println("[2] Update Quantity");
            System.out.println("[3] Add Basic Needs");
            System.out.println("[4] Delete Basic Needs");
            System.out.println("[0] Go back to Admin Page");
            printLine();

            System.out.print("Option :: ");
            chooseBneed = sc.nextInt();

            switch (chooseBneed) {
                case 1:
                    balanceChecking();
                    break;
                case 2:
                    updateQuantity();
                    break;
                case 3:
                    Clrscr();
                    addBasicNeeds();
                    break;
                case 4:
                    deleteBasicNeeds();
                    break;
                case 0:
                    System.out.println();
                    System.out.println("Go back to Admin Page!");
                    test = false;
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid Choice! Please Select Again!");
                    test = true;
                    break;
            }
        } while (test);
    }

    /*
     * FUNCTION NAME : displayBasicNeeds
     * DESCRIPTION : function to display basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void displayBasicNeeds() {
        printLine();
        System.out.println("<<< LIST OF BASIC NEEDS >>>");
        printLine();
        String str = "";
        System.out.printf("%-4s %-15s %-10s %-6s", "No",
                "Name", "Quantity", "Description",
                "Type");
        System.out.println();
        for (int i = 0; i < bNeeds.size(); i++) {
            str = "[" + (i + 1) + "] ";
            System.out.printf("%-1s %-15s %-10s %-6s", str,
                    bNeeds.get(i).getBasicName(), bNeeds.get(i).getQuantity(),
                    bNeeds.get(i).getDesc(), bNeeds.get(i).getType());
            System.out.println();
        }
        printLine();

    }

    /*
     * FUNCTION NAME : balanceChecking
     * DESCRIPTION : function to check balance of the basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void balanceChecking() {
        boolean test;
        do {
            test = false;
            Clrscr();
            if (bNeeds.size() == 0) {
                System.out.println("No basic needs in the list!");
                return;
            }
            displayBasicNeeds();
            System.out.println();
            System.out.print("Choose Basic Need To View: ");
            choiceB = in1.nextInt();
            if (choiceB < 1 || choiceB > bNeeds.size()) {
                test = true;
                System.out.println("Invalid Selection! Please Select again");
            }

        } while (test);
        bNeeds.get(choiceB - 1).balancecheck();
    }

    /*
     * FUNCTION NAME : updateQuantity
     * DESCRIPTION : function to update the quantity of the chosen basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void updateQuantity() throws Exception {
        boolean test;
        do {
            test = false;
            Clrscr();
            displayBasicNeeds();
            System.out.println();
            System.out.print("Choose Basic Need To Update: ");
            choiceB = in1.nextInt();
            printLine();
            if (choiceB < 1 || choiceB > bNeeds.size()) {
                test = true;
                System.out.println("Invalid Selection! Please Select again");
            }
        } while (test);
        System.out.print("Enter New Quantity: ");
        int tempNewQuantity = in1.nextInt();
        bNeeds.get(choiceB - 1).setQuantity(tempNewQuantity);

        // update file
        saveFile();
    }

    /*
     * FUNCTION NAME : deleteBasicNeeds
     * DESCRIPTION : function to delete basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void deleteBasicNeeds() throws Exception {
        boolean test;
        int index;
        do {
            test = false;
            Clrscr();
            displayBasicNeeds();
            System.out.println();
            System.out.print("Enter the index of the basic needs you want to delete: ");
            index = sc.nextInt();
            if (index < 1 || index > bNeeds.size()) {
                test = true;
                System.out.println("Invalid Selection! Please Select again");
            }
        } while (test);

        bNeeds.remove(index - 1);
        System.out.println("You have deleted the basic needs");
        saveFile();
    }

    /*
     * FUNCTION NAME : addBasicNeeds
     * DESCRIPTION : function to add basic needs
     * AUTHOR : AUM JEEVAN A/L JEEVAN A20EC0021
     */
    static void addBasicNeeds() throws Exception {
        Scanner input = new Scanner(System.in);
        String basicName, Desc;
        int quantity;
        char type;

        printLine();
        System.out.println("<<< ADDING NEW BASIC NEEDS >>>");
        printLine();
        System.out.print("                                  Enter the name :: ");
        basicName = input.nextLine();
        System.out.print("                              Enter the quantity :: ");
        quantity = sc.nextInt();
        System.out.print("                          Enter the descriptions :: ");
        Desc = input.nextLine();
        System.out.print("Enter the type [m-meal, c-clothes, h-healthcare] :: ");
        type = sc.next().charAt(0);
        bNeeds.add(new BasicNeeds(basicName, quantity, Desc, type));
        System.out.println();
        System.out.println("                               You have added the basic needs!");

        // update file
        saveFile();
    }

    /*
     * FUNCTION NAME : displayLocation
     * DESCRIPTION : function to display location of shelters
     * AUTHOR : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK A20EC0079
     */
    static void displayLocation() {
        System.out.println("<<< " + shelter.size() + " LOCATIONS TO CHOOSE >>>");
        printLine();
        for (int i = 0; i < shelter.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + shelter.get(i).getshelName() + ", " + shelter.get(i).getCity()
                    + ", " + shelter.get(i).getState());
        }
        printLine();
    }

    /*
     * FUNCTION NAME : displayVolunteer
     * DESCRIPTION : function to display volunteer list
     * AUTHOR : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK A20EC0079
     */
    static void displayVolunteer() {
        System.out.println("<<< LIST OF VOLUNTEER TO CHOOSE >>>");
        printLine();
        String str = "";
        System.out.printf("%-5s %-30s %-20s %n", "No", "Name", "Volunteer ID");
        for (int i = 0; i < volunteer.size(); i++) {
            str = "[" + (i + 1) + "] ";
            System.out.printf("%-5s %-30s %-20s %n", str, volunteer.get(i).getName(),
                    volunteer.get(i).getVolunteerID());
        }
        printLine();
    }

    /*
     * FUNCTION NAME : addHomeless
     * DESCRIPTION : add homeless people into the list of homeless adult and
     * children
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    static void addHomeless() throws Exception {
        Scanner a1 = new Scanner(System.in);
        Scanner a2 = new Scanner(System.in);
        Scanner a3 = new Scanner(System.in);
        Scanner a4 = new Scanner(System.in);
        Scanner c1 = new Scanner(System.in);
        Scanner c2 = new Scanner(System.in);

        // input
        String firstName, lastName, gender, cid, aid, pname, pocc;
        int age, stayDays;
        Shelter location;

        System.out.println("<<< ADD HOMELESS PEOPLE >>>");
        printLine();
        System.out.print("First Name: ");
        firstName = a1.nextLine();
        System.out.print("Last Name: ");
        lastName = a2.nextLine();
        System.out.print("Gender: ");
        gender = a1.nextLine();
        System.out.print("Age: ");
        age = a1.nextInt();

        System.out.println();
        displayLocation();

        do {
            System.out.print("Option :: ");
            choose = sc.nextInt();

        } while (choose < 1 || choose > shelter.size());
        location = shelter.get(choose - 1);

        System.out.print("Days staying: ");
        stayDays = in1.nextInt();

        sc.nextLine();

        // validate age
        if (age > 0 && age <= 15) {
            System.out.print("Child ID: ");
            cid = c1.next();
            System.out.print("Parent name (Type - if none): ");
            pname = c2.next();

            if (pname.equals("-")) {
                pname = "None";
                opstatus = true;
            } else {
                opstatus = false;
            }

            // send to ChildLess class
            child.add(new ChildLess(firstName, lastName, gender, age, location, stayDays, cid, pname, opstatus));

            // save input into text file
            saveFile();

        } else if (age > 15 && age < 90) {
            System.out.print("Adult ID: ");
            aid = a3.nextLine();
            System.out.print("Past Occupation: ");
            pocc = a4.nextLine();

            // send to Adultless class
            adult.add(new AdultLess(firstName, lastName, gender, age, location, stayDays, aid, pocc));

            // save input into text file
            saveFile();

        } else {
            System.out.println("Invalid age.");
        }

        // invoke reduce basic needs function
        reducebasicneeds();
        
        //clear buffer
        in1.nextLine();
        in2.nextLine();
        a1.nextLine();
        a2.nextLine();
        a3.nextLine();
        a4.nextLine();
        c1.nextLine();
        c2.nextLine();

        System.out.println();
    }

    /*
     * FUNCTION NAME : updateHomeless
     * DESCRIPTION : function to update homeless people
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    static void updateHomeless() throws Exception {
        Scanner a1 = new Scanner(System.in);
        Scanner a2 = new Scanner(System.in);

        String tempfirstName, templastName, tempgender, temppname, inputorphan;
        int tempage, tempstayDays;
        String tempaid, tempchildID, temppastOccupation;
        boolean temporphan = false;
        Shelter templocation;

        System.out.println("<<< UPDATE HOMELESS PEOPLE >>>");
        printLine();
        System.out.println("Please select which category you want to update");
        printLine();
        System.out.println("ADULT - A");
        System.out.println("CHILD - C");
        printLine();
        System.out.print("Option :: ");
        category = in1.next().charAt(0);

        in1.nextLine(); // clear buffer

        Clrscr();
        if (Character.toUpperCase(category) == 'A') {
            if (adult.size() == 0) {
                System.out.println("No adult list to be deleted");
            } else {
                int numA = 1;
                System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s", "No",
                        "Full name", "Gender", "Age",
                        "Location", "Days stayed", "Adult ID", "Past Occupation");
                System.out.println();
                printLine();

                for (int i = 0; i < adult.size(); i++) {
                    System.out.printf("[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s",
                            numA, adult.get(i).getName(), adult.get(i).getGender(),
                            adult.get(i).getAge(), adult.get(i).getLocation(),
                            adult.get(i).getStayDays(), adult.get(i).getAdultID(),
                            adult.get(i).getOccup());
                    System.out.println();

                    numA++;
                }
                printLine();
                System.out.println();
                System.out.print("Choose Adult To Update: ");
                choiceA = in1.nextInt();
                printLine();

                System.out.println("[1] First Name");
                System.out.println("[2] Last Name");
                System.out.println("[3] Gender");
                System.out.println("[4] Age");
                System.out.println("[5] Location");
                System.out.println("[6] Stay Days");
                System.out.println("[7] Adult ID");
                System.out.println("[8] Past Occupation");
                System.out.println();
                System.out.print("Choose item to update: ");
                choiceA2 = in1.nextInt();
                printLine();
                System.out.println();

                if (choiceA2 == 1) {
                    System.out.print("Enter New First Name: ");
                    tempfirstName = a1.nextLine();
                    adult.get(choiceA - 1).setfirstName(tempfirstName);
                    System.out.println(adult.get(choiceA - 1).getfName() + " has been updated.");
                } else if (choiceA2 == 2) {
                    System.out.print("Enter New Last Name: ");
                    templastName = a2.nextLine();
                    adult.get(choiceA - 1).setlastName(templastName);
                    System.out.println(adult.get(choiceA - 1).getlName() + " has been updated.");
                } else if (choiceA2 == 3) {
                    System.out.print("Enter New Gender: ");
                    tempgender = a1.next();
                    adult.get(choiceA - 1).setgender(tempgender);
                    System.out.println(adult.get(choiceA - 1).getGender() + " has been updated.");
                } else if (choiceA2 == 4) {
                    System.out.print("Enter New Age: ");
                    tempage = a1.nextInt();
                    adult.get(choiceA - 1).setAge(tempage);
                    System.out.println(adult.get(choiceA - 1).getAge() + " has been updated.");
                } else if (choiceA2 == 5) {
                    displayLocation();

                    do {
                        System.out.print("Option :: ");
                        choose = sc.nextInt();

                    } while (choose < 1 || choose >= shelter.size());
                    templocation = shelter.get(choose - 1);
                    adult.get(choiceA - 1).setLocation(templocation);
                    System.out.println(adult.get(choiceA - 1).getLocation() + " has been updated.");
                } else if (choiceA2 == 6) {
                    System.out.print("Enter New Stay Days: ");
                    tempstayDays = in1.nextInt();
                    adult.get(choiceA - 1).setStaydays(tempstayDays);
                    System.out.println(adult.get(choiceA - 1).getStayDays() + " has been updated.");
                } else if (choiceA2 == 7) {
                    System.out.print("Enter New Adult ID: ");
                    tempaid = in1.next();
                    adult.get(choiceA - 1).setaid(tempaid);
                    System.out.println(adult.get(choiceA - 1).getAdultID() + " has been updated.");
                } else if (choiceA2 == 8) {
                    System.out.print("Enter New Past Occupation: ");
                    temppastOccupation = in1.next();
                    adult.get(choiceA - 1).setpocc(temppastOccupation);
                    System.out.println(adult.get(choiceA - 1).getOccup() + " has been updated.");
                } else if (choiceA2 == 9) {
                    System.out.println("Exiting Update.");
                } else {
                    System.out.println("Invalid Input.");
                }
            }

        } else if (Character.toUpperCase(category) == 'C') {
            if (child.size() == 0) {
                System.out.println("No child list to be deleted");
            } else {
                int numC = 1;
                String childstatus = " ";

                System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                        "No", "Full name", "Gender", "Age", "Location", "Days stayed",
                        "Child ID",
                        "Parent Name", "Orphan Status");
                System.out.println();
                printLine();
                for (int i = 0; i < child.size(); i++) {
                    // verify child status
                    if (child.get(i).getOrphanStatus() == true) {
                        childstatus = "Orphan";
                    } else if (child.get(i).getOrphanStatus() == false) {
                        childstatus = "Non-orphan";
                    }
                    System.out.printf(
                            "[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                            numC, child.get(i).getName(), child.get(i).getGender(),
                            child.get(i).getAge(), child.get(i).getLocation(),
                            child.get(i).getStayDays(), child.get(i).getChildID(),
                            child.get(i).getParentName(), childstatus);
                    System.out.println();

                    numC++;
                }
                printLine();
                System.out.println();
                System.out.print("Choose Child To Update: ");
                choiceC = in1.nextInt();
                printLine();
                System.out.println();

                System.out.println("[1] First Name");
                System.out.println("[2] Last Name");
                System.out.println("[3] Gender");
                System.out.println("[4] Age");
                System.out.println("[5] Location");
                System.out.println("[6] Stay Days");
                System.out.println("[7] Child ID");
                System.out.println("[8] Parent Name");
                System.out.println("[9] Orphan Status");
                System.out.println();
                System.out.print("Choose item to update: ");
                choiceC2 = in1.nextInt();
                printLine();
                System.out.println();

                // update child
                if (choiceC2 == 1) {
                    System.out.print("Enter New First Name: ");
                    tempfirstName = in1.next();
                    child.get(choiceC - 1).setfirstName(tempfirstName);
                    System.out.println(child.get(choiceC - 1).getfName() + " has been updated.");
                } else if (choiceC2 == 2) {
                    System.out.print("Enter New Last Name: ");
                    templastName = in1.next();
                    child.get(choiceC - 1).setlastName(templastName);
                    System.out.println(child.get(choiceC - 1).getlName() + " has been updated.");
                } else if (choiceC2 == 3) {
                    System.out.print("Enter New Gender: ");
                    tempgender = in1.next();
                    child.get(choiceC - 1).setgender(tempgender);
                    System.out.println(child.get(choiceC - 1).getAge() + "has been updated.");
                } else if (choiceC2 == 4) {
                    System.out.print("Enter New Age: ");
                    tempage = in1.nextInt();
                    child.get(choiceC - 1).setAge(tempage);
                    System.out.println(child.get(choiceC - 1).getAge() + " has been updated.");
                } else if (choiceC2 == 5) {
                    displayLocation();

                    do {
                        System.out.print("Option :: ");
                        choose = sc.nextInt();

                    } while (choose < 1 || choose >= shelter.size());
                    templocation = shelter.get(choose - 1);
                    child.get(choiceC - 1).setLocation(templocation);
                    System.out.println(child.get(choiceC - 1).getLocation() + " has been updated.");
                } else if (choiceC2 == 6) {
                    System.out.print("Enter New Stay Days: ");
                    tempstayDays = in1.nextInt();
                    child.get(choiceC - 1).setStaydays(tempstayDays);
                    System.out.println(child.get(choiceC - 1).getStayDays() + " has been updated.");
                } else if (choiceC2 == 7) {
                    System.out.print("Enter New Child ID: ");
                    tempchildID = in1.next();
                    child.get(choiceC - 1).setChildID(tempchildID);
                    System.out.println(child.get(choiceC - 1).getChildID() + " has been updated.");
                } else if (choiceC2 == 8) {
                    System.out.print("Enter New Parent Name: ");
                    temppname = in1.next();
                    child.get(choiceC - 1).setpname(temppname);
                    System.out.println(child.get(choiceC - 1).getParentName() + " has been updated.");
                } else if (choiceC2 == 9) {
                    System.out.print("Enter New Orphan Status [YES/NO]: ");
                    inputorphan = in1.next();
                    if (inputorphan.equals("YES")) {
                        temporphan = true;
                    } else if (inputorphan.equals("NO")) {
                        temporphan = false;
                    }
                    child.get(choiceC - 1).setorphan(temporphan);
                    System.out.println(inputorphan + " has been updated.");
                } else if (choiceC2 == 10) {
                    System.out.println("Exiting Update.");
                } else {
                    System.out.println("Invalid Input.");
                }
            }
        }

        // update text
        saveFile();

        //clear buffer
        in1.nextLine();
        sc.nextLine();
        a1.nextLine();
        a2.nextLine();
    }

    /*
     * FUNCTION NAME : deleteHomeless
     * DESCRIPTION : delete homeless people from the list
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    static void deleteHomeless() throws Exception {

        System.out.println("<<< DELETE HOMELESS PEOPLE >>>");
        printLine();

        // validation for category
        if (adult.size() == 0 && child.size() == 0) {
            System.out.println("No Homeless People Available!");
            System.out.println();
        } else {
            System.out.println("Please select which category you want to delete");
            printLine();
            System.out.println("ADULT - A");
            System.out.println("CHILD - C");
            printLine();
            System.out.print("Option :: ");
            category = in1.next().charAt(0);

            in1.nextLine(); // clear buffer

            Clrscr();
            if (Character.toUpperCase(category) == 'A') {
                if (adult.size() == 0) {
                    System.out.println("No adult list to be deleted");
                } else {
                    int numA = 1;
                    System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s", "No",
                            "Full name", "Gender", "Age",
                            "Location", "Days stayed", "Adult ID", "Past Occupation");
                    System.out.println();
                    printLine();

                    for (int i = 0; i < adult.size(); i++) {
                        System.out.printf("[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s",
                                numA, adult.get(i).getName(), adult.get(i).getGender(),
                                adult.get(i).getAge(), adult.get(i).getLocation(),
                                adult.get(i).getStayDays(), adult.get(i).getAdultID(),
                                adult.get(i).getOccup());
                        System.out.println();

                        numA++;
                    }
                    System.out.println();
                    System.out.print("Choose Adult To Delete: ");
                    choiceA = in1.nextInt();
                    System.out.println("Adult " + adult.get(choiceA - 1).getName() + " has been deleted.");
                    adult.remove(choiceA - 1);

                    System.out.println();
                }

            } else if (Character.toUpperCase(category) == 'C') {
                if (child.size() == 0) {
                    System.out.println("No child list to be deleted");
                } else {
                    int numC = 1;
                    String childstatus = " ";

                    System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                            "No", "Full name", "Gender", "Age", "Location", "Days stayed",
                            "Child ID",
                            "Parent Name", "Orphan Status");
                    System.out.println();
                    printLine();

                    for (int i = 0; i < child.size(); i++) {
                        // verify child status
                        if (child.get(i).getOrphanStatus() == true) {
                            childstatus = "Orphan";
                        } else if (child.get(i).getOrphanStatus() == false) {
                            childstatus = "Non-orphan";
                        }
                        System.out.printf(
                                "[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                                numC, child.get(i).getName(), child.get(i).getGender(),
                                child.get(i).getAge(), child.get(i).getLocation(),
                                child.get(i).getStayDays(), child.get(i).getChildID(),
                                child.get(i).getParentName(), childstatus);
                        System.out.println();

                        numC++;
                    }
                    System.out.println();
                    System.out.print("Choose Child To Delete: ");
                    choiceC = in1.nextInt();
                    System.out.println("Child " + child.get(choiceC - 1).getName() + " has been deleted.");
                    child.remove(choiceC - 1);

                    System.out.println();
                }
            }

            // save updated text
            saveFile();

            //clear buffer
            in1.nextLine();
        }
    }

    /*
     * FUNCTION NAME : displayHomelessPeople
     * DESCRIPTION : display homeless people list
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    static void displayHomelessPeople() {
        String childstatus = " ";

        System.out.println("<<<< LIST OF REGISTERED HOMELESS PEOPLE >>>>");
        printLine();
        System.out.println("<<<< ADULT >>>>");
        printLine();

        if (adult.size() == 0) {
            System.out.println("No adult people is registered.");
        } else {
            int numA = 1;
            System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s", "No", "Full name", "Gender", "Age",
                    "Location", "Days stayed", "Adult ID", "Past Occupation");
            System.out.println();
            for (int i = 0; i < adult.size(); i++) {
                System.out.printf("[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s",
                        numA, adult.get(i).getName(), adult.get(i).getGender(),
                        adult.get(i).getAge(), adult.get(i).getLocation(),
                        adult.get(i).getStayDays(), adult.get(i).getAdultID(),
                        adult.get(i).getOccup());
                System.out.println();

                numA++;
            }
        }

        printLine();
        System.out.println("<<<< CHILD >>>>");
        printLine();

        if (child.size() == 0) {
            System.out.println("No child people is registered.");
        } else {
            int numC = 1;
            System.out.printf("%-3s %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                    "No", "Full name", "Gender", "Age", "Location", "Days stayed", "Child ID",
                    "Parent Name", "Orphan Status");
            System.out.println();

            for (int i = 0; i < child.size(); i++) {

                // verify child status
                if (child.get(i).getOrphanStatus() == true) {
                    childstatus = "Orphan";
                } else if (child.get(i).getOrphanStatus() == false) {
                    childstatus = "Non-orphan";
                }

                System.out.printf("[%-1s] %-15s %-10s %-6s %-40s %-15s %-10s %-15s %-20s",
                        numC, child.get(i).getName(), child.get(i).getGender(),
                        child.get(i).getAge(), child.get(i).getLocation(),
                        child.get(i).getStayDays(), child.get(i).getChildID(),
                        child.get(i).getParentName(), childstatus);
                System.out.println();

                numC++;
            }
        }
        printLine();
    }

    /*
     * FUNCTION NAME : ManageShelter
     * DESCRIPTION : function to manage shelter such as add, update, and delete
     * shelter
     * AUTHOR : IESKANDAR ZULQARNAIN BIN GHAZALI A20EC0046
     */
    static void ManageShelter() throws Exception {
        boolean test;
        do {
            Clrscr();
            test = false;
            System.out.println("<<< SERVICES AVAILABLE >>>");
            printLine();
            System.out.println("[1] Add Shelter");
            System.out.println("[2] Update Shelter");
            System.out.println("[3] Delete Shelter");
            System.out.println("[0] Go back to Admin Page");
            printLine();

            System.out.print("Option :: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    Clrscr();
                    AddShelter();
                    break;
                case 2:
                    Clrscr();
                    UpdateShelter();
                    break;
                case 3:
                    Clrscr();
                    DeleteShelter();
                    break;
                case 0:
                    System.out.println("Returning back to Admin Page!");
                    adminOpt = false;
                    break;
                default:
                    System.out.println("Invalid Choice! Please Select Again!");
                    adminOpt = true;
                    break;
            }
            SystemPause();
        } while (test);
    }

    /*
     * FUNCTION NAME : AddShelter
     * DESCRIPTION : function to add shelter
     * AUTHOR : IESKANDAR ZULQARNAIN BIN GHAZALI A20EC0046
     */
    static void AddShelter() throws Exception {
        Scanner input = new Scanner(System.in);
        String shelName, city, state;
        printLine();
        System.out.println("<<< ADDING NEW SHELTER >>>");
        printLine();
        System.out.print("\nEnter Shelter Name          :: ");
        shelName = input.nextLine();
        System.out.print("Enter City Name of Shelter  :: ");
        city = input.nextLine();
        System.out.print("Enter State Name of Shelter :: ");
        state = input.nextLine();
        shelter.add(new Shelter(shelName, city, state));

        System.out.println();
        System.out.println("The Shelter has successfully added!");
        saveFile();

    }

    /*
     * FUNCTION NAME : UpdateShelter
     * DESCRIPTION : function to update shelter
     * AUTHOR : IESKANDAR ZULQARNAIN BIN GHAZALI A20EC0046
     */
    static void UpdateShelter() throws Exception {
        Scanner input = new Scanner(System.in);
        String shelName, city, state;
        int choose;
        boolean test;
        do {
            test = false;
            displayLocation();
            System.out.print("Option :: ");
            choose = sc.nextInt();
            if (choose < 1 || choose > shelter.size()) {
                test = true;
                System.out.println("Invalid Shelter! Please Choose Again!");
            }

        } while (test);

        do {
            Clrscr();
            test = false;
            System.out.println("<<< UPDATE DETAILS OF SHELTER >>>");

            printLine();
            System.out.println("[1] Shelter Name");
            System.out.println("[2] City of Shelter");
            System.out.println("[3] State of Shelter");
            System.out.println("[0] Go back to Admin Page");
            printLine();

            System.out.print("Option :: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    System.out.println("Enter new Shelter Name :: ");
                    shelName = input.nextLine();
                    shelter.get(choose - 1).setshelName(shelName);
                    break;
                case 2:
                    System.out.println("Enter new City Name of Shelter :: ");
                    city = input.nextLine();
                    shelter.get(choose - 1).setshelName(city);
                    break;
                case 3:
                    System.out.println("Enter new State Name of Shelter :: ");
                    state = input.nextLine();
                    shelter.get(choose - 1).setshelName(state);
                    break;
                case 0:
                    System.out.println("Returning back to Manage Shelter Page!");
                    adminOpt = false;
                    break;
                default:
                    System.out.println("Invalid Choice! Please Select Again!");
                    adminOpt = true;
                    break;
            }
        } while (test);
        saveFile();
    }

    /*
     * FUNCTION NAME : DeleteShelter
     * DESCRIPTION : function to delete shelter
     * AUTHOR : IESKANDAR ZULQARNAIN BIN GHAZALI A20EC0046
     */
    static void DeleteShelter() throws Exception {
        int choose;
        boolean test;
        do {
            test = false;
            displayLocation();
            System.out.print("Option :: ");
            choose = sc.nextInt();
            if (choose < 1 || choose > shelter.size()) {
                test = true;
                System.out.println("Invalid Shelter! Please Choose Again!");
            }

        } while (test);
        shelter.remove(choose - 1);
        System.out.println("The Shelter is successfully Remove!");
        saveFile();
    }

    /*
     * FUNCTION NAME : ManageVolunteer
     * DESCRIPTION : function to manage volunteer such as update, and delete
     * Volunteer Details
     * AUTHOR : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK A20EC0079
     */
    static void manageVolunteer() throws Exception {
        boolean test;
        do {
            Clrscr();
            test = false;
            System.out.println("<<< SERVICES AVAILABLE >>>");
            printLine();
            System.out.println("[1] Update Volunteer Details");
            System.out.println("[2] Delete Volunteer");
            System.out.println("[0] Go back to Admin Page");
            printLine();

            System.out.print("Option :: ");
            choose = sc.nextInt();
            switch (choose) {
                case 1:
                    UpdateVolunteer();
                    SystemPause();
                    break;
                case 2:
                    DeleteVolunteer();
                    SystemPause();
                    break;
                case 0:
                    System.out.println("Returning back to Admin Page!");
                    adminOpt = false;
                    break;
                default:
                    System.out.println("Invalid Choice! Please Select Again!");
                    adminOpt = true;
                    break;
            }
        } while (test);
    }

    /*
     * FUNCTION NAME : UpdateVolunteer
     * DESCRIPTION : function to update volunteer details
     * AUTHOR : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK A20EC0079
     */
    static void UpdateVolunteer() throws Exception {
        Scanner input = new Scanner(System.in);
        String username, password;
        Shelter location;
        int choose, vollOpt, shelOpt;
        boolean test;
        do {
            test = false;
            System.out.println();
            displayVolunteer();
            System.out.print("Option :: ");
            choose = sc.nextInt();
            if (choose < 1 || choose > volunteer.size()) {
                test = true;
                System.out.println("Invalid Volunteer! Please Choose Again!");
            }

        } while (test);

        do {
            Clrscr();
            test = false;
            System.out.println();
            System.out.println("<<< UPDATE DETAILS OF VOLUNTEER >>>");

            printLine();
            System.out.println("[1] Username of Volunteer");
            System.out.println("[2] Password of Volunteer");
            System.out.println("[3] Work Location of Volunteer (Shelter)");
            System.out.println("[0] Go back to Admin Page");
            printLine();
            System.out.print("Option :: ");
            vollOpt = sc.nextInt();
            System.out.println();
            switch (vollOpt) {
                case 1:
                    System.out.print("Enter new Volunteer Username :: ");
                    username = input.nextLine();
                    volunteer.get(choose - 1).setvolunteerID(username);
                    break;
                case 2:
                    System.out.print("Enter new Volunteer Password:: ");
                    password = input.nextLine();
                    volunteer.get(choose - 1).setvolunteerPass(password);
                    break;
                case 3:
                    displayLocation();
                    System.out.println();
                    do {
                        test = false;
                        System.out.print("Option :: ");
                        shelOpt = sc.nextInt();
                        if (shelOpt < 1 || shelOpt > shelter.size()) {
                            test = true;
                            System.out.println("Invalid Shelter! Please Choose Again!");
                        }

                    } while (test);
                    location = shelter.get(shelOpt - 1);
                    volunteer.get(choose - 1).setworkingLocation(location);
                    break;
                case 0:
                    System.out.println("Returning back to Manage Admin Page!");
                    adminOpt = false;
                    break;
                default:
                    System.out.println("Invalid Choice! Please Select Again!");
                    adminOpt = true;
                    break;
            }
        } while (test);
        saveFile();
    }

    /*
     * FUNCTION NAME : DeleteVolunteer
     * DESCRIPTION : function to delete volunteer
     * AUTHOR : MOHAMAD HAZIQ ZIKRY BIN MOHAMMAD RAZAK A20EC0079
     */
    static void DeleteVolunteer() throws Exception {
        int choose;
        boolean test;
        do {
            test = false;
            System.out.println();
            displayVolunteer();
            System.out.print("Option :: ");
            choose = sc.nextInt();
            if (choose < 1 || choose > volunteer.size()) {
                test = true;
                System.out.println();
                System.out.println("Invalid Volunteer! Please Choose Again!");
            }

        } while (test);
        volunteer.remove(choose - 1);
        System.out.println("The Volunteer is successfully Remove!");
        saveFile();
    }

    /*
     * FUNCTION NAME : printLine
     * DESCRIPTION : to print lines
     * AUTHOR : Brendan Dylan Gampa anak Joseph Dusit@Dusit A20EC0021
     */
    public static void printLine() {
        for (int i = 0; i < 150; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    /*
     * FUNCTION NAME : Clrscr
     * DESCRIPTION : function to clear screen
     * AUTHOR : ALL TEAM MEMBERS
     */
    public static void Clrscr() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {
        }
    }

    /*
     * FUNCTION NAME : SystemPause
     * DESCRIPTION : function to pause commands
     * AUTHOR : ALL TEAM MEMBERS
     */
    public static void SystemPause() {
        System.out.print("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
        }
    }

    /*
     * FUNCTION NAME : readFile
     * DESCRIPTION : Function to read various text files
     * AUTHOR : ALL TEAM MEMBERS
     */
    static void readFile() throws Exception {

        // admin details
        String temp;
        Scanner inputfile = new Scanner(new File("Admin.txt"));
        String fname, lname, gender, username, pass;
        int age;
        Shelter Location = new Shelter();
        String[] str;
        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            fname = str[0];
            lname = str[1];
            gender = str[2];
            age = Integer.parseInt(str[3]);
            username = str[4];
            pass = str[5];
            admin.add(new Admin(fname, lname, gender, age, username, pass));
        }
        while (inputfile.hasNextLine())
            ;

        // shelter details
        inputfile = new Scanner(new File("Shelter.txt"));
        String shelName, city, state;
        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            shelName = str[0];
            city = str[1];
            state = str[2];
            shelter.add(new Shelter(shelName, city, state));
        }

        // volunteer details
        inputfile = new Scanner(new File("Volunteer.txt"));
        String workloca;
        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            fname = str[0];
            lname = str[1];
            gender = str[2];
            age = Integer.parseInt(str[3]);
            username = str[4];
            pass = str[5];
            workloca = str[6];
            for (int i = 0; i < shelter.size(); i++) {
                if (workloca.equals(shelter.get(i).getshelName())) {
                    Location = shelter.get(i);
                }
            }
            volunteer.add(new Volunteer(fname, lname, gender, age, username, pass, Location));
        }
        while (inputfile.hasNextLine())
            ;

        // adult homeless details
        inputfile = new Scanner(new File("Adult.txt"));
        String homelocc, adultID, pastOcc;
        int stayingDays;

        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            fname = str[0];
            lname = str[1];
            gender = str[2];
            age = Integer.parseInt(str[3]);
            homelocc = str[4];
            for (int i = 0; i < shelter.size(); i++) {
                if (homelocc.equals(shelter.get(i).getshelName())) {
                    Location = shelter.get(i);
                }
            }
            stayingDays = Integer.parseInt(str[5]);
            adultID = str[6];
            pastOcc = str[7];
            adult.add(new AdultLess(fname, lname, gender, age, Location, stayingDays, adultID, pastOcc));
        }

        // child homeless details
        inputfile = new Scanner(new File("Child.txt"));
        String childID, parentName;
        boolean orphanStatus;
        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            fname = str[0];
            lname = str[1];
            gender = str[2];
            age = Integer.parseInt(str[3]);
            homelocc = str[4];
            for (int i = 0; i < shelter.size(); i++) {
                if (homelocc.equals(shelter.get(i).getshelName())) {
                    Location = shelter.get(i);
                }
            }
            stayingDays = Integer.parseInt(str[5]);
            childID = str[6];
            parentName = str[7];
            orphanStatus = Boolean.parseBoolean(str[8]);
            child.add(
                    new ChildLess(fname, lname, gender, age, Location, stayingDays, childID, parentName, orphanStatus));
        }

        // basic neeed details
        inputfile = new Scanner(new File("basicNeeds.txt"));
        String bname, bdesc;
        char btype;
        int bquant;
        while (inputfile.hasNextLine()) {
            temp = inputfile.nextLine();
            str = temp.split(", ");

            bname = str[0];
            bquant = Integer.parseInt(str[1]);
            bdesc = str[2];
            btype = str[3].charAt(0);
            bNeeds.add(new BasicNeeds(bname, bquant, bdesc, btype));
        }

    }

    /*
     * FUNCTION NAME : saveFile
     * DESCRIPTION : function to save various text files
     * AUTHOR : ALL TEAM MEMBERS
     */
    static void saveFile() throws Exception {

        // admin output file
        FileWriter outputFile = new FileWriter("Admin.txt");
        for (int i = 0; i < admin.size(); i++) {
            outputFile.write(
                    admin.get(i).getSaveName() + ", " + admin.get(i).getGender() + ", " + admin.get(i).getAge() + ", "
                            + admin.get(i).getAdminID() + ", " + admin.get(i).getAdminPass() + "\n");
        }
        outputFile.close();

        // volunteer output file
        outputFile = new FileWriter("Volunteer.txt");
        for (int i = 0; i < volunteer.size(); i++) {
            outputFile.write(volunteer.get(i).getSaveName() + ", " + volunteer.get(i).getGender() + ", "
                    + volunteer.get(i).getAge() + ", "
                    + volunteer.get(i).getVolunteerID() + ", " + volunteer.get(i).getVolunteerPass() + ", "
                    + volunteer.get(i).getWorkingLocation() + "\n");
        }
        outputFile.close();

        // adult output file
        outputFile = new FileWriter("Adult.txt");
        for (int i = 0; i < adult.size(); i++) {
            outputFile.write(adult.get(i).getSaveName() + ", " + adult.get(i).getGender() + ", "
                    + adult.get(i).getAge() + ", " + adult.get(i).getLocation() + ", "
                    + adult.get(i).getStayDays() + ", "
                    + adult.get(i).getAdultID() + ", " + adult.get(i).getOccup() + "\n");
        }
        outputFile.close();

        // child output file
        outputFile = new FileWriter("Child.txt");
        for (int i = 0; i < child.size(); i++) {
            outputFile.write(child.get(i).getSaveName() + ", " + child.get(i).getGender() + ", "
                    + child.get(i).getAge() + ", " + child.get(i).getLocation() + ", "
                    + child.get(i).getStayDays() + ", "
                    + child.get(i).getChildID() + ", " + child.get(i).getParentName() + ", "
                    + child.get(i).getOrphanStatus() + "\n");
        }
        outputFile.close();

        // basic need output file
        outputFile = new FileWriter("basicNeeds.txt");
        for (int i = 0; i < bNeeds.size(); i++) {
            outputFile.write(bNeeds.get(i).getBasicName() + ", " + bNeeds.get(i).getQuantity() + ", "
                    + bNeeds.get(i).getDesc() + ", "
                    + bNeeds.get(i).getType() + "\n");
        }
        outputFile.close();

        // shelter output file
        outputFile = new FileWriter("Shelter.txt");
        for (int i = 0; i < shelter.size(); i++) {
            outputFile.write(shelter.get(i).getshelName() + ", " + shelter.get(i).getCity() + ", "
                    + shelter.get(i).getState() + "\n");
        }
        outputFile.close();
    }

}
