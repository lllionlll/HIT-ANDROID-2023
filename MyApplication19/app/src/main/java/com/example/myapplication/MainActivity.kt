package com.example.myapplication

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.FileUtils
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class MainActivity : AppCompatActivity() {

    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    private val adapter by lazy { UserAdapter(::onClickItem)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recycle.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        binding.recycle.adapter = adapter


        val apiService = ApiHelper.getInstance().create(ApiService::class.java)

        apiService.getAllUser().enqueue(object : Callback<ApiResponse<List<User>>> {
            override fun onResponse(
                call: Call<ApiResponse<List<User>>>,
                response: Response<ApiResponse<List<User>>>
            ) {

                if (response.isSuccessful) {
                    val listUser = response.body()?.data ?: mutableListOf()
                    adapter.setData(listUser)
                }
            }

            override fun onFailure(call: Call<ApiResponse<List<User>>>, t: Throwable) {

            }
        })
//
//        apiService.getUserByID(2).enqueue(object : Callback<ApiResponse<User>> {
//            override fun onResponse(
//                call: Call<ApiResponse<User>>,
//                response: Response<ApiResponse<User>>
//            ) {
//                Log.e("-------------------", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
//
//            }
//        })
//
//        apiService.getUserByID(2).enqueue(object : Callback<ApiResponse<User>> {
//            override fun onResponse(
//                call: Call<ApiResponse<User>>,
//                response: Response<ApiResponse<User>>
//            ) {
//                Log.e("-------------------", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ApiResponse<User>>, t: Throwable) {
//
//            }
//        })

//        apiService.forgetPassword("nhl.edm@gmail.com").enqueue(object : Callback<ApiResponse<String>> {
//            override fun onResponse(
//                call: Call<ApiResponse<String>>,
//                response: Response<ApiResponse<String>>
//            ) {
//                Log.e("-------------------", response.body().toString())
//            }
//
//            override fun onFailure(call: Call<ApiResponse<String>>, t: Throwable) {
//
//            }
//
//        })


    }

    private fun onClickItem(item: User, listUser:List<User>){
        Toast.makeText(this, item.fullName, Toast.LENGTH_LONG).show()
        val newList = listUser.toMutableList()
            newList.remove(item)
        adapter.setData(newList)
    }

}