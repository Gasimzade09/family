package dao;

import model.User;

import java.time.LocalDate;

public interface Trees {
    public void addPerson(String firstName, String lastName, LocalDate localDate, User user);
    public String addFather(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public String addMother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public String addGrandFather(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public String addGrandFatherByMom(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addGrandMother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addGrandMotherByMom(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addBrother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addSister(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addWife(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addHusband(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addSon(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void addDaughter(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey);
    public void showTrees(User u);
    public void deleteTree(int treeKey);
}
