public class DiscretePreference {
    String[] preferences;

    public DiscretePreference(String values) {
        if (values == null || values.trim().equals("")) {
            return;
        }

        preferences = values.split("/");
    }

    public boolean contains(String property) {
        for (String preference : preferences) {
            if (property.equals(preference)) {
                return true;
            }
        }
        return false;
    }
}
