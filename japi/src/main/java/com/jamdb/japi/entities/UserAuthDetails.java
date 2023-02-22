package com.jamdb.japi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;

@Entity(name="user_auth_details")

public class UserAuthDetails extends BaseEntity{
    @MapsId("uuid")
    @OneToOne
    private User user;
    @Column(name = "user_name",unique = true,nullable = true)
    private String userName;

    @Column(name="password",nullable = false)
    private

}
