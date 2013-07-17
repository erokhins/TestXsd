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
package grammar.c3.t4

trait Value {
    val value: String
}

class A(override val value: String): Value {
    class object {
        val a1 = A("str1")
        val a2 = A("str2")
        val a3 = A("str3")
    }
    fun toString(): String {
        return "A:$value"
    }
}

fun <T: Value> getObjForValue(klass: Class<T>, value: String): T? {
    val obj = klass.getDeclaredField("object\$").get(null)
    val objClass = klass.getClasses().find { it.getSimpleName() == "object" }
    if (objClass != null) {
        for (method in objClass.getMethods()) {
            try {
                if (method.getName()!!.startsWith("get")) {
                    val property = method.invoke(obj) as T
                    if (property.value == value) {
                        return property
                    }
                }
            } catch (e : Exception) {
                // do nothing
            }
        }
    }
    return null
}

fun <T> createNew(klass: Class<T>, value: String): T  {
    return klass.getConstructors()[0].newInstance(value) as T
}

fun main(args: Array<String>) {
    val klass = javaClass<A>()
//    val a = getObjForValue(klass, "str3")
    println(createNew(klass, "123"))
}


