package com.Thienbao.booking.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name="fullname")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "sex")
    private USER_SEX sex;

    @Column(name = "isDeleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="role_id",referencedColumnName = "id")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Hotel> hotels;

    @OneToMany(mappedBy = "user")
    private List<HotelReviews> hotelReviews;

    @OneToMany(mappedBy = "user")
    private List<ReviewReplies> reviewReplies;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookingList;

}
