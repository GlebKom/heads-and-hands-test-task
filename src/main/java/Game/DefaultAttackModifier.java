package Game;

class DefaultAttackModifier implements AttackModifier {
    @Override
    public int getAttackModifier(Creature attacker, Creature defender) {
        return attacker.getAttackValue() - defender.getDefendValue() + 1;
    }
}