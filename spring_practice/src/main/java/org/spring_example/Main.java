package org.spring_example;

import org.spring_example.components.ContactManager;
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

import static java.lang.System.out;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ProfileWorker.class);
        out.println("Дорогой пользователь!");
        ContactManager contactManager = null;
        contactManager = context.getBean(ContactManager.class);
        String path = (String) context.getBean("path");
        showAllContacts(contactManager);
        out.println("Для работы принимаются следующие команды");
        boolean goahead = true;
        while (goahead){
            printMessage();
            Scanner scn = new Scanner(System.in);
            int command = Integer.parseInt(scn.nextLine());
            switch (command) {
                case 1:
                    try {
                        contactManager.addContact(scn.nextLine());
                        out.println("Контакт добавлен");
                    } catch (ContactValidationException e) {
                        System.err.println(e.getMessage());
                    } catch (ContactAlreadyExistsException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    try {
                        contactManager.deleteById(scn.nextLine());
                        out.println("Контакт удален");
                    } catch (NotExistsContactException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 3:
                    try {
                        contactManager.updateContact(scn.nextLine());
                        out.println("Контакт обновлен");
                    } catch (ContactValidationException e) {
                        System.err.println(e.getMessage());
                    } catch (NotExistsContactException e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 4:
                    showAllContacts(contactManager);
                    break;
                case 5:
                    goahead = false;
                    try {
                        Files.write(Path.of(path),
                                contactManager.toString().getBytes(StandardCharsets.UTF_8));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    out.println("Работа завершена");
                    break;
                default:
                    System.err.println("Некорректный ввод.");
            }

        }

    }

    public static void printMessage(){
        out.println("1 - ввод нового контакта в формате 'Ф. И. О. | Номер телефона | Адрес электронной почты'");
        out.println("2 - удаление пользователя по email");
        out.println("3 - редактирование пользователя в формате 'Ф. И. О. | Номер телефона | Адрес электронной почты' ");
        out.println("4 - вывод списка контактов");
        out.println("5 - выход и сохранение текущего списка контактов");
    }

    public static void showAllContacts(ContactManager cm) {
        out.println("на данный момент в базе есть следующие контакты:");
        out.println(cm);
    }
}