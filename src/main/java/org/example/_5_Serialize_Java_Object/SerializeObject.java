package org.example._5_Serialize_Java_Object;

import java.io.Serializable;

public class SerializeObject implements Serializable {

    private String name;
    private int price;

    public SerializeObject(int price, String name) {
        this.price = price;
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SerializeObject{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
