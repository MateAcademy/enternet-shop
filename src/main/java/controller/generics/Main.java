//package controller.generics;
//
///**
// * @author Sergey Klunniy
// */
//public class Main {
//    public static void main(String[] args) {
//        Gold gold = new Gold(1000);
//        Storage storage = new Storage(gold);
//
//        Gold value = (Gold) storage.getValue();
//        value.changeOfmany();
//
//
//
//    }
//}
//
//class Gold {
//    private int massa;
//
//    public Gold(int massa) {
//        this.massa = massa;
//    }
//
//    public void changeOfmany() {
//        System.out.println("У меня " + massa + ", грамм золота обменяны на гривну: " +  massa*50);
//    }
//}
//
//class Storage {
//    private Object value;
//
//    public Object getValue() {
//        return value;
//    }
//
//    public void setValue(Object value) {
//        this.value = value;
//    }
//
//    public Storage(Object value) {
//        this.value = value;
//    }
//}