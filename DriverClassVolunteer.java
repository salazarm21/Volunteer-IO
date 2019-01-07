package volunteerio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Marina Salazar 
 * Computer Science 180 
 * Project 3
 */
public class DriverClassVolunteer {
//Variables below are used throughout program
    final static int NUMBER_OF_VOLUNTEERS = 25;
    public static final File DATA_FILE = new File("volunteerFile");
    final static int WEEKSINYEAR = 52;
    public static int weekAvgPoints;
    public static int[][] hours = new int[NUMBER_OF_VOLUNTEERS][WEEKSINYEAR];
    public static String[] names = new String[NUMBER_OF_VOLUNTEERS];
    public static double[] hoursPerWeekAverage;
    public static double[] volunteerPoints;

    public static void main(String[] args) throws FileNotFoundException {
        //Displays the introduction paragraph
        System.out.println("The purpose of this program is to find the volunteer of the year. A data\n"
                + "file is read in which has each volunteer's last name followed by the number of hours that \n"
                + "volunteer worked each week for the data collection perid. The average number of hours is \n"
                + "computed for each week and if the volunteer exceeds that average they are awarded an addition \n"
                + "one hour for that week. The volunteer for the year is the person with the greatest number of hours.");
        //Methods
        readFile();
        calculateAvgHoursWorkedPerWeek(hours);
        calculatePointsForHoursWorked();
        pointsForAboveAvgHours(hoursPerWeekAverage, volunteerPoints);
        outputWinner(volunteerPoints);

    }
//read the data from the file and inputs it into the array
    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(DATA_FILE);
        String Data = "";
//reads data in file
        for (int row = 0; row < hours.length; row++) {
            names[row] = sc.next();
            for (int col = 0; col < hours[row].length; col++) {
                hours[row][col] = sc.nextInt();
            }
        }

    }
//Calculates the average hours worked per week for individuals and group
    public static double[] calculateAvgHoursWorkedPerWeek(int[][] hours) {
        //loops thru array data to sum values and calculate average for each week
        hoursPerWeekAverage = new double[WEEKSINYEAR];
        double totalHoursWorked;
        int rowlength;
        int collength;
        for (collength = 0; collength < WEEKSINYEAR; collength++) {
            totalHoursWorked = 0;
            for (int[] hoursVolunteered : hours) {
                totalHoursWorked += hoursVolunteered[collength];

                hoursPerWeekAverage[collength] = totalHoursWorked / NUMBER_OF_VOLUNTEERS;
            }
        }
        return hoursPerWeekAverage;
    }
//calculates the points earned for hours worked
    public static double[] calculatePointsForHoursWorked() {
        volunteerPoints = new double[NUMBER_OF_VOLUNTEERS];
        for (int row = 0; row < hours.length; row++) {
            int VolunteerTotalHours = 0;

            for (int col = 0; col < hours[row].length; col++) {
                VolunteerTotalHours += hours[row][col];
            }
            volunteerPoints[row] = VolunteerTotalHours;
        }
        return volunteerPoints;
    }
//gives points to people who work longer than the average hours worked for that week
    public static double[] pointsForAboveAvgHours(double[] hoursPerWeekAverage, double[] volunteerPoints) {

        for (int row = 0; row < NUMBER_OF_VOLUNTEERS; row++) {
            for (int col = 0; col < WEEKSINYEAR; col++) {
                if (hours[row][col] > hoursPerWeekAverage[col]) {
                    volunteerPoints[row] = volunteerPoints[row] + 1;

                }
            }

        }
        return volunteerPoints;

    }
//Outputs the winner who had the most points
    public static double outputWinner(double[] volunteerPoints) {
        double FinalPoints = volunteerPoints[0];
        int winner = 0;
        for (int i = 0; i < volunteerPoints.length; i++) {
            if (volunteerPoints[i] > FinalPoints) {
                FinalPoints = volunteerPoints[i];
                winner = i;
            }
        }
        //displays winner
        System.out.println("\nThe volunteer of the year is: " + names[winner] + " ,");
        System.out.println("who had " + FinalPoints + " volunteer points for the year.");

        return FinalPoints;
    }
}
