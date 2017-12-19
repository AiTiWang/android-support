package com.i4evercai.android.support.reactivex

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleOwner
import android.util.Log
import com.i4evercai.android.support.BuildConfig
import com.i4evercai.android.support.utils.LogUtils
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.lifecycle.kotlin.bindUntilEvent
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject

/**
 *
 * @Description: RxBus
 * @author Fitz
 * @email FitzPro@qq.com
 * @date 2017/7/31 10:29
 * @version V1.0
 */
object RxBus {
    private val TAG = javaClass.simpleName

    /**
     * Used to hold all subscriptions for Bus events and unsubscribe properly when needed.
     */
    private val subscriptionsMap: HashMap<Any, CompositeDisposable> by lazy {
        HashMap<Any, CompositeDisposable>()
    }

    /**
     * Avoid using this property directly, exposed only because it's used in inline fun
     */
    val rxBus = PublishSubject.create<Any>().toSerialized()

    /**
     * Sends an event to Bus. Can be called from any thread
     */
    fun send(event: Any) {
        try {
            rxBus.onNext(event)
        } catch (e: Exception) {
            if (BuildConfig.DEBUG){
                e.printStackTrace()
            }
            LogUtils.d("RxBus","RxBus传递事件报错：${e.localizedMessage}")
        }
    }

    /**
     * Subscribes for events of certain type T. Can be called from any thread
     */
    inline fun <reified T : Any> observe(): Observable<T> {
        return rxBus.ofType(T::class.java)
    }

    /**
     * Unregisters subscriber from Bus events.
     * Calls unsubscribe method of your subscriptions
     * @param subscriber subscriber to unregister
     */
    fun unregister(subscriber: Any) {
        val compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            Log.w(TAG, "Trying to unregister subscriber that wasn't registered")
        } else {
            compositeSubscription.clear()
            subscriptionsMap.remove(subscriber)
        }
    }

    internal fun register(subscriber: Any, disposable: Disposable) {
        var compositeSubscription = subscriptionsMap[subscriber]
        if (compositeSubscription == null) {
            compositeSubscription = CompositeDisposable()
        }

        compositeSubscription.add(disposable)
        subscriptionsMap[subscriber] = compositeSubscription
    }
}

/**
 * Registers the subscription to correctly unregister it later to avoid memory leaks
 * @param subscriber subscriber object that owns this subscription
 */
fun Disposable.registerInBus(subscriber: Any) {
    RxBus.register(subscriber, this)
}

fun <T> Observable<T>.registerInBus(onEventListener: ((event: T) -> Unit),
                                    observeOnScheduler: Scheduler, owner: LifecycleOwner,
                                    subscriber: Any) {
    this.registerInBus(onEventListener, observeOnScheduler, owner,Lifecycle.Event.ON_DESTROY, subscriber)
}

fun <T> Observable<T>.registerInBus(onEventListener: ((event: T) -> Unit),
                                    observeOnScheduler: Scheduler, owner: LifecycleOwner,
                                    event: Lifecycle.Event, subscriber: Any) {
    this.observeOn(observeOnScheduler)
            .bindUntilEvent(owner,event)
            .subscribe { event: T -> onEventListener.invoke(event) }
            .registerInBus(this)
}