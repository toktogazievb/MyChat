package com.example.mychat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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

        val message = ArrayList<ChatModel>()
        val chatAdapter = ChatAdapter(message, activity)
        viewBinding.contMessage.adapter = chatAdapter
        viewBinding.contMessage.layoutManager = LinearLayoutManager(activity)
        var isUser1 = true
        Toast.makeText(context, "User 1", Toast.LENGTH_LONG).show()
        viewBinding.apply {
            val args: ChatFragmentArgs by navArgs()
            val getName = args.name
            val getPhoto = args.photo
            val getNumber = args.number

            tvName.text = getName
            Glide.with(imgContact).load(getPhoto).into(imgContact)

            contInfo.setOnClickListener {
//                val box = Bundle().apply {
//                    putString("contactName", tvName.text.toString())
//                    putString("contactNumber", number)
//                    putString("contactPhoto", photo)
//                }
                val name = getName
                val number = getPhoto
                val photo = getNumber
                val action = ChatFragmentDirections.actionChatFragmentToContactInfoFragment(
                    photo,
                    name,
                    number
                )
                findNavController().navigate(action)
            }
            imgBtn.setOnClickListener {

                val messageText = etMessage.text.toString()
                if (messageText.isNotEmpty()) {
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
