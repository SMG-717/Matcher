import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CSVReader {

    private static HashMap<Heading, Integer> indexMap = new HashMap<>();
    public static List<Person> people = new ArrayList<>();

    public static void readLinesFrom(String location) {
        Path path = Paths.get(location);
        StringBuilder csvSource = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(path);
            for (String line : lines) {
                csvSource.append(line).append('\n');
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
            indexMap.put(Heading.getHeading(splitHeadings[i]), i);
        }
    }

    public static void constructPeople(String[] list) {
        for (int i = 1; i < list.length; i++) {
            String[] properties = list[i].split(",");
            String name = properties[indexMap.get(Heading.NAME)];
            String gender = properties[indexMap.get(Heading.GENDER)];
            Person person = new Person(name, gender);

            for (Heading key : indexMap.keySet()) {
                if (key == Heading.NAME || key == Heading.GENDER) {
                    continue;
                }

                if (key.isPreference) {
                    person.setPreference(key, properties[indexMap.get(key)]);
                } else {
                    person.setProperty(key, properties[indexMap.get(key)]);
                }
            }

            people.add(person);
        }
    }
}
