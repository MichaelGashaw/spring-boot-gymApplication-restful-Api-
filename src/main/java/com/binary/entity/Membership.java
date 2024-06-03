package com.binary.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "membership_table")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Membership {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private double price;
    private String duration;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "member_id")
    private Member member;


    public Membership(String type, double price, String duration, Member member) {
        this.type = type;
        this.price = price;
        this.duration = duration;
        this.member = member;
    }
}