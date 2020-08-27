import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This class reads data from a csv file (specifically one saved as an MS DOS csv).
 * It then assigns the headers to Heading objects and constructs people systematically.
 */

public class CSVReader {

    // Map that stores the Headings with their column number from the csv
    private static HashMap<Heading, Integer> indexMap = new HashMap<>();

    // List that stores all the constructed people from the database
    public static List<Person> people = new ArrayList<>();

    // Reads all the lines from a csv then calls methods for filing the indexMap and constructs people
    public static void readLinesFrom(String location) {
        Path path = Paths.get(location);
        String[] lines;

        try {

            // Reads all lines and then converts into String array
            lines = Files.readAllLines(path).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        setIndices(lines[0]);
        constructPeople(lines);
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

                // Name and Gender were already set in the Person constructor
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
