import java.util.Random;

public class PeopleGenerator {
    static Random gen = new Random();
    static String[] nameList = "Bret Snow,Mehmet Watt,Hasan Park,Mikolaj Mack,Nana Brandt,Raja Shah,Bilal Dudley,Abubakar Black,Rhys Arnold,Iosif Patrick,Walter Rodrigues,Evan Almond,Ziggy Stacey,Connagh Bloggs,Bobbi Kearns,Donald Correa,Arif Martin,Ellis Brennan,Subhaan Michael,Umayr Macdonald,Mustafa Copeland,Mina Soto,Jaye Burke,Alfie-Lee Snider,Saif Best,Clayton Valentine,Wil Lawrence,Lewis Francis,Stanley Reyes,Kim Wolf,Robson Key,Shiloh Hopper,Nadir Donald,Dario Ramirez,Bernard Swanson,Rayhaan Ballard,Donell White,Norman Livingston,Bartlomiej Alford,Aden Rosas,Tyreese Huffman,Gia Adkins,Brandy Pierce,Szymon Sykes,Loki Graham,Siddharth Douglas,Garfield Zhang,Eamonn Hardy,Denny Ochoa,Clyde Jordan,Shyla Prentice,Dotty Mclaughlin,Shelbie Chambers,Cordelia Bassett,Leilani Wilks,Rosa Byers,Jillian Crosby,Teri Cervantes,Clarke Read,Manha Rasmussen,Kristy Redmond,Kay Wilkins,Nichola Thorne,Tess Fox,Atlanta Mccarthy,Liana Battle,Nina Morrison,Kayley Conrad,Marnie Boyd,Hermione Quinn,Anna Mays,Hira Davila,Jo Firth,Jade Wilson,Isla-Mae Sanders,Amelia-Rose Larson,Anja Shelton,Carmen Montes,Natasha Hackett,Ellis Wilkinson,Amina Cooper,Sakina Ochoa,Lynsey Amin,Sandra O'Neill,Aiysha Duke,Saarah Porter,Elana Holloway,Dominique Fuentes,Isla Perez,Lani Savage,Alyx Enriquez,Riley Hines,Jorge Power,Amrit Prince,Etta Bloggs,Elliot Kennedy,Elisa Bentley,Coco Riddle,Antonina Oconnor,Kristina Shields".split(",");
    static String[] jobList = "Doctor,Scientist,Engineer,Teacher,Actor,Singer,Artist,Musician,Programmer,Police Officer,Accountant,Bussinessman,Firefighter,Company Director,Writer,Journalist,News Anchor,Pilot,Soldier,Astronaut,Mathematician,Chef,Secretary,Bank Teller,Civil Servant,Maid/Butler,Shopkeeper,Salesman,Min Wage".split(",");
    static String[] placeList = "Alexandria,Aswan,Asyut,Beheira,Beni Suef,Cairo,Dakahlia,Damietta,Faiyum,Gharbia,Giza,Ismailia,Kafr El Sheikh,Luxor,Matruh,Minya,Monufia,New Valley,North Sinai,Por Said,Qalyubia,Qena,Red Sea,Sharqia,Sohag,South Sinai,Suez".split(",");


    public static void main(String[] args) {
        int length = 100;
        if (args != null && args.length > 0) {
            try {
                length = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Name,Gender,Age,Occupation,Height,Residence," +
                "Age Preference,Occupation Preference,Height Preference,Residence Preference");

        for (int i = 0; i < length; i++) {
            String text = nameList[i] + "," + // Name
                    (i > 50 ? "F," : "M,") + // Gender

                    (gen.nextInt(32) + 18) + "," + // Age
                    jobList[gen.nextInt(jobList.length)] + "," + // Occupation
                    (i > 50 ? gen.nextInt(179 - 150) + 150 : gen.nextInt(194 - 163) + 163) + "," + // Height
                    placeList[gen.nextInt(placeList.length)] + "," + // Residence

                    randomAgePreference() + "," + // Age Preference
                    randomOccupationPreference() + "," + // Occupation Preference
                    randomHeightPreference(i > 50) + "," + // Height Preference
                    randomResidencePreference(); // Residence Preference

            System.out.println(text);
        }

    }

    public static String randomAgePreference() {
        return randomContinuousPreference(new int[] {20, 25, 30, 35, 40, 45, 50}, 18, 50);

    }

    public static String randomHeightPreference(boolean isFemale) {
        if (isFemale) {
            return randomContinuousPreference(new int[] {160, 165, 170, 175, 180, 185, 190, 195, 200}, 163, 194);
        } else {
            return randomContinuousPreference(new int[] {150, 155, 160, 165, 170, 175, 180}, 150, 179);
        }
    }

    public static String randomResidencePreference() {
        return randomDiscretePreference(placeList);
    }

    public static String randomOccupationPreference() {
        return randomDiscretePreference(jobList);
    }

    public static String randomContinuousPreference(int[] boundArray, int min, int max) {
        if (gen.nextInt(100) < 10) {
            return "-";
        }
        int r = gen.nextInt();
        if (r % 3 == 1) {
            return ">" + (gen.nextInt(max - min) + min);
        } else if (r % 3 == 2) {
            return "<" + (gen.nextInt(max - min) + min);
        } else {
            r = gen.nextInt(boundArray.length - 1);
            int start = boundArray[r];
            int end = boundArray[gen.nextInt(boundArray.length - r - 1) + r + 1];
            return start + "-" + end;
        }

    }


    private static String randomDiscretePreference(String[] thinglist) {
        int num = gen.nextInt(5);
        if (num == 0) return "-";

        StringBuilder text = new StringBuilder();
        for (int i = 0; i < num;) {
            String thing = thinglist[gen.nextInt(thinglist.length)];
            if (!text.toString().contains(thing)) {
                text.append(thing);
                i++;
                if (i < num) {
                    text.append("/");
                }
            }
        }
        return text.toString();
    }
}
