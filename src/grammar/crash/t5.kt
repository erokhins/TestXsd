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
package grammar.crash.t5


class A {
    fun foo(): Int {return 4}

    fun bar(f: A.() -> Unit = {}) {}
}

class B {
    class D {
        {
            A().foo()
            A().bar {
//                this.foo()
               // this.foo()   // no error
//                foo()        // Kotlin: Expression is inaccessible from a nested class 'D', use 'inner' keyword to make the class inner
            }
        }
    }
}

val A.a: Int
    get() {
        return this.foo()
    }

fun main(args: Array<String>) {
    B()
}