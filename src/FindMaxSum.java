import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Furkan on 16.03.2017.
 */
public class FindMaxSum {

    public static final int ROWSIZE = 15;
    public static final int COLUMNSIZE = 15;

    public static int[][] numbersArray = new int[ROWSIZE][COLUMNSIZE];

    /**
     * Wrapper method
     */
    public static int findMaxSum(String fileName, ArrayList<Location> path) {

        readNumbersFromFile(fileName, numbersArray);

        if(isPrime(numbersArray[0][0])){
            return 0;
        }else{
            return findMaxSumRec(0, 0, ROWSIZE, path);
        }

    }

    /**
     * Finds maximum sum number and points its way
     *
     * @param x    row index
     * @param y    column index
     * @param size row size
     * @return maximum sum value according to problem rules
     */
    private static int findMaxSumRec(int x, int y, int size, ArrayList<Location> path) {

        int totalLeft = 0, totalRight = 0;

        ArrayList<Location> tempListLeft = new ArrayList<>();
        ArrayList<Location> tempListRight = new ArrayList<>();

        if (x >= size) {
            return 0;
        }else if(x == (size -1)){
            path.add(new Location(x, y, numbersArray[x][y]));
            return numbersArray[x][y];
        }

        if (!isPrime(numbersArray[x + 1][y])) {
            totalRight = findMaxSumRec(x + 1, y, size, tempListRight);
        }

        if (!isPrime(numbersArray[x + 1][y + 1])) {
            totalLeft = findMaxSumRec(x + 1, y + 1, size, tempListLeft);
        }

        // backtrace
        if (totalLeft > totalRight) {
            path.addAll(tempListLeft);
            path.add(new Location(x, y, numbersArray[x][y]));
            return totalLeft + numbersArray[x][y];
        } else {
            path.addAll(tempListRight);
            path.add(new Location(x, y, numbersArray[x][y]));
            return totalRight + numbersArray[x][y];
        }

    }

    /**
     * Checks whether number is prime
     *
     * @param number input namber
     * @return true or false
     */
    private static boolean isPrime(int number) {

        for (int i = 2; i < number; ++i) {
            if ((number % i) == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Reads number from given file and stores them into numbersArray
     * @param fileName file name
     * @param numbersArray two dimensional numbers array
     */
    private static void readNumbersFromFile(String fileName, int[][] numbersArray) {

        try {

            InputStream inputStream = new FileInputStream(fileName);

            Scanner scanner = new Scanner(inputStream);

            int indexCounter = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");
                for (int i = 0; i < numbers.length; ++i) {
                    numbersArray[indexCounter][i] = Integer.parseInt(numbers[i]);
                }

                ++indexCounter;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Data structure to keep way points
     */
    private static class Location {
        private int x; // x coordinate of number
        private int y; // y coordinate of number
        private int number; // number

        public Location(int x, int y, int number) {
            this.x = x;
            this.y = y;
            this.number = number;
        }

        public int getX(){return x;}
        public int getY(){return y;}
        public int getNumber(){return number;}
    }


    public static void main(String args[]) {

        ArrayList<Location> path = new ArrayList<>();

        System.out.printf("\n#### Value of Maximum Sum \n\n%d", findMaxSum("test.txt", path));

        System.out.println("\n\n#### Path of Maximum Sum \n");
        for (int i = path.size() - 1; i >= 0; --i) {
            System.out.print(path.get(i).number+ " > ");
        }

        System.out.print("Finish!");

        System.out.println("\n\n#### Path Visualization of Maximum Sum \n");
        for (int i = 0; i < ROWSIZE; ++i) {
            int listIndex = path.size() - i - 1;
            for (int j = 0; j < COLUMNSIZE; ++j) {
                if (listIndex < path.size() && listIndex >= 0) {
                    if (path.get(listIndex).getX() == i && path.get(listIndex).getY() == j) {
                        System.out.printf("%d\t", path.get(listIndex).getNumber());
                    } else {
                        System.out.printf("0\t");
                    }
                }else{
                    System.out.printf("0\t");
                }
            }
            System.out.print('\n');
        }
    }
}



