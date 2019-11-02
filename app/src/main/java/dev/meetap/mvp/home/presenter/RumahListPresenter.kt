package dev.meetap.mvp.home.presenter

import dev.meetap.mvp.home.model.RumahItem
import dev.meetap.mvp.home.model.RumahListInteractor
import dev.meetap.mvp.home.view.RumahListView

class RumahListPresenter(private var rumahListView: RumahListView?, private val rumahListInteractor: RumahListInteractor)
    : RumahListInteractor.OnFinishedListener {

    fun getRumahData() {
        rumahListView?.showProgress()
        rumahListInteractor.requestRumahListDataAPI(this)
    }

    fun onDestroy() {
        rumahListView = null
    }

    override fun onResultSuccess(arrRumahUpdates: List<RumahItem>) {
        rumahListView?.hideProgress()
        rumahListView?.setRumahData(arrRumahUpdates)
    }

    override fun onResultFail(strError: String) {
        rumahListView?.hideProgress()
        rumahListView?.getDataFailed(strError)
    }

    fun onItemClick(adapterPosition: Int) {
        rumahListView?.onItemClick(adapterPosition)
    }
}