package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;

public interface AdminServiceImp {
    UserDto getUserById(Long id);
    UserDto getUserByEmail(String email);
    UserDto updateUserByAdmin(UpdateUserByAdminRequest updateUserByAdminRequest);
    String banUserByAdmin(long id);
    String unbanUserByAdmin(long id);
    HotelDto updateHotelByAdmin(UpdateHotelByAdminRequest request);
}
