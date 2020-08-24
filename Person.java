import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Person {
    String name;
    String gender;
    int age;
    String occupation;
    int income;
    int height;
    String residence;

    HashMap<String, Object> qualities;
    HashMap<String, Preference> preferences;

    Person assignedCompanion;
    List<Person> potentialCompanions;

    public Person(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getGender() {

    }

    public void setPreference(String preference, Preference value) {
        preferences.put(preference, value);
    }

    public void setQuality(String quality, Object value) {
        qualities.put(quality, value);

        if (quality.equalsIgnoreCase("gender")) {
            String gender = ((String) value).equalsIgnoreCase("male") ? "female" : "male";
            preferences.put("gender", new DiscretePreference(gender));
        }
    }

    public Person getAssignedCompanion() {
        return assignedCompanion;
    }

    public void setAssignedCompanion(Person assignedCompanion) {
        this.assignedCompanion = assignedCompanion;
    }

    public boolean isPaired() {
        return assignedCompanion != null;
    }

    public void addPotentialCompanion(Person newPerson) {
        if (potentialCompanions == null) potentialCompanions = new ArrayList<>();

        potentialCompanions.add(newPerson);
    }

    public void calculatePreferences() {

    }
}
