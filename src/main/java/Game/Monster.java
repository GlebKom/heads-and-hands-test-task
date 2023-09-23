package Game;

public class Monster extends Creature{

    RandomValue random = new DefaultRandom();
    DiceToss dice = new SixEdgedDice();
    public Monster(int attackValue, int defendValue, long HPValue, int damageLowerLimit, int damageUpperLimit) {
        super(attackValue, defendValue, HPValue, damageLowerLimit, damageUpperLimit);
    }


}
