package dao;

import db.MockDb;
import model.Person;
import model.User;

import java.time.LocalDate;

public class Tree implements Trees{
    private int count = 0;
    private int id;

    @Override
    public void addPerson(String firstName, String lastName, LocalDate localDate, User user) {
        count++;
        id = user.getId();
        MockDb.personMap.put(count, new Person(firstName, lastName, localDate, null));
        MockDb.tree.put(user.getId(), MockDb.personMap);
    }

    @Override
    public String addFather(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        boolean addFather = true;
        if(MockDb.personMap.get(count).getBirthDate().getYear() - birthDate.getYear() < 16){
            return "Отец должен быть старше минимум на 16 лет";
        }
        else if(MockDb.personMap.get(count)!=null){
            addFather = true;
        }
        else{
            addFather = false;
        }
        if (addFather){
            Person father = new Person(firstName, lastName, birthDate, deathDate);
            MockDb.personMap.get(treeKey).setFather(father);
            MockDb.tree.put(id, MockDb.personMap);
            return "Отец успешно добавлен в дерево";
        }
        else {
            return "Сначала добавьте дерево";
        }
    }

    @Override
    public String addMother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        boolean addMother = true;
        if(MockDb.personMap.get(count).getBirthDate().getYear() - birthDate.getYear() < 16){
            return "Мать должна быть старше минимум на 16 лет";
        }
        else if(MockDb.personMap.get(count)!=null){
            addMother = true;
        }
        else{
            addMother = false;
        }
        if(addMother){
            Person mother = new Person(firstName, lastName, birthDate, deathDate);
            MockDb.personMap.get(treeKey).setMother(mother);
            MockDb.tree.put(id, MockDb.personMap);
            return "Мать успешно добавлена в дерево";
        }
        else {
            return "Сначала добавьте дерево";
        }
    }

    @Override
    public String addGrandFather(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        boolean addGrandFather = true;
        LocalDate now = LocalDate.now();
        if(MockDb.personMap.get(count).getFather().getBirthDate().getYear() - birthDate.getYear() < 16){
            return "Дедушка должен быть старше отца минимум на 16 лет";
        }
        if(deathDate.getDayOfYear() > now.getDayOfYear()){
            return "Введите дату смерти корректно вы не ясновидящий";
        }
        if(birthDate.getDayOfYear() > now.getDayOfYear()){
            return "Введите дату рождения корректно вы не ясновидящий";
        }
        else if(MockDb.personMap.get(treeKey)!=null || MockDb.personMap.get(count).getFather() != null){
            addGrandFather = true;
        }
        else{
            addGrandFather = false;
        }
        if(addGrandFather){
            Person grandFather = new Person(firstName, lastName, birthDate, deathDate);
            MockDb.personMap.get(count).getFather().setFather(grandFather);
            MockDb.tree.put(id, MockDb.personMap);
            return "Дедушка успешно добавлен в дерево";
        }
        else {
            return "Сначала добавьте дерево или отца";
        }
    }

    @Override
    public String addGrandFatherByMom(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        boolean addGrandFather = true;
        LocalDate now = LocalDate.now();
        if(MockDb.personMap.get(treeKey).getMother().getBirthDate().getYear() - birthDate.getYear() < 16){
            return "Дедушка должен быть старше мамы минимум на 16 лет";
        }
        if(deathDate.getDayOfYear() > now.getDayOfYear()){
            return "Введите дату смерти корректно вы не ясновидящий";
        }
        if(birthDate.getDayOfYear() > now.getDayOfYear()){
            return "Введите дату рождения корректно вы не ясновидящий";
        }
        else if(MockDb.personMap.get(treeKey)!=null || MockDb.personMap.get(count).getFather() != null){
            addGrandFather = true;
        }
        else{
            addGrandFather = false;
        }
        if(addGrandFather){
            Person grandFatherByMom = new Person(firstName, lastName, birthDate, deathDate);
            MockDb.personMap.get(treeKey).getMother().setFather(grandFatherByMom);
            MockDb.tree.put(id, MockDb.personMap);
            return "Дедушка успешно добавлен в дерево";
        }
        else {
            return "Сначала добавьте дерево или маму";
        }
    }

    @Override
    public void addGrandMother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person grandMother = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).getFather().setMother(grandMother);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addGrandMotherByMom(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person grandMotherByMom = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).getMother().setMother(grandMotherByMom);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addBrother(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person bro = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setBrother(bro);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addSister(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person sister = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setSister(sister);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addWife(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person wife = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setWife(wife);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addHusband(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person husband = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setHusband(husband);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addSon(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person son = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setSon(son);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void addDaughter(String firstName, String lastName, LocalDate birthDate, LocalDate deathDate, int treeKey) {
        Person daughter = new Person(firstName, lastName, birthDate, deathDate);
        MockDb.personMap.get(treeKey).setDaughter(daughter);
        MockDb.tree.put(id, MockDb.personMap);
    }

    @Override
    public void showTrees(User u) {
        int i = 1;
        boolean isTrue = false;
        for (int j = 1; j <= MockDb.tree.size(); j++) {
            if(MockDb.tree.get(u.getId()) != null){
                for (Person p:MockDb.tree.get(u.getId()).values()) {
                    System.out.println(i + ".  " +p);
                    i++;
                    isTrue = true;
                }
            }
        }
        if(!isTrue)
            System.out.println("Вы не добавили ни одного дерева");
    }

    @Override
    public void deleteTree(int treeKey) {
        MockDb.personMap.remove(treeKey);
    }
}