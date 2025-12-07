package ma.enset.chatbotservice.agents;


import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class AIAgent {

    private final ChatClient chatClient;

    public AIAgent(ChatClient.Builder builder, ChatMemory memory,
                   ToolCallbackProvider toolCallbackProvider) {
        log.info("Initializing AI Agent");
        Arrays.stream(toolCallbackProvider.getToolCallbacks()).forEach(toolCallback ->
                log.info(String.valueOf(toolCallback.getToolDefinition())));

        this.chatClient = builder
                .defaultSystem("""
                    Vous êtes un assistant qui se charge de répondre aux questions de l'utilisateur
                    en fonction du contexte fourni.
                    Si aucun contexte n'est fourni, répond avec "Je ne sais pas"
                    """)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(memory).build())
                .defaultToolCallbacks(toolCallbackProvider)
                .build();
    }

    public String ask(String query) {
        return chatClient.prompt().user(query).call().content();
    }
}
