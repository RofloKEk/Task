import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class MinMovesToEqualize {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java MinMovesToEqualize <input_file>");
            return;
        }

        String inputFilePath = args[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(inputFilePath)));
            int[] nums = reader.lines()
                               .mapToInt(Integer::parseInt)
                               .toArray();
            reader.close();

            Arrays.sort(nums);

            int n = nums.length;
            int median = (n % 2 == 0) ? (nums[n / 2 - 1] + nums[n / 2]) / 2 : nums[n / 2];

            int totalMoves = 0;
            for (int num : nums) {
                totalMoves += Math.abs(num - median);
            }

            System.out.println("Минимальное количество ходов: " + totalMoves);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка преобразования данных в целые числа: " + e.getMessage());
        }
    }
}
