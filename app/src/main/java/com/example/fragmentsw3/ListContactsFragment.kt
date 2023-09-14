package com.example.fragmentsw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListContactsFragment : Fragment() {
    private val contacts: MutableList<Contact> = mutableListOf()
    private lateinit var adapter: ContactsRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        for (i in 1..20) {
            contacts.addAll(
                listOf(
                    Contact("Johnny", "Cage", "89993335115"),
                    Contact("Meg", "Thomas", "89993335555"),
                    Contact("Dwight", "Fairfield", "89993335556"),
                    Contact("William", "Overbeck", "899933355557"),
                    Contact("Bill", "Murray", "89993335558"),
                )
            )
        }
        return inflater.inflate(R.layout.fragment_list_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = ContactsRecyclerViewAdapter(contacts)
        adapter.onClick = {
            findNavController().navigate(
                R.id.action_listContactsFragment_to_contactFragment, bundleOf(Pair("contact", it))
            )
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.contacts_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@ListContactsFragment.adapter
        }

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}