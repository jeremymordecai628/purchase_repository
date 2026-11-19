package com.modex.webapp.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "organizations")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private  String offices

    @OneToMany(mappedBy = "organization")
    private List<User> users;

    // Getters and setters
}
