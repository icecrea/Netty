package com.example.cp10http.xml.pojo;

import lombok.Data;

import java.util.List;

/**
 * @description:
 * @author: icecrea
 * @create: 2019-06-26 13:38
 **/
@Data
public class Customer {
    private long customerNumber;

    private String firstName;

    private String lastName;

    private List<String> middleNames;
}
