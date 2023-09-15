package com.example.fragmentsw3

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

private const val TAG_DELETED = "Item Deleted"

class ContactsRecyclerViewAdapter(private val origData: MutableList<Contact>) :
    RecyclerView.Adapter<ContactsRecyclerViewAdapter.ContactsRecyclerViewHolder>() {
    var onClick: ((Contact) -> Unit)? = null
    private var data: MutableList<Contact> = origData

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsRecyclerViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contacts, parent, false)
        return ContactsRecyclerViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ContactsRecyclerViewHolder, position: Int) {
        val contact = data[position]

        holder.name.text = "${contact.name} ${contact.surname}"
        holder.phone.text = contact.phone
        Picasso.get().load(contact.avatarURL).into(holder.avatar)
        holder.itemView.setOnClickListener { onClick?.invoke(contact) }
        holder.itemView.setOnLongClickListener {
            showAlertDialog(
                R.string.contact_delete,
                R.string.contact_delete_confirm,
                holder.itemView.context,
                position
            )
            true
        }
    }

    private fun showAlertDialog(
        @StringRes title: Int,
        @StringRes message: Int,
        context: Context,
        position: Int
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setCancelable(true)
            .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
            .setPositiveButton(R.string.ok) { dialog, _ ->
                deleteItem(position)
                dialog.dismiss()
            }
            .show()
    }

    private fun deleteItem(position: Int) {
        Log.d(TAG_DELETED, origData.remove(data[position]).toString())
        data.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount - position)
    }

    override fun getItemCount(): Int = data.size

    inner class ContactsRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.contact_item_name)
        val phone: TextView = view.findViewById(R.id.contact_item_phone)
        val avatar: ImageView = view.findViewById(R.id.contact_item_avatar)
    }

    fun setFilteredData(list: MutableList<Contact>) {
        data = list
        notifyDataSetChanged()
    }
}