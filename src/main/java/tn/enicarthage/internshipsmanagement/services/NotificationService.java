package tn.enicarthage.internshipsmanagement.services;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final SimpMessagingTemplate messagingTemplate;

    public void sendMessage(final String topicSuffix) {
        messagingTemplate.convertAndSend("/topic/" + topicSuffix, "Default message from our WS service");
    }

    public void sendMessage(final String topicSuffix, final String payload) {
        messagingTemplate.convertAndSend("/topic/" + topicSuffix, payload);
    }
}
