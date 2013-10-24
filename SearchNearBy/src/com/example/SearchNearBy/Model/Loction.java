package com.example.SearchNearBy.Model;

/**
 * Created with IntelliJ IDEA.
 * User: Admin
 * Date: 13-10-21
 * Time: 下午1:18
 * To change this template use File | Settings | File Templates.
 */
public class Loction {
    private String name;
    private String  x;
    private String  y;
    private String address;
    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
