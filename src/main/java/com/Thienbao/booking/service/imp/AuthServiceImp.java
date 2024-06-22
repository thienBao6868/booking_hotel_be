package com.Thienbao.booking.service.imp;

import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.LogoutRequest;

public interface AuthServiceImp {
    User getUserByEmail(String email);
    void logout(LogoutRequest logoutRequest);
}
