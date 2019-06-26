package com.example.cp10http.xml.pojo;

import lombok.Data;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-26 13:37
 **/
@Data
public class Address {
    private String street1;

    private String street2;

    private String city;

    private String state;

    private String postCode;

    private String country;
}
