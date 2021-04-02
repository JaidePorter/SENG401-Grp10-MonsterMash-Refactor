/**
 * @HeadUrl https://github.com/DownGoat/MonsterMash/blob/development/G12/src/MonsterMash/data/Notification.java
 * 
 * Copyright (c) 2013 Aberystwyth University
 * All rights reserved.
 */

package data;

import java.util.*;

/**
 * NameGenerator to generate random name for monsters. The name is a combination
 * of a front name and last name.
 * $Author Llionv $ 
 */
public class NameGenerator {

    private static List startWords = new ArrayList();
    private static List endWords = new ArrayList();

    /**
     * Get a random name, the random name is of a for name and last name.
     * @return  The name.
     */
    public static String getName() {
        String thestartWords[] = {"Blessed", "Cursed", "Devious", "Forgotten", "Greater",
            "Hidden", "Hood", "Lost", "Vile", "Blood", "Creeping",
            "Sacred", "Whip", "Pixie", "Toad", "Dreadshredder", "Minature",
            "Terrifying", "Primordial", "Freezing", "Mighty", "Rainbow",
            "Slithering", "Babbling", "Withered"};
        String theendWords[] = {"Snake", "Hawk", "Pudding", "Kraken", "Shark", "Hedgehog",
            "Hacker", "Lurker", "Knight", "Slayer", "Demon", "Grotesque",
            "Serpant", "Spectre", "Billy", "Lizard", "Swarm", "Strangler", "Mummy", ""};

        startWords.addAll(Arrays.asList(thestartWords));
        endWords.addAll(Arrays.asList(theendWords));

        String name = startWords.get(randomInt(0, 24)) + " " + endWords.get(randomInt(0, 18));

        return name;
    }

    /**
     * Private function to get a random int between a range.
     * @param min Min   
     * @param max Max
     * @return A random number between min and max.
     */
    private static int randomInt(int min, int max) {
        return (int) (min + (Math.random() * (max + 1 - min)));
    }

    /**
     * Private method to get the a random element from a list.
     * @param v The list of strings
     * @return a random element.
     */
    private static String getRandomElementFrom(List<String> v) {
        return v.get(randomInt(0, v.size() - 1));
    }
}