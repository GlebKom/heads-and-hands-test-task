package Game;

public class Player extends Creature{

    // counter for heals
    private int healUsed;

    public Player() {
        super();
    }

    public Player(int attackValue, int defendValue, long HPValue, int damageLowerLimit, int damageUpperLimit) {
        super(attackValue, defendValue, HPValue, damageLowerLimit, damageUpperLimit);
    }

    public void heal() {

        // situation, when we have available heals and this creature is still alive
        if (healUsed < 4 && this.isAlive()) {
            long HPAfterHealing = Math.min((long) (this.getHPValue() + this.getMAX_HP_VALUE() * 0.3), this.getMAX_HP_VALUE());
            this.setHPValue(HPAfterHealing);
            System.out.println(this.getClass().getName() + " used health. HP after healing : " + HPAfterHealing);
            healUsed ++;

        // if this creature exceeded the limit of heals
        } else if (healUsed >= 4) {
            System.out.println(this.getClass().getName() + " tried to use heal, but its quantity is over. His current HP : " +
                    this.getHPValue());

        // if this creature is already dead
        } else if (!this.isAlive()){
            System.out.println(this.getClass().getName() + "tried to heal, but he is already dead.");
        }

    }
}
