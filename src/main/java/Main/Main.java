package Main;

import Game.Creature;
import Game.Monster;
import Game.Player;

public class Main {
    public static void main(String[] args) {

        // clearly indicated creatures
        System.out.println("Clearly indicated creatures\n");
        Monster monster = new Monster(23, 13, 41, 5, 10);
        Player player = new Player(20, 11, 51, 1, 10);

        monster.showInfo();
        player.showInfo();

        while (player.isAlive() && monster.isAlive()) {
            player.attack(monster);
            monster.attack(player);

            if ((float) player.getHPValue() / player.getMAX_HP_VALUE() < 0.3f) {
                player.heal();
            }

        }

        // randomly indicated creatures
        System.out.println("\nRandomly indicated creatures\n");
        Monster randomMonster = new Monster();
        Player randomPlayer = new Player();

        randomMonster.showInfo();
        randomPlayer.showInfo();

        while (randomPlayer.isAlive() && randomMonster.isAlive()) {
            randomPlayer.attack(randomMonster);
            randomMonster.attack(randomPlayer);

            if ((float) player.getHPValue() / player.getMAX_HP_VALUE() < 0.3f) {
                player.heal();
            }

        }
    }
}