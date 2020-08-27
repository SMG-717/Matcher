import org.w3c.dom.NodeList;

import java.util.*;

/**
 * This is the Main class and the one responsible for pairing the couples.
 */

public class Main {

    // List of all people in the database
    static List<Person> people = CSVReader.people;

    // Main method that starts up the csv reading process and then matches people
    public static void main(String[] args) {

        // Putting the csv file without the full paths means it's in the program directory
        CSVReader.readLinesFrom("Book1.csv");

        matchPeople();
        // printLikes();
    }

    public static void matchPeople() {

        // Note the minus (-) before the o.priority variable.
        // For some reason it sorts the preference lists the right way round
        Comparator<PersonPair> comparator = Comparator.comparingInt(o -> -o.priority);

        for (Person p : people) {
            PriorityQueue<PersonPair> queue = new PriorityQueue<>(1, comparator);
            int max = 0;
            for (Person q: people) {
                if (p == q) continue;

                int preferencesMet = p.getSatisfactionNum(q);
                if (preferencesMet > 0) {
                    queue.add(new PersonPair(q, preferencesMet));
                }

                if (preferencesMet > max) {
                    max = preferencesMet;
                }
            }

            for (int i = 0; i < queue.size(); i++) {
                p.addPotentialCompanions(queue.poll().person);
                if (i > 10) {
                    break;
                }
            }
        }

        Integer[][] array = makePriorityPairs(10);
        List<Person> matchedPeople = new LinkedList<>();
        int num = 1;
        for (Integer[] pair : array) {
            for (Person p : people) {
                for (Person q : people) {
                    if (matchedPeople.contains(p) || matchedPeople.contains(q)) {
                        continue;
                    }
                    try {
                        if (p.potentialCompanions.get(pair[0]) == q && q.potentialCompanions.get(pair[1]) == p) {
                            System.out.println(num++ + ". " + q.name + " is most suitable for " + p.name);
                            matchedPeople.add(p);
                            matchedPeople.add(q);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        // e.printStackTrace();
                    }
                }
            }
        }
    }

    public static Integer[][] makePriorityPairs(int limit) {
        if (limit <= 0) {
            return null;
        }

        List<Integer[]> pairs = new ArrayList<>();

        for (int i = 0; i <= limit; i++) {
            for (int j = i; j >= 0; j--) {
                pairs.add(new Integer[] {j, i - j});
            }
        }

        return pairs.toArray(new Integer[0][0]);
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
