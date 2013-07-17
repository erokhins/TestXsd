package grammar.c3;

/**
 * Created with IntelliJ IDEA.
 * User: erokhins
 * Date: 7/15/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class AA {
    private boolean flag = true;

    public static class B {
        public void test() {
        }
    }
}

class D extends AA {
    public void foo() {
        B a = new B();
    }
}

class S {
    public void test() {
        AA a = (new AA());
        D.B b = new D.B();
    }
}
