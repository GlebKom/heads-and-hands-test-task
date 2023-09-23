package Main;

import Characters.Creature;
import Characters.Monster;
import Characters.Player;

public class Main {
    public static void main(String[] args) {
        Creature monster = new Monster(17, 13, 41, 5, 10);
        Player player = new Player(20, 0, 51, 3, 9);

        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        player.attack(monster);
        player.heal();
        player.heal();
        player.heal();
        player.heal();
        player.heal();
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        monster.attack(player);
        player.attack(monster);

    }
}