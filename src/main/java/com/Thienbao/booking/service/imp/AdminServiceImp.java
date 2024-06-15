package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;

public interface AdminServiceImp {
    UserDto getUserById(Long id);
    UserDto updateUserByAdmin(UpdateUserByAdminRequest updateUserByAdminRequest);

    HotelDto updateHotelByAdmin(UpdateHotelByAdminRequest request);
}
