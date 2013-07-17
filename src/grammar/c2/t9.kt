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
package grammar.c2.t9

open class A {
    public fun invoke(f: A.() -> Unit) {}
}

class B {
    public fun invoke(f: B.() -> Unit) {}
}

open class C
val C.attr = A()

open class D: C()
val D.attr = B()


fun main(args: Array<String>) {
    val b =  D()
//    b.attr {} // overload resolution ambiguity

    val d = b.attr
    d {}      // no error
}



