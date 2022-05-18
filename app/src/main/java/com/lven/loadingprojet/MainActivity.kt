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
        LoadingManager.BASE_LOADING_LAYOUT_ID = (R.layout.pager_loading)
        LoadingManager.BASE_RETRY_LAYOUT_ID = (R.layout.pager_retry)
        LoadingManager.BASE_DATA_ERROR_LAYOUT_ID = (R.layout.pager_data_error)
        LoadingManager.BASE_EMPTY_LAYOUT_ID = (R.layout.pager_empty)

        loadingManager = LoadingManager.generate(loadingParent, onLoadingListener)
        loadingManager.showContent()
    }

    fun loading(view: View) {
        loadingManager.showLoading(true)
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


    /**
     * 模板写法
     */
    override fun getOnLoadingListener(): OnLoadingListener {
        return AppLoadingListener(this)
    }

    /**
     * 1.页面加载完成回调
     */
    override fun onViewLoaded(state: LoadState, view: View) {
        Log.e("TAG", state.name)
    }

    /**
     * 1.页面加载状态改变的回调，如果有需求可做一些通用操作
     */
    override fun onLoadingChanged(state: LoadState, view: View) {
        Log.e("TAG", state.name)
    }

    /**
     * 页面加载到哪个ViewGroup上
     */
    @SuppressLint("WrongViewCast")
    override fun getLoadingParent(): Any {
        return findViewById(R.id.fl_load_pager)
    }

    /**
     * 可根据实际情况定制空页面
     */
    override fun getEmptyLayoutId(): Int {
        return -1
    }

    /**
     * 这方法给基类用
     */
    override fun isNeedLoading(): Boolean {
        return true
    }

}