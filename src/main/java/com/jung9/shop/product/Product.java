package com.jung9.shop.product;
import javax.persistence.*;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prdtId;

    private String imgPath;

    private String prdtName;

    private int prdtPrice;

    private int stock;

    private String productDetails;

    private int orderCount;

    private String prdtType;

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getPrdtName() {
        return prdtName;
    }

    public void setPrdtName(String prdtName) {
        this.prdtName = prdtName;
    }

    public int getPrdtPrice() {
        return prdtPrice;
    }

    public void setPrdtPrice(int prdtPrice) {
        this.prdtPrice = prdtPrice;
    }
}