package Utils;

public class SixEdgedDice implements DiceToss {
    private final RandomValue randomValue = new DefaultRandom();
    @Override
    public int toss() {
        return randomValue.randomValue(1, 6);
    }
}
