package com.practice.lld.Pattern;
 interface Coffee {

    int getCost();
    String getDescription();
}

class Espresso implements  Coffee{

    @Override
    public int getCost() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "expresso coffee";
    }
}
abstract class CoffeeDecorator implements  Coffee{
    Coffee coffee;

}
class MilkDecoraotr extends CoffeeDecorator{

    public MilkDecoraotr(Coffee coffe) {
        super();
        this.coffee = coffe;
    }

    @Override
    public int getCost() {
        return  coffee.getCost()+150;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription()+",milk added";
    }
}
class FoamDecoraotr extends CoffeeDecorator{

    public FoamDecoraotr(Coffee coffe) {
        super();
        this.coffee = coffe;
    }


    @Override
    public int getCost() {
        return coffee.getCost()+100;
    }

    @Override
    public String getDescription() {
        return coffee.getDescription()+",Foam added";
    }
}
public class Main{

    public static void main(String[] args) {
        Coffee coffe = new Espresso();
        coffe = new MilkDecoraotr(coffe);
        coffe = new FoamDecoraotr(coffe);
        System.out.println("price"+coffe.getCost());
        System.out.println("desc::"+coffe.getDescription());
    }

}
