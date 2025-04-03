package com.carongo.CarOnGo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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
public class VehicleHistoryReport {

    @Id
    @SequenceGenerator(name = "report_seq",sequenceName = "report_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "report_seq")
    private Long reportId;

    @Column(nullable = false)
    @NotBlank(message = "Report Url is required")
    private String reportUrl;

    @Column(nullable = false,columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate reportDate = LocalDate.now();

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "listing_id",referencedColumnName = "listingId")
    private CarListing listingId;
}
