package com.wavvy.net

/**
 * description :把网易云格式的 cookie 清洗成标准格式
 * author : Cherry77551
 * date : 2026/7/18
 */
object CookieUtil {

    /**
     * 把网易云 API 返回的 cookie 字符串转成标准 HTTP Cookie 格式。
     * 输入示例："MUSIC_A=xxx;; MUSIC_A_T=xxx;; Path=/; HttpOnly"
     * 输出示例："MUSIC_A=xxx; MUSIC_A_T=xxx"
     */
    fun cleanCookie(rawCookie: String?): String? {
        if (rawCookie.isNullOrBlank()) return null

        // 网易云原始 cookie 是 ";;" 分隔，但保存到本地后已经是标准 ";" 分隔
        // 这里需要同时兼容两种格式
        val separator = if (rawCookie.contains(";;")) ";;" else ";"

        return rawCookie.split(separator)
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() }
            .map { it.substringBefore(";").trim() }
            .filter { it.contains("=") && it.substringAfter("=").isNotBlank() }
            .joinToString("; ")
            .takeIf { it.isNotBlank() }
    }

    /**
     * 把标准 HTTP Cookie 字符串按 ";" 拆分成 name-value 映射。
     */
    fun parseCookie(cookie: String?): Map<String, String> {
        if (cookie.isNullOrBlank()) return emptyMap()

        return cookie.split(";")
            .asSequence()
            .map { it.trim() }
            .filter { it.isNotBlank() && it.contains("=") }
            .map {
                val name = it.substringBefore("=").trim()
                val value = it.substringAfter("=").trim()
                name to value
            }
            .filter { it.second.isNotBlank() }
            .toMap()
    }
}
