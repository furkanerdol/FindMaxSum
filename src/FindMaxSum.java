import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Furkan on 16.03.2017.
 */
public class FindMaxSum {

    public static final int ROWSIZE = 16;
    public static final int COLUMNSIZE = 16;

    public static int[][] numbersArray = new int[ROWSIZE][COLUMNSIZE];

    /**
     * Wrapper method
     */
    public static int findMaxSum(String fileName) {

        readNumbersFromFile(fileName, numbersArray);

        return findMaxSumRec(0, 0, ROWSIZE);

    }

    /**
     * Finds maximum sum number and points its way
     *
     * @param x    row index
     * @param y    column index
     * @param size row size
     * @return maximum sum value according to problem rules
     */
    private static int findMaxSumRec(int x, int y, int size) {

        int total = 0, totalLeft = 0, totalMiddle = 0, totalRight = 0;

        if (x >= size) {
            return 0;
        }

        if (y > 0) {

            if (!isPrime(numbersArray[x + 1][y - 1])) {
                totalLeft = findMaxSumRec(x + 1, y - 1, size);
            }
            if (!isPrime(numbersArray[x + 1][y])) {
                totalMiddle = findMaxSumRec(x + 1, y, size);
            }
            if (!isPrime(numbersArray[x + 1][y + 1])) {
                totalRight = findMaxSumRec(x + 1, y + 1, size);
            }

            if (totalRight >= totalMiddle && totalRight >= totalLeft) {
                return totalRight + numbersArray[x][y];
            } else if (totalLeft >= totalMiddle && totalLeft >= totalRight) {
                return totalLeft + numbersArray[x][y];
            } else {
                return totalMiddle + numbersArray[x][y];
            }


        } else {

            if (!isPrime(numbersArray[x + 1][y])) {
                totalMiddle = findMaxSumRec(x + 1, y, size);
            }
            if (!isPrime(numbersArray[x + 1][y + 1])) {
                totalRight = findMaxSumRec(x + 1, y + 1, size);
            }

            if (totalRight >= totalMiddle) {
                return totalRight + numbersArray[x][y];
            } else {
                return totalMiddle + numbersArray[x][y];
            }
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


    public static void readNumbersFromFile(String fileName, int[][] numbersArray) {

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


    public static void main(String args[]) {

        System.out.printf("\n***** Maximum Sum Value : %d\n", findMaxSum("test.txt"));

    }

}



