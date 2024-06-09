package org.spring_example;

import org.spring_example.config.DefaultAppConfig;
import org.spring_example.exceptions.ContactAlreadyExistsException;
import org.spring_example.exceptions.ContactValidationException;
import org.spring_example.exceptions.NotExistsContactException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean isShowed = false;
        ApplicationContext context = new AnnotationConfigApplicationContext(DefaultAppConfig.class);
        System.out.println("Дорогой пользователь, на данный момент в базе есть следующие контакты:");
        ContactManager contactManager = null;
        try {
            contactManager = context.getBean(ProfileWorker.class).getContactManager();
        } catch (ContactValidationException e) {
            throw new RuntimeException(e);
        } catch (ContactAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        System.out.println(contactManager);
        System.out.println("Для работы принимаются следующие команды");
        boolean goahead = true;
        while (goahead){
            printMessage();
            Scanner scn = new Scanner(System.in);
            int command = Integer.parseInt(scn.nextLine());
            switch (command) {
                case 1:
                    try {
                        contactManager.addContact(scn.nextLine());
                        System.out.println("Контакт добавлен");
                    } catch (ContactValidationException e) {
                        System.err.println(e.getMessage());
                    } catch (ContactAlreadyExistsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        contactManager.deleteById(scn.nextLine());
                        System.out.println("Контакт удален");
                    } catch (NotExistsContactException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        contactManager.updateContact(scn.nextLine());
                        System.out.println("Контакт обновлен");
                    } catch (ContactValidationException e) {
                        System.err.println(e.getMessage());
                    } catch (NotExistsContactException e) {
                        System.err.println(e.getMessage());
                    }
                case 4:
                    goahead = false;
                    try {
                        Files.write(Path.of("src/main/resources/contacts.list"),
                                contactManager.toString().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("Работа завершена");
                    break;
                default:
                    System.err.println("Некорректный ввод.");
            }

        }

    }

    public static void printMessage(){
        System.out.println("1 - ввод нового контакта в формате 'Ф. И. О. | Номер телефона | Адрес электронной почты'");
        System.out.println("2 - удаление пользователя по email");
        System.out.println("3 - редактирование пользователя в формате 'Ф. И. О. | Номер телефона | Адрес электронной почты' ");
        System.out.println("4 - выход и сохранение изменений в базе");
    }
}