package pixelbattle.ultimates;

import pixelbattle.core.Fighter;
import pixelbattle.core.Ultimate;

/** Ультимейт мага «Метеорит». */
public class Meteor implements Ultimate {

    @Override
    public String getName() {
        return "Метеорит";
    }

    @Override
    public String getDescription() {
        return "Завдає 20–30 шкоди кожному живому ворогу";
    }

    @Override
    public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
        int totalDmg = 0;
        for (Fighter e : enemies) {
            if (e.isAlive()) {
                totalDmg += e.takeDamage(Fighter.random(20, 30));
            }
        }
        return user.getName() + " викликає Метеорит: " + totalDmg + " загальної шкоди!";
    }
}