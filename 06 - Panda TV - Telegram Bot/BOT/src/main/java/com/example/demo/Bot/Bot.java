package com.example.demo.Bot;

import com.example.demo.Auth.AuthEntity;
import com.example.demo.Content.Peliculas;
import com.example.demo.Content.Series;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Configuration
@EnableJpaRepositories("com.example.demo.Users") // Especifica el paquete de los repositorios
public class Bot extends TelegramLongPollingBot {

    Series series = new Series();
    List<String> seriesList;
    List<String> netflixList;
    AuthEntity authEntity = new AuthEntity();
    Long admin_id = 6460774312L;

    public Bot() {
    }

    public void sendMessage(String message, Long id) {
        SendMessage answer = new SendMessage();
        answer.setChatId(id);
        answer.setText(message);
        try {
            execute(answer);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendImage(String caption, String id, File file) {
        SendPhoto sendPhoto = new SendPhoto();
        sendPhoto.setCaption(caption);
        sendPhoto.setChatId(id);
        try {
            sendPhoto.setPhoto(new InputFile(new FileInputStream(file), file.getName()));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            execute(sendPhoto);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        //Devolvemos el usuario que configuramos en BotFather
        return "PandaCU_bot";
    }

    @Override
    public String getBotToken() {
        //Devolvemos el token generado por BotFather
        return "6930418936:AAHmnQvI4fECN6qsp2If1cnTcVjISYoEWRU";
    }

    @Override
    public void onRegister() {
        super.onRegister();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message_text = update.getMessage().getText();
            if (message_text.equals("/start")) {
                final long chat_id = update.getMessage().getChatId();
                sendMessage("Work", chat_id);

                Series series = new Series();

                series.getSeries();
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}