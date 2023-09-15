package com.example.fragmentsw3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
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
                    Contact("Johnny", "Cage", "8999333511$i", "https://picsum.photos/128"),
                    Contact("Meg", "Thomas", "8999333555$i", "https://picsum.photos/64"),
                    Contact("Dwight", "Fairfield", "8999333556$i", "https://picsum.photos/32"),
                    Contact("William", "Overbeck", "8999333557$i", "https://picsum.photos/16"),
                    Contact("Bill", "Murray", "8999333558$i", "https://picsum.photos/8"),
                )
            )
        }
        return inflater.inflate(R.layout.fragment_list_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val isTablet = resources.getBoolean(R.bool.isTablet)
        adapter = ContactsRecyclerViewAdapter(contacts)
        if (isTablet) {
            adapter.onClick = {
                with(parentFragmentManager.findFragmentById(R.id.fragment_container_2) as ContactFragment) {
                    contact = it
                    displayInfo()
                }
            }
        } else {
            adapter.onClick = {
                findNavController().navigate(
                    R.id.action_listContactsFragment_to_contactFragment,
                    bundleOf(Pair("contact", it))
                )
            }
        }

        val searchView: SearchView = view.findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean = false

            override fun onQueryTextChange(p0: String?): Boolean {
                filterData(p0)
                return true
            }
        })

        val recyclerView: RecyclerView = view.findViewById(R.id.contacts_recycler_view)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = this@ListContactsFragment.adapter
        }

        super.onViewCreated(view, savedInstanceState)
    }

    private fun filterData(query: String?) {
        if (query != null) {
            val filteredData = mutableListOf<Contact>()
            for (c in contacts) {
                if (c.fullName.lowercase().contains(query))
                    filteredData.add(c)
            }

            adapter.setFilteredData(filteredData)
        }
    }

    fun notifyAdapter() = adapter.notifyDataSetChanged()
}