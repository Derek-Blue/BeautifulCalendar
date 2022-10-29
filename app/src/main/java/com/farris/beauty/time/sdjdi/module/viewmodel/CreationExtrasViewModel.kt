package com.farris.beauty.time.sdjdi.module.viewmodel

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.MutableCreationExtras
import androidx.lifecycle.viewmodel.viewModelFactory
import java.lang.IllegalArgumentException

/**
 * registered need creation extras view model class, and take parameters with key
 */
val mViewModelFactory = viewModelFactory {
//    initializer {
//
//    }
}

@MainThread
inline fun <reified VM : ViewModel> ComponentActivity.extrasViewModel(
    noinline parameters: ParametersDefinition? = null
) : Lazy<VM> {
    return viewModels (
        extrasProducer = {
            MutableCreationExtras(this.defaultViewModelCreationExtras).apply {
                parameters?.let { this.insertValue(it) }
            }
        },
        factoryProducer = { mViewModelFactory }
    )
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.parentViewModel() : Lazy<VM> {
    return viewModels(
        ownerProducer = {
            requireParentFragment()
        }
    )
}

@MainThread
inline fun <reified VM : ViewModel> Fragment.extrasViewModel(
    noinline parameters: ParametersDefinition? = null
) : Lazy<VM> {
    return viewModels (
        ownerProducer = {
            this
        },
        extrasProducer = {
            MutableCreationExtras(this.defaultViewModelCreationExtras).apply {
                parameters?.let { this.insertValue(it) }
            }
        },
        factoryProducer = { mViewModelFactory }
    )
}

@Suppress("UNCHECKED_CAST")
fun MutableCreationExtras.insertValue(parameters: () -> List<ViewModelParameter<*>>) {
    parameters.invoke().forEach { parameter ->
        val key = parameter.key
        when (val value = parameter.value) {
            is String -> {
                this[key as CreationExtras.Key<String>] = value
            }
            is Long -> {
                this[key as CreationExtras.Key<Long>] = value
            }
            is Int -> {
                this[key as CreationExtras.Key<Int>] = value
            }
            is Boolean -> {
                this[key as CreationExtras.Key<Boolean>] = value
            }
            is Float -> {
                this[key as CreationExtras.Key<Float>] = value
            }
            is Double -> {
                this[key as CreationExtras.Key<Double>] = value
            }
            // Extras Type
            else -> {
                throw IllegalArgumentException("Unknown Type: ${value?.javaClass?.name}")
            }
        }
    }
}

fun parametersOf(vararg parameters: ViewModelParameter<*>) =
    if (parameters.size <= 5) {
        parameters.toList()
    }else {
        throw IllegalArgumentException("DefinitionParameters size is to much")
    }

typealias ParametersDefinition = () -> List<ViewModelParameter<*>>

data class ViewModelParameter<V>(
    val value : V,
    val key : CreationExtras.Key<V>
)

infix fun <V> V.withKey(key: CreationExtras.Key<V>):  ViewModelParameter<V> = ViewModelParameter(this, key)