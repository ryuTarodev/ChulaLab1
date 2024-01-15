package logic.game;

import logic.components.*;

import java.util.ArrayList;

public class GameController {
    public ArrayList<Market> markets;
    public ArrayList<Player> players;
    public ArrayList<Monster> monsters;
    public ArrayList<Ore> ores;
    public GameController instance;
    public boolean gameEnd;

    public GameController getInstance(){
        if (instance == null) {
            new GameController();
        }
        return instance;
    }

    public GameController() {
        markets = new ArrayList<>();
        players = new ArrayList<>();
        monsters = new ArrayList<>();
        ores = new ArrayList<>();
        ores.add(new Ore("Gold", 180));
        ores.add(new Ore("Silver", 140));
        ores.add(new Ore("Platinum", 250));
        ores.add(new Ore("Bronze", 40));
        gameEnd = false;
    }

    public void addMarket(Market market){
        markets.add(market);
    }

    public void addMonster(Monster monster){
        monsters.add(monster);
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public void endDay(){
        for (Player player: players) {
            player.setEnergy(player.getEnergy()-3);
            //TODO: deal Damage when Energy <0 = absolute of Energy
            if (player.getEnergy() < 0){
                player.getStatus().setHp(player.getStatus().getHp() - Math.abs(player.getEnergy()));
                player.setEnergy(0);
            }
            if (player.getStatus().getHp() < 0){
                player.getStatus().setHp(0);
            }
        }
    }

    public void removeDeadPlayerAndMonster(){
        players.removeIf(player -> player.getStatus().getHp() <= 0);
        monsters.removeIf(monster -> monster.getStatus().getHp() <= 0);
    }

    public boolean buyOre(int iplayer, int jnumber){

        if (iplayer <= players.size() && jnumber <= ores.size()){
            players.get(iplayer).buyOre(ores.get(jnumber));
            return true;
        }
        return false;

    }

    public boolean checkGameEnd(){
        if (!gameEnd){
            for (Player player: players){
                if (player.getOres().size() == 4) {
                    gameEnd = true;
                    break;
                }
            }
        }
        return gameEnd;
    }

    public Status createNewStatus(int hp, int durability, int attack, int magic){
        if (hp < 0 || durability < 0 || attack < 0 || magic < 0){
            return null;
        }
        return new Status(hp, durability, attack, magic);
    }
}
