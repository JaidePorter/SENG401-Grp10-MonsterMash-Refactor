package data;

import data.Monster;
import data.Tier;
import data.TierOneMonster;
import data.TierTwoMonster;
import data.TierThreeMonster;

public class MonsterFactory {
    
    public static Monster getMonster(Enum<Tier> monsterTier) {
        Monster monster;
        if (monsterTier.toString == "ONE") {
            monster = new TierOneMonster();
        }
        else if (monsterTier.toString == "TWO") {
            monster = new TierTwoMonster();
        }
        else if (monsterTier.toString == "THREE") {
            monster = new TierThreeMonster();
        }
        else {
            monster = null;
        }

        return monster;
    }
}
