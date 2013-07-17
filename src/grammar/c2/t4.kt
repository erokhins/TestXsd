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
package grammar.c2.t4

abstract class Attributes {
    var name = "No name"
    var time = "10:00"
}

class CommonAttributes: Attributes() {
    fun invoke(f: CommonAttributes.() -> Unit) {
        this.f()
    }
}

class A
val A.attr = A_Attr()

class A_Attr : Attributes() {
    fun invoke(f: A_Attr.() -> Unit) {
        this.f()
    }
}
var A_Attr.href = ""

class TABLE
class TABLE_InnerAttributes : Attributes() {
    var height = 10
    fun invoke(f: TABLE_InnerAttributes.() -> Unit) {
        this.f()
    }
}
val TABLE.attr = TABLE_InnerAttributes()
fun TABLE.tr(content: TR.() -> Unit) {}

class TR {
    val attr = CommonAttributes()
}



