package edu.bhcc;

/**
 * Sex enum. (I believe that people have more than two genders, please don't kill me~)
 */
public enum Sex {
    MALE,
    FEMALE;

    public static Sex parse(String sex) {
        sex = sex.toLowerCase();

        if (sex.equals("male")) return Sex.MALE;
        if (sex.equals("female")) return Sex.FEMALE;

        return null;
    }
}
