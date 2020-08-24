import java.util.List;

public class Main {
    static List<Person> people = CSVReader.people;
    public static void main(String[] args) {
        CSVReader.readLinesFrom("C:/Users/HP/IdeaProjects/Match Maker/src/Book1.csv");
        for (Person p : people) {
            System.out.println(p.name);
        }
        matchPeople();
    }

    public static void matchPeople() {
        for (Person p : people) {

        }
    }

    public static void getPeople(Preference... p) {

    }


}
