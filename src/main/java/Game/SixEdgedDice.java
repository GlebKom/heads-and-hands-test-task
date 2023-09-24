package Game;

class SixEdgedDice implements DiceToss {
    private final RandomValue randomValue = new DefaultRandomValue();
    @Override
    public int toss() {
        return randomValue.getRandomValue(1, 6);
    }
}
