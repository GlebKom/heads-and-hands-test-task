package Utils;

import Characters.Creature;

public class DefaultAttackModifier implements AttackModifier {
    @Override
    public int getAttackModifier(Creature attacker, Creature defender) {
        return 0;
    }
}