package me.oktop.java8inaction.generic;
class Info {
    public int value;
}
class GenericExtends extends Info {
    public int rank;

    public GenericExtends(int rank) {
        this.rank = rank;
    }
}

class PersonExtends<T extends Info> {
    public T info;

    public PersonExtends(T info) {
        this.info = info;
    }
}
class GenericExtendsPractice {
    public static void main(String[] args) {
        // Info 이거나 Info의 자식만 사용 가능
        PersonExtends<Info> personExtends1 = new PersonExtends(new Info());
        PersonExtends<GenericExtends> personExtends2 = new PersonExtends(new GenericExtends(1));
    }
}
