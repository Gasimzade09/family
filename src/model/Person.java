package model;

import java.time.LocalDate;

public class Person {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate deathDate;
    private String gender;

    private Person father;
    private Person mother;
    private Person sister;
    private Person brother;
    private Person son;
    private Person daughter;
    private Person husband;
    private Person wife;


    // Setters

    public void firstName(String name) { this.firstName = name;}

    public void setLastName(String lastName) { this.lastName = lastName; }

    public void setBirthDate(LocalDate birthDate) {this.birthDate = birthDate;}

    public void setDeathDate(LocalDate deathDate) {this.deathDate = deathDate;}

    public void setFather(Person father) { this.father = father; }

    public void setMother(Person mother) { this.mother = mother; }

    public void setSister(Person sister) { this.sister = sister; }

    public void setBrother(Person brother) { this.brother = brother; }

    public void setSon(Person son) { this.son = son; }

    public void setDaughter(Person daughter) { this.daughter = daughter; }

    public void setHusband(Person husband) { this.husband = husband; }

    public void setWife(Person wife) { this.wife = wife; }

    // Getters
    public String getFirstName() {return firstName;}

    public String getLastName() { return lastName; }

    public LocalDate getBirthDate() {return birthDate;}

    public LocalDate getDeathDate() {return deathDate;}

    public Person getFather() { return father; }

    public Person getMother() { return mother; }

    public Person getSister() { return sister; }

    public Person getBrother() { return brother; }

    public Person getSon() { return son; }

    public Person getDaughter() { return daughter; }

    public Person getHusband() { return husband; }

    public Person getWife() { return wife; }

    // Constructors
    public Person(String name, String lastName, LocalDate birthDate, LocalDate deathDate) {
        this.firstName = name;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.deathDate = deathDate;
    }

    public Person() {}

    @Override
    public String toString() {
        return "Tree{" +
                "name= " + firstName +
                ", last name= "+ lastName+
                ", birthDate= " + birthDate +
                ", deathDate= " + deathDate +
                " father= "+father+
                ", Mother= "+mother+
                ", Sister= "+sister+
                '}';
    }
}