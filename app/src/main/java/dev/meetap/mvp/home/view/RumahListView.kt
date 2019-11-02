package dev.meetap.mvp.home.view

import dev.meetap.mvp.home.model.RumahItem

interface RumahListView {
    fun showProgress()
    fun hideProgress()
    fun setRumahData(arrRumahUpdates: List<RumahItem>)
    fun getDataFailed(strError: String)
    fun onItemClick(adapterPosition: Int)
}