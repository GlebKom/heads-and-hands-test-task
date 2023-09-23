package Main;

import Game.Creature;
import Game.Monster;
import Game.Player;

public class Main {
    public static void main(String[] args) {
        Creature monster = new Monster(17, 13, 41, 5, 10);
        Player player = new Player(20, 13, 51, 3, 9);

        monster.showInfo();
        player.showInfo();

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