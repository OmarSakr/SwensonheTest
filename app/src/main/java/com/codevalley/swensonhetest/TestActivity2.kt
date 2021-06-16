package com.codevalley.swensonhetest

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.codevalley.swensonhetest.databinding.ActivityTest2Binding

class TestActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityTest2Binding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTest2Binding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        binding.tvResult.setText(intent.getDoubleExtra("value", 1.0).toString())
        binding.tvCurrency.setText(intent.getStringExtra("name").toString())

        binding.etAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (TextUtils.isEmpty(binding.etAmount.getText().toString())) {
                    binding.tvResult.setText("0")
                } else {
                    binding.tvResult.setText(
                        (binding.etAmount.getText().toString()
                            .toDouble() * intent.getDoubleExtra("value", 1.0)).toString()
                    )
                }
            }
        })

    }
}