import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Task2 {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Неверное количество аргументов. Укажите пути к двум файлам.");
            return;
        }

        try (Scanner circleScanner = new Scanner(new File(args[0]));
             Scanner pointsScanner = new Scanner(new File(args[1]))) {


            double circleX = circleScanner.nextDouble();
            double circleY = circleScanner.nextDouble();
            double radius = circleScanner.nextDouble();


            while (pointsScanner.hasNextDouble()) {
                double pointX = pointsScanner.nextDouble();
                double pointY = pointsScanner.nextDouble();

                int position = getPosition(circleX, circleY, radius, pointX, pointY);
                System.out.println(position);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден: " + e.getMessage());
        }
    }


    private static int getPosition(double circleX, double circleY, double radius, double pointX, double pointY) {
        double distanceSquared = Math.pow(pointX - circleX, 2) + Math.pow(pointY - circleY, 2);
        double radiusSquared = Math.pow(radius, 2);

        if (distanceSquared < radiusSquared) {
            return 1;
        } else if (distanceSquared == radiusSquared) {
            return 0;
        } else {
            return 2;
        }
    }
}