package com.example.calculadora_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Numbers
        tvOne.setOnClickListener{appendOnExpresstion("1", canClear = true)}
        tvTwo.setOnClickListener{appendOnExpresstion("2", canClear = true)}
        tvThree.setOnClickListener{appendOnExpresstion("3", canClear = true)}
        tvFour.setOnClickListener{appendOnExpresstion("4", canClear = true)}
        tvFive.setOnClickListener{appendOnExpresstion("5", canClear = true)}
        tvSix.setOnClickListener{appendOnExpresstion("6", canClear = true)}
        tvSeven.setOnClickListener{appendOnExpresstion("7", canClear = true)}
        tvEight.setOnClickListener{appendOnExpresstion("8", canClear = true)}
        tvNine.setOnClickListener{appendOnExpresstion("9", canClear = true)}
        tvZero.setOnClickListener{appendOnExpresstion("0", canClear = true)}
        tvDot.setOnClickListener{appendOnExpresstion(".", canClear = true)}

        //Operators
        tvPlus.setOnClickListener{appendOnExpresstion("+",canClear = false)}
        tvMinus.setOnClickListener{appendOnExpresstion("-",canClear = false)}
        tvMul.setOnClickListener{appendOnExpresstion("*",canClear = false)}
        tvDiv.setOnClickListener{appendOnExpresstion("/",canClear = false)}
        tvOpen.setOnClickListener{appendOnExpresstion("(",canClear = false)}
        tvClose.setOnClickListener{appendOnExpresstion(")",canClear = false)}

        tvClear.setOnClickListener{
            tvExpression.text=""
            tvResult.text=""
        }

        tvBack.setOnClickListener{
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()){
                tvExpression.text = string.substring(0,string.length-1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener{
            try {

                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if(result==longResult.toDouble())
                    tvResult.text=longResult.toString()
                else
                    tvResult.text=result.toString()

            }catch (e:Exception){
                Log.d("Exception", "message: " + e.message)
            }
        }


    }

    fun appendOnExpresstion( string: String, canClear : Boolean){

        if(tvResult.text.isNotEmpty()){
            tvExpression.text=""
        }

        if(canClear){
            tvResult.text=""
            tvExpression.append(string)
        }else{
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text=""
        }
    }
}
