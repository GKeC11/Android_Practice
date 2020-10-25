package com.example.module_common

import android.app.Service
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Binder
import android.os.IBinder

class ServiceManager {

    companion object{
        var serviceInstanceMap: HashMap<String, Service> = hashMapOf()

        fun register(context: Context, serviceClass: Class<out Service>){
            Intent(context, serviceClass).also {
                context?.bindService(it,
                    serviceConn, Context.BIND_AUTO_CREATE)
            }
        }

        fun findService(className: String): Service?{
            return serviceInstanceMap[className]
        }

        val serviceConn = object : ServiceConnection {
            override fun onServiceDisconnected(componentName: ComponentName?) {

            }

            override fun onServiceConnected(componentName: ComponentName?, binder: IBinder?) {
                val binder = binder as BaseBinder
                val service = binder.getService()
                serviceInstanceMap[service::class.java.simpleName] = service
            }
        }
    }
}

abstract class BaseBinder : Binder() {

    abstract fun getService(): Service
}