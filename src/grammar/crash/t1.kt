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

package grammar.crash

trait A

class DP {
    var str = ""
    fun get(a: A, property: PropertyMetadata): String {
        return str
    }
    fun set(a: A, property: PropertyMetadata, value: String) {
        str = value
    }
}

var A.bar by DP()  //work
public var A.foo: String by DP() //error generate LightClass

class B: A

fun main(args: Array<String>) {
    var a: String = B().foo
    println(B().foo)
    NewTag().h2 {
        +""
    }
}





trait AllowText {
    public fun String.plus()
}

open class Tag {
    protected fun String.plus() {
        // added text node
    }
}

class NewTag: Tag(), AllowText {}

public fun Tag.h1(run: Tag.() -> Unit) {}

public fun NewTag.h2(run: NewTag.() -> Unit) {}