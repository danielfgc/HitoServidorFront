package com.front.models;

import lombok.Data;

@Data
public class Review {
    private Integer id;
    private int iduser;
    private int idcategory;
    private String title;
    private String body;
    private float valoration;
    private int likes;
    private int dislikes;

}
