package Game;

public class Monster extends Creature{

    public Monster() {
        super();
    }

    public Monster(int attackValue, int defendValue, long HPValue, int damageLowerLimit, int damageUpperLimit) {
        super(attackValue, defendValue, HPValue, damageLowerLimit, damageUpperLimit);
    }


}
