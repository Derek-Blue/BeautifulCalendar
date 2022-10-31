package com.farris.beauty.time.sdjdi.screen.welcome

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.farris.beauty.time.sdjdi.R
import com.farris.beauty.time.sdjdi.databinding.ActivityWelcomeBinding
import com.farris.beauty.time.sdjdi.screen.MainActivity
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class WelcomeActivity: AppCompatActivity(R.layout.activity_welcome) {

    private lateinit var binding: ActivityWelcomeBinding

    private val viewModel by viewModels<WelcomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.finishInitFlow
                    .collectLatest {
                        startActivity(MainActivity.createIntent(this@WelcomeActivity))
                    }
            }
        }
    }
}