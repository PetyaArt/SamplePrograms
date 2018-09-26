package com.company;

import java.util.Scanner;

import static java.lang.Math.*;

public class Main {

    private static double[] points;
    private static double[] values;
    private static double step;

    public static void main(String[] args) {
        Main main = new Main();
        double[] nArray = main.createArray();
        double[] nArray2 = main.fun(nArray);
        double[] mArray = main.createArray(nArray.length);
        double[] mArray2 = main.fun(mArray);
        double[] mArraypN = pN(main, nArray, nArray2, mArray);
        double[] mArraypl = new double[mArray.length];
        for (int i = 0; i < mArray.length; i++) {
            mArraypl[i] = pl(mArray[i], nArray, nArray2);
        }

        System.out.println();
        for (int i = 0; i < mArray.length; i++) {
            if (i == 0) {
                String formattedDouble = String.format("%.8f", mArray[i]);
                System.out.print(formattedDouble + "        ");
                String formattedDouble1 = String.format("%.8f", mArray2[i]);
                System.out.print(formattedDouble1 + "        ");
                String formattedDouble2 = String.format("%.8f", mArraypN[i]);
                System.out.print(formattedDouble2 + "        ");
                String formattedDouble3 = String.format("%.8f", mArraypl[i]);
                System.out.print(formattedDouble3 + "        ");
                System.out.println();
            } else {
                String formattedDouble = String.format("%.8f", mArray[i]);
                System.out.print(formattedDouble + "        ");
                String formattedDouble1 = String.format("%.8f", mArray2[i]);
                System.out.print(formattedDouble1 + "        ");
                String formattedDouble2 = String.format("%.8f", mArraypN[i]);
                System.out.print(formattedDouble2 + "        ");
                String formattedDouble3 = String.format("%.8f", mArraypl[i]);
                System.out.print(formattedDouble3 + "        ");
                System.out.println();
            }
        }
    }

    private static double[] pN(Main main, double[] nArray, double[] nArray2, double[] mArray) {
        points = nArray;
        values = nArray2;
        step = points[1] - points[0];
        double[] newArray = new double[mArray.length];
        for (int i = 0; i < mArray.length; i++) {
            newArray[i] = main.def(mArray[i]);
        }

        return newArray;
    }

    private double[] createArray() {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        double[] array = new double[size + 1];
        array[0] = -1;
        double length = 2;
        double counter = length / size;
        double buffer = -1;
        for (int i = 1; i < array.length; i++) {
            buffer = buffer + counter;
            array[i] = buffer;
        }
        return array;
    }

    private double[] createArray(int N) {
        double[] array = new double[N * 2 + 1];
        array[0] = -1;
        double length = 2;
        double counter = length / (N * 2);
        double buffer = -1;
        for (int i = 1; i < array.length; i++) {
            buffer = buffer + counter;
            array[i] = buffer;
        }

        return array;
    }

    private double[] fun(double array[]) {
        double[] arrayBuffer = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            arrayBuffer[i] = (pow(array[i], 2) + 2) + sin(pow(array[i], 2) + 2);
        }
        return arrayBuffer;
    }

    private double def(double x) {

        double n = points.length;

        double result = cj(0);
        for (int j = 1; j < n; j++)
        {
            double something = cj(j);
            for (int k = 0; k < j; k++)
                something *= x - points[k];
            result += something;
        }
        return result;
    }

    double cj(int j) {
        if (j == 0)
            return values[0];
        double delta = delta(j, j);
        return delta / (factorial(j) * Math.pow(step, j));
    }

    double delta(int p, int i) {
        if (p == 1)
            return values[i] - values[i - 1];
        return delta(p - 1, i) - delta(p - 1, i - 1);
    }

    double factorial(double value) {
        if (value == 0)
            return 1;
        return value * factorial(value - 1);
    }


    static double pl(double x, double[] xv, double[] yv){
        float sum = 0;
        for(int i = 0; i < xv.length; i++){
            float mul = 1;
            for(int j = 0; j < xv.length; j++){
                if(i != j){
                    mul *= (x-xv[j])/(xv[i]-xv[j]);
                }
            }
            sum += yv[i]*mul;
        }
        return sum;
    }
}
