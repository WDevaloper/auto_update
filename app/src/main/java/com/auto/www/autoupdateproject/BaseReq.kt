package com.auto.www.autoupdateproject

import android.text.TextUtils
import androidx.annotation.CallSuper
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.util.*
import kotlin.Comparator


//session	false	String	登录session（登录时需传递）
//version	true	String	当前版本号（1.1.1）
//device	true	int	设备（2：安卓，4：IOS）
//deviceId	false	String	设备ID
//appid	true	String
//timestamp	true	String
//nonce	true	String
//sign
open  class BaseReq {
    // 不能使用复写get属性，否则可能会造成 参数值不一致
    val timestamp: String = "${System.currentTimeMillis()}"
    val nonce: String = "${System.currentTimeMillis()}"
    val appId: String = "android_customer"
    val session: String = ""
    val version: String = "10000"
    val device: Int = 2

    val sign: String
        get() = getApiSign()

    private fun getApiSign(): String {
        val params = getParams()
        val sb = StringBuffer()
        params.toSortedMap(Comparator { key1, key2 -> return@Comparator key1.compareTo(key2) })
                .forEach {
                    //不为空才参加签名
                    if (!TextUtils.isEmpty(it.value.toString())) {
                        sb.append(it.key).append(it.value)
                    }
                }

        return "X1F7rWMLjlO0Vx4p${sb}X1F7rWMLjlO0Vx4p".md5()
    }

    @CallSuper
    open fun getParams(): TreeMap<String, Any?> {
        val params = TreeMap<String, Any?>()
        params["appid"] = appId
        params["nonce"] = nonce
        params["timestamp"] = timestamp
        params["version"] = version
        return params
    }


    fun getParam(): Map<String, Any?> {
        val apiSign = getApiSign()
        val params = getParams()
        params.put("sign", apiSign)
        return params.toMap()
    }

    /**
     * 32位MD5加密
     */
    fun String.md5() = run {
        val hash = hash(toByteArray(StandardCharsets.UTF_8), "md5")
        val hex = StringBuilder(hash.size * 2)
        for (b in hash) {
            val i: Int = b.toInt() and 0xff //获取低八位有效值
            val hexString = Integer.toHexString(i)//将整数转化为16进制
            if (hexString.length < 2) hex.append("0") //如果是一位的话，补0
            hex.append(hexString)
        }
        hex.toString()
    }

    private fun hash(data: ByteArray, algorithm: String): ByteArray {
        val messageDigest = MessageDigest.getInstance(algorithm)
        return messageDigest.digest(data)
    }

}

