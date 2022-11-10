package au.edu.jcu.cp3406.converter;

public class Convert {
/*
This convert the input value to binary, octal, decimal, and hexadecimal
 */
    String binary = null, octal = null, decimal = null, hexadecimal = null;


    /*
    First convert the input to decimal value then convert it to each base value by to string methods
    and return the base value.
     */
    String convertToBinary(String input, int k) {
        decimal = Integer.valueOf(input, k).toString(); // convert to decimal
        binary = Integer.toBinaryString(Integer.parseInt(decimal));
        return binary;
    }

    String convertToOctal(String input, int k) {
        decimal = Integer.valueOf(input, k).toString(); // convert to decimal
        octal = Integer.toOctalString(Integer.parseInt(decimal));
        return octal;
    }

    String convertToDecimal(String input, int k) {
        decimal = Integer.valueOf(input, k).toString(); // convert to decimal
        return decimal;
    }

    String convertToHexadecimal(String input, int k) {
        decimal = Integer.valueOf(input, k).toString(); // convert to decimal
        hexadecimal = Integer.toHexString(Integer.parseInt(decimal));
        return hexadecimal;
    }
}
