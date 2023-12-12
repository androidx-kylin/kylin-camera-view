package androidx.kylin.camera.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.kylin.camera.R

/**
 * 默认基础的捕获控件，提供三个操作：相册、拍照、切换摄像头
 * @author RAE
 * @date 2023/12/12
 * @copyright Copyright (c) https://github.com/raedev All rights reserved.
 */
open class CaptureControlView : CameraControlView {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        initView()
    }

    protected lateinit var btnTakePhoto: View

    private fun initView() {
        LayoutInflater.from(context).inflate(R.layout.camera_contol_view_cature, this, true)
        btnTakePhoto = findViewById(R.id.btn_take_photo)
        btnTakePhoto.setOnClickListener { onTakePhotoClick() }
    }

    /**
     * 拍照
     */
    protected open fun onTakePhotoClick() {
        Toast.makeText(context, "click!", Toast.LENGTH_SHORT).show()
    }


}