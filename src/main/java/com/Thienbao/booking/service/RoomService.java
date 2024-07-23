package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.AmenitiesDto;
import com.Thienbao.booking.dto.RoomDto;
import com.Thienbao.booking.dto.RoomTypeDto;
import com.Thienbao.booking.model.Room;
import com.Thienbao.booking.model.RoomAmenities;
import com.Thienbao.booking.repository.AmenitiesRepository;
import com.Thienbao.booking.repository.RoomRepository;
import com.Thienbao.booking.service.imp.RoomServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class RoomService implements RoomServiceImp {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomDto> getAllRoom() {

        List<Room> rooms = roomRepository.findAll();
        List<RoomDto> roomDTOS = new ArrayList<>();
        rooms.forEach(item ->{
            RoomDto roomDto = new RoomDto();

            roomDto.setNameHotel((item.getHotel().getName()));

            List<RoomTypeDto> roomTypeDtos = new ArrayList<>();
            for (Room room : item.getRoomType().getRoomList()){
                RoomTypeDto roomTypeDto = new RoomTypeDto();
                roomTypeDto.setId(room.getRoomType().getId());
                roomTypeDto.setName(room.getRoomType().getName());
                roomTypeDtos.add(roomTypeDto);
            };
            roomDto.setRoomType(roomTypeDtos);

            roomDto.setRoomNumber(item.getRoomNumber());
            roomDto.setPrice(item.getPrice());

            List<AmenitiesDto> amenitiesDTOList = new ArrayList<>();
            for (RoomAmenities roomAmenity : item.getRoomAmenitiesList()) {
                AmenitiesDto amenitiesDTO = new AmenitiesDto();
                amenitiesDTO.setId(roomAmenity.getAmenity().getId());
                amenitiesDTO.setName(roomAmenity.getAmenity().getName());
                amenitiesDTO.setIcon(roomAmenity.getAmenity().getIcon());
                amenitiesDTOList.add(amenitiesDTO);
            }
            roomDto.setAmenities(amenitiesDTOList);

            List<String> images = new ArrayList<>();
            item.getRoomImageList().forEach(itemImage ->{
                images.add(itemImage.getImagePath());
            });
            roomDto.setImage(images);

            roomDto.setStatus(item.getStatus());

            roomDTOS.add(roomDto);

        });
        return roomDTOS ;
    }
}
