package com.example.mychat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mychat.databinding.ItemContactBinding

class ContactAdapter(
    val contacts: ArrayList<ContactModel>,
    val onItemClick: (Int) -> Unit
) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    inner class ContactViewHolder(private val viewBinding: ItemContactBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(contactModel: ContactModel) {
            viewBinding.apply {
                tvNumber.text = contactModel.number
                tvName.text = contactModel.name
                Glide.with(imgContact).load(contactModel.image).into(imgContact);
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.onBind(contacts[position])
        holder.itemView.setOnClickListener {
            onItemClick.invoke(position)
        }
    }
}