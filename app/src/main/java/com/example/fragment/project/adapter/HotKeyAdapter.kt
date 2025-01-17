package com.example.fragment.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.example.fragment.library.base.component.adapter.BaseAdapter
import com.example.fragment.library.common.bean.HotKeyBean
import com.example.fragment.project.databinding.ItemHotKeyBinding

class HotKeyAdapter : BaseAdapter<HotKeyBean>() {

    override fun onCreateViewBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return ItemHotKeyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    }

    override fun onItemView(holder: ViewBindHolder, position: Int, item: HotKeyBean) {
        val binding = holder.binding as ItemHotKeyBinding
        binding.hotKey.text = item.name
    }

}