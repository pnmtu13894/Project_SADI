package com.minhtu.firebaseproject.Entities;

import android.location.Address;

import java.util.Date;
import java.util.Locale;

/**
 * Created by minhtu on 5/6/17.
 */

public class Order {

    private int orderID;
    private int cartID;
    private Date dateCreated;
    private Date dateShipped;
    //    TODO: hoi Tu tai sao lai chua field customerID khi co the get field nay tu orderID // Ok anh, minh co the get no tu` Shopping Cart
//    private String customerID;
    private String status;
    //    TODO: hoi y kien tu ve su dung class Address de chua class Address // Y' anh la xai google Api Address Location?
    private Address shippingAddress;

    //    TODO: hỏi Tú cho phép mình tách address ra thành country, tỉnh/thành, quận
    Order(int cartID, String shipAddLine, String shipAddCountry, String shipAddProv, String shipAddDis) {
        this.cartID = cartID;
        this.dateCreated = new Date();
        this.dateShipped = null;
//        TODO: implement this.orderID tu dong
        this.status = "Not shipped";
        this.shippingAddress = new Address(new Locale("vi", "VN"));
        this.shippingAddress.setCountryName(shipAddCountry);
        this.shippingAddress.setAdminArea(shipAddProv);
        this.shippingAddress.setSubAdminArea(shipAddDis);
        this.shippingAddress.setAddressLine(0, shipAddLine);
    }

    public void cancleOrder(int orderID) {
//        TODO: to implement

    }

    public String getOrderStatus() {
//        TODO: to implement
        return "";
    }

    public void placeOrder() {
//        TODO: to implement
    }

    public int getOrderID() {
        return orderID;
    }

    public int getCartID() {
        return cartID;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

}
