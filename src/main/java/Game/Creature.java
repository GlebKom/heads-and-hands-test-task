package Game;

public abstract class Creature {

    private final int attackValue;
    private final int defendValue;
    private long HPValue;
    private final int damageLowerLimit;
    private final int damageUpperLimit;
    private final long MAX_HP_VALUE;
    private AttackModifier attackModifier;
    private DiceToss dice;
    private RandomValue ruleForRandom;

    protected void setAttackModifier(AttackModifier attackModifier) {
        this.attackModifier = attackModifier;
    }

    protected void setDice(DiceToss dice) {
        this.dice = dice;
    }

    protected void setRuleForRandom(RandomValue ruleForRandom) {
        this.ruleForRandom = ruleForRandom;
    }

    protected void setDefaultAttackModifier() {
        this.attackModifier = new DefaultAttackModifier();
    }

    protected void setDefaultDice() {
        this.dice = new SixEdgedDice();
    }

    protected void setDefaultRuleForRandom() {
        this.ruleForRandom = new DefaultRandom();
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
        }

        this.attackValue = attackValue;
        this.defendValue = defendValue;
        this.HPValue = HPValue;
        this.MAX_HP_VALUE = HPValue;
        this.damageLowerLimit = damageLowerLimit;
        this.damageUpperLimit = damageUpperLimit;

        setDefaultDice();
        setDefaultAttackModifier();
        setDefaultRuleForRandom();
    }

    protected int getAttackValue() {
        return attackValue;
    }

    protected int getDefendValue() {
        return defendValue;
    }


    protected long getHPValue() {
        return this.HPValue;
    }

    protected boolean isAlive() {
        return this.getHPValue() > 0;
    }

    protected void setHPValue(long HPValue) {
        if (HPValue <= 0) {
            // hp < 0 means, that this character is already dead
            this.HPValue = 0;

        } else {
            this.HPValue = HPValue;
        }
    }

    int getDamageUpperLimit() {
        return this.damageUpperLimit;
    }

    protected int getDamageLowerLimit() {
        return this.damageLowerLimit;
    }

    protected long getMAX_HP_VALUE() {
        return MAX_HP_VALUE;
    }

    public void attack(Creature enemy){

        if (this == enemy) {
            // when character tries to attack himself
            throw new IllegalArgumentException("Character can't hurt himself");
        }

        if (!this.isAlive()) {

            // if this character is already dead
            System.out.println(this.getClass().getName() + " wants revenge and still can't believe he's already dead.");

        } else if (!enemy.isAlive()) {

            // if enemy character is already dead
            System.out.println(this.getClass().getName() + " wanted to fight, but, unfortunately, " +
                    enemy.getClass().getName() + " is already dead.");

        } else {

            // checking is at least one dice toss was successful or not, by default it is not
            boolean isSuccess = false;
            int triesCount = this.attackModifier.getAttackModifier(this, enemy) > 0
                    ? this.attackModifier.getAttackModifier(this, enemy)
                    : 1;

            // tossing a dice
            for (int tryCount = 0; tryCount < triesCount; tryCount++) {
                if (dice.toss() >= 5) {
                    isSuccess = true;
                    break;
                }
            }

            // decreasing enemy's hp if toss was successful
            if (isSuccess) {
                int damageGiven = ruleForRandom.randomValue(this.getDamageLowerLimit(), this.getDamageUpperLimit());

                enemy.setHPValue(enemy.getHPValue() - damageGiven);

                System.out.println(this.getClass().getName() + " attacked " + enemy.getClass().getName() +
                        " and caused " + damageGiven + " damage. " + enemy.getClass().getName() + "'s HP : " + enemy.getHPValue());

            } else {
                System.out.println(this.getClass().getName() + " tried to attack " + enemy.getClass().getName() +
                        " but failed ");
            }
        }
    }

    public void showInfo() {
        System.out.printf("Name: %s\n" +
                "Attack value : %d\n" +
                "Armor value : %d\n" +
                "HP value : %d\n" +
                "Damage : %d - %d\n\n",
                this.getClass().getName(),
                this.getAttackValue(),
                this.getDefendValue(),
                this.getHPValue(),
                this.getDamageLowerLimit(),
                this.getDamageUpperLimit());
    }
}