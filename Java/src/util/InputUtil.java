package util;

import java.math.BigDecimal;
import java.util.Scanner;

//todo: input validatie
public class InputUtil {
    private static Scanner input = new Scanner(System.in);
    public static Integer getIntInput(){
        try {
        return Integer.parseInt(input.nextLine());
        } catch (NumberFormatException ex) {
            System.out.print("Wrong input, please enter a number: ");
            return getIntInput();
        }
    }


    public static String getStringInput(){
        String s = input.nextLine();

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
