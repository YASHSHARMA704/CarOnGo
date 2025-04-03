package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarListing {

    @Id
    @SequenceGenerator(name = "listing_seq",sequenceName = "listing_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "listing_seq")
    private Long listingId;

    @Column(nullable = false)
    @NotBlank(message = "Make is required")
    @Size(min = 1,max = 20,message = "make length should be in between 1 to 20 characters long")
    private String make;

    @Column(nullable = false)
    @NotBlank(message = "Model is required")
    @Size(min = 1,max = 20,message = "model length should be in between 1 to 20 characters long")
    private String model;

    @Column(nullable = false)
    @NotNull(message = "Year is required")
    @Digits(integer = 4, fraction = 0, message = "Year must be a 4-digit number")
    private Integer year;

    @Column(nullable = false)
    @NotNull(message = "Mileage is required")
    private Long mileage;

    @Column(nullable = false)
    @NotNull(message = "Price is required")
    @Digits(integer = 10,fraction = 2)
    private BigDecimal price;

    @Column(nullable = false)
    @NotBlank(message = "Description is required")
    @Size(min = 1,max = 60,message = "description length should be in between 1 to 60 characters long")
    private String description;

    @Column(nullable = false)
    @NotBlank(message = "Vehicle identification number is required")
    private String vin;

    @Column(nullable = false)
    @NotBlank(message = "Location is required")
    private String location;

    @Column(nullable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate listingDate = LocalDate.now();

    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean isSold = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "userId")
    private UserAccount userAccount;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "listingId")
    private VehicleHistoryReport vehicleHistoryReport;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "carListing")
    private CarImage carImage;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "carListing")
    private CarSpecification carSpecification;
}
