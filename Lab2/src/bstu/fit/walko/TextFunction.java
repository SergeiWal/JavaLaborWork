package bstu.fit.walko;

import java.util.Scanner;

public class TextFunction {

    private int value;
    public  int sint;
    public void setValue(int value) {
        this.value = value;
    }

    public TextFunction(int value) {
        this.value = value;
    }

    //TODO TextFunction methods
    public String getValue()
    {
        return getString();
    }

    private String getString() {
        Scanner in =new Scanner(System.in);
        String message = in.nextLine();
        return message;
    }

    public void onCreate()
    {
        for (int j = 0; j < 5; j++) {
            System.out.println("Counter" + j);
        }
    }

}
