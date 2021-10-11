package com.mashup.kkyuni.feature.calendar.presentation

import androidx.annotation.IntDef
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper

open class SnapPageScrollListener(
    private var snapHelper: SnapHelper,
    private var type: Int = -1,
    private var notifyOnInit: Boolean = false,
    private var listener: OnChangeListener,
    private var snapPosition: Int = RecyclerView.NO_POSITION
) : RecyclerView.OnScrollListener() {

    @IntDef(value = [ON_SCROLL, ON_SETTLED])
    annotation class Type

    fun interface OnChangeListener {
        fun onSnapped(position: Int)
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (type == ON_SETTLED && newState == RecyclerView.SCROLL_STATE_IDLE) {
            notifyListenerIfNeeded(getSnapPosition(recyclerView))
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if ((type == ON_SCROLL) || hasItemPosition().not()) {
            notifyListenerIfNeeded(getSnapPosition(recyclerView))
        }
    }

    private fun getSnapPosition(recyclerView: RecyclerView): Int {
        val layoutManager = recyclerView.layoutManager ?: return RecyclerView.NO_POSITION
        val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
        return layoutManager.getPosition(snapView)
    }

    private fun notifyListenerIfNeeded(newSnapPosition: Int) {
        if (snapPosition != newSnapPosition) {
            if (notifyOnInit && hasItemPosition().not()) {
                listener.onSnapped(newSnapPosition)
            } else if (hasItemPosition()) {
                listener.onSnapped(newSnapPosition)
            }
            snapPosition = newSnapPosition
        }
    }

    private fun hasItemPosition(): Boolean {
        return snapPosition != RecyclerView.NO_POSITION
    }

    companion object {
        const val ON_SCROLL = 0
        const val ON_SETTLED = 1
    }

}
