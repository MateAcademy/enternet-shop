/**
 * @author Sergey Klunniy
 */
public class Main1 {

    int a = 5;

    static int b = 10;

    void test1 () {}
    static void test2 () {}
    }





class Main extends Main1{

    void test (){
        super.a = 6;
        b = 6;

        super.test1();
        test2();
    }
}