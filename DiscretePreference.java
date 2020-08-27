public class DiscretePreference extends Preference {
    String[] preferences;

    public DiscretePreference(String values) {
        if (values == null || values.trim().equals("")) {
            return;
        }

        preferences = values.split("/");
    }

    public int satisfies(String property) {
        if (preferences == null || preferences.length == 0) {
            return 1;
        }

        for (String preference : preferences) {
            if (preference.equals(property)) {
                return 2;
            }
        }
        return 0;
    }
}
