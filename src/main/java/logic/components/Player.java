package logic.components;

import java.util.ArrayList;

public class Player {
    private String name;
    private Status status;
    private int energy;
    private int money;
    private ArrayList<Food> foods;
    private ArrayList<Potion> potions;
    private ArrayList<Ore> ores;

    public Player(String name, Status status) {
        this.name = name;
        this.status = status;
        energy = 10;
        money = 100;
        foods = new ArrayList<>();
        potions = new ArrayList<>();
        ores = new ArrayList<>();
    }

    public Player(String name, Status status, int energy, int money) {
        this.name = name;
        this.status = status;
        this.energy = energy;
        this.money = money;
        foods = new ArrayList<>();
        potions = new ArrayList<>();
        ores = new ArrayList<>();
    }

    public boolean buyOre(Ore ore){
        if (money > ore.getCost()){
            money -= ore.getCost();
            ores.add(ore);
            return true;}
        return false;
    }

    public void drinkPotion(int index){
        Potion potion = potions.get(index);
        status.addStatus(potion.getIncreasingStatus());
        potions.remove(potion);
    }

    public void eatFood(int index){
        Food food = foods.get(index);
        energy += food.getEnergy();
        foods.remove(food);
    }

    public void sellPotion(int index){
        Potion potion = potions.get(index);
        money += potion.getPrice();
        potions.remove(potion);
    }

    public void sellFood(int index){
        Food food = foods.get(index);
        money += food.getPrice();
        foods.remove(food);
    }

    //TODO: Attack - durability - hp
    public void attack(Monster monster){
        int damage = status.getAttack()-monster.getStatus().getDurability();
        if (damage < 0) damage = 1;
        monster.getStatus().setHp(Math.max(monster.getStatus().getHp() - damage, 0));

    }

    public  void magicAttack(Monster monster){
        int magicDamage = status.getMagic();
        monster.getStatus().setHp(Math.max(monster.getStatus().getHp() - magicDamage, 0));
     }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public ArrayList<Potion> getPotions() {
        return potions;
    }

    public void setPotions(ArrayList<Potion> potions) {
        this.potions = potions;
    }

    public ArrayList<Ore> getOres() {
        return ores;
    }

    public void setOres(ArrayList<Ore> ores) {
        this.ores = ores;
    }
}
