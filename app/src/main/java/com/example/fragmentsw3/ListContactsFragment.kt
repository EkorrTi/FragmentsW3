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
    val contacts = listOf(
        Contact("Johnny", "Cage", "89993335115"),
        Contact("Meg", "Thomas", "89993335115"),
        Contact("Dwight", "Fairfield", "89993335115"),
        Contact("Bill", "Murray", "89993335115"),
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ContactsRecyclerViewAdapter(contacts)
        val recyclerView = view.findViewById<RecyclerView>(R.id.contacts_recycler_view)

        adapter.onClick = {
            findNavController().navigate(
                R.id.action_listContactsFragment_to_contactFragment, bundleOf( Pair("contact", it) )
            )
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter
        }
    }
}