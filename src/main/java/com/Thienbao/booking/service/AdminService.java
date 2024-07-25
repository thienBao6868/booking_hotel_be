package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.UserDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.exception.UpdateException;
import com.Thienbao.booking.mapper.HotelMapper;
import com.Thienbao.booking.mapper.UserMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.AdminGetUserByEmailRequest;
import com.Thienbao.booking.payload.request.AdminGetUserByIdUserRequest;
import com.Thienbao.booking.payload.request.UpdateHotelByAdminRequest;
import com.Thienbao.booking.payload.request.UpdateUserByAdminRequest;
import com.Thienbao.booking.payload.response.BaseResponse;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.UserRepository;
import com.Thienbao.booking.service.imp.AdminServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public UserDto getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        User userFound = user.get();
        return userMapper.userConvertToUserDto(userFound);
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
    public String banUserByAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
//        user.ifPresent(u -> {
//            u.setDeleted(true);
//            userRepository.save(u);
//            System.out.println("Banned user");});
        if(user.isPresent()){
            User userBanned = user.get();
            userBanned.setDeleted(true);
            userRepository.save(userBanned);
            return "UserID " + id + " is banned!";
        } else {
            return "UserID " + id + " is not found.";
        }
    }

    @Override
    public String unbanUserByAdmin(long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User checkUserIsBanned = user.get();
            if (checkUserIsBanned.isDeleted()){
                checkUserIsBanned.setDeleted(false);
                userRepository.save(checkUserIsBanned);
                return  "UserID " + id + " is available now.";
            } else {
                return  "UserID " + id + " is not banned.";
            }
        } else {
            return "UserID " + id + " is not found.";
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
    }


}
