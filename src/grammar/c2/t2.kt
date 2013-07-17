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
package grammar.c2.t2


abstract class Attributes<T> {
    var name = "No name"
    var time = "10:00"
    fun invoke(f: T.() -> Unit) {
        (this as T).f()
    }
}

class CommonAttributes: Attributes<CommonAttributes>()

class A {
    class InnerAttributes : Attributes<InnerAttributes>() {
        var href = ""
    }
    val attr = InnerAttributes()
}

class TABLE {
    class InnerAttributes : Attributes<InnerAttributes>() {
        var height = 10
    }
    val attr = InnerAttributes()
}
fun TABLE.tr(content: TR.() -> Unit) {}

class TR {
    val attr = CommonAttributes()
}

fun main(args: Array<String>) {
    with(A()) {
        attr {
            href
            name
        }
        attr.href

        with(TABLE()) {
            attr {
                height
            }
        }
    }
}
