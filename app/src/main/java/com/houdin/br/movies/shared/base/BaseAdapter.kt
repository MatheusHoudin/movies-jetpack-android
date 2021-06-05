package com.houdin.br.movies.shared.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Matheus Gomes on 05/06/2021.
 */
abstract class BaseAdapter<ObjectType> : RecyclerView.Adapter<BaseViewHolder<ObjectType>>() {

    private val dataSet = mutableListOf<ObjectType>()

    abstract fun getViewHolder(parent: ViewGroup): BaseViewHolder<ObjectType>

    @LayoutRes
    abstract fun getLayoutRes(): Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = getViewHolder(parent)

    override fun onBindViewHolder(holder: BaseViewHolder<ObjectType>, position: Int) =
        holder.bind(dataSet[position], position)

    override fun getItemCount() = dataSet.size

    fun addItems(values: List<ObjectType>) {
        dataSet.apply {
            clear()
            addAll(values)
        }
        notifyDataSetChanged()
    }
}
