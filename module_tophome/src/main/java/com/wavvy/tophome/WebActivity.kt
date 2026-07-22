package com.wavvy.tophome

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.example.lib.common.BaseActivity
import com.example.lib.route.RoutePath
import com.example.module_tophome.R
import com.example.module_tophome.databinding.ActivityWebBinding

@Route(path = RoutePath.WEB)
class WebActivity : BaseActivity<ActivityWebBinding>() {
    @JvmField
    @Autowired(name = "url")
    var url: String? = null
    override fun getViewBinding(): ActivityWebBinding {
       return ActivityWebBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)

    }

    override fun initEvent() {
        binding.web.apply {
            settings.cacheMode = WebSettings.LOAD_NO_CACHE
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            url?.let {
                loadUrl(it)
            }
        }
    }


}