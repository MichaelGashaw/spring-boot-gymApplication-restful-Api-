package com.binary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "member_table")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Membership> memberships;

    public Member(String firstName, String lastName, String email, String phone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }
}