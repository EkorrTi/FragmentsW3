package com.example.fragmentsw3

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class MyItemDecorator: RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val mBounds = Rect()
        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.style = Paint.Style.FILL
        paint.color = Color.BLUE
        for (i in 0..parent.childCount){
            val child = parent.getChildAt(i)
            //parent.getDecoratedBoundsWithMargins(child, mBounds)
            mBounds.left = 0
            mBounds.right = child.translationX.roundToInt()
            mBounds.bottom = child.translationY.roundToInt()
            mBounds.top = 2
            c.drawRect(mBounds, paint)
        }
    }
}