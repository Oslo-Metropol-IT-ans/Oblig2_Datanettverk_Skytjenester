package org.emnmem.oblig2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private String username;
    private int Id;
    private int room_id;
    private int user_id;
    private String message;
}
