package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.exception.UpdateException;
import com.Thienbao.booking.mapper.HotelMapper;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.UserRepository;
import com.Thienbao.booking.service.imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService implements AdminServiceImp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HotelMapper hotelMapper;

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Not found user with id " + id));
        return userMapper.userConvertToUserDto(user);
    }


    @Override
    public UserDto updateUserByAdmin(UpdateUserByAdminRequest updateUserByAdminRequest) {
        User user = userRepository.findById(updateUserByAdminRequest.getUserId()).orElseThrow(() -> new NotFoundException("Not found user with id " + updateUserByAdminRequest.getUserId()));
        try {
            user.setDeleted(updateUserByAdminRequest.isDeleted());
            return userMapper.userConvertToUserDto(userRepository.save(user));
        } catch (Exception ex) {
            throw new UpdateException("Error update User By Admin: " + ex.getMessage());
        }
    }

    @Override
    public HotelDto updateHotelByAdmin(UpdateHotelByAdminRequest request) {
        Hotel hotel = hotelRepository.findById(request.getHotelId()).orElseThrow(() -> new NotFoundException("Not found hotel with Id: " + request.getHotelId()));
        try {
            hotel.setDeleted(request.isDeleted());
            return hotelMapper.hotelConvertToHotelDto(hotelRepository.save(hotel));
        } catch (Exception ex) {
            throw new UpdateException("Error update Hotel By Admin: " + ex.getMessage());
        }
    };
}
