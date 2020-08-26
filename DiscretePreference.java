public class DiscretePreference extends Preference {
    String[] preferences;

    public DiscretePreference(String values) {
        if (values == null || values.trim().equals("")) {
            return;
        }

        preferences = values.split("/");
    }

    public boolean satisfies(String property) {
        if (preferences == null || preferences.length == 0) {
            return true;
        }

        for (String preference : preferences) {
            if (preference.equals(property)) {
                return true;
            }
        }
        return false;
    }
}
