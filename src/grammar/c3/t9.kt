/*
 * Copyright 2010-2013 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package grammar.c3.t9

class A<out T> {
    fun invoke(f: A<T>.() -> Unit) {
        (this as A<T>).f()
    }
}

open class X {
    open val attr = A<X>()
}
open class Y : X() {
    override val attr = A<Y>()
}
open class Z : Y() {
    override val attr = A<Z>()
}

var A<X>.a1 = 1
var A<Y>.a2 = 2
var A<Z>.a3 = 3

fun main(args: Array<String>) {
    val x = X()
    val y = Y()
    val z = Z()
    with(x) {
        attr {
            a1 = 2
        }
        with(y) {
            attr {
                a1 = 3
                a2 = 4
            }
            with(z) {
                attr.a1 = 5
                attr.a2 = 6
                attr.a3 = 7
            }
        }
    }
    print(x.attr.a1)
    print(y.attr.a1)
    print(y.attr.a2)
    print(z.attr.a1)
    print(z.attr.a2)
    print(z.attr.a3)
}
