package logic.components;

public class Potion {
    private String name;
    private int price;
    private Status increasingStatus;

    public Potion(String name, int price, Status increasingStatus) {
        this.name = name;
        this.price = Math.max(price, 1);
        this.increasingStatus = increasingStatus;
    }

    @Override
    public boolean equals(Object obj) {
        Potion potion = (Potion) obj;
        return potion.getName().equals(name) && potion.getPrice() == price && potion.getIncreasingStatus().equals(increasingStatus);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = Math.max(price, 1);
    }

    public Status getIncreasingStatus() {
        return increasingStatus;
    }

    public void setIncreasingStatus(Status increasingStatus) {
        this.increasingStatus = increasingStatus;
    }
}
