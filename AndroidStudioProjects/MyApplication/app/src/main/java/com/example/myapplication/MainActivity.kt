package com.example.myapplication

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.util.Log
import android.widget.Toast
import android.content.Context

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import retrofit2.http.Body
import retrofit2.http.POST


class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // Обработка полученного уведомления
        // В remoteMessage содержится информация о полученном уведомлении
        // Например, текст уведомления: remoteMessage.notification?.body

        // Получение текста уведомления
        val notificationText = remoteMessage.notification?.body ?: "Уведомление без текста"

        // Отображение уведомления в Toast
        Toast.makeText(applicationContext, notificationText, Toast.LENGTH_LONG).show()

        // Проверяем, было ли уведомление нажато
        remoteMessage.notification?.let {
            // Обработка нажатия на уведомление
            // Например, открытие определенного экрана приложения
            // или выполнение других действий в зависимости от содержания уведомления
        }
    }
    override fun onNewToken(token: String) {
        // Отправить полученный токен на ваш сервер
        sendTokenToServer(token)
    }

    private fun sendTokenToServer(token: String) {
        // Создаем экземпляр Retrofit
        val retrofit = Retrofit.Builder()
            .baseUrl("http://127.0.0.1:5000") // Замените YOUR_SERVER_URL на URL вашего сервера
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Создаем интерфейс для выполнения запросов к серверу
        val service = retrofit.create(ApiService::class.java)

        // Выполняем запрос на сервер
        val call = service.sendToken(token)
        call.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // Токен успешно отправлен на сервер
                    // Здесь можно добавить дополнительные действия при успешной отправке, например:
                    Log.d("TokenSent", "Token successfully sent to server")
                    // Можно также добавить сообщение об успешной отправке через Toast:
                    Toast.makeText(applicationContext, "Token successfully sent to server", Toast.LENGTH_SHORT).show()
                    // Или выполнить другие действия в зависимости от вашего приложения
                } else {
                    // Произошла ошибка при отправке токена на сервер
                    // Здесь можно добавить обработку ошибки, например:
                    Log.e("TokenSendError", "Error sending token to server: ${response.message()}")
                    // Можно также добавить сообщение об ошибке через Toast:
                    Toast.makeText(applicationContext, "Error sending token to server: ${response.message()}", Toast.LENGTH_SHORT).show()
                    // Или выполнить другие действия в зависимости от вашего приложения
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("NetworkError", "Error: ${t.message}")
            }
        })
    }

    // Интерфейс для выполнения запросов к серверу
    interface ApiService {
        // Пример метода для отправки токена на сервер
        @POST("send_token")
        fun sendToken(@Body token: String): Call<Void>
    }
}



class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
    }
}

class MainActivity : AppCompatActivity() {
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}