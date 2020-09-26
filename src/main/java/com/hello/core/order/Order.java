package com.hello.core.order;

public class Order {

    private Long mamberId;
    private String itemName;
    private int itemPrice;
    private int discountPrice;

    public Order(Long mamberId, String itemName, int itemPrice, int discountPrice) {
        this.mamberId = mamberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int calculatePrice() {
        return itemPrice - discountPrice;
    }

    public Long getMamberId() {
        return mamberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "mamberId=" + mamberId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
