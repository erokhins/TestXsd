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
package grammar.c2.t3



open class A {
    val a = 1
}
class B: A() {
    val b = 2
}

fun foo(f: A.() -> Unit) {}  // 1
fun foo(f: B.() -> Unit) {}  // 2


fun test() {
    foo {
        //b // why unresolved?
    }
}