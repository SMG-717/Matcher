import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Person {
    final String name;
    final String gender;

    HashMap<Heading, String> properties;
    HashMap<Heading, Preference> preferences;

    List<Person> potentialCompanions;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;

        preferences = new HashMap<>();
        properties = new HashMap<>();

    }

    public void setPreference(Heading preference, String value) {

        Preference p;
        if (preference.isContinuous) {
            p = new ContinuousPreference(value);
        } else {
            p = new DiscretePreference(value);
        }

        preferences.put(preference, p);
    }

    public void setProperty(Heading quality, String value) {
        properties.put(quality, value);

        if (quality == Heading.GENDER) {
            String gender = value.equalsIgnoreCase("male") ? "female" : "male";
            preferences.put(Heading.GENDER, new DiscretePreference(gender));
        }
    }


    public void addPotentialCompanions(Person... newPeople) {
        if (newPeople == null) return;

        if (potentialCompanions == null) {
            potentialCompanions = new ArrayList<>();
        }

        Collections.addAll(potentialCompanions, newPeople);
    }

    public int getSatisfactionNum(Person p) {
        if (p.gender.equals(gender)) {
            return 0;
        }

        int amount = 0;

        for (Heading h : preferences.keySet()) {
            if (preferences.get(h).satisfies(p.getProperty(Heading.getCounterpart(h)))) {

                // System.out.println(p.name + " satisfies " + name + "'s " + h.name());
                amount++;
            }
        }
        return amount;
    }

    public String getProperty(Heading h) {
        return properties.get(h);
    }

}
