/*
 *GravityCalculator.java
 *
 * Version:
 *     $Id$
 *
 * Revisions:
 *     $Log$
 */
/**
 * This program determines the distance travelled and the current velocity of
 * the object thrown under the influence of the gravitational constant of the
 * planet. Here, the user has the choice of selecting from 2 planets with
 * different constants : earth and mars.
 *
 * @author      Suchit Nandal sn1566@rit.edu
 * @author      Ninad Godambe ng9810@rit.edu
 */

public class GravityCalculator {
    /**
     * @param gravity - Gravity constant of the planet.
     * @param time - The input time given.
     * @return - Returns the distance travelled by the object.
     * This method is used to calculate the distance travelled by an object
     * travelling for a specified amount of time.
     */
    public static double distance(double gravity, double time) {
        double length;
        length = 0.5 * gravity * time * time;
        return length;
    }
    /**
     * @param gravity - Gravity constant of the planet.
     * @param time - The input time given.
     * @return - Returns the current velocity of the object.
     * This method calculates the velocity of the same object under the same
     * amount of time.
     */
    public static double velocity(double gravity, double time) {
        double velocity;
        velocity = gravity * time;
        return velocity;
    }

    /**
     * @param args - Planet name -- Time
     * The driver method for the other methods.
     */
    public static void main(String args[]) {
        double earth = 9.807;
        double mars = 3.711;
        double gravity = 0;

        //Checks for the length of arguments.
        if (args.length == 2) {
            //Checks if the words entered are earth or mars. If not,
            // it displays an error message
            if (args[0].equalsIgnoreCase("earth") ||
                    args[0].equalsIgnoreCase("mars")) {
                // If the argument entered is earth, then the program selects
                // the gravity constant of earth for further calculations.
                if (args[0].equalsIgnoreCase("earth")) {
                    gravity = earth;
                }
                // If the argument entered is earth, then the program selects
                // the gravity constant of earth for further calculations.
                else if (args[0].equalsIgnoreCase("mars")) {
                    gravity = mars;
                }

                //Converting argument to double.
                double time = Double.parseDouble(args[1]);
                //Printing the distance travelled by the object.
                System.out.println("The objects position after " + time +
                        " seconds is  " +
                        Math.round(distance(gravity, time) * 100.0) / 100.0 +
                        " m from starting position.");
                //Printing the current velocity of the object.
                System.out.println("Current velocity is "
                        + Math.round(velocity(gravity, time) * 100.0) / 100.0 +
                        " m/s");
            }
            else {
                System.out.println("Incorrect Planet Specified !");
            }
        }

        //If argument length is not two then print the correct format.
        else {
            System.out.println("Incorrect Input Format");
            System.exit(0);
        }
    }
}