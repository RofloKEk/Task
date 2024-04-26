import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Task4 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Неверное количество аргументов. Укажите путь к файлу с числами.");
            return;
        }

        try (Scanner scanner = new Scanner(new File(args[0]))) {
            int[] nums = readArrayFromFile(scanner);
            int minMoves = calculateMinMoves(nums);
            System.out.println(minMoves);
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден: " + e.getMessage());
        }
    }


    private static int[] readArrayFromFile(Scanner scanner) {
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        return nums;
    }


    private static int calculateMinMoves(int[] nums) {
        Arrays.sort(nums);
        int median = nums[nums.length / 2];
        int moves = 0;
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        return moves;
    }
}
