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
        val newRequest = if (!cookie.isNullOrBlank()) {
            request.newBuilder()
                .header("Cookie", cookie)
                .build()
        } else {
            request
        }

        val response = chain.proceed(newRequest)
        //获取服务器返回的新 Cookie
        val setCookies = response.headers("Set-Cookie")
        if (setCookies.isNotEmpty()) {
            val existing = CookieManager.getCookie()
                ?.split("; ")
                ?.filter { it.isNotBlank() }
                ?.toMutableSet() //方便去重
                ?: mutableSetOf()//如果本地没 Cookie，创建一个空 Set
            //把 Set-Cookie: name=value; Path=/ 截断，只取 name=value 部分
            val newPairs = setCookies.map {
                it.substringBefore(";") }
                .filter { it.isNotBlank() }
            //把新 Cookie 合并到已有的里
            existing.addAll(newPairs)
            CookieManager.saveCookie(existing.joinToString("; "))
        }

        return response
    }
}