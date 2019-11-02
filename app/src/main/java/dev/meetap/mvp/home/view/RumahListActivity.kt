package dev.meetap.mvp.home.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import dev.meetap.mvp.R
import dev.meetap.mvp.home.model.RumahItem
import dev.meetap.mvp.home.model.RumahListInteractor
import dev.meetap.mvp.home.presenter.RumahListPresenter
import dev.meetap.mvp.showToast
import kotlinx.android.synthetic.main.activity_rumah_list.*

class RumahListActivity : AppCompatActivity(), RumahListView {
    private lateinit var rumahListPresenter: RumahListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rumah_list)
        rumahListPresenter = RumahListPresenter(this, RumahListInteractor())
        progressBar.visibility = View.GONE
        recyclerView.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        rumahListPresenter.getRumahData()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
    }

    override fun setRumahData(arrRumahUpdates: List<RumahItem>) {
//        recyclerView.adapter = RumahListAdapter(arrRumahUpdates, rumahListPresenter::onItemClick)    // OR
        recyclerView.adapter = RumahListAdapter(arrRumahUpdates) {
            rumahListPresenter.onItemClick(it)
        }
    }

    override fun getDataFailed(strError: String) {
        showToast(this, strError)
    }

    override fun onItemClick(adapterPosition: Int) {
        showToast(this, "You clicked $adapterPosition")
    }

    override fun onDestroy() {
        rumahListPresenter.onDestroy()
        super.onDestroy()
    }
}
