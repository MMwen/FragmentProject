package com.example.fragment.module.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.fragment.library.base.component.adapter.BaseAdapter
import com.example.fragment.module.home.R
import com.example.fragment.module.home.databinding.ItemHistorySearchBinding

class HistorySearchAdapter : BaseAdapter<String>() {

    init {
        addOnClickListener(R.id.delete)
    }

    override fun onCreateViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemHistorySearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onItemView(holder: ViewBindHolder, position: Int, item: String) {
        val binding = holder.binding as ItemHistorySearchBinding
        binding.title.text = item
    }

}