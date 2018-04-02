package util;

import java.math.BigDecimal;
import java.util.Scanner;

//todo: input validatie
public class InputUtil {
    static Scanner input = new Scanner(System.in);
    public static int getIntInput(){
            return input.nextInt();
    }

    public static String getStringInput(){
        String s = input.next();
        if (s == null){
            System.out.print("Please enter a String: ");
            return getStringInput();
        } else {
            return s;
        }
    }

    //todo change to BigDecimal
    public static BigDecimal getBigDecimalInput(){
        return input.nextBigDecimal();
    }
}
