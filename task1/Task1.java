import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task1 {
    public static List<Integer> circularPath(int n, int m) {
            List<Integer> path = new ArrayList<>();
            int current = 1;
            while (!path.contains(current)) {
                path.add(current);
                current = (current + m - 2) % n + 1;
            }
            return path;
        }

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите значение n: ");
            int n = scanner.nextInt();
            System.out.print("Введите значение m: ");
            int m = scanner.nextInt();

            List<Integer> path = circularPath(n, m);

            String pathString = path.stream()
                    .map(String::valueOf)
                    .reduce("", String::concat);

            System.out.println("Путь: " + pathString);
        }
    }

