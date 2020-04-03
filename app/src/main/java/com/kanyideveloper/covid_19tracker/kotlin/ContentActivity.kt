package com.kanyideveloper.covid_19tracker.kotlin

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.kanyideveloper.covid_19tracker.R
import com.shashank.sony.fancydialoglib.FancyAlertDialog
import com.shashank.sony.fancydialoglib.Icon
import org.json.JSONException
import org.json.JSONObject

class ContentActivity : AppCompatActivity() {

    private val TAG = "good"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var statisticsList: ArrayList<StatisticsList>
    lateinit var editTextSearch: EditText

    private val URL = "https://covid2019-api.herokuapp.com/v2/current"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val toolbar = findViewById<Toolbar>(R.id.myToolbar)

        toolbar.title = "Covid-19 Tracker"
        setSupportActionBar(toolbar)

        editTextSearch = findViewById(R.id.searchEdittext)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        statisticsList = ArrayList()

        editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())
            }
        })

        getData()
    }

    fun filter(e: String) {
        val filteredList = ArrayList<StatisticsList>()
        for (item in statisticsList) {
            if (item.country?.toLowerCase()?.contains(e.toLowerCase())!!) {
                filteredList.add(item)
            }
        }
        adapter.filterList(filteredList)
    }

    private fun getData() {
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Loading...")
        progressDialog.show()
        val stringRequest = StringRequest(Request.Method.GET, URL, Response.Listener { response ->
            progressDialog.dismiss()
            Log.e("CurrentData", "Response: $response")
            try {
                recyclerView = findViewById(R.id.recyclerView)
                val jsonObject = JSONObject(response)
                val jsonArray = jsonObject.getJSONArray("data")
                for (i in 0 until jsonArray.length()) {
                    val jsonObject1 = jsonArray.getJSONObject(i)
                    val statisticsList1 = StatisticsList(jsonObject1.getString("location"), jsonObject1.getString("active"),
                            jsonObject1.getString("recovered"), jsonObject1.getString("deaths"), jsonObject1.getString("confirmed"))
                    statisticsList.add(statisticsList1)
                }
                adapter = RecyclerViewAdapter(applicationContext, statisticsList)
                recyclerView.adapter = adapter
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }, Response.ErrorListener { error -> Toast.makeText(this@ContentActivity, error.message, Toast.LENGTH_SHORT).show() })
        val requestQueue = Volley.newRequestQueue(this)
        requestQueue.add(stringRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.about -> {
                FancyAlertDialog.Builder(this)
                        .setTitle("About the App")
                        .setNegativeBtnText("")
                        .setNegativeBtnBackground(Color.parseColor("#ffffff"))
                        .setBackgroundColor(Color.parseColor("#303f9f"))
                        .setMessage("Covid-19 Tracker is an android application that helps you to be updated on the current statistics of the Corona virus spread throughout the different parts of the world. The data is updated everyday from a trusted API.")
                        .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                        .setPositiveBtnText("OK")
                        .setIcon(R.drawable.github, Icon.Visible).build()
            }
            R.id.developer -> {
                FancyAlertDialog.Builder(this)
                        .setTitle("Joel Kanyi")
                        .setNegativeBtnText("View Repo")
                        .setNegativeBtnBackground(Color.parseColor("#33cc33"))
                        .setBackgroundColor(Color.parseColor("#303f9f"))
                        .setMessage("""
                            Android developer
                            Software developer
                            Student
                            LinkedIn: Joel Kanyi
                            Github: github.com/JoelKanyi
                            Phone No: +254706003891
                            Kotlin by: github.com/ngangavic
                            """.trimIndent())
                        .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                        .setPositiveBtnText("OK")
                        .setIcon(R.drawable.github, Icon.Visible)
                        .OnNegativeClicked {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse("https://github.com/JoelKanyi/Covid-19-Tracker")
                            startActivity(intent)
                        }.build()
            }
            R.id.tollNumbers -> {
                FancyAlertDialog.Builder(this)
                        .setTitle("Ministry of Health")
                        .setNegativeBtnText("Call")
                        .setNegativeBtnBackground(Color.parseColor("#33cc33"))
                        .setBackgroundColor(Color.parseColor("#303f9f"))
                        .setMessage("""
                            Corona virus disease is NOT airborne. It is transmitted through droplets when an infected person coughs or sneezes.
                            For help call 719 or dial *719#
                            """.trimIndent())
                        .setPositiveBtnBackground(Color.parseColor("#ff8041"))
                        .setPositiveBtnText("OK")
                        .setIcon(R.drawable.github, Icon.Visible)
                        .OnNegativeClicked {
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:719")
                            startActivity(intent)
                        }.build()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}