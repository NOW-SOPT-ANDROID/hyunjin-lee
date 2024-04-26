package com.sopt.now.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sopt.now.databinding.FragmentSearchBinding
import com.sopt.now.search.repo.Repo
import com.sopt.now.search.repo.RepoAdapter
import com.sopt.now.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Response

class SearchFragment: Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RepoAdapter()
        binding.rvRepos.layoutManager = LinearLayoutManager(context)
        binding.rvRepos.adapter = adapter

        RetrofitClient.instance.listRepos().enqueue(object :
            retrofit2.Callback<List<Repo>> {
            override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                if (response.isSuccessful) {
                    adapter.setRepos(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                Log.d("search", "오류..")
            }
        })
    }
}