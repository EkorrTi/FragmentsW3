package com.example.fragmentsw3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsRecyclerViewAdapter(val data: List<Contact>): RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsRecyclerViewHolder>() {
    var onClick: ((Contact) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsRecyclerViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contacts, parent, false)
        return ContactsRecyclerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ContactsRecyclerViewHolder, position: Int) {
        val contact = data[position]

        holder.name.text = "${contact.name} ${contact.surname}"
        holder.phone.text = contact.phone
        holder.itemView.setOnClickListener { onClick?.invoke(contact) }
    }

    override fun getItemCount(): Int = data.size

    class ContactsRecyclerViewHolder(view: View): RecyclerView.ViewHolder(view){
        val name: TextView = view.findViewById(R.id.contact_item_name)
        val phone: TextView = view.findViewById(R.id.contact_item_phone)
    }
}