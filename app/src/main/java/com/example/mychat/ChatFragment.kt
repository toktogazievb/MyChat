package com.example.mychat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mychat.databinding.FragmentChatBinding


class ChatFragment : Fragment() {

    lateinit var viewBinding: FragmentChatBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        viewBinding = FragmentChatBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var number: String? = null
        var image: String? = null
        val message= ArrayList<ChatModel>()
        val chatAdapter = ChatAdapter(message, activity)
        viewBinding.contMessage.adapter = chatAdapter
        viewBinding.contMessage.layoutManager = LinearLayoutManager(activity)
        var isUser1 = true
        Toast.makeText(context, "User 1", Toast.LENGTH_LONG).show()
        viewBinding.apply {
            if (arguments != null) {
                tvName.text = arguments?.getString("contactName")
                image = arguments?.getString("contactPhoto")
                Glide.with(imgContact).load(image).into(imgContact)
                number = arguments?.getString("contactNumber")
            }
            contInfo.setOnClickListener {
                val bundle = Bundle().apply {
                    putString("contactName", tvName.text.toString())
                    putString("contactNumber", number)
                    putString("contactPhoto", image)
                }
                val fragment = ContactInfoFragment()
                fragment.arguments = bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container_view_tag, fragment).addToBackStack(null)
                    .commit()
            }
            imgBtn.setOnClickListener {

                val messageText = etMessage.text.toString()
                if(messageText.isNotEmpty()){
                    val newMessage = ChatModel(messageText, isUser1)
                    message.add(newMessage)
                    chatAdapter.notifyItemInserted(message.size - 1)
                    isUser1 = !isUser1
                    etMessage.text.clear()
                    contMessage.smoothScrollToPosition(message.size - 1)
                }
            }
        }

    }

//    fun createTV1(text: String): TextView {
//        val textView = TextView(ContextThemeWrapper(context, R.style.User1_TextView))
//        textView.text = text
//        val params = LinearLayout.LayoutParams(
//            LayoutParams.WRAP_CONTENT,
//            LayoutParams.WRAP_CONTENT
//        )
//        params.gravity = Gravity.END
//        params.setMargins(20, 12, 20, 8)
//        textView.layoutParams = params
//        return textView
//    }
//
//    fun createTV2(text: String): TextView {
//        val textView = TextView(ContextThemeWrapper(context, R.style.User2_TextView))
//        textView.text = text
//        val params = LinearLayout.LayoutParams(
//            LayoutParams.WRAP_CONTENT,
//            LayoutParams.WRAP_CONTENT
//        )
//        params.gravity = Gravity.START
//        params.setMargins(20, 12, 20, 8)
//        textView.layoutParams = params
//        return textView
//    }
}
