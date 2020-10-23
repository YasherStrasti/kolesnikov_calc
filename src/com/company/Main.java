package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class Conversions
{
    public static boolean is_roman(String str)
    {
        char[] romans = {'I', 'V', 'X'};

        for (int i = 0; i < str.length(); i++)
        {
            if (Arrays.binarySearch(romans, str.charAt(i)) < 0)
                return (false);
        }
        return (true);
    }

    public static String int_to_roman(int number)
    {
        String[] romans_units = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] romans_dozens = {"X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String result = "";
        int temp = 0;

        if (number == 100)
            return ("C");
        else
        {
            temp = number / 10;
            if (temp >= 1)
            {
                result = result + romans_dozens[temp - 1];
                number = number % 10;
            }
            if (number != 0)
                result = result + romans_units[number - 1];
        }
        return (result);
    }

    public static int roman_to_int(String roman)
    {
        int result = 0;
        ArrayList<Integer> ints_arr = new ArrayList<Integer>();

        for (int i = 0; i < roman.length(); i++)
        {
            int index = 0;
            if (roman.charAt(i) == 'I')
                ints_arr.add(1);
            else if (roman.charAt(i) == 'V')
                ints_arr.add(5);
            else if (roman.charAt(i) == 'X')
                ints_arr.add(10);
        }
        for (int i = 0; i < ints_arr.size(); i++) {

            if (i + 1 < ints_arr.size()) {
                if (ints_arr.get(i) >= ints_arr.get(i + 1)) {
                    result = result + ints_arr.get(i);
                }
                else {

                    result = result + ints_arr.get(i + 1) - ints_arr.get(i);
                    i++;
                }
            }
            else
            {
                result = result + ints_arr.get(i);
            }
        }
        return (result);
    }
}

class Operations
{
    public static void print_error(int error)
    {
        if (error == 1)
        {
            System.out.println("Incorrect number of operands");
            return ;
        }
        else if (error == 2)
        {
            System.out.println("Invalid operand");
            return ;
        }
        else if (error == 3)
        {
            System.out.println("Both arabian and roman numbers");
            return ;
        }
        else if (error == 4)
        {
            System.out.println("Incorrect input");
            return ;
        }
    }

    public static boolean is_roman_arabian(String number)
    {
        for (int i = 0; i < number.length(); i++)
        {
            if ((number.charAt(i) >= '0' && number.charAt(i) <= '9') || Conversions.is_roman(number))
                return (true);
        }
        return (false);
    }

    public static int check_correct(String[] args)
    {
        char[] operands = {'+', '-', '*', '/'};
        args[0] = args[0].toUpperCase();
        args[2] = args[2].toUpperCase();
        if (args.length != 3)
            return (1);
        if (!is_roman_arabian(args[0]))
            return (4);
        if (!is_roman_arabian(args[2]))
            return (4);
        if (args[1].length() != 1)
            return (2);
        if (Arrays.binarySearch(operands, args[1].charAt(0)) < 0)
        {
            if (args[1].charAt(0) == '*')
                return (0);
            else
                return (2);
        }
        return (0);
    }

    public static int do_calc(int op_1, int op_2, String operand)
    {
        if (operand.equals("+"))
            return (op_1 + op_2);
        else if (operand.equals("-"))
            return (op_1 - op_2);
        else if (operand.equals("*"))
            return (op_1 * op_2);
        else if (operand.equals("/"))
        {
            if (op_2 != 0)
                return (op_1 / op_2);
            else
                return (0);
        }
        return (0);
    }
}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String input_str = input.nextLine();

        int result = 0;
        String[] vars = input_str.split(" ");
        int error = 0;
        if ((error = Operations.check_correct(vars)) != 0)
        {
            Operations.print_error(error);
            return ;
        }
        vars[0] = vars[0].toUpperCase();
        vars[2] = vars[2].toUpperCase();
        int op_1 = 0;
        int op_2 = 0;
        boolean is_romanian = false;
        if (Conversions.is_roman(vars[0]) && Conversions.is_roman(vars[2]))
        {
            is_romanian = true;
            op_1 = Conversions.roman_to_int(vars[0]);
            op_2 = Conversions.roman_to_int(vars[2]);

        }
        else if (Conversions.is_roman(vars[0]) || Conversions.is_roman(vars[2]))
        {
            Operations.print_error(3);
            return ;
        }
        else
        {
            op_1 = Integer.parseInt(vars[0]);
            op_2 = Integer.parseInt(vars[2]);
        }
        if (op_1 > 10 || op_2 > 10 || op_1 < 1 || op_2 < 1)
        {
            Operations.print_error(4);
            return ;
        }
        result = Operations.do_calc(op_1, op_2, vars[1]);
        if (is_romanian)
            System.out.println(Conversions.int_to_roman(result));
        else
            System.out.println(result);
        return ;
    }
}
