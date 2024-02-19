package tinycache

class TinyCache<K, V>(private val cacheSize: Int) {
    private val cache: LinkedHashMap<K, V> =
            object : LinkedHashMap<K, V>(16, 0.75f, true) {
                override fun removeEldestEntry(eldest: Map.Entry<K, V>): Boolean {
                    return size > cacheSize
                }
            }

    @Synchronized
    fun get(key: K): V? {
        return cache[key]
    }

    @Synchronized
    fun put(key: K, value: V) {
        cache[key] = value
    }

    @Synchronized
    fun clear() {
        cache.clear()
    }

    @Synchronized
    fun size(): Int {
        return cache.size
    }

    override fun toString(): String =
            cache.entries.joinToString(", ", "Cache{", "}") { "${it.key}=${it.value}" }
}
