package ibf2021.d12.workshop12.model;

import java.io.Serializable;

public class Generate implements Serializable{
    // serialization is writing and saving a file
    // ensures that jdk can identify the object when it is streamed
    // Serialization should be implmented for Java objects that is streamed through a network
    private int numberVal;
    
    public void setNumberVal(int numberVal) {
        this.numberVal = numberVal;
    }

    public int getNumberVal() {
        return this.numberVal;
    }

}
