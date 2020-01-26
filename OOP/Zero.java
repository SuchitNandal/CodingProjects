/*
 * Zero.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * Given a set of any integers, this program determines if there exists any such
 * subset whose sum equals to zero. Once any single such set is found, the
 * condition is satisfied and the combination or the subset is printed.
 *
 * @author Suchit Nandal sn1566@rit.edu
 */

import java.util.Arrays;

public class Zero {
    static int[] set1 = {1, 2, 3, 5, 1, 3};
    static int[] set2 = {1, 3, 5, -9};

    /**
     * @param Set1 - Input a set of integers.
     * This method is used to check if any such subset exists in the given set,
     * whose sum equals to 0.
     */
    public static void check(int[] Set1) {
        System.out.println("Checking for subset " +
                Arrays.toString(Set1));
        //Length of input set being checked.
        int size = Set1.length;
        int upperlimit = (int) Math.pow(2, size);
        int found = 0;
        //For loop for creating maximum number of subsets.
        for (int i = 0; i < upperlimit; i++) {
            int sum = 0;
            int number = 0;
            //For loop of length of each subset.
            for (int j = 0; j < Set1.length; j++) {
                if ((i & (1 << j)) > 0) {
                    sum += Set1[j];
                    number += 1;
                }
                if (found == 1) break;
            }
            //If sum is zero and number of element is greater then 0 then print.
            if (sum == 0 & number > 0) {
                System.out.print("Found a subset that sums to zero : ");
                found = 1;
                //Printing the subset.
                for (int j = 0; j < Set1.length; j++) {
                    if ((i & (1 << j)) > 0) {
                        System.out.print(Set1[j] + " ");
                    }
                }
                System.out.println();
            }
        }
        //If no subset is found, then print Unable to find subset.
        if (found == 0)
            System.out.println("Unable to find subset.");

    }

    /**
     * @param args
     * The main driver method that runs the check method on the given sets.
     */
    public static void main(String args[]) {
        check(set1);
        check(set2);

    }
}

    public static int Calculation(String first, String second) {


        for (int i = first.length(); i > 0; i--) {
           if (Character.toString(first[i-1]).equals("1")){
               sum1 +=(int) Math.pow(2, first.length()-i);
           }
        }

        for (int i = second.length(); i > 0; i--) {
            if (Character.toString(second[i-1]).equals("1")){
                sum1 +=(int) Math.pow(2, second.length()-i);
            }
        }

        int sum = sum1+sum2;
        System.out.println("Adding" +sum1+" "+sum2+);
        System.out.println("Sum in Decimal = "+sum);
        return sum;
    }
