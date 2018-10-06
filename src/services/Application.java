package services;

import dao.Tree;
import db.MockDb;
import model.User;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Application implements App {
    private Scanner sc = new Scanner(System.in);
    private String login;
    private String pass;
    private int pinCode;
    private Tree tree = new Tree();
    private User userTmp;


    @Override
    public boolean editTree(int treeKey) {
        if(treeKey>0 && treeKey<=MockDb.personMap.size()){
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean removeTree(int treeKey){
        if(treeKey>0 && treeKey<=MockDb.personMap.size()){
            tree.deleteTree(treeKey);
            return true;
        }else
            return false;
    }


    @Override
    public boolean registration(User newUser){
        boolean isExist = false;
        for (User u:MockDb.users) {
            if (u.getUserName().equals(newUser.getUserName())){
                isExist = true;
            }
        }
        if(!isExist){
            MockDb.users.add(newUser);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public boolean login(String login, String pass){
        boolean loggedIn = false;
        for ( User user:MockDb.users ) {
            if(user.getUserName().equals(login) && user.getPassword().equals(pass)) {
                userTmp = user;
                loggedIn = true;
                break;
            }
        }
        return loggedIn;
    }

    @Override
    public void addTree(String firstName, String lastName, LocalDate localDate) {
        tree.addPerson(firstName, lastName, localDate, userTmp);
        System.out.println("Дерево было успешно создано...");
    }


    // Добавление членов семьи

    @Override
    public void addFather(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey){
        System.out.println(tree.addFather(name, lastName, birthDate, deathDate, treeKey));
    }

    @Override
    public void addMother(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addMother(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addGrandFatherByDad(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addGrandFather(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addGrandFatherByMom(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addGrandFatherByMom(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addGrandMotherByDad(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addGrandMother(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addGrandMotherByMom(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addGrandMotherByMom(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addBrother(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addBrother(name, lastName, birthDate, null, treeKey);
    }

    @Override
    public void addSister(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addSister(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addWife(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addWife(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addHusband(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addHusband(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addSon(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addSon(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public void addDaughter(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        tree.addDaughter(name, lastName, birthDate, deathDate, treeKey);
    }

    @Override
    public boolean changePassword(String userName, int pin, String pass) {
        boolean truePin = false;
        for (User u:MockDb.users) {
            if(u.getUserName().equals(userName) && u.getPinCode() == pin){
                MockDb.users.get(MockDb.users.indexOf(u)).setPassword(pass);
                truePin = true;
            }
        }
        return truePin;
    }

    @Override
    public void showTrees(String login) {
        for (User u:MockDb.users) {
            if(u.getUserName().equals(login)) {
                tree.showTrees(u);
            }
        }
    }
}