package Game;

public abstract class Creature {

    private final int attackValue;
    private final int defendValue;
    private long HPValue;
    private final int damageLowerLimit;
    private final int damageUpperLimit;
    private final long MAX_HP_VALUE;

    // this fields help in attack logic
    private AttackModifier attackModifier;
    private DiceToss dice;
    private RandomValue randomValue;

    // setting default values for service field
    {
        setDefaultDice();
        setDefaultAttackModifier();
        setDefaultRuleForRandom();
    }

    public Creature() {
        this.attackValue = randomValue.getRandomValue(5, 20);
        this.defendValue = randomValue.getRandomValue(5, 20);
        this.HPValue = randomValue.getRandomValue(10, 50);
        this.damageLowerLimit = randomValue.getRandomValue(1, 5);
        this.damageUpperLimit = randomValue.getRandomValue(damageLowerLimit + 1, damageLowerLimit + 6);
        this.MAX_HP_VALUE = HPValue;
    }

    public Creature(int attackValue,
                    int defendValue,
                    long HPValue,
                    int damageLowerLimit,
                    int damageUpperLimit) throws IllegalArgumentException{

        if (attackValue < 0 || attackValue > 30) {
            throw new IllegalArgumentException("Attack value must be in range from 1 to 30");
        } else if (defendValue < 0 || defendValue > 30) {
            throw new IllegalArgumentException("Defend value must be in range from 1 to 30");
        } else if (HPValue < 0) {
            throw new IllegalArgumentException("HP value must be greater than or equal to 0");
        } else if (damageLowerLimit > damageUpperLimit) {
            throw new IllegalArgumentException("Damage upper limit must be greater than damage lower limit");
        } else if (damageLowerLimit < 0 || damageUpperLimit < 0) {
            throw new IllegalArgumentException("Damage limits must be greater than 0");
        }

        this.attackValue = attackValue;
        this.defendValue = defendValue;
        this.HPValue = HPValue;
        this.MAX_HP_VALUE = HPValue;
        this.damageLowerLimit = damageLowerLimit;
        this.damageUpperLimit = damageUpperLimit;
    }

    protected void setAttackModifier(AttackModifier attackModifier) {
        this.attackModifier = attackModifier;
    }

    protected void setDice(DiceToss dice) {
        this.dice = dice;
    }

    protected void setRandomValue(RandomValue randomValue) {
        this.randomValue = randomValue;
    }

    protected void setDefaultAttackModifier() {
        this.attackModifier = new DefaultAttackModifier();
    }

    protected void setDefaultDice() {
        this.dice = new SixEdgedDice();
    }

    protected void setDefaultRuleForRandom() {
        this.randomValue = new DefaultRandomValue();
    }

    protected void setHPValue(long HPValue) {
        if (HPValue <= 0) {
            // hp < 0 means, that this character is already dead
            this.HPValue = 0;
        } else {
            this.HPValue = HPValue;
        }
    }

    public int getAttackValue() {
        return attackValue;
    }

    public int getDefendValue() {
        return defendValue;
    }


    public long getHPValue() {
        return this.HPValue;
    }

    public int getDamageUpperLimit() {
        return this.damageUpperLimit;
    }

    public int getDamageLowerLimit() {
        return this.damageLowerLimit;
    }

    public long getMAX_HP_VALUE() {
        return MAX_HP_VALUE;
    }

    public boolean isAlive() {
        return this.getHPValue() > 0;
    }


    // default attack logic
    public void attack(Creature enemy){

        // when character tries to attack himself
        if (this == enemy) {
            throw new IllegalArgumentException("Character can't hurt himself");
        }

        // if this character is already dead
        if (!this.isAlive()) {
            System.out.println(this.getClass().getName() + " can't attack, because he is dead");
        } else if (!enemy.isAlive()) {
            // if enemy character is already dead
            System.out.println(this.getClass().getName() + " wanted to fight, but " +
                    enemy.getClass().getName() + " is already dead.");

        } else {

            // checking is at least one dice toss was successful or not, by default it is not
            boolean isDiceSuccessful = false;

            //init count of tries to toss the dice, be default it equals attackModifier, but it has to equal at least 1
            int triesCount = this.attackModifier.getAttackModifier(this, enemy) > 0
                    ? this.attackModifier.getAttackModifier(this, enemy)
                    : 1;

            // tossing a dice
            for (int tryCount = 0; tryCount < triesCount; tryCount++) {
                if (dice.toss() >= 5) {
                    isDiceSuccessful = true;
                    break;
                }
            }

            // decreasing enemy's hp if toss was successful
            if (isDiceSuccessful) {
                int damageGiven = randomValue.getRandomValue(this.getDamageLowerLimit(), this.getDamageUpperLimit());

                enemy.setHPValue(enemy.getHPValue() - damageGiven);

                System.out.println(this.getClass().getName() + " attacked " + enemy.getClass().getName() +
                        " and caused " + damageGiven + " damage. " + enemy.getClass().getName() + "'s HP : " + enemy.getHPValue());

            // else sending a message, that attack was unsuccessful
            } else {
                System.out.println(this.getClass().getName() + " tried to attack " + enemy.getClass().getName() +
                        " but failed ");
            }

            // checking if that attack killed an enemy, if it is - sending the message
            if (isDiceSuccessful && !enemy.isAlive()) {
                System.out.println(this.getClass().getName() + " killed " + enemy.getClass().getName());
            }
        }
    }

    // just showing info about the creature
    public void showInfo() {
        System.out.printf("""
                        Name: %s
                        Attack value : %d
                        Armor value : %d
                        HP value : %d
                        Damage : %d - %d

                        """,
                this.getClass().getName(),
                this.getAttackValue(),
                this.getDefendValue(),
                this.getHPValue(),
                this.getDamageLowerLimit(),
                this.getDamageUpperLimit());
    }
}