package logic.components;

public class Status{
    private int hp;
    private int durability;
    private int attack;
    private int magic;

    public Status(int hp, int durability, int attack, int magic) {
        this.setHp(hp);
        this.setDurability(durability);
        this.setAttack(attack);
        this.setMagic(magic);
    }

    @Override
    public boolean equals(Object obj) {
        Status status = (Status) obj ;
        return status.getHp() == hp && status.getDurability() == durability && status.getAttack() == attack && status.getMagic() == magic;
    }

    public void addStatus(Status another){
        if(another.getHp() < 0 || another.getDurability() < 0 || another.getAttack() < 0 || another.getMagic() < 0){
            throw new IllegalArgumentException("Bad Status");
        }
        this.hp += another.getHp();
        this.durability += another.getDurability();
        this.attack += another.getAttack();
        this.magic += another.getMagic();


    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp < 0) throw new IllegalArgumentException("Bad Status");
        this.hp = hp;
    }

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        if (durability < 0) throw new IllegalArgumentException("Bad Status");
        this.durability = durability;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        if (attack < 0) throw new IllegalArgumentException("Bad Status");
        this.attack = attack;
    }

    public int getMagic() {
        return magic;
    }

    public void setMagic(int magic) {
        if (magic < 0) throw new IllegalArgumentException("Bad Status");
        this.magic = magic;
    }

    @Override
    public String toString() {
        return "Status{" +
                "hp=" + hp +
                ", durability=" + durability +
                ", attack=" + attack +
                ", magic=" + magic +
                '}';
    }
}
