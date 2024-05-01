package tn.enicarthage.internshipsmanagement.restControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
public class NotificationController {

    private final SimpMessagingTemplate messagingTemplate;

    @PostMapping("/send-notification")
    public void sendNotification(@RequestBody String message) {
        messagingTemplate.convertAndSend("/topic/notification", message);
    }
}