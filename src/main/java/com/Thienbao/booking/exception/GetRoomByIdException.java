package com.Thienbao.booking.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class GetRoomByIdException extends RuntimeException {
    private String message ;
}
