import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVReader {

    private static HashMap<String, Integer> indexMap = new HashMap<>();
    public static List<Person> people = new ArrayList<>();

    public static void readLinesFrom(String location) {
        Path path = Paths.get(location);
        StringBuilder csvSource = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                csvSource.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] listString = csvSource.toString().replaceAll("\r", "").split("\n");
        setIndices(listString[0]);
        constructPeople(listString);
    }

    public static void setIndices(String headings) {
        String[] splitHeadings = headings.split(",");
        for (int i = 0; i < splitHeadings.length; i++) {
            indexMap.put(splitHeadings[i], i);
        }
    }

    public static void constructPeople(String[] list) {
        for (int i = 1; i < list.length; i++) {
            String[] properties = list[i].split(",");
            Person person = new Person();
            person.setName(properties[indexMap.get("Name")]);
            person.setGender(properties[indexMap.get("Gender")]);

            person.setAge(properties[indexMap.get("Age")]);
            person.setOccupation(properties[indexMap.get("Occupation")]);
            person.setIncome(properties[indexMap.get("Income")]);
            person.setHeight(properties[indexMap.get("Height")]);
            person.setResidence(properties[indexMap.get("Residence")]);

            person.setAgePreference(properties[indexMap.get("Age Preference")]);
            person.setOccupationPreference(properties[indexMap.get("Occupation Preference")]);
            person.setIncomePreference(properties[indexMap.get("Income Preference")]);
            person.setHeightPreference(properties[indexMap.get("Height Preference")]);
            person.setResidencePreference(properties[indexMap.get("Residence Preference")]);

            people.add(person);
        }
    }
}
