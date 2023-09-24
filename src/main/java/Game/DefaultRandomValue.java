package Game;

import java.util.Random;

public class DefaultRandomValue implements RandomValue{
    @Override
    public int getRandomValue(int lowerLimit, int upperLimit) {
        Random random = new Random();
        return random.nextInt(lowerLimit, upperLimit);
    }
}
