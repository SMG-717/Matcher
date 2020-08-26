import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Main {
    static List<Person> people = CSVReader.people;
    public static void main(String[] args) {
        CSVReader.readLinesFrom("C:/Users/shereen serry/IdeaProjects/Matcher/Book1.csv");
        for (Person p : people) {
            System.out.println(p.name);
        }
        matchPeople();
    }

    public static void matchPeople() {

        Comparator<PersonPair> comparator = Comparator.comparingInt(o -> o.priority);

        for (Person p : people) {
            PriorityQueue<PersonPair> queue = new PriorityQueue<>(1, comparator);
            for (Person q: people) {
                if (p == q) continue;

                int preferencesMet = p.getSatisfactionNum(q);
                if (preferencesMet != 0) {
                    queue.add(new PersonPair(q, preferencesMet));
                }
            }

            for (int i = 0; i < queue.size(); i++) {
                p.addPotentialCompanions(queue.poll().person);
            }

        }
    }
}
