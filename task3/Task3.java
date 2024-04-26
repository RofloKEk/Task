import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class Task3 {

    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("Неверное количество аргументов. Укажите пути к трем файлам: values.json, tests.json, report.json");
            return;
        }


        Map<Integer, String> valueMap = readValues(args[0]);


        JSONObject testsJson = readJson(args[1]);


        fillValues(testsJson, valueMap);


        saveJson(testsJson, args[2]);
    }


    private static Map<Integer, String> readValues(String filePath) {
        Map<Integer, String> valueMap = new HashMap<>();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                JSONObject jsonObject = new JSONObject(line);
                int id = jsonObject.getInt("id");
                String value = jsonObject.getString("value");
                valueMap.put(id, value);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден: " + e.getMessage());
        }
        return valueMap;
    }


    private static JSONObject readJson(String filePath) {
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Файл не найден: " + e.getMessage());
        }
        return new JSONObject(content.toString());
    }


    private static void fillValues(JSONObject jsonObject, Map<Integer, String> valueMap) {
        if (jsonObject.has("values")) {
            JSONArray valuesArray = jsonObject.getJSONArray("values");
            for (int i = 0; i < valuesArray.length(); i++) {
                fillValues(valuesArray.getJSONObject(i), valueMap);
            }
        } else {
            int id = jsonObject.getInt("id");
            jsonObject.put("value", valueMap.getOrDefault(id, ""));
        }
    }


    private static void saveJson(JSONObject jsonObject, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(jsonObject.toString(2));
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Не удалось создать файл: " + e.getMessage());
        }
    }



    private static void writeJson(JSONObject jsonObject, String filePath) {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.write(jsonObject.toString(2));
        } catch (FileNotFoundException e) {
            System.err.println("Ошибка: Не удалось создать файл: " + e.getMessage());
        }
    }
}