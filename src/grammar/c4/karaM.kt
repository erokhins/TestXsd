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
package grammar.c4.t2


open class TagClass {
    var id = ""
}

class Tag<T: TagClass>(val containingTag: Tag<out TagClass>?, val t: T) {
    val attr: T = t
    fun attr(f: T.() -> Unit) {

    }
}

class A: TagClass() {
    var href = ""
}

class INPUT: TagClass() {
    var value = ""
}

class TABLE: TagClass()
class TR: TagClass()
class TD: TagClass()

fun Tag<out TagClass>.a(f: Tag<A>.() -> Unit) {}
fun Tag<out TagClass>.input(f: Tag<INPUT>.() -> Unit) {}
fun Tag<out TagClass>.table(f: Tag<TABLE>.() -> Unit) {}
fun Tag<out TagClass>.tr(f: Tag<TR>.() -> Unit) {}
fun Tag<out TagClass>.td(f: Tag<TD>.() -> Unit) {}


fun main(args: Array<String>) {
    Tag<A>(null, A()).table {
        tr {
            td {
                attr {
                    id = ""
                }
                input {
                    attr.value = ""
                    attr {
                        value = ""
                    }
                    a {
                        attr {
                            href = ""
                            id = ""
                        }
                    }
                }

            }
        }
    }
}