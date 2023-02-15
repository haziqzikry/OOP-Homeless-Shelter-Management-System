/*
    CLASS NAME      : BasicNeeds
    DESCRIPTION     : This class stores basic information of basic needs and implements the Balance class
    SUB-FUNCTION(s) : getBasicName(), getQuantity(), getDesc(), getType(), setBasicName(), setQuantity(), setDesc(), setBasicType(), decrementQuantity(), void balancecheck(), void setDescription(), void setPrice 
    AVAILABLE 
    AUTHOR          : AUM JEEVAN A/L AUM NIRANGKAR

*/
public class BasicNeeds implements Balance {
    private String basicName;
    private int quantity;
    private String desc;
    private char type; // m-meal, c-clothes, h-healthcare

    BasicNeeds(String basicName, int quantity, String desc, char type) {
        this.basicName = basicName;
        this.quantity = quantity;
        this.desc = desc;
        this.type = type;
    }

    // accessors
    String getBasicName() {
        return basicName;
    }

    int getQuantity() {
        return quantity;
    }

    String getDesc() {
        return desc;
    }

    char getType() {
        return type;
    }

    // mutators
    void setBasicName(String basicName) {
        this.basicName = basicName;
    }

    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    void setDesc(String desc) {
        this.desc = desc;
    }

    void setBasicType(char type) {
        this.type = type;
    }

    void decrementQuantity() { // decrease basic quantity by 1
        this.quantity--;
    }

    public void balancecheck() { // checking the balance of basic needs
        if (this.quantity == 0) {
            System.out.println("You are out of " + this.basicName);
        } else {
            System.out.println("You have " + this.quantity + " " + this.basicName + " left");
        }
    }

    public void setDescription(String next) {
    }

    public void setPrice(double nextDouble) {
    }

}
