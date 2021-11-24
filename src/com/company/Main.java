package com.company;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    // Получить коллекцию пользователей из файла и вывести в консоль
    // * Возраст сделать опциональным
    // * Отсортировать пользователей по возрасту по убыванию, у кого нет возраста в начало списка
    // Н 19:45
    // З 20;46
    public static void main(String[] args) {
        final String filename = "C:\\Users\\Новутбук\\IdeaProjects\\untitled1\\src\\com\\company\\users.txt";
        List<User> users = new ArrayList<>();
        try {
            try (Stream<String> stream = Files.lines(Paths.get(filename))) {
                List<String> strings = stream.collect(Collectors.toList());
                for (int i = 0; i < strings.size() - 2; i = i + 3) {
                    User user = new User(strings.get(i), strings.get(i + 1));
                    user.setAge(strings.get(i+2));
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        users.sort(Comparator.comparing(User::getAge));
        users.forEach(u -> System.out.println(u.toString()));
    }

    static class User {
        private String secondName;
        private String firstName;
        private int age;

        @Override
        public String toString() {
            return "User{" +
                    "secondName='" + secondName + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", age=" + age +
                    '}';
        }

        public User(String secondName, String firstName) {
            this.secondName = secondName;
            this.firstName = firstName;
        }

        public void setAge(String age) {
            if(isNumeric(age)) {
                this.age = Integer.parseInt(age);
            }
            else {
                this.age = -1;
            }
        }

        public int getAge() {
            return age;
        }

        private static boolean isNumeric(String str){
            return str != null && str.matches("[0-9.]+");
        }
    }
}
