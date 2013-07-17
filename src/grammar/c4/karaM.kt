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
package grammar.c4.kara

import grammar.c4.depr.*

open class TagAttributes
    var TagAttributes.id = ""

class Tag<out T: TagAttributes>(val containingTag: Tag<TagAttributes>?, val t: T) {
    val attr: T = t
    fun attr(f: T.() -> Unit) {

    }

    fun <T : TagAttributes> build(f: Tag<T>.() -> Unit, t:T, tagName: String) {
        val tagT = Tag<T>(this, t)
        tagT.f()
        println(t)
    }
}


class A: TagAttributes()
    var A.href = ""

class INPUT: TagAttributes()
    var INPUT.value = ""

var Tag<A>.href: String
    get() {
        return this.t.href
    }
    set(v: String) {
        this.t.href = v
    }


class TABLE: TagAttributes()
class TR: TagAttributes()
class TH: TagAttributes()
class TD: TagAttributes()

fun Tag<TagAttributes>.a(f: Tag<A>.() -> Unit) = build(f, A(), "a")
fun Tag<TagAttributes>.input(f: Tag<INPUT>.() -> Unit) = build(f, INPUT(), "input")
fun Tag<TagAttributes>.table(f: Tag<TABLE>.() -> Unit) = build(f, TABLE(), "table")
fun Tag<TagAttributes>.tr(f: Tag<TR>.() -> Unit) = build(f, TR(), "tr")
fun Tag<TagAttributes>.td(f: Tag<TD>.() -> Unit) = build(f, TD(), "td")
fun Tag<TagAttributes>.th(f: Tag<TH>.() -> Unit) = build(f, TH(), "th")
fun Tag<TagAttributes>.del(f: Tag<TagAttributes>.() -> Unit) = build(f, TagAttributes(), "del")



fun main(args: Array<String>) {
    Tag<A>(null, A()).table {
        tr {
            th {
                del {
                }
            }
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