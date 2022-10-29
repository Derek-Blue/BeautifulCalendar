package com.farris.beauty.time.sdjdi.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.farris.beauty.time.sdjdi.R
import com.farris.beauty.time.sdjdi.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observer()
    }

    private fun observer() {
        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.viewState.map {
                        it.repositoryData
                    }
                        .collectLatest {

                        }
                }

                launch {
                    viewModel.viewState.map {
                        it.isProgress
                    }
                        .collectLatest {
                            binding.progress.isVisible = it
                        }
                }

                launch {
                    viewModel.error
                        .collectLatest {
                            showAlert(it)
                        }
                }
            }
        }
    }

    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("ERROR")
            .setMessage(message)
            .setPositiveButton("ok") { _, _ ->

            }
            .show()
    }
}