package com.houdin.br.movies.shared.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Matheus Gomes on 05/06/2021.
 */
abstract class BaseViewHolder<ObjectType>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: ObjectType, position: Int)
}
