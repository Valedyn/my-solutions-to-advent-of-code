package Day2;

public class Cube {
    private final Integer amount;
    private final CubeTypes type;

    public Cube(int amount, CubeTypes type){
        this.amount = amount;
        this.type = type;
    }

    public Integer getAmount(){
        return amount;
    }
    public CubeTypes getType(){
        return type;
    }
    @Override
    public String toString(){
        return String.format("%d %s", amount, type.toString().toLowerCase());
    }
}
