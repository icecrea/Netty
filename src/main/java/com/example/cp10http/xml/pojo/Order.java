package com.example.cp10http.xml.pojo;

import lombok.Data;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-26 13:39
 **/
@Data
public class Order {

    private long orderNumber;

    private Customer customer;

    private Address billTo;

    private Shipping shipping;

    private Address shipTo;

    private Float total;
}
