package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergey Klunniy
 */
public class Main {
    private static List<Car> carList = new ArrayList<>();

    public static void main(String[] args) {
        carList.add(new Car());
        carList.add(new Car());

        System.out.println(carList);
    }

}

class Car {}