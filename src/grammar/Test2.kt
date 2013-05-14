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

package grammar

import java.util.HashMap

trait AG {
    val attributes: MutableMap<String, Any>

    public fun get(name: String): Any? {
        return attributes.get(name);
    }

    public fun set(name: String, value: Any) {
        attributes.put(name, value)
    }
}

class IAG1: AG1 {
    override val attributes: MutableMap<String, Any> = HashMap<String, Any>()
}

trait AG1: AG {
}


class IAG2: AG2 {
    override val attributes: MutableMap<String, Any> = HashMap<String, Any>()
}

trait AG2: AG {
}

public abstract class Attribute<T>(val name: String) {
    fun get(tag: AG, property: PropertyMetadata): T {
        return tag[name] as T
    }
    fun set(tag: AG, property: PropertyMetadata, value: T) {
        tag[name] = value
    }

}

class StringAttr(name: String): Attribute<String>(name) {}

public var AG1.foo: String by StringAttr("foo")

public var AG2.bar: String by StringAttr("bar")


open class C {}

class B: AG1 by IAG1(){

}


fun main(args : Array<String>) {
    println(4)
}
