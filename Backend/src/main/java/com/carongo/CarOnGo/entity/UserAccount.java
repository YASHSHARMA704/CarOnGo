package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAccount {

    @Id
    @SequenceGenerator(name = "user_sequence",sequenceName = "user_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_sequence")
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "user_name",nullable = false,unique = true)
    @NotEmpty(message = "Username is required")
    @Size(min = 3,max = 15,message = "Username must be between 3 and 15 characters long")
    private String userName;

    @Column(nullable = false)
    @NotEmpty(message = "Password is required")
    @Size(min=4,message = "Password length should be at least 4")
    private String password;

    @Column(nullable = false,unique = true)
    @NotEmpty(message = "Email is required")
    private String email;

    @Column(name = "role",nullable = false,columnDefinition = "VARCHAR(50) DEFAULT 'BUYER'")
    private Role role = Role.BUYER;

    @Column(name = "registration_date", nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate registrationDate = LocalDate.now();

    @Column(name = "is_verified",nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isVerified = false;

    public enum Role{
        ADMIN,BUYER,SELLER
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userAccount")
    private ProfilePicture profilePicture;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "userAccount",fetch = FetchType.EAGER)
    private UserProfile userProfile;

    @OneToMany(mappedBy = "userAccount", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private CarListing carListing;

}
