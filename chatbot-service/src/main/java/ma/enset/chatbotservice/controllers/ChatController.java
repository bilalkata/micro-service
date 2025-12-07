package ma.enset.chatbotservice.controllers;

import lombok.AllArgsConstructor;
import ma.enset.chatbotservice.agents.AIAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private final AIAgent aiAgent;

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE+ ";charset=UTF-8")
    public String ask(@RequestParam(name = "query") String query) {
        return aiAgent.ask(query);
    }
}
