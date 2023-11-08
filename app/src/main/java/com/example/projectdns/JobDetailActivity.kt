package com.example.projectdns

import android.os.Bundle
import android.telecom.Call
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.security.auth.callback.Callback


class JobDetailActivity : AppCompatActivity() {
    interface JobService {
        // Define the endpoint for fetching job details by jobId
        @GET("recruitment/positions/{jobId}.json")
        fun getJobDetail(@Path("jobId") jobId: String): Call<JobDetail>
    }

    data class JobDetail(
        val id: String,
        val title: String,
        val description: String,
        // Add more fields as needed
    )
}
class JobDetailActivity : AppCompatActivity() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dev6.dansmultipro.co.id/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val jobService = retrofit.create(JobDetailActivity.JobService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_job_detail)

        val jobId = intent.getStringExtra("job_id")

        if (jobId != null) {
            // Make the API call to fetch job details
            val call = jobService.getJobDetail(jobId)

            call.enqueue(object : Callback<JobDetailActivity.JobDetail> {
                override fun onResponse(call: Call<JobDetailActivity.JobDetail>, response: Response<JobDetailActivity.JobDetail>) {
                    if (response.isSuccessful) {
                        val jobDetail = response.body()
                        if (jobDetail != null) {
                            // Update UI with job detail data
                            // For example, set text in TextViews
                            title.text = jobDetail.title
                            desc.text = jobDetail.description
                        } else {
                            // Handle null response
                        }
                    } else {
                        // Handle unsuccessful response (e.g., error message)
                    }
                }

                override fun onFailure(call: Call<JobDetail>, t: Throwable) {
                    // Handle network errors
                }
            })
        }
    }
}
