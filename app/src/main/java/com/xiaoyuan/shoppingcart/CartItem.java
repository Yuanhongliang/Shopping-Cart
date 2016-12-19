package com.xiaoyuan.shoppingcart;

/**
 * 购物车Item
 * Created by Administrator on 2016/12/19.
 */
public class CartItem {
    private String title;
    private String imgUrl;
    private String price;
    private boolean check;

    public CartItem() {
    }

    public CartItem(String title, String price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
