package com.sopt.now.presentation.main.search.repo

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sopt.now.R

class RepoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val nameTextView: TextView = itemView.findViewById(R.id.tv_repo_name)
    private val descriptionTextView: TextView = itemView.findViewById(R.id.tv_repo_description)
    private val languageTextView: TextView = itemView.findViewById(R.id.tv_repo_language)
    private val starsTextView: TextView = itemView.findViewById(R.id.tv_repo_stars)

    fun bind(repo: Repo) {
        nameTextView.text = repo.name
        descriptionTextView.text = repo.description
        languageTextView.text = repo.language
        starsTextView.text = repo.stargazers_count.toString()
    }
}