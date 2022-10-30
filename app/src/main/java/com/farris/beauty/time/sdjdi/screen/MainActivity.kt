package com.farris.beauty.time.sdjdi.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.farris.beauty.time.sdjdi.R
import com.farris.beauty.time.sdjdi.databinding.ActivityMainBinding
import com.farris.beauty.time.sdjdi.screen.sample.SampleAdapter
import com.farris.beauty.time.sdjdi.type.WeatherElementType
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

        initView()
        observer()
    }

    private fun initView() {
        binding.recyclerView.apply {
            adapter = SampleAdapter()
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayout.VERTICAL))
        }

        binding.chooseTextView.setOnClickListener {
            val names = WeatherElementType.values().map {
                it.elementName
            }.toTypedArray()
            AlertDialog.Builder(this)
                .setTitle("select element")
                .setSingleChoiceItems(names, -1) { dialog, which ->
                    names.getOrNull(which)?.let {
                        WeatherElementType.fromName(it)
                    }?.let {
                        viewModel.setElement(it)
                    }
                    dialog.dismiss()
                }
                .show()
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                launch {
                    viewModel.viewState.map {
                        it.items
                    }
                        .collectLatest {
                            (binding.recyclerView.adapter as? SampleAdapter)?.submitList(it)
                        }
                }

                launch {
                    viewModel.viewState.map {
                        it.isProgress
                    }
                        .collectLatest {
                            binding.lockConstrainLayout.isVisible = it
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