package logic.components;

public class Monster {
    private String name;
    private Status status;
    private Food food;
    private Potion potion;

    public Monster(String name, Status status) {
        this.name = name;
        this.status = status;
        this.food = new Food("bread", 10 ,10);
        this.potion = null;
    }

    public void attack(Player player){
        int damage = status.getAttack()-player.getStatus().getDurability();
        if (damage < 0) damage = 1;
        player.getStatus().setHp(Math.max(player.getStatus().getHp() - damage, 0));

    }

    public  void magicAttack(Player player){
        int magicDamage = status.getMagic();
        player.getStatus().setHp(Math.max(player.getStatus().getHp() - magicDamage, 0));
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

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Potion getPotion() {
        return potion;
    }

    public void setPotion(Potion potion) {
        this.potion = potion;
    }
}
