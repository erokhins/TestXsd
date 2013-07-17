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
package grammar.kk.t2

import grammar.kk.t2.AttributeElement.attr


//class I: A<I>


open class AttributeElement {
    object attr
}


class Attr


class A: AttributeElement() {
    val attr.a1 = 3
    val attr.a2 = 5
}

class B: AttributeElement() {
    val attr.a4 = 6
}

fun main(args: Array<String>) {
    with(A()) {
        println(attr.a1)
        with(B()) {
            println(attr.a2)
        }
    }

}