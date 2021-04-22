package com.example.entity;

import lombok.*;
import javax.persistence.*;

@Entity
@Getter @Setter
@Builder @NoArgsConstructor @AllArgsConstructor
@Table
public class Face {

    @Id @GeneratedValue
    private Integer id;

    private String gender;
    private float age;
    private float width;
    private float height;
    private float jaw;
    private float eyebrow;
    private float nose;
    private float eye;
    private float lip;





}
