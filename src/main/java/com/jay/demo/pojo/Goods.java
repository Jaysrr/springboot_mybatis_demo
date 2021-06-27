package com.jay.demo.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: springboot_mybatis_demo
 * @description: 生成的pojo
 **/
public class Goods {
    private Long id;

    private String name;

    private String code;

    private String type;

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", intro='" + intro + '\'' +
                ", creater='" + creater + '\'' +
                ", ctime=" + ctime +
                ", updater='" + updater + '\'' +
                ", utime=" + utime +
                ", status=" + status +
                ", picture='" + picture + '\'' +
                ", warehouse=" + warehouse +
                '}';
    }

    private String brand;

    private BigDecimal price;

    private String intro;

    private String creater;

    private Date ctime;

    private String updater;

    private Date utime;

    private Boolean status;

    private String picture;

    private Long warehouse;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand == null ? null : brand.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater == null ? null : creater.trim();
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getUtime() {
        return utime;
    }

    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public Long getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Long warehouse) {
        this.warehouse = warehouse;
    }
}