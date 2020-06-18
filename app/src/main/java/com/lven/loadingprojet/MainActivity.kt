package com.lven.loadingprojet

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.lven.loading.LoadState
import com.lven.loading.LoadingLayout
import com.lven.loading.LoadingManager
import com.lven.loading.OnLoadingListener
import com.lven.loading.listener.AppLoadingListener
import com.lven.loading.listener.AppPagerListener

class MainActivity : AppCompatActivity(), AppPagerListener {
    lateinit var loadingManager: LoadingManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading)

        // 测试用，实际用自己开发的页面
        // 测试用，实际用自己开发的页面
        LoadingManager.BASE_LOADING_LAYOUT_ID = (R.layout.pager_loading)
        LoadingManager.BASE_RETRY_LAYOUT_ID = (R.layout.pager_retry)
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = (R.layout.pager_data_error)
        LoadingManager.BASE_EMPTY_LAYOUT_ID = (R.layout.pager_empty)

        loadingManager = LoadingManager.generate(loadingParent, onLoadingListener)
        loadingManager.showContent()
    }

    fun loading(view: View) {
        loadingManager.showLoading()
    }

    fun retry(view: View) {
        loadingManager.showRetry()
    }

    fun dataError(view: View) {
        loadingManager.showNetOrDataError(this)
    }

    fun empty(view: View) {
        loadingManager.showEmpty()
    }

    fun content(view: View) {
        loadingManager.showContent()
    }

    override fun getEmptyLayoutId(): Int {
        return -1
    }

    override fun onLoadingChanged(state: LoadState, loadingLayout: LoadingLayout) {
        Log.e("TAG", state.name)
    }

    override fun getOnLoadingListener(): OnLoadingListener {
        return AppLoadingListener(this)
    }

    override fun onViewLoaded(state: LoadState, view: View) {
        Log.e("TAG", state.name)
    }

    @SuppressLint("WrongViewCast")
    override fun getLoadingParent(): Any {
        return findViewById(R.id.fl_load_pager)
    }

    override fun isNeedLoading(): Boolean {
        return true
    }

}