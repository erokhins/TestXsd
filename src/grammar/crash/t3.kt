package grammar.crash.t3

/**
 * Created by erokhins on 7/2/13.
 */


open class I {
    protected var foo: Int = 4
}

trait A {
    public var foo: Int
}
var A.foo = 4

class B: A, I() // no impl foo





fun main(args: Array<String>) {
    println(B())
}