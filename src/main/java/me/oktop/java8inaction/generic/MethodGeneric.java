package me.oktop.java8inaction.generic;

class Family {
    public String name;

    public Family(String name) {
        this.name = name;
    }
}
class MethodGeneric {

    public int value;
    public int data;

    public <U> void printValue(U value) {
        System.out.println(value);
    }

    public static void main(String[] args) {
        Family family = new Family("family");
        MethodGeneric methodGeneric = new MethodGeneric();
        methodGeneric.printValue(family);
    }

}
