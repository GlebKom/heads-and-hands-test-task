package Utils;

import Characters.Creature;

public interface AttackModifier {
    int getAttackModifier(Creature attacker, Creature defender);
}
