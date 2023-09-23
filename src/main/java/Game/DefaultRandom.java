package Game;

import java.util.Random;

public class DefaultRandom implements RandomValue{
    @Override
    public int randomValue(int lowerLimit, int upperLimit) {
        Random random = new Random();
        return random.nextInt(lowerLimit, upperLimit);
    }
}
