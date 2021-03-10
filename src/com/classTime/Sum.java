package com.classTime;

public class Sum {

    public static void main(String[] args) {
        int sum = 0;

        for (int i = 0; i < args.length; i ++) {
            int value = Integer.parseInt(args[i]);

            sum += value;
            if (i == args.length - 1) {
                System.out.print(value);
            } else {
                System.out.print(value + " + ");
            }
        }

        System.out.println(" = " + sum);
    }

}
