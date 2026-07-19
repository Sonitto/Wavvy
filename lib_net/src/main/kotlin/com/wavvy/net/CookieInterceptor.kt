package com.wavvy.net

import okhttp3.Interceptor
import okhttp3.Response

/**
 * description :实现OkHttp的Interceptor接口，拦截网络请求
 * author : Cherry77551
 * date : 2026/7/17 21:48
 */
class CookieInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val cookie = CookieManager.getCookie()
        //如果本地有 Cookie，就在请求头里加上 Cookie: xxx
        //这里必须先清洗成标准 ";" 格式，否则网易云 ";;" 格式服务端解析不了
        val cleanCookie = CookieUtil.cleanCookie(cookie)
        val newRequest = if (!cleanCookie.isNullOrBlank()) {
            request.newBuilder()
                .header("Cookie", cleanCookie)
                .build()
        } else {
            request
        }

        val response = chain.proceed(newRequest)
        //获取服务器返回的新 Cookie
        val setCookies = response.headers("Set-Cookie")
        if (setCookies.isNotEmpty()) {
            val essentialCookies = listOf("MUSIC_A", "MUSIC_A_T", "MUSIC_R_T", "__csrf", "NMTID", "os")
            val cookieMap = CookieUtil.parseCookie(CookieManager.getCookie()).toMutableMap()

            // 合并服务器新返回的关键 Cookie
            setCookies.forEach { cookie ->
                val pair = cookie.trim().substringBefore(";").trim()
                if (pair.contains("=")) {
                    val name = pair.substringBefore("=")
                    val value = pair.substringAfter("=")
                    if (value.isNotBlank()) {
                        cookieMap[name] = value
                    }
                }
            }

            // 只保留关键字段，其他字段可能很多但服务端不依赖
            CookieManager.saveCookie(
                cookieMap
                    .filter { it.key in essentialCookies || it.key.startsWith("MUSIC_") }
                    .map { "${it.key}=${it.value}" }
                    .joinToString("; ")
            )
        }

        return response
    }
}
