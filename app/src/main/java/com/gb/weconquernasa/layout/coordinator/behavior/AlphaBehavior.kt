package com.gb.weconquernasa.layout.coordinator.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class AlphaBehavior(context: Context, attrs: AttributeSet? = null) :
    CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        if(dependency is AppBarLayout){
            child.alpha = 1 - abs(2 * dependency.y) / dependency.height.toFloat()
            child.x =
                (dependency.width.toFloat() - child.width.toFloat()) * (1 - abs(2 * dependency.y) / dependency.height.toFloat())
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }
}