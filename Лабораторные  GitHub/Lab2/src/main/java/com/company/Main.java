package com.company;

// File Name SendEmail.java

import com.sun.mail.imap.IMAPStore;


import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class Main {
    public static void receiveEmail(String imapHost, String storeType,
                                    String user, String password) {
        try {
            //1) получаю сеанс объекта
            Properties properties = new Properties();
            properties.put("mail.imap.host", imapHost);
            properties.put("mail.imap.port","993");
            properties.put("mail.imap.ssl.enable","true");
            properties.put("mail.imap.auth","true");
            Session emailSession = Session.getDefaultInstance(properties);

            //2) создаю imapstore и подключаюсь к почте
            IMAPStore emailStore1 = (IMAPStore) emailSession.getStore(storeType);
            emailStore1.connect(user, password);

            //3) создаю объект папки и открываю ее
            Folder emailFolder = emailStore1.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            //4) Получаю сообщения с почты и заношу их в массив
            Message[] messages = emailFolder.getMessages();
            for (int i = 0; i < messages.length; i++) {
                Message message = messages[i];
                System.out.println("---------------------------------");
                System.out.println("Email Number " + (i + 1));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());
            }

            //5) Закрываю папку и сеанс
            emailFolder.close(false);
            emailStore1.close();

        } catch (NoSuchProviderException e) {e.printStackTrace();}
        catch (MessagingException e) {e.printStackTrace();}
        catch (IOException e) {e.printStackTrace();}
    }

    public static void main(String [] args) {
        //Для получения СМС
        String host1 = "imap.gmail.com";//change accordingly
        String mailStoreType = "imap";
        String username= "kurdoglo0408@gmail.com";
        String password= "**********";//change accordingly
        receiveEmail(host1, mailStoreType, username, password);

        //Для отправки СМС
        // Кому отправляем
        String to = "kurdoglo_1998@mail.ru";
        // От кого
        String from = "kurdoglo0408@gmail.com";
        String host = "smtp.gmail.com";
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host",host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");
        Session session = Session.getInstance(properties,new javax.mail.Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("kurdoglo0408@gmail.com","******");
            }
        });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setText("Hello Mad World");
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("TestMessage");
            System.out.println("Sending..");
            Transport.send(message);
            System.out.println("Sent message successfully");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
