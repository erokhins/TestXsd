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
package grammar.crash.t11



class B {
    val attr = Attr()
    class Attr {
        var name: String = ""
        var width: Int = 0
        fun invoke(f: Attr.() -> Unit) {
            this.f()
        }
    }
}

fun main(args: Array<String>) {
    with(B()) {
        attr.name = "43"
        attr {
            name = "35"
            width = 2
        }
    }
}