package com.codevalley.swensonhetest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.swensonhetest.databinding.ItemTestBinding
import java.lang.String
import java.util.*

class TesstAdapterr(var context: Context) : RecyclerView.Adapter<TesstAdapterr.ViewHolder>(){

    var homeList: ArrayList<TestModel2>? = null
    var layoutInflater: LayoutInflater? = null

    init {
        homeList = ArrayList()
        this.context = context
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTestBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }


    override fun getItemCount(): Int {
        return homeList?.size!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvName.setText(homeList!![position].getName())
        holder.binding.tvValue.setText(String.valueOf(homeList!![position].getValue()))
        holder.itemView.setOnClickListener { v: View? ->
            val intent = Intent(context, TestActivity2::class.java)
            intent.putExtra("name", homeList!![position].getName())
            intent.putExtra("value", homeList!![position].getValue())
            context!!.startActivity(intent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }



    fun addAll(data: List<TestModel2>?) {
        homeList!!.clear()
        homeList!!.addAll(data!!)
        notifyDataSetChanged()
    }


    class ViewHolder(binding: ItemTestBinding) : RecyclerView.ViewHolder(binding.getRoot())  {


        var binding: ItemTestBinding

        init {
            this.binding = binding
        }

    }
}