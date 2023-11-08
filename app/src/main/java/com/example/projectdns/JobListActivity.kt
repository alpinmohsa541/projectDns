package com.example.projectdns

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job

class JobListActivity : AppCompatActivity() {
    private var page = 1 // Initial page number
    private var isLoading = false
    private var isLastPage = false
    private val jobList = mutableListOf<Job>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_list)

        val recyclerView = findViewById<RecyclerView>(R.id.jobRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        val adapter = JobAdapter(jobList)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        // Implement pagination with RecyclerView.OnScrollListener

        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && !isLastPage) {
                    if (layoutManager.findLastVisibleItemPosition() == jobList.size - 1) {
                        // Load more data
                        page++
                        loadJobData(page)
                    }
                }
            }
        })

        // Initial data load
        loadJobData(page)
    }

    private fun loadJobData(page: Int) {
        isLoading = true
        // Make API call to fetch job data with optional parameters
        // Handle the API response and update the jobList
        // Update isLoading and isLastPage flags
    }
}