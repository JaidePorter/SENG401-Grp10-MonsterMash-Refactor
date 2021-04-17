package data;

import data.Monster;
import data.Tier;
import data.TierOneMonster;
import data.TierTwoMonster;
import data.TierThreeMonster;
import java.util.Date;

public class MonsterFactory {
    
    public static Monster getMonster(Enum<Tier> monsterTier) {
        Monster monster;
        if (monsterTier.toString() == "ONE") {
            monster = new TierOneMonster();
        }
        else if (monsterTier.toString() == "TWO") {
            monster = new TierTwoMonster();
        }
        else if (monsterTier.toString() == "THREE") {
            monster = new TierThreeMonster();
        }
        else {
            monster = null;
        }

        return monster;
    }

    public static Monster getMonster(Enum<Tier> monsterTier, String name, String userID) {
        Monster monster;
        if (monsterTier.toString() == "ONE") {
            monster = new TierOneMonster(name, userID);
        }
        else if (monsterTier.toString() == "TWO") {
            monster = new TierTwoMonster(name, userID);
        }
        else if (monsterTier.toString() == "THREE") {
            monster = new TierThreeMonster(name, userID);
        }
        else {
            monster = null;
        }

        return monster;
    }

    public static Monster getMonster(Enum<Tier> monsterTier, String id, String name, Date dob, Date dod, Double baseStrength, Double currentStrength, Double baseDefence, Double currentDefence, Double baseHealth, Double currentHealth, float fertility, String userID, int saleOffer, int breedOffer) {
        Monster monster;
        if (monsterTier.toString() == "ONE") {
            monster = new TierOneMonster(id, name, dob, dod, baseStrength, currentStrength, baseDefence, currentDefence, baseHealth, currentHealth, fertility, userID, saleOffer, breedOffer);
        }
        else if (monsterTier.toString() == "TWO") {
            monster = new TierTwoMonster(id, name, dob, dod, baseStrength, currentStrength, baseDefence, currentDefence, baseHealth, currentHealth, fertility, userID, saleOffer, breedOffer);
        }
        else if (monsterTier.toString() == "THREE") {
            monster = new TierThreeMonster(id, name, dob, dod, baseStrength, currentStrength, baseDefence, currentDefence, baseHealth, currentHealth, fertility, userID, saleOffer, breedOffer);
        }
        else {
            monster = null;
        }

        return monster;
    }
}
