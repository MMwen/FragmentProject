package com.example.fragment.library.common.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.example.fragment.library.base.component.activity.OnBackPressedListener
import com.example.fragment.library.base.utils.WebHelper
import com.example.fragment.library.common.constant.Keys
import com.example.fragment.library.common.databinding.FragmentWebBinding
import com.example.fragment.library.common.model.BaseViewModel
import com.tencent.smtt.sdk.WebView

class WebFragment : ViewModelFragment<FragmentWebBinding, BaseViewModel>(),
    OnBackPressedListener {

    companion object {
        @JvmStatic
        fun newInstance(): WebFragment {
            return WebFragment()
        }
    }

    private lateinit var webHelper: WebHelper
    private var url = "https://wanandroid.com/"

    override fun setViewBinding(inflater: LayoutInflater): FragmentWebBinding {
        return FragmentWebBinding.inflate(inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            url = this.getString(Keys.URL).toString()
        }
        setupView()
        webViewSetting()
    }

    override fun onResume() {
        super.onResume()
        baseActivity.registerOnBackPressedListener(this::class.java.simpleName, this)
    }

    override fun onPause() {
        super.onPause()
        baseActivity.removerOnBackPressedListener(this::class.java.simpleName)
    }

    override fun onBackPressed(): Boolean {
        return if (webHelper.webView.canGoBack()) {
            webHelper.webView.goBack()
            true
        } else {
            false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        webHelper.onDestroy()
    }

    private fun setupView() {
        binding.black.setOnClickListener {
            baseActivity.onBackPressed()
        }
    }

    private fun webViewSetting() {
        webHelper = WebHelper.with(binding.webContainer)
        webHelper.onReceivedTitleListener = object : WebHelper.OnReceivedTitleListener {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                binding.title.text = title
            }
        }
        webHelper.loadUrl(url)
    }

}