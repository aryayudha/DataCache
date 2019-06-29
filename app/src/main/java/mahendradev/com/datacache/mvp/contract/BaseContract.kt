package mahendradev.com.datacache.mvp.contract

/**
 * Created by Arya Yudha Mahendra on 29/06/2019.
 */
class BaseContract {

    interface View {}

    interface Presenter<in T>{
        fun subscribe()
        fun unsubcribe()
        fun attach(view: T)
    }
}