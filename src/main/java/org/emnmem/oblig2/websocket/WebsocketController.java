package org.emnmem.oblig2.websocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
public class WebsocketController {

    @MessageMapping("/chat.send")
    @SendTo("/topic/public")
    public PushDto sendMessage(@Payload PushDto room) {
        return room;
    }

}

@AllArgsConstructor
@NoArgsConstructor
@Data
class PushDto {
    String roomId;
}
