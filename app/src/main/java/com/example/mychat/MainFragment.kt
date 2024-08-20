package com.example.mychat

import android.R.attr
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.fragment.app.Fragment
import com.example.mychat.databinding.FragmentMainBinding


class MainFragment : Fragment() {

    lateinit var viewBinding: FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isUser2 = false
        Toast.makeText(context, "User 1", Toast.LENGTH_LONG).show()
        viewBinding.imgBtn.setOnClickListener {
            val message: String = viewBinding.etMessage.text.toString()
            if (!isUser2 && message.isNotEmpty()) {
                Toast.makeText(context, "User 2", Toast.LENGTH_LONG).show()
                viewBinding.imgBtn.setBackgroundResource(R.drawable.bg_btn_sent)
                val tv1 = createTV1(message)
                viewBinding.contMessage.addView(tv1)
                isUser2 = true
            } else if (isUser2 && message.isNotEmpty()) {
                Toast.makeText(context, "User 1", Toast.LENGTH_LONG).show()
                viewBinding.imgBtn.setBackgroundResource(R.drawable.bg_btn_sent_2)
                val tv2 = createTV2(message)
                viewBinding.contMessage.addView(tv2)
                isUser2 = false
            }
            viewBinding.etMessage.text.clear()
        }
    }

    fun createTV1(text: String): TextView {
        val textView = TextView(ContextThemeWrapper(context, R.style.User1_TextView))
        textView.text = text
        val params = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT)
        params.gravity = 8388613
        params.setMargins(20, 12, 20, 8)
        textView.layoutParams = params
        return textView
    }

    fun createTV2(text: String): TextView {
        val textView = TextView(ContextThemeWrapper(context, R.style.User2_TextView))
        textView.text = text
        val params = LinearLayout.LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        params.gravity = 8388611
        params.setMargins(20, 12, 20, 8)
        textView.layoutParams = params
        return textView
    }
}
