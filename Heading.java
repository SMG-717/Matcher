import java.util.ArrayList;
import java.util.List;

enum Heading {
    NAME(false, false, "name", "اسم", "إسم", "اسم", "إسم"),
    GENDER(false, false, "gender", "الجنس", "النوع"),
    OCCUPATION(false, false, "occupation", "job", "الوظيفة"),
    RESIDENCE(false, false, "residence", "location", "العنوان", "المكان", "السكن"),
    INCOME(true, false, "income", "salary", "pay", "الدخل", "الراتب", "المرتب"),
    HEIGHT(true, false, "height", "الطول"),
    AGE(true, false, "age", "العمر", "السن"),
    WEIGHT(true, false,"weight", "الوزن"),
    EDUCATION(false, false, "education", "المؤهل"),
    GENDER_PREFERENCE(false, true, "gender preference"),
    OCCUPATION_PREFERENCE(false, true, "occupation preference", "الوظيفة المطلوبة"),
    RESIDENCE_PREFERENCE(false, true, "residence preference", "العنوان المطلوب"),
    INCOME_PREFERENCE(true, true, "income preference", "الدخل المطلوب"),
    HEIGHT_PREFERENCE(true, true, "height preference", "الطول المطلوب"),
    AGE_PREFERENCE(true, true, "age preference", "العمر المطلوب"),
    WEIGHT_PREFERENCE(true, true, "weight preference", "الوزن المطلوب"),
    EDUCATION_PREFERENCE(false, true, "education preference", "المؤهل المطلوب");

    Heading(boolean isContinuous, boolean isPreference, String... names) {
        this.names = new ArrayList<>();
        this.isContinuous = isContinuous;
        this.isPreference = isPreference;
        for (String name : names) {
            this.names.add(name.toLowerCase().trim());
        }
    }

    private List<String> names;
    public final boolean isContinuous, isPreference;

    public boolean validlyDefined(String key) {
        return names != null && names.contains(key);
    }

    static Heading getHeading(String rawHeading) {
        rawHeading = rawHeading.toLowerCase().trim();
        for (Heading h : Heading.values()) {
            if (h.validlyDefined(rawHeading)) {
                return h;
            }
        }
        return null;
    }

    static Heading getCounterpart(Heading h) {
        try {
            if (h.name().contains("PREFERENCE")) {
                return (Heading) Heading.class.getField(h.name().replaceAll("_PREFERENCE", "")).get(null);
            } else {
                return (Heading) Heading.class.getField(h.name() + "_PREFERENCE").get(null);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return null;
        }
    }
}
