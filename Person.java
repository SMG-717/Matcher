public class Person {
    String name;
    String gender;
    int age;
    String occupation;
    int income;
    int height;
    String residence;

    ContinuousPreference agePreference;
    DiscretePreference occupationPreference;
    ContinuousPreference incomePreference;
    ContinuousPreference heightPreference;
    DiscretePreference residencePreference;

    Person assignedCompanion;
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        int parsedAge = 0;
        try {
            parsedAge = Integer.parseInt(age);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(age + " is not a number");
        }
        if (parsedAge <= 0 ) {
            throw new IllegalArgumentException("Age cannot be a negative number");
        } else if (parsedAge < 0) {
            throw new IllegalArgumentException(name + " is too young for this!");
        } else if (parsedAge > 130) {
            throw new IllegalArgumentException("No one is this old yet");
        }
        this.age = parsedAge;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setIncome(String income) {
        int parsedIncome;
        try {
            parsedIncome = Integer.parseInt(income);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(income + " is not a number");
        }
        if (parsedIncome < 0) {
            throw new IllegalArgumentException("Income cannot be a negative number");
        }
        this.income = parsedIncome;
    }

    public void setHeight(String height) {
        int parsedHeight = 0;
        try {
            parsedHeight = Integer.parseInt(height);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(height + " is not a number");
        }
        if (parsedHeight <= 0 ) {
            throw new IllegalArgumentException("Height cannot be a negative number");
        }

        this.height = parsedHeight;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public void setAgePreference(String agePreference) {
        this.agePreference = new ContinuousPreference(agePreference);
    }

    public void setOccupationPreference(String occupationPreference) {
        this.occupationPreference = new DiscretePreference(occupationPreference);
    }

    public void setIncomePreference(String incomePreference) {
        this.incomePreference = new ContinuousPreference(incomePreference);
    }

    public void setHeightPreference(String heightPreference) {
        this.heightPreference = new ContinuousPreference(heightPreference);
    }

    public void setResidencePreference(String residencePreference) {
        this.residencePreference = new DiscretePreference(residencePreference);
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
}
