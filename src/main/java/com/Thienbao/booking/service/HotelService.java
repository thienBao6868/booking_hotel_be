package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.HotelDetailDto;
import com.Thienbao.booking.dto.HotelDto;
import com.Thienbao.booking.dto.HotelListDto;
import com.Thienbao.booking.exception.NotFoundException;
import com.Thienbao.booking.mapper.HotelMapper;
import com.Thienbao.booking.model.Hotel;
import com.Thienbao.booking.model.HotelAddress;
import com.Thienbao.booking.model.HotelImage;
import com.Thienbao.booking.model.User;
import com.Thienbao.booking.payload.request.InsertHotelRequest;
import com.Thienbao.booking.repository.HotelAddressRepository;
import com.Thienbao.booking.repository.HotelImageRepository;
import com.Thienbao.booking.repository.HotelRepository;
import com.Thienbao.booking.repository.UserRepository;
import com.Thienbao.booking.service.imp.FileServiceImp;
import com.Thienbao.booking.service.imp.HotelServiceImp;
import com.Thienbao.booking.utils.Helper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelService implements HotelServiceImp {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HotelAddressRepository hotelAddressRepository;

    @Override
    public List<HotelListDto> getHotels(){
        List<Hotel> hotels = hotelRepository.findAll();
        List<HotelListDto> hotelListDto = new ArrayList<>();
        for(Hotel hotel: hotels){
            hotelListDto.add(hotelMapper.hotelConvertToHotelListDto(hotel));
        }
        return hotelListDto;
    }

    @Override
    public HotelDetailDto getHotelDetail(int id){
        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        Hotel hotel = hotelRepository.findById(id).orElseThrow(()-> new NotFoundException("Not found hotel with id"));
        return hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto);
    }

    @Override
    public  List<HotelDetailDto> getHotelsByUserId(Long userId){

        HotelDetailDto hotelDetailDto = new HotelDetailDto();
        List<HotelDetailDto> hotelDetailDtoList = new ArrayList<>();
        List<Hotel> hotels = hotelRepository.findByUserId(userId);

        for (Hotel hotel: hotels)
        {
            hotelDetailDtoList.add(hotelMapper.hotelConvertHotelDetailDto(hotel,hotelDetailDto));
        }
        return hotelDetailDtoList;
    }


    @Autowired
    private FileServiceImp fileServiceImp;

    @Autowired
    private Helper helper;

    @Autowired
    private HotelImageRepository hotelImageRepository;

    //    @Transactional

    @Transactional
    @Override
    public Hotel insertHotel(HttpServletRequest request , InsertHotelRequest hotelRequest){
        fileServiceImp.saveFile(hotelRequest.getAvatar());

        Hotel hotelEntity = new Hotel();

        hotelEntity.setName(hotelRequest.getName());
        hotelEntity.setDescription(hotelRequest.getDescription());
        hotelEntity.setPhone(hotelRequest.getPhone());
        hotelEntity.setRating(hotelRequest.getRating());
        hotelEntity.setOpenTime(hotelRequest.getOpenTime());
        hotelEntity.setCloseTime(hotelRequest.getCloseTime());
        hotelEntity.setCheckinTime(hotelRequest.getCheckInTime());
        hotelEntity.setCheckoutTime(hotelRequest.getCheckOutTime());
        hotelEntity.setAvatar(hotelRequest.getAvatar().getOriginalFilename());

        User user = userRepository.findById(helper.getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        hotelEntity.setUser(user);


        HotelAddress hotelAddress = new HotelAddress();
        hotelAddress.setHotel(hotelRepository.save(hotelEntity));
        hotelAddress.setCity(hotelRequest.getCity());
        hotelAddress.setDistrict(hotelRequest.getDistrict());
        hotelAddress.setCountry(hotelRequest.getCountry());
        hotelAddress.setProvince(hotelRequest.getProvince());
        hotelAddress.setStreetName(hotelRequest.getStreetName());
        hotelAddress.setStreetNumber(hotelRequest.getStreetNumber());
        hotelAddressRepository.save(hotelAddress);

        HotelImage hotelImage = new HotelImage();
        hotelImage.setImageDescription(hotelRequest.getImageDesc());
        hotelImage.setImagePath(hotelRequest.getImagePath());
        hotelImage.setImageTitle(hotelRequest.getImageTitle());
        hotelImage.setUploadDate(hotelRequest.getUploadDate());
          hotelImage.setHotel(hotelRepository.save(hotelEntity));
        hotelImageRepository.save(hotelImage);





        return hotelRepository.save(hotelEntity);










    }

}
