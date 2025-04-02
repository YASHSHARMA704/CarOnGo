package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {

    @Id
    @SequenceGenerator(name = "profile_sequence",sequenceName = "profile_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "profile_sequence")
    @Column(name = "profile_id")
    private Long profileId;

    @NotEmpty(message = "First Name is required")
    @Size(min = 2,max = 15,message = "First name must be between 2 and 15 characters long")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotEmpty(message = "Last Name is required")
    @Size(min = 1,max = 15,message = "Last name must be between 1 and 15 characters long, If You don't have last name then use '.' ")
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @Column(name = "phone_number", nullable = false)
    @NotEmpty(message = "Phone number is required")
    private String phoneNumber;

    @NotEmpty(message = "Address is required")
    @Column(nullable = false)
    private String address;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private UserAccount userAccount;
}
