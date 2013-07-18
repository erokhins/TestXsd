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

import grammar.c4.kara.depr.*
import jet.deprecated as depr

open class TagType(val tag: Tag<*>)
    var TagType.id = ""

open class Tag<out T: TagType>(val containingTag: Tag<TagType>?, val t: (Tag<*>) -> T) {
    val attr: T get() = t(this)
    protected var text:String = ""

    fun attr(f: T.() -> Unit) {
        attr.f()
    }

    fun <T : TagType> build(f: Tag<T>.() -> Unit, t: (Tag<*>) -> T, tagName: String) {
        val tagT = Tag<T>(this, t)
        tagT.f()
        println(t)
    }
    fun <T : TagType> build(f: TagWithText<T>.() -> Unit, t: (Tag<*>) -> T, tagName: String) {
        val tagT = TagWithText<T>(this, t)
        tagT.f()
        println(t)
    }

    depr("") open fun String.plus() {}
}

class TagWithText<out T: TagType>(containingTag: Tag<TagType>?, t: (Tag<*>) -> T) : Tag<T>(containingTag, t) {
    override fun String.plus() {
        text = this
    }
}


class A(tag: Tag<*>): TagType(tag)
    var A.href = ""

class INPUT(tag: Tag<*>): TagType(tag)
    var INPUT.value = ""

var Tag<A>.href: String
    get() {
        return this.attr.href
    }
    set(v: String) {
        this.attr.href = v
    }


class TABLE(tag: Tag<*>): TagType(tag)
class TR(tag: Tag<*>): TagType(tag)
class TH(tag: Tag<*>): TagType(tag)
class TD(tag: Tag<*>): TagType(tag)

fun <T: TagType> foo(f: (Tag<T>) -> T) {

}

fun bar() {
    foo(::TABLE)
}

fun Tag<TagType>.a(f: Tag<A>.() -> Unit) = build(f, ::A, "a")
fun Tag<TagType>.input(f: Tag<INPUT>.() -> Unit) = build(f, ::INPUT, "input")
fun Tag<TagType>.table(f: Tag<TABLE>.() -> Unit) = build(f, ::TABLE, "table")
fun Tag<TagType>.tr(f: Tag<TR>.() -> Unit) = build(f, ::TR, "tr")
fun Tag<TagType>.td(f: TagWithText<TD>.() -> Unit) = build(f, ::TD, "td")
fun Tag<TagType>.th(f: Tag<TH>.() -> Unit) = build(f, ::TH, "th")
fun Tag<TagType>.del(f: Tag<TagType>.() -> Unit) = build(f, ::TagType, "del")



fun main(args: Array<String>) {
    Tag<A>(null, ::A).table {
        tr {
            +""
            th {
                del {
                }
            }
            td {
                +""
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