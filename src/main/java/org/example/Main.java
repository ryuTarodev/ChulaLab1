package org.example;

import logic.components.*;
import logic.game.GameController;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {
    public static void main(String[] args) {
        testStartGame();
    }
    public static void testStartGame(){
        System.out.println("Enter any string to go to next step");
        GameController game = new GameController();
        Scanner sc = new Scanner(System.in);
        String input = sc.next();


        while (!game.gameEnd){
            printMainMenu();
            input = sc.next();
            System.out.println("====================================");
            switch (input){
                case "0" -> exitGame() ;
                case "1" -> addMarket(sc, game);
                case "2" -> addMonster(sc, game);
                case "3" -> addPlayer(sc, game);
                case "4" -> addFoodToMarket(sc, game);
                case "5" -> addPotionToMarket(sc, game);
                case "6" -> listAllInfoPlayer(sc, game);
                case "7" -> listAllInfoMonster(sc, game);
                case "8" -> buyFoodOrPotionFromMarket(sc, game);
                case "9" -> playerAttackMonster(sc, game);
                case "10" -> monsterAttackPlayer(sc, game);
                case "11" -> playerBuyOre(sc, game);
                case "12" -> playerDrinkPotion(sc, game);
                case "13" -> playerEatFood(sc, game);
                case "14" -> playerSellPotion(sc, game);
                case "15" -> playerSellFood(sc, game);
                case "16" -> {
                    game.endDay();
                    game.removeDeadPlayerAndMonster();
                }
            }
        }

    }
    public static void printMainMenu(){
        System.out.println("====================================");
        System.out.println("Choose an option");
        System.out.println("------------------------------------");
        System.out.println("<1> Add Market");
        System.out.println("<2> Add Monster");
        System.out.println("<3> Add Player");
        System.out.println("<4> Add Food to the Market");
        System.out.println("<5> Add Potion to the Market");
        System.out.println("<6> Show all information of Player");
        System.out.println("<7> Show all information of Monster");
        System.out.println("<8> Buy Food or Potion From Market");
        System.out.println("<9> Player Attack Monster");
        System.out.println("<10> Monster Attack Player");
        System.out.println("<11> Buy Ore");
        System.out.println("<12> Drink Potion");
        System.out.println("<13> Eat Food");
        System.out.println("<14> Sell Potion");
        System.out.println("<15> Sell Food");
        System.out.println("<16> EndDay");
        System.out.println("------------------------------------");
        System.out.println("<0> Exit");
        System.out.println("====================================");
    }
    public static void exitGame() {
        System.out.println("Exiting the game. Thank you for playing!");
        System.exit(0);
    }
    public static void addMarket(Scanner sc, GameController game){
        System.out.println("Enter market name");
        String nameMarket = sc.next();
        game.addMarket(new Market(nameMarket));
        System.out.println("Market created");
    }
    public static void addMonster(Scanner sc, GameController game){
        System.out.println("Enter monster name");
        String nameMonster = sc.next();
        System.out.println("Enter hp");
        int hpMonster = sc.nextInt();
        System.out.println("Enter durability");
        int durabilityMonster = sc.nextInt();
        System.out.println("Enter attack");
        int attackMonster = sc.nextInt();
        System.out.println("Enter magic");
        int magicMonster = sc.nextInt();
        Status statusMonster = game.createNewStatus(hpMonster, durabilityMonster, attackMonster, magicMonster);
        game.addMonster(new Monster(nameMonster, statusMonster));
        System.out.println("Monster created");
    }
    public static void addPlayer(Scanner sc, GameController game){
        System.out.println("Enter player name");
        String namePlayer = sc.next();
        System.out.println("Enter hp");
        int hpPlayer = sc.nextInt();
        System.out.println("Enter durability");
        int durabilityPlayer = sc.nextInt();
        System.out.println("Enter attack");
        int attackPlayer = sc.nextInt();
        System.out.println("Enter magic");
        int magicPlayer = sc.nextInt();
        Status statusPlayer = game.createNewStatus(hpPlayer, durabilityPlayer, attackPlayer, magicPlayer);
        game.addPlayer(new Player(namePlayer, statusPlayer));
        System.out.println("Player created");
    }
    public static void addFoodToMarket(Scanner sc, GameController game){
        System.out.println("Choose market");
        for (int i = 0; i < game.markets.size(); i++) {
            System.out.printf("<%d> %s\n", i, game.markets.get(i).getName());
        }
        int chosenMarket = sc.nextInt();
        System.out.println("Enter food name");
        String nameFood = sc.next();
        System.out.println("Enter food price");
        int priceFood = sc.nextInt();
        System.out.println("Enter food Energy");
        int energyFood = sc.nextInt();
        game.markets.get(chosenMarket).getFoods().add(new Food(nameFood, priceFood, energyFood));
        System.out.println("Food is added to Market");
    }
    public static void addPotionToMarket(Scanner sc, GameController game){
        System.out.println("Choose market");
        for (int i = 0; i < game.markets.size(); i++) {
            System.out.printf("<%d> %s%n", i, game.markets.get(i).getName());
        }
        int chosenMarket = sc.nextInt();
        System.out.println("Enter Potion name");
        String namePotion = sc.next();
        System.out.println("Enter Potion price");
        int pricePotion = sc.nextInt();
        System.out.println("Enter Increasing hp");
        int hpPotion = sc.nextInt();
        System.out.println("Enter Increasing durability");
        int durabilityPotion = sc.nextInt();
        System.out.println("Enter Increasing attack");
        int attackPotion = sc.nextInt();
        System.out.println("Enter Increasing magic");
        int magicPotion = sc.nextInt();
        Status increasingStatusPotion = game.createNewStatus(hpPotion, durabilityPotion, attackPotion, magicPotion);
        game.markets.get(chosenMarket).getPotions().add(new Potion(namePotion, pricePotion, increasingStatusPotion));
        System.out.println("Potion is added to Market");
    }
    public static void listAllInfoPlayer(Scanner sc, GameController game){
        System.out.println("Choose player");
        for (int i = 0; i < game.players.size() ; i++) {
            System.out.printf("<%d> Player: %s\n", i, game.players.get(i).getName());
            System.out.printf("Hp: %d Durability: %d Attack: %d Magic: %d Energy: %d Money: %d\n"
                    ,game.players.get(i).getStatus().getHp()
                    ,game.players.get(i).getStatus().getDurability()
                    ,game.players.get(i).getStatus().getAttack()
                    ,game.players.get(i).getStatus().getMagic()
                    ,game.players.get(i).getEnergy()
                    ,game.players.get(i).getMoney());
        }
        int chosenPlayer = sc.nextInt();
        System.out.printf("Player: %s\n", game.players.get(chosenPlayer).getName());
        System.out.printf("Hp: %d Durability: %d Attack: %d Magic: %d Energy: %d Money: %d\n"
                ,game.players.get(chosenPlayer).getStatus().getHp()
                ,game.players.get(chosenPlayer).getStatus().getDurability()
                ,game.players.get(chosenPlayer).getStatus().getAttack()
                ,game.players.get(chosenPlayer).getStatus().getMagic()
                ,game.players.get(chosenPlayer).getEnergy()
                ,game.players.get(chosenPlayer).getMoney());
        System.out.println("Food:");
        for (Food food: game.players.get(chosenPlayer).getFoods()){
            System.out.println(food.getName());
        }
        System.out.println("Potion:");
        for (Potion potion: game.players.get(chosenPlayer).getPotions()){
            System.out.println(potion.getName());
        }
        System.out.println("Ore:");
        for (Ore ore: game.players.get(chosenPlayer).getOres()){
            System.out.println(ore.getName());
        }
    }
    public static void listAllInfoMonster(Scanner sc, GameController game){
        System.out.println("Choose Monster");
        for (int i = 0; i < game.monsters.size() ; i++) {
            System.out.printf("<%d> Monster: %s\n", i, game.monsters.get(i).getName());
            System.out.printf("Hp: %d Durability: %d Attack: %d Magic: %d\n"
                    ,game.monsters.get(i).getStatus().getHp()
                    ,game.monsters.get(i).getStatus().getDurability()
                    ,game.monsters.get(i).getStatus().getAttack()
                    ,game.monsters.get(i).getStatus().getMagic());
        }
        int chosenMonster = sc.nextInt();
        System.out.printf("Monster: %s\n", game.monsters.get(chosenMonster).getName());
        System.out.printf("Hp: %d Durability: %d Attack: %d Magic: %d\n"
                ,game.monsters.get(chosenMonster).getStatus().getHp()
                ,game.monsters.get(chosenMonster).getStatus().getDurability()
                ,game.monsters.get(chosenMonster).getStatus().getAttack()
                ,game.monsters.get(chosenMonster).getStatus().getMagic());
        System.out.printf("Drop Food: %s, Drop Potion: %s\n", game.monsters.get(chosenMonster).getFood(), game.monsters.get(chosenMonster).getPotion());
    }
    public static void buyFoodOrPotionFromMarket(Scanner sc, GameController game){
        System.out.println("Choose one choice");
        System.out.println("<1> Buy Potion");
        System.out.println("<2> Buy Food");
        int choice = sc.nextInt();
        System.out.println("Choose player who is buyer");
        for (int i = 0; i < game.players.size(); i++) {
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
            System.out.printf("Money: %d\n", game.players.get(i).getMoney());
        }
        int chosenBuyer = sc.nextInt();
        System.out.println("Choose Market");
        for (int i = 0; i < game.markets.size(); i++) {
            System.out.printf("<%d> %s\n", i, game.markets.get(i).getName());
        }
        int chosenMarket = sc.nextInt();
        switch (choice) {
            case 1 -> {
                System.out.println("Choose Potion you want to buy");
                for (int i = 0; i < game.markets.get(chosenMarket).getPotions().size(); i++) {
                    System.out.printf("<%d> %s\n", i, game.markets.get(chosenMarket).getPotions().get(i).getName());
                    System.out.printf("Increasing Status Hp: %d Durability: %d Attack: %d Magic: %d\n"
                            , game.markets.get(chosenMarket).getPotions().get(i).getIncreasingStatus().getHp()
                            , game.markets.get(chosenMarket).getPotions().get(i).getIncreasingStatus().getDurability()
                            , game.markets.get(chosenMarket).getPotions().get(i).getIncreasingStatus().getAttack()
                            , game.markets.get(chosenMarket).getPotions().get(i).getIncreasingStatus().getMagic());
                    System.out.printf("Price: %d\n", game.markets.get(chosenMarket).getPotions().get(i).getPrice());
                }
                int chosenPotion = sc.nextInt();
                //TODO: money = money - price
                game.players.get(chosenBuyer).setMoney(game.players.get(chosenBuyer).getMoney()-game.markets.get(chosenMarket).getPotions().get(chosenPotion).getPrice());
                //TODO : add Potion to Player , remove from Market
                game.players.get(chosenBuyer).getPotions().add(game.markets.get(chosenMarket).getPotions().get(chosenPotion));
                game.markets.get(chosenMarket).getPotions().remove(game.markets.get(chosenMarket).getPotions().get(chosenPotion));
                System.out.println("Buy potion completed");
            }
            case 2 -> {
                System.out.println("Choose Food you want to buy");
                for (int i = 0; i < game.markets.get(chosenMarket).getFoods().size(); i++) {
                    System.out.printf("<%d> %s\n", i, game.markets.get(chosenMarket).getFoods().get(i).getName());
                    System.out.printf("Price: %d Energy: %d\n", game.markets.get(chosenMarket).getFoods().get(i).getPrice(), game.markets.get(chosenMarket).getFoods().get(i).getEnergy());
                }
                int chosenFood = sc.nextInt();
                //TODO: money = money - price
                game.players.get(chosenBuyer).setMoney(game.players.get(chosenBuyer).getMoney()-game.markets.get(chosenMarket).getFoods().get(chosenFood).getPrice());
                //TODO : add Food to Player , remove from Market
                game.players.get(chosenBuyer).getFoods().add(game.markets.get(chosenMarket).getFoods().get(chosenFood));
                game.markets.get(chosenMarket).getFoods().remove(game.markets.get(chosenMarket).getFoods().get(chosenFood));
                System.out.println("Buy food completed");
            }
        }
    }
    public static void playerAttackMonster(Scanner sc, GameController game){
        System.out.println("Choose Player which is attacker");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Monster which is attacked");
        for (int i = 0; i < game.monsters.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.monsters.get(i).getName());
        }
        int chosenMonster = sc.nextInt();
        System.out.println("Choose type of attack");
        System.out.println("<1> Normal Attack");
        System.out.println("<2> Magic Attack");
        int chosenAttackType = sc.nextInt();
        switch (chosenAttackType) {
            case 1 -> game.players.get(chosenPlayer).attack(game.monsters.get(chosenMonster));
            case 2 -> game.players.get(chosenPlayer).magicAttack(game.monsters.get(chosenMonster));
        }
        System.out.printf("Monster %s now have hp %d\n", game.monsters.get(chosenMonster).getName(), game.monsters.get(chosenMonster).getStatus().getHp());
        //TODO: Monster Drop Items
        if (game.monsters.get(chosenMonster).getStatus().getHp() <= 0){
            game.players.get(chosenPlayer).getFoods().add(game.monsters.get(chosenPlayer).getFood());
            game.players.get(chosenPlayer).getPotions().add(game.monsters.get(chosenPlayer).getPotion());
            System.out.printf("Monster Drop: %s, %s\n", game.monsters.get(chosenPlayer).getFood().getName(), game.monsters.get(chosenPlayer).getPotion().getName());
        }
        game.removeDeadPlayerAndMonster();
    }
    public static void monsterAttackPlayer(Scanner sc, GameController game){
        System.out.println("Choose Monster which is attacker");
        for (int i = 0 ; i < game.monsters.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.monsters.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Player which is attacked");
        for (int i = 0; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenMonster = sc.nextInt();
        System.out.println("Choose type of attack");
        System.out.println("<1> Normal Attack");
        System.out.println("<2> Magic Attack");
        int chosenAttackType = sc.nextInt();
        switch (chosenAttackType) {
            case 1 -> game.monsters.get(chosenPlayer).attack(game.players.get(chosenMonster));
            case 2 -> game.monsters.get(chosenPlayer).magicAttack(game.players.get(chosenMonster));
        }
        System.out.printf("Monster %s now have hp %d\n", game.players.get(chosenMonster).getName(), game.players.get(chosenMonster).getStatus().getHp());
        game.removeDeadPlayerAndMonster();

    }
    public static void playerBuyOre(Scanner sc, GameController game){
        System.out.println("Choose Player");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
            System.out.printf("Money: %d\n", game.players.get(i).getMoney());
            StringJoiner sj = new StringJoiner(" ");
            for (Ore ore: game.players.get(i).getOres()){
                sj.add(ore.getName());
            }
            System.out.printf("Ore: %s\n", sj);
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose ore you want to buy");
        for (int i = 0; i < game.ores.size() ; i++){
            System.out.printf("<%d> %s Cost: %d\n", i, game.ores.get(i).getName(), game.ores.get(i).getCost());
        }
        int chosenOre = sc.nextInt();
        game.buyOre(chosenPlayer,chosenOre);
        System.out.println("Buy ore success");
    }
    public static void playerDrinkPotion(Scanner sc, GameController game){
        System.out.println("Choose Player");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Potion you want to drink");
        for (int i = 0; i< game.players.get(chosenPlayer).getPotions().size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(chosenPlayer).getPotions().get(i).getName());
            System.out.printf("Increasing Status Hp : %d Durable : %d Attack : %d Magic : %d\n"
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getHp()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getDurability()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getAttack()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getMagic());
            System.out.printf("Price : %d", game.players.get(chosenPlayer).getPotions().get(i).getPrice());
        }
        int chosenPotion = sc.nextInt();
        game.players.get(chosenPlayer).drinkPotion(chosenPotion);
        System.out.println("Drink Potion completed");
    }
    public static void playerEatFood(Scanner sc, GameController game){
        System.out.println("Choose Player");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Food you want to eat");
        for (int i = 0 ; i < game.players.get(chosenPlayer).getFoods().size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(chosenPlayer).getFoods().get(i).getName());
            System.out.printf("Energy : %d Price : %d\n", game.players.get(chosenPlayer).getFoods().get(i).getEnergy(), game.players.get(chosenPlayer).getFoods().get(i).getPrice());
        }
        int chosenFood = sc.nextInt();
        game.players.get(chosenPlayer).eatFood(chosenFood);
        System.out.println("Consume Food completed");
    }
    public static void playerSellPotion(Scanner sc, GameController game){
        System.out.println("Choose Player");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Potion you want to drink");
        for (int i = 0; i< game.players.get(chosenPlayer).getPotions().size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(chosenPlayer).getPotions().get(i).getName());
            System.out.printf("Increasing Status Hp : %d Durable : %d Attack : %d Magic : %d\n"
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getHp()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getDurability()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getAttack()
                    , game.players.get(chosenPlayer).getPotions().get(i).getIncreasingStatus().getMagic());
            System.out.printf("Price : %d", game.players.get(chosenPlayer).getPotions().get(i).getPrice());
        }
        int chosenPotion = sc.nextInt();
        game.players.get(chosenPlayer).sellPotion(chosenPotion);
        System.out.println("Sell Potion completed");
    }
    public static void playerSellFood(Scanner sc, GameController game){
        System.out.println("Choose Player");
        for (int i = 0 ; i < game.players.size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(i).getName());
        }
        int chosenPlayer = sc.nextInt();
        System.out.println("Choose Food you want to eat");
        for (int i = 0 ; i < game.players.get(chosenPlayer).getFoods().size() ; i++){
            System.out.printf("<%d> %s\n", i, game.players.get(chosenPlayer).getFoods().get(i).getName());
            System.out.printf("Energy : %d Price : %d\n", game.players.get(chosenPlayer).getFoods().get(i).getEnergy(), game.players.get(chosenPlayer).getFoods().get(i).getPrice());
        }
        int chosenFood = sc.nextInt();
        game.players.get(chosenPlayer).sellFood(chosenFood);
        System.out.println("Sell Food completed");
    }

}



  
