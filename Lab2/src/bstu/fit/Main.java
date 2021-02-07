package bstu.fit;

import bstu.fit.walko.TextFunction;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        //TODO new method

        TextFunction txtFunc = new TextFunction(123);
        System.out.println(txtFunc.getValue());

        for (int i=0; i<9; i++)
            if (i > -1)
                if (i < 10)
                    System.out.println(i);

    //
    txtFunc.onCreate();
    }
}
