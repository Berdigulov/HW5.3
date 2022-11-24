package com.example.hw53

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.hw53.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var imageAdapter=ImageAdapter(arrayListOf())
    private var page:Int = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding){

            btnNext.setOnClickListener {
                doRequest(++page)
            }

            btnFind.setOnClickListener {
                doRequest(page)

            }
            recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (!recyclerView.canScrollVertically(1)) {
                        doRequest(++page)


                    }
                }
            })
        }
    }

    private fun ActivityMainBinding.doRequest(page:Int) {
        RetrofitService.api.getImage(etPhoto.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(call: Call<PixaModel>, response: Response<PixaModel>) {
                    if (response.isSuccessful) {
                        response.body()?.hits?.let {
                            it.forEach{
                                imageAdapter.addImage(it)
                            }
                            binding.recycler.adapter = imageAdapter
                    Log.e("ololo", "onResponse: ${response.body()!!.hits[0].largeImageURL}")
                }
            }
            }
                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}", )
                }

            })
}
}