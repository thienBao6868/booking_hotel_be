package com.Thienbao.booking.dto;

import com.Thienbao.booking.model.Booking;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.Role;
import com.Thienbao.booking.model.USER_SEX;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

    private Long id;

    private String email;

    private String fullName;

    private String avatar;

    private String phone;

    private String address;

    private USER_SEX sex;

    private boolean isDeleted;

    private Role role;

    private List<HotelDto> hotels;

}
