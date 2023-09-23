package Characters;

public class Player extends Creature{

    private int healUsed;
    public Player(int attackValue, int defendValue, long HPValue, int damageLowerLimit, int damageUpperLimit) {
        super(attackValue, defendValue, HPValue, damageLowerLimit, damageUpperLimit);
    }

    public void heal() {
        if (healUsed < 4) {
            long HPAfterHealing = Math.min((long) (this.getHPValue() + this.getMAX_HP_VALUE() * 0.3), this.getMAX_HP_VALUE());
            this.setHPValue(HPAfterHealing);
            System.out.println(this.getClass().getName() + " used health. HP after healing : " + HPAfterHealing);
            healUsed ++;
        } else {
            System.out.println(this.getClass().getName() + " tried to use heal, but their quantity is over. His current HP : " +
                    this.getHPValue());
        }

    }
}
