package services;

import model.User;

import java.time.LocalDate;

public interface App {

    public boolean registration(User newUser);

    public boolean login(String login, String pass);

    public void addTree(String firstName, String lastName, LocalDate localDate);

    public void showTrees(String login);

    public boolean editTree(int treeKey);

    public boolean removeTree(int treeKey);

    public void addFather(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addMother(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addGrandFatherByDad(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addGrandFatherByMom(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addGrandMotherByDad(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addGrandMotherByMom(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addBrother(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addSister(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addWife(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addHusband(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addSon(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public void addDaughter(String name, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);

    public boolean changePassword(String userName, int pin, String pass);
}