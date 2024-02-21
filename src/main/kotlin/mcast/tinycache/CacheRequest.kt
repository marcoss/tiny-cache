package tinycache

import kotlinx.serialization.Serializable

@Serializable data class CacheRequest(val key: String, val value: String)
