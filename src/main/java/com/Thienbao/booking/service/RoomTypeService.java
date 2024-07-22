package com.Thienbao.booking.service;

import com.Thienbao.booking.dto.RoomTypeDto;
import com.Thienbao.booking.exception.DeleteException;
import com.Thienbao.booking.exception.InsertRoomTypeException;
import com.Thienbao.booking.exception.UpdateRoomTypeException;
import com.Thienbao.booking.model.Room;
import com.Thienbao.booking.model.RoomType;
import com.Thienbao.booking.payload.request.InsertRoomTypeRequest;
import com.Thienbao.booking.payload.request.UpdateRoomTypeRequest;
import com.Thienbao.booking.repository.RoomAmenitiesRepository;
import com.Thienbao.booking.repository.RoomRepository;
import com.Thienbao.booking.repository.RoomTypeRepository;
import com.Thienbao.booking.service.imp.RoomTypeServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomTypeService implements RoomTypeServiceImp {

    @Autowired
    private RoomTypeRepository roomTypeRepository ;
    @Autowired
    private RoomRepository roomRepository ;

    @Autowired
    private RoomAmenitiesRepository roomAmenitiesRepository ;

    @Transactional
    @Override
    public RoomType insertRoomType(HttpServletRequest request, InsertRoomTypeRequest typeRequest) {

            if (typeRequest.getName() == null || typeRequest.getName().isEmpty()){
                throw new InsertRoomTypeException("Tên loại phòng không được để trống");
            }
            RoomType roomType = new RoomType();
            roomType.setName(typeRequest.getName());
            return roomTypeRepository.save(roomType);
    }

    @Override
    public List<RoomTypeDto> getAllRoomType(HttpServletRequest request) {
        List<RoomType> listRoomTypeEntity = roomTypeRepository.findAll();
        List<RoomTypeDto> listDto = new ArrayList<>();
        listRoomTypeEntity.forEach(item ->{
            RoomTypeDto roomTypeDto = new RoomTypeDto();
            roomTypeDto.setId(item.getId());
            roomTypeDto.setName(item.getName());

            listDto.add(roomTypeDto);
        });
        return listDto;
    }

    @Override
    @Transactional
    public void deleteRoomType(HttpServletRequest request, int id) {
        if (!roomTypeRepository.existsById(id)){
            throw new DeleteException("Không tìm thấy loại phòng" +id);
        }
        List<Room> rooms = roomRepository.findByRoomTypeId(id);
        for (Room room : rooms){
            roomAmenitiesRepository.deleteByRoomId(room.getId());
            roomRepository.delete(room);
        }
        roomTypeRepository.deleteById(id);

    }

    @Override
    public RoomType updateRoomType(HttpServletRequest request, UpdateRoomTypeRequest updateRoomTypeRequest, int id) {
        RoomType roomType = roomTypeRepository.findById(id).orElseThrow(()-> new UpdateRoomTypeException("Không tìm thấy loại phòng" +id));
        roomType.setName(updateRoomTypeRequest.getName());
        return roomTypeRepository.save(roomType);
    }
}
