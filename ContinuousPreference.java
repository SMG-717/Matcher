public class ContinuousPreference extends Preference {
    int minPreference;
    int maxPreference;

    public ContinuousPreference(String range) {
        minPreference = Integer.MIN_VALUE;
        maxPreference = Integer.MAX_VALUE;

        if (range == null || range.trim().equals("") || range.trim().equals("-")) {
            return;
        }

        if (range.contains("-") && range.indexOf('-') > 1) {
            String[] array = range.split("-");
            minPreference = Integer.parseInt(array[0].trim());
            maxPreference = Integer.parseInt(array[1].trim());
        } else if (range.contains("to")) {
            String[] array = range.split("to");
            minPreference = Integer.parseInt(array[0].trim());
            maxPreference = Integer.parseInt(array[1].trim());
        } else if (range.contains(">")) {
            range = range.replaceAll(">", "");
            minPreference = Integer.parseInt(range.trim());
        } else if (range.contains("<")) {
            range = range.replaceAll("<", "");
            maxPreference = Integer.parseInt(range.trim());
        } else {
            maxPreference = Integer.parseInt(range.trim());
            minPreference = Integer.parseInt(range.trim());
        }

        if (minPreference > maxPreference) {
            throw new IllegalArgumentException("Minimum preference cannot be bigger than Maximum Preference");
        }
    }

    public boolean satisfies(String property) {
        if (property == null) {
            return true;
        }
        int num = Integer.parseInt(property);
        return num >= minPreference && num <= maxPreference;
    }
}
