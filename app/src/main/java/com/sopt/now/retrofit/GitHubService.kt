package com.sopt.now.retrofit

import com.sopt.now.search.repo.Repo
import retrofit2.Call
import retrofit2.http.GET

interface GitHubService {
    @GET("users/2hyunjinn/repos")
    fun listRepos(): Call<List<Repo>>
}