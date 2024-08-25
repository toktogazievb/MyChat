package com.example.mychat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mychat.databinding.FragmentContactInfoBinding


class ContactInfoFragment : Fragment() {

    lateinit var viewBinding: FragmentContactInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentContactInfoBinding.inflate(layoutInflater)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {
            val args: ContactsFragmentArgs by navArgs()
            val getName = args.name
            val getPhoto = args.photo
            val getNumber = args.number

            tvNumber.text = getNumber
            tvName.text = getName
            Glide.with(imgContact).load(getPhoto).into(imgContact)

        }
    }

}