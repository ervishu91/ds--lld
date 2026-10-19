package com.practice.ds.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class StockminaxWithPriceCorrection {

    Map<Integer,Integer> priceMap;
    TreeMap<Integer,Integer> priceFreq;

    StockminaxWithPriceCorrection(){
        priceMap = new HashMap<>();
        priceFreq = new TreeMap<>();

    }
    public void UpsertPrice(int timeStamp, int price){
        Integer oldValue = priceMap.put(timeStamp,price);
        if(oldValue!=null && priceFreq.containsKey(oldValue)){
            int oldFreq  = priceFreq.get(oldValue);
            if(oldFreq<=1)
                priceFreq.remove(oldValue);
            else
                priceFreq.put(oldValue,oldFreq-1);
        }
            priceFreq.put(price,priceFreq.getOrDefault(price,0)+1);

    }
    public int getMinprice(){
        if (priceFreq.isEmpty())
            return -1;
        return priceFreq.firstKey();
    }
    public int getMaxprice(){
        if (priceFreq.isEmpty())
            return -1;
        return priceFreq.lastKey();
    }

    public static void main(String[] args) {
        StockminaxWithPriceCorrection stockPrice = new StockminaxWithPriceCorrection();




        System.out.println("min is"+stockPrice.getMinprice()+",max is:"+stockPrice.getMaxprice());
    }
}
