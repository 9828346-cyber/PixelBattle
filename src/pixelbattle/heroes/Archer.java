package pixelbattle.heroes;

import pixelbattle.core.Fighter;
import pixelbattle.core.Hero;
import pixelbattle.core.Skill;
import pixelbattle.core.Target;
import pixelbattle.core.Ultimate;
import pixelbattle.engine.Sprite;

/** Лучниця — швидка й спритна. */
public class Archer extends Hero {

    public Archer() {
        super("Лучниця", 80, new Sprite("elf_f_idle_anim", 4, 4));

        addSkill(new Skill("Постріл", Target.ENEMY, 0, (user, target) ->
                user.getName() + " стріляє в " + target.getName() + ": "
                        + target.takeDamage(random(10, 16)) + " шкоди"));

        // TODO 3: «Подвійний постріл»
        addSkill(new Skill("Подвійний постріл", Target.ENEMY, 1, (user, target) -> {
            int dmg1 = target.takeDamage(random(7, 11));
            int dmg2 = target.takeDamage(random(7, 11));
            return user.getName() + " робить подвійний постріл у " + target.getName() + ": "
                    + (dmg1 + dmg2) + " шкоди";
        }));

        // TODO 9: ультимейт «Злива стріл» з полем-лічильником
        setUltimate(new Ultimate() {
            private int arrows = 3;

            @Override
            public String getName() {
                return "Злива стріл";
            }

            @Override
            public String getDescription() {
                return "Випускає по " + arrows + " стріл (4–8 шкоди) у кожного живого ворога";
            }

            @Override
            public String activate(Fighter user, Fighter[] allies, Fighter[] enemies) {
                int totalDmg = 0;
                for (Fighter e : enemies) {
                    if (e.isAlive()) {
                        for (int i = 0; i < arrows; i++) {
                            if (!e.isAlive()) break;
                            totalDmg += e.takeDamage(random(4, 8));
                        }
                    }
                }
                int fired = arrows;
                arrows++;
                return user.getName() + " випускає зливу по " + fired + " стріл: "
                        + totalDmg + " загальної шкоди!";
            }
        });
    }

    // TODO 5: пасивка «Ухилення»
    @Override
    protected int modifyIncomingDamage(int damage) {
        return chance(25) ? 0 : damage;
    }
}