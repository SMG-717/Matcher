import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * This is the Main class and the one responsible for pairing the couples.
 */

public class Main {
    static List<Person> people = CSVReader.people;
    public static void main(String[] args) {
        CSVReader.readLinesFrom("Book1.csv");
        matchPeople();
        printLikes();
    }

    public static void matchPeople() {
        Comparator<PersonPair> comparator = Comparator.comparingInt(o -> -o.priority);

        for (Person p : people) {
            PriorityQueue<PersonPair> queue = new PriorityQueue<>(1, comparator);
            for (Person q: people) {
                if (p == q) continue;

                int preferencesMet = p.getSatisfactionNum(q);
                if (preferencesMet > 0) {
                    queue.add(new PersonPair(q, preferencesMet));
                }
            }

            for (int i = 0; i < queue.size(); i++) {
                p.addPotentialCompanions(queue.poll().person);
            }

        }
    }

    public static void printLikes() {
        for (Person p : people) {
            System.out.print(p.name + " likes :\n    ");
            for (Person q : p.potentialCompanions) {
                System.out.print(q.name + ", ");
            }
            System.out.println("\b\b\n");
        }
    }
}
