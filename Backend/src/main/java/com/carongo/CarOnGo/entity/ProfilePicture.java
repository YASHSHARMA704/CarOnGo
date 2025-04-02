package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfilePicture {

    @Id
    @SequenceGenerator(name = "profilePicture_sequence",sequenceName = "profilePicture_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "profilePicture_sequence")
    @Column(name = "profilePicture_id")
    private Long profilePictureId;

    // extra work -> Stores default url based on name.
    @NotEmpty(message = "Image URL is required")
    @Column(name = "image_url", nullable = false)
    private String imageUrl;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private UserAccount userAccount;
}
