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
public class CarSpecification {

    @Id
    @SequenceGenerator(name = "specification_seq",sequenceName = "specification_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "specification_seq")
    private Long specificationId;

    @Column(nullable = false)
    @NotBlank(message = "Transmission is required")
    private String transmission;

    @Column(nullable = false)
    @NotBlank(message = "Fuel Type is required")
    private String fuelType;

    @Column(nullable = false)
    @NotBlank(message = "Color is required")
    private String color;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_id",referencedColumnName = "listingId")
    private CarListing carListing;
}
