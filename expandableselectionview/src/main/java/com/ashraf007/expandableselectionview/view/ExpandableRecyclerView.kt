package com.ashraf007.expandableselectionview.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.ashraf007.expandableselectionview.extension.collapse
import com.ashraf007.expandableselectionview.extension.expand

class ExpandableRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private var animating = false
    var maxHeight: Int = Int.MAX_VALUE

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newHeightSpec = when {
            animating -> heightMeasureSpec
            else -> MeasureSpec.makeMeasureSpec(maxHeight, MeasureSpec.AT_MOST)
        }
        super.onMeasure(widthMeasureSpec, newHeightSpec)
    }

    fun expand(animationDuration: Long) {
        this.animating = true
        this.expand(maxHeight, animationDuration) {
            this.animating = false
        }
    }

    fun collapse(animationDuration: Long) {
        this.animating = true
        this.collapse(animationDuration) {
            this.animating = false
        }
    }
}
