package controller;

import dao.Tree;
import javafx.stage.Stage;
import model.Person;
import model.User;
import services.Application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private Scanner sc = new Scanner(System.in);
    private String login;
    private String pass;
    private int pinCode;
    private Tree tree = new Tree();
    Application app = new Application();


    public void registrationForm(){
        System.out.print("Введите имя пользователя: ");
        login = sc.next();
        System.out.print("Введите пароль: ");
        pass = sc.next();
        System.out.println("Выберите пол 1. мужской\t\t2. женский");
        int gKey = sc.nextInt();
        System.out.println("Введите пин код для восстановления пароля в дальнейшем");
        pinCode = sc.nextInt();
        String gender;
        if(gKey == 1)
            gender = "Man";
        else
            gender = "Woman";
        User newUser = new User(login, pass, gender, User.getNextId(), pinCode);
        if(app.registration(newUser)){
            System.out.println("Пользователь успешно зарегистрирован");
            menu();
        }else{
            System.out.println("Пользователь с таким логином уже существует");
        }

    }


    public void loginForm(){
        System.out.print("Введите имя пользователя: ");
        login = sc.next();
        System.out.print("Введите пароль: ");
        pass = sc.next();
        if (app.login(login, pass)){
            System.out.println("Вы удачно вошли в аккаунт");
            userMenu();
        }else {
            System.out.println("Имя пользователя или пароль введено неправильно, повторите попытку");
            menu();
        }
    }

    public void restorePassword(){
        System.out.print("Введите имя пользователя:\t");
        String userName = sc.next();
        System.out.print("Введите пин код, который указывали при регистрации:\t");
        int pin = sc.nextInt();
        System.out.print("Введите новый пароль:\t");
        String password = sc.next();
        if(app.changePassword(userName, pin, password)){
            System.out.println("Пароль успешно изменен");
        }
        else {
            System.out.println("Вы ввели неправильный пинкод, или пользователь с таким именем не существует. Повторите попытку или вернитесь в главное меню");
            System.out.println("1. Главное меню");
            System.out.println("2. Повторить попытку");
            int key = sc.nextInt();
            if (key == 1){
                menu();
            }else{
                restorePassword();
            }
        }
    }

    public void changePassword(){
        System.out.print("Введите пин код, который указывали при регистрации:\t");
        int pin = sc.nextInt();
        System.out.print("Введите новый пароль:\t");
        String password = sc.next();
        if(app.changePassword(login, pin, password)){
            System.out.println("Пароль успешно изменен");
        }
        else {
            System.out.println("Вы ввели неправильный пинкод, или пользователь с таким именем не существует. Повторите попытку или вернитесь в главное меню");
            System.out.println("1. Главное меню");
            System.out.println("2. Повторить попытку");
            int key = sc.nextInt();
            if (key == 1){
                userMenu();
            }else{
                changePassword();
            }
        }
    }

    public void addTree(){
        System.out.print("Введите имя человека:\t");
        String firstName = sc.next();
        System.out.print("Введите фамилию:\t");
        String lastName = sc.next();
        System.out.print("Введите дату рождения человека в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate localDate = LocalDate.parse(date, formatter);
        app.addTree(firstName, lastName, localDate);
        userMenu();
    }

    public void editTree(){
        showTree();
        int treeKey;
        System.out.print("Введите число соответствующее дереву или любую цифру для возврата в предыдущее меню: ");
        treeKey = sc.nextInt();
        if (app.editTree(treeKey)){
            treeMenu(treeKey);
        }else {
            userMenu();
        }
    }

    public void removeTree(){
        showTree();
        System.out.print("Введите число соответствующее дереву или любую цифру для возврата в предыдущее меню: ");
        int treeKey = sc.nextInt();
        if (app.removeTree(treeKey)){
            System.out.println("Дерево успешно удалено");
        }
    }

    public void showTree(){
        System.out.println("Ваши деревья");
        app.showTrees(login);
        int key;
        System.out.println("Нажмите любую цифру чтоб вернуться назад");
        key = sc.nextInt();
        userMenu();
    }

    public void addFather(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя отца: ");
        String name = sc.next();
        System.out.print("Введите фамилию отца: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addFather(treeKey);
        }
        System.out.print("Введите дату рождения отца в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.println("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 0) {
            deathDate = null;
        }
        else if(select == 1) {
            System.out.print("Введите дату смерти отца в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addFather(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addMother(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя матери: ");
        String name = sc.next();
        System.out.print("Введите фамилию матери: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addMother(treeKey);
        }
        System.out.print("Введите дату рождения матери в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 0) {
            deathDate = null;
        }
        else if(select == 1) {
            System.out.print("Введите дату смерти матери в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addMother(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addGrandFatherByDad(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя дедушки: ");
        String name = sc.next();
        System.out.print("Введите фамилию дедушки: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addGrandFatherByDad(treeKey);
        }
        System.out.print("Введите дату рождения дедушки в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти дедушки в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addGrandFatherByDad(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addGrandFatherByMom(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя дедушки: ");
        String name = sc.next();
        System.out.print("Введите фамилию дедушки: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addGrandFatherByMom(treeKey);
        }
        System.out.print("Введите дату рождения дедушки в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти дедушки в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addGrandFatherByMom(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addGrandMotherByDad(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя бабушки: ");
        String name = sc.next();
        System.out.print("Введите фамилию бабушки: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addGrandFatherByDad(treeKey);
        }
        System.out.print("Введите дату рождения бабушки в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти бабушки в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addGrandMotherByDad(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addGrandMotherByMom(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя бабушки: ");
        String name = sc.next();
        System.out.print("Введите фамилию бабушки: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addGrandMotherByMom(treeKey);
        }
        System.out.print("Введите дату рождения бабушки в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти бабушки в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addGrandMotherByMom(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addBrother(int treeKey) {
        LocalDate deathDate = null;
        System.out.print("Введите имя брата: ");
        String name = sc.next();
        System.out.print("Введите фамилию брата: ");
        String lastName = sc.next();
        while (!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            addBrother(treeKey);
        }
        System.out.print("Введите дату рождения брата в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти брата в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addBrother(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addSister(int treeKey){
        LocalDate deathDate = null;
        System.out.print("Введите имя сестры: ");
        String name = sc.next();
        System.out.print("Введите фамилию сестры: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")){
            addSister(treeKey);
        }
        System.out.print("Введите дату рождения сестры в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти сестры в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addSister(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addWife(int treeKey) {
        LocalDate deathDate = null;
        System.out.print("Введите имя жены: ");
        String name = sc.next();
        System.out.print("Введите фамилию жены: ");
        String lastName = sc.next();
        while (!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            addWife(treeKey);
        }
        System.out.print("Введите дату рождения жены в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти сестры в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addWife(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addHusband(int treeKey) {
        LocalDate deathDate = null;
        System.out.print("Введите имя мужа: ");
        String name = sc.next();
        System.out.print("Введите фамилию мужа: ");
        String lastName = sc.next();
        while(!name.matches("[a-zA-Z]+") || lastName.matches("[a-zA-Z]+")){
           addHusband(treeKey);
        }
        System.out.print("Введите дату рождения мужа в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти сестры в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addHusband(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addSon(int treeKey) {
        LocalDate deathDate = null;
        System.out.print("Введите имя сына: ");
        String name = sc.next();
        System.out.print("Введите фамилию сына: ");
        String lastName = sc.next();
        while (!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            addSon(treeKey);
        }
        System.out.print("Введите дату рождения сына в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти сестры в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addSon(name, lastName, birthDate, deathDate, treeKey);
    }

    public void addDaughter(int treeKey) {
        LocalDate deathDate = null;
        System.out.print("Введите имя дочери: ");
        String name = sc.next();
        System.out.print("Введите фамилию дочери: ");
        String lastName = sc.next();

        while (!name.matches("[a-zA-Z]+") || !lastName.matches("[a-zA-Z]+")) {
            addDaughter(treeKey);
        }

        System.out.print("Введите дату рождения дочери в формате \"ДД.ММ.ГГГГ\"\t");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String date = sc.next();
        LocalDate birthDate = LocalDate.parse(date, formatter);
        System.out.print("Введите \'1\' если человек умер, иначе введите \'0\': ");
        int select = sc.nextInt();
        if(select == 1) {
            System.out.print("Введите дату смерти сестры в формате \"ДД.ММ.ГГГГ\"\t");
            date = sc.next();
            deathDate = LocalDate.parse(date, formatter);
        }
        app.addDaughter(name, lastName, birthDate, deathDate, treeKey);
    }

    public void menu(){
        int key;
        while(true){
            System.out.println("1. Регистрация");
            System.out.println("2. Вход");
            System.out.println("3. Забыли пароль?");
            key = sc.nextInt();
            if(key == 1){
                registrationForm();
            }
            else if(key == 2){
                loginForm();
            }
            else if(key == 3){
                restorePassword();
            }
            else{
                System.out.println("Введите 1 для регистрации, 2 для входа, 3 для восстановления пароля. Не умничай!");
                menu();
            }
        }
    }

    public void userMenu(){
        int key;
        while (true){
            System.out.println("1. Добавить дерево");
            System.out.println("2. Показать мои деревья");
            System.out.println("3. Редактировать дерево");
            System.out.println("4. Удалить дерево");
            System.out.println("5. Изменить пароль");
            System.out.println("6. Выйти из учетной записи");
            System.out.println("0. Завершить программу");
            key = sc.nextInt();
            if (key == 1){
                addTree();
            } else if(key == 2){
                showTree();
            } else if(key == 3){
                editTree();
            }else if(key == 4){
                removeTree();
            }else if (key == 5){
                changePassword();
            }else if(key == 6){
                menu();
            }else{
                System.exit(0);
            }
        }
    }


    public void treeMenu(int treeKey) {
        while (true){
            int key;
            System.out.println("1. Добавить отца");
            System.out.println("2. Добавить мать");
            System.out.println("3. Добавить дедушку");
            System.out.println("4. Добавить бабушку");
            System.out.println("5. Добавитьбрата");
            System.out.println("6. Добавитьсестру");
            System.out.println("7. Добавитьжену");
            System.out.println("8. Добавитьмужа");
            System.out.println("9. Добавитьсына");
            System.out.println("10.Добавить дочь");
            System.out.println("0. Назад");
            key = sc.nextInt();

            // Добавление отца в дерево
            if(key == 1){
                addFather(treeKey);
            }

            // Добавление матери в дерево
            else if(key == 2){
                addMother(treeKey);
            }

            // Добавление дедушки в дерево
            else if(key == 3){
                System.out.println("1. Добавить дедушку по отцу");
                System.out.println("2. Добавить дедушку по матери");
                System.out.println("0. Назад");
                key = sc.nextInt();
                if(key == 1){
                    addGrandFatherByDad(treeKey);
                }else if(key == 2){
                    addGrandFatherByMom(treeKey);
                }
            }

            // Добавление бабушки в дерево
            else if (key == 4){
                System.out.println("1. Добавить бабушку по отцу");
                System.out.println("2. Добавить бабушку по матери");
                System.out.println("0. Назад");
                key = sc.nextInt();
                if(key == 1){
                    addGrandMotherByDad(treeKey);
                }else if(key == 2){
                    addGrandMotherByMom(treeKey);
                }
            }

            // Добавление брата в дерево
            else if (key == 5){
                addBrother(treeKey);
            }

            // Добавление сестры в дерево
            else if(key == 6){
                addSister(treeKey);
            }

            else if(key == 7){
                addWife(treeKey);
            }

            else if(key == 8){
                addHusband(treeKey);
            }

            else if(key == 9){
                addSon(treeKey);
            }

            else if(key == 10){
                addDaughter(treeKey);
            }

            else if(key == 0){
                userMenu();
            }else {
                System.out.println("Введите правильную цифпу соответсвующую пункту меню");
                treeMenu(treeKey);
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.menu();

    }
}