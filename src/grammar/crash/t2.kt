package grammar.crash.t2

/**
 * Created with IntelliJ IDEA.
 * User: erokhins
 * Date: 7/1/13
 * Time: 5:57 PM
 * To change this template use File | Settings | File Templates.
 */


open class A {
    protected open fun foo(): Int {
        return 5
    }
}

trait B {
    public fun foo(): Int
}

class C(val bar: Int): A(), B {
    override fun foo(): Int {
        object: B {
            public override fun foo(): Int  {
                this@C.bar
            }
        }
        return foo();
    }
}

