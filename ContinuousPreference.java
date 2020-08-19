public class ContinuousPreference {
    int minPreference;
    int maxPreference;

    public ContinuousPreference(String range) {
        minPreference = 0;
        maxPreference = Integer.MAX_VALUE;

        if (range == null || range.trim().equals("")) {
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
            maxPreference = Integer.MAX_VALUE;
        } else if (range.contains("<")) {
            range = range.replaceAll("<", "");
            minPreference = 0;
            maxPreference = Integer.parseInt(range.trim());
        } else {
            maxPreference = Integer.parseInt(range.trim());
            minPreference = Integer.parseInt(range.trim());
        }

        if (minPreference > maxPreference) {
            throw new IllegalArgumentException("Minimum preference cannot be bigger than Maximum Preference");
        }
    }

    public boolean isInRange(int number) {
        return number > minPreference && number < maxPreference;
    }
}
