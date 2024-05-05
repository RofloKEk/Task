import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Value {
    public int id;
    public String value;
}

class Test {
    public int id;
    public String title;
    public String value;
    public List<Test> values;
}

public class ReportGenerator {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Неправильное количество аргументов.");
            return;
        }

        String valuesFile = args[0];
        String testsFile = args[1];
        String reportFile = args[2];

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Value> valueList = mapper.readValue(
                new File(valuesFile),
                mapper.getTypeFactory().constructCollectionType(List.class, Value.class)
            );

            Map<Integer, String> idToValue = new HashMap<>();
            for (Value v : valueList) {
                idToValue.put(v.id, v.value);
            }

            List<Test> tests = mapper.readValue(
                new File(testsFile),
                mapper.getTypeFactory().constructCollectionType(List.class, Test.class)
            );

            setValues(tests, idToValue);

            mapper.writeValue(new File(reportFile), tests);

            System.out.println("Отчет успешно сгенерирован.");

        } catch (IOException e) {
            System.err.println("Произошла ошибка: " + e.getMessage());
        }
    }

    private static void setValues(List<Test> tests, Map<Integer, String> idToValue) {
        for (Test t : tests) {
            if (idToValue.containsKey(t.id)) {
                t.value = idToValue.get(t.id);
            }

            if (t.values != null) {
                setValues(t.values, idToValue);
            }
        }
    }
}
