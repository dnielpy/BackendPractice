package com.example.demo.Auth;

import com.example.demo.Content.Peliculas;
import com.example.demo.Content.Series;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Configuration
@EnableJpaRepositories("com.example.demo.Users") // Especifica el paquete de los repositorios
public class Auth extends TelegramLongPollingBot {

    Series series = new Series();
    List<String> seriesList;
    List<String> netflixList;
    AuthEntity authEntity = new AuthEntity();
    Long admin_id = 6460774312L;

    public Auth() {
    }

    public void updateContent(){
        sendMessage("⌛\uFE0F Actualizando contenido ⌛\uFE0F", admin_id);
        seriesList = series.getSeries();
        netflixList = series.getNetflix();
        if (seriesList.isEmpty() || netflixList.isEmpty()) {
            sendMessage("⚠\uFE0F Problemas al conectarse a los servidores de https://visuales.uclv.cu/", admin_id);
        }
        else {
            sendMessage("✅ Lista actualizada con exito ✅", admin_id);
        }
    }

    public String getNetflix() {
        Series series = new Series();
        List<String> seriesList = series.getNetflix();
        StringBuilder mesage = new StringBuilder();
        for (String s : seriesList) {
            mesage.append(s + "\n");
        }
        return mesage.toString();
    }

    public String getPeliculas() {
        Peliculas peliculas = new Peliculas();
        List<String> peliculasList = peliculas.getPeliculas();
        StringBuilder mesage = new StringBuilder();
        for (String s : peliculasList) {
            mesage.append(s + "\n");
        }
        return mesage.toString();
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
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {

            if (Objects.equals(update.getMessage().getChatId(), admin_id) && update.getMessage().getText().equals("/update")) {
                updateContent();
            }

            String message_text = update.getMessage().getText();
            if (update.getMessage().getText().equals("/start")) {
                File file = new File("C:\\Users\\ASUS\\Documents\\Code\\Github\\Panda-TV\\src\\main\\java\\com\\example\\demo\\Img\\logo-compressed.jpg");
                sendImage("Bienvenido a Panda TV \uD83D\uDCFA", update.getMessage().getChat().getId().toString(), file);
                final long chat_id = update.getMessage().getChatId();
                SendMessage message = new SendMessage(); // Create a message object object
                message.setChatId(chat_id);
                message.setText("¿Que quieres ver hoy?");
                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
                List<InlineKeyboardButton> rowInline = new ArrayList<>();
                InlineKeyboardButton button = new InlineKeyboardButton();
                button.setText("Series");
                button.setCallbackData("series");
                rowInline.add(button);

                InlineKeyboardButton buttonPeliculas = new InlineKeyboardButton();
                buttonPeliculas.setText("Peliculas");
                buttonPeliculas.setCallbackData("peliculas");
                rowInline.add(buttonPeliculas);
                // Set the keyboard to the markup


                rowsInline.add(rowInline);
                // Add it to the message

                markupInline.setKeyboard(rowsInline);
                message.setReplyMarkup(markupInline);
                try {
                    execute(message); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {

            }
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("series")) {

                InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();

                if (seriesList == null) {
                    sendMessage("⚠\uFE0F Estamos teniendo dificultades tecnicas en nuestros servidores ⚠\uFE0F", id);
                    sendMessage("⚠\uFE0F El servidor de https://visuales.uclv.cu/ esta presentando problemas ⚠\uFE0F", admin_id);
                } else {
                    SendMessage message = new SendMessage(); // Create a message object object
                    message.setChatId(id);
                    message.setText("\uD83C\uDF7F Nuestras series \uD83C\uDF7F");
                    for (String string : seriesList) {
                        List<InlineKeyboardButton> rowInline = new ArrayList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText(string);
                        button.setCallbackData(string.toLowerCase());
                        rowInline.add(button);
                        rowsInline.add(rowInline);
                    }
                    markupInline.setKeyboard(rowsInline);
                    message.setReplyMarkup(markupInline);
                    try {
                        execute(message); // Sending our message object to user
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (call_data.equals("peliculas")) {
                Peliculas peliculas = new Peliculas();
                List<String> peliculasList = peliculas.getPeliculas();
                StringBuilder mesage = new StringBuilder();
                for (String s : peliculasList) {
                    mesage.append(s + "\n");
                }
                SendMessage answer = new SendMessage();

                String peliculas_list = getPeliculas();
                answer.setChatId(id);
                if (peliculas_list.equals("")) {
                    sendMessage("⚠\uFE0F Estamos teniendo dificultades tecnicas en nuestros servidores ⚠\uFE0F", id);
                    Long admin_id = 6460774312L;
                    sendMessage("⚠\uFE0F El servidor de https://visuales.uclv.cu/ esta presentando problemas ⚠\uFE0F", admin_id);
                } else {
                    answer.setText(getNetflix());
                    try {
                        execute(answer);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }

    @Override
    public void onUpdatesReceived(List<Update> updates) {
        super.onUpdatesReceived(updates);
    }
}