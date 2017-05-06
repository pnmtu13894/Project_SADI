package com.minhtu.firebaseproject.Entities;

import java.util.Date;

/**
 * Created by minhtu on 5/6/17.
 */

public class ShoppingCart {

    private int cartID;
    private String customerID;
    //    TODO: noi tu dateAdded phai la class Date // Ok anh
    private Date dateAdded;

    ShoppingCart(String customerID) {
        this.customerID = customerID;
        this.dateAdded = new Date();
//        TODO: set cartID tu dong

    }

    //    TODO: hoi Tu tai sao lai phai co method addCartItem khi chi can call constructor cua class CartItem
//    public void addCartItem(int cartID, int productID){}
//    TODO: noi Tu la Tu thieu new quantity // Oops, my fault... Sorry anh
    public void updateQuantity(int cartID, int productID, int new_quant) {
//        TODO: to implement
    }

    public void deleteShoppingCart(int cartID){
        // TODO: to implement
    }

    //    TODO: noi Tu la neu khong co productID thi lam sao ma biet duoc cartItem nao to delete // Ok anh
    public void deleteCartItem(int cartID, int productID) {
//        TODO: to implement
    }

    //    TODO: noi Tu la khong co method view CartDetail vi de view CartDetail ta su dung mot method supply boi Firebase // Cai nay anh co the
    // noi so qua ve no' vao toi' mai khong a?
    public void checkOut() {
//        TODO: to implement
    }

    public double calculateTotoalPrice(int cartID) {
        return 0.0;
    }

    public int getCartID() {
        return cartID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

}
