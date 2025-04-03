package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarImage {

    @Id
    @SequenceGenerator(name = "car_image_seq",sequenceName = "car_image_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "car_image_seq")
    private Long imageId;

    @Column(name = "car_image_url", nullable = false)
    @NotBlank(message = "Image Url is required")
    private String imageUrl;

    @Column(name = "is_main_image",nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isMainImage = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "listing_id",referencedColumnName = "listingId")
    private CarListing carListing;

}
