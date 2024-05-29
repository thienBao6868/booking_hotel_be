package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity(name = "hotel_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Column(name="image_title")
    private String imageTitle;

    @Column(name = "image_description")
    private String imageDescription;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "upload_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime uploadDate;

}
