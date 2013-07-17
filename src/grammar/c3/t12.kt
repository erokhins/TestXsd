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
package grammar.c3.t12

import java.util.HashMap


class MapSetter<K, V>(val map: MutableMap<K, V>) {
    public fun K.modAssign(v : V) {
        map.put(this, v)
    }
}

fun <K, V> MutableMap<K, V>.putAll(f: MapSetter<K, V>.() -> Unit) {
    MapSetter<K, V>(this).f()
}

fun main(args: Array<String>) {
    val map = HashMap<String, Int>()
    map.putAll {
        "a" %= 1
        "b" %= 2
        "c" %= 3
    }
    println(map)
}

