package androidx.kylin.camera.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.kylin.camera.demo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // bind camera to layout
        binding.cameraLayout.bind(this) {}
    }
}