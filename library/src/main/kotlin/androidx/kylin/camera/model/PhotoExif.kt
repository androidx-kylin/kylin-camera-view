package androidx.kylin.camera.model

import android.annotation.SuppressLint
import android.location.Location
import android.media.ExifInterface
import android.os.Build
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

/**
 *
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.Q)
class PhotoExif(private val file: File) {
    private val format = SimpleDateFormat("yyyy:MM:dd HH:mm:ss")

    fun write(info: CameraMediaInfo, block: (() -> Map<String, Any>)? = null) {
        val map = mutableMapOf<String, Any?>()
        block?.invoke()?.also { map.putAll(it) }
        map["fileName"] = info.filename
        map["filePath"] = info.file.path
        map["fileSize"] = info.fileSize
        map["width"] = info.width
        map["height"] = info.height
        info.location?.let {
            map["latitude"] = it.latitude
            map["longitude"] = it.longitude
            map["provider"] = it.provider
            map["locationTime"] = it.time
        }
        info.sensorInfo?.let {
            map["azimuth"] = it.azimuth
            map["pitch"] = it.pitch
            map["roll"] = it.roll
        }
        val exif = ExifInterface(file)
        map.forEach { (k, v) ->
            v ?: return // 值不能为空
            exif.setAttribute(k, v.toString())
        }
        map[ExifInterface.TAG_DATETIME_ORIGINAL] = format.format(Date())
        exif.setAttribute(ExifInterface.TAG_USER_COMMENT, map.toJson())
        exif.saveAttributes()
    }

    fun read(): CameraMediaInfo? {
        val exif = ExifInterface(file)
        if (!exif.hasAttribute(ExifInterface.TAG_USER_COMMENT)) return null
        val json = exif.getAttribute(ExifInterface.TAG_USER_COMMENT) ?: return null
        val obj = JSONObject(json)
        val fileName = obj.getString("fileName")
        val filePath = obj.getString("filePath")
        val fileSize = obj.getLong("fileSize")
        val width = obj.getInt("width")
        val height = obj.getInt("height")
        val provider = obj.getString("provider") ?: "Unknown"
        val latitude = obj.getDouble("latitude")
        val longitude = obj.getDouble("longitude")
        val locationTime = obj.getString("locationTime")
        val azimuth = obj.getInt("azimuth")
        val pitch = obj.getInt("pitch")
        val roll = obj.getInt("roll")

        return CameraMediaInfo(
            fileName,
            File(filePath),
            fileSize,
            width,
            height,
            Location(provider).apply {
                this.latitude = latitude
                this.longitude = longitude
                this.time = locationTime.toLong()
            },
            DeviceSensorInfo(azimuth, pitch, roll)
        )
    }

    private fun Map<String, Any?>.toJson(): String? {
        val obj = JSONObject()
        this.forEach { (k, v) ->
            obj.put(k, v)
        }
        return runCatching { obj.toString() }.getOrNull()
    }
}