package com.codevalley.swensonhetest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.swensonhetest.databinding.ActivityMainBinding
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    var images_object: JSONObject? = null
    var testList: ArrayList<TestModel2> = ArrayList<TestModel2>()
    var testModel2: TestModel2? = null
    var testAdapter: TestAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initRecycler()
        currencyConverter()
        isAnagrams("debit card", "bad credit")
        recursive(9)
        iterative(9)
    }


    fun firstQ() {
//        Add arithmetic operators (add, subtract, multiply, divide) to make the following expressions true. You can use any parentheses you’d like. You don’t need to write any code for this question.
//3 1 3 9 = 12

//        1) (9-3)*(3-1)=12

    }


    //SecondQ
    fun isAnagrams(str1: String, str2: String): Boolean {
        //Both String Length must be Equal
        if (str1.length != str2.length) {
            return false
        }

        //Convert Strings to character Array
        val strArray1 = str1.toCharArray()
        val strArray2 = str2.toCharArray()

        //Sort the Arrays
        Arrays.sort(strArray1)
        Arrays.sort(strArray2)

        //Convert Arrays to String
        val sortedStr1 = String(strArray1)
        val sortedStr2 = String(strArray2)

        //Check Both String Equals or not After Sorting
        //and Return value True or False
        return sortedStr1 == sortedStr2
    }


    //    ThirdQ:
    fun recursive(n: Long): Long = if (n < 2) n else recursive(n - 1) + recursive(n - 2)

    fun iterative(n: Long): Long {
        if (n < 2) return n
        var minusOne: Long = 1
        var minusTwo: Long = 0
        var result = minusOne
        for (i in 2..n) {
            result = minusOne + minusTwo
            minusTwo = minusOne
            minusOne = result
        }
        return result
    }
//   End Of ThirdQ


    ///fourthQ


    fun currencyConverter() {
        RetroWeb.getClient()?.create(AppServices::class.java)?.test()?.enqueue(object :
            Callback<TestModel> {

            override fun onResponse(call: Call<TestModel>, userModel: Response<TestModel>) {
                if (userModel != null) {
                    binding.tvBase.text = userModel.body()?.base
                    try {
                        images_object = JSONObject(userModel.body()?.rates.toString())
                        val keys = images_object!!.names()
                        val values = images_object!!.toJSONArray(keys)
                        for (i in 0 until keys.length()) {
                            testModel2 = TestModel2()
                            testModel2!!.setName(keys[i].toString())
                            testModel2!!.setValue(values[i].toString().toDouble())
                            testList.add(testModel2!!)
                        }
                        testAdapter!!.addAll(testList)
                        testAdapter!!.notifyDataSetChanged()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<TestModel>, t: Throwable) {

                t.printStackTrace()
            }
        })
    }


    private fun initRecycler() {
        testAdapter = TestAdapter(this)
        val linearLayoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.rvTest.layoutManager = linearLayoutManager
        binding.rvTest.adapter = testAdapter
    }

}