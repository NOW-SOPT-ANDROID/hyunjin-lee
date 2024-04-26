package com.sopt.now.search.repo

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.R

class RepoAdapter(private val repoList: MutableList<Repo> = mutableListOf()) : RecyclerView.Adapter<RepoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_reop_list, parent, false)
        return RepoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        val repo = repoList[position]
        holder.bind(repo)
    }

    override fun getItemCount() = repoList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setRepos(repos: List<Repo>) {
        repoList.clear()
        repoList.addAll(repos)
        notifyDataSetChanged()
    }
}