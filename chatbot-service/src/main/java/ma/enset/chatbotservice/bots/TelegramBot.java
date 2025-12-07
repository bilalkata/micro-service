package ma.enset.chatbotservice.bots;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import ma.enset.chatbotservice.agents.AIAgent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import reactor.core.publisher.Flux;

@Slf4j
@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${telegram.bot.key}")
    private String key;

    @Value("${telegram.bot.name}")
    private String name;

    private final AIAgent aiAgent;

    public TelegramBot(AIAgent aiAgent) {
        this.aiAgent = aiAgent;
    }

    @Override
    public String getBotUsername() {
        return this.name;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!update.hasMessage()) return;

        String message = update.getMessage().getText();
        String response = aiAgent.ask(message);
        sendMessage(update.getMessage().getChatId(), response);
    }

    @Override
    public String getBotToken() {
        return this.key;
    }

    @PostConstruct
    public void register() {
        try {
            TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
            api.registerBot(this);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void sendMessage(Long id, String message) {
        try {
            SendMessage sendMessage = new SendMessage(String.valueOf(id), message);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
