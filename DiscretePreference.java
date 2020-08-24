public class DiscretePreference extends Preference {
    String[] preferences;

    public DiscretePreference(String values) {
        if (values == null || values.trim().equals("")) {
            return;
        }

        preferences = values.split("/");
    }

    public boolean satisfies(String property) {
        for (String preference : preferences) {
            if (property.equals(preference)) {
                return true;
            }
        }
        return false;
    }
}
