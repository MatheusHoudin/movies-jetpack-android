package com.houdin.br.movies.features.movies.presentation

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Matheus Gomes on 08/06/2021.
 */
class MoviesListDecorator : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val currentPosition = parent.getChildLayoutPosition(view)
        val columnPosition = currentPosition % GRID_COLUMN_NUMBER
        if (isMiddleMovieItem(columnPosition)) {
            outRect.apply {
                right = PADDING_BETWEEN_MOVIE_ITEMS
                left = PADDING_BETWEEN_MOVIE_ITEMS
            }
        }
    }

    private fun isMiddleMovieItem(columnPosition: Int) = columnPosition == COLUMN_MIDDLE_POSITION

    private companion object {
        const val PADDING_BETWEEN_MOVIE_ITEMS = 12
        const val GRID_COLUMN_NUMBER = 3
        const val COLUMN_MIDDLE_POSITION = 1
    }
}
