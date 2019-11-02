package dev.meetap.mvp.home.model

import android.text.TextUtils
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import dev.meetap.mvp.BuildConfig

class RumahListInteractor {
    companion object {
        private val TAG: String = RumahListInteractor::class.java.simpleName
    }

    interface OnFinishedListener {
        fun onResultSuccess(arrRumahUpdates: List<RumahItem>)
        fun onResultFail(strError: String)
    }

    fun requestRumahListDataAPI(onFinishedListener: OnFinishedListener) {
        AndroidNetworking.get(BuildConfig.BASE_URL+"rumah/readAll")
                .setPriority(Priority.LOW)
                .build()
                .getAsObject(DataResponseNullable::class.java, object : ParsedRequestListener<DataResponseNullable> {
                    override fun onResponse(dataResponseNullable: DataResponseNullable) {
                        val rumahItemList: MutableList<RumahItem>? = mutableListOf()
                        /**
                         * Note: we have two different data model- rumah: RumahItem & RumahItemNullable
                         * We are getting server data in RumahItemNullable and then
                         * filtering it to remove any null or empty item.
                         * This filtered data is then copied to the RumahItem item.
                         * This way we are reducing nullability
                         */
                        dataResponseNullable
                                .records?.asSequence()?.filterNotNull()
                                ?.filterNot { TextUtils.isEmpty(it.author) || TextUtils.isEmpty(it.title) || TextUtils.isEmpty(it.description) }
                                ?.map { RumahItem(it.author!!, it.description!!, it.title!!) }?.toList()
                                ?.forEach { rumahItemList?.add(it) }

                        if (rumahItemList != null && !rumahItemList.isEmpty()) {
                            onFinishedListener.onResultSuccess(rumahItemList)
                        } else {
                            onFinishedListener.onResultFail("Nothing to show")
                        }
                    }

                    override fun onError(anError: ANError) {
                        // handle error
                        onFinishedListener.onResultFail("Something went wrong")
                    }
                })
    }
}