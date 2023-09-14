package com.example.fragmentsw3

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class ContactFragment : Fragment() {
    private lateinit var name: EditText
    private lateinit var surname: EditText
    private lateinit var phone: EditText
    private lateinit var editButton: Button
    private lateinit var saveButton: Button
    var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { contact = it.getSerializable("contact") as Contact }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        name = view.findViewById(R.id.contact_name)
        surname = view.findViewById(R.id.contact_surname)
        phone = view.findViewById(R.id.contact_phone)
        editButton = view.findViewById(R.id.edit_contact_button)
        saveButton = view.findViewById(R.id.save_contact_button)

        if (contact != null) displayInfo()
        else view.isVisible = false

        editButton.setOnClickListener { makeEditable() }
        saveButton.setOnClickListener { saveChanges() }
    }

    fun displayInfo() {
        name.setText(contact?.name)
        surname.setText(contact?.surname)
        phone.setText(contact?.phone)
        view?.isVisible = true
    }

    private fun makeEditable() {
        name.isFocusable = true
        name.isFocusableInTouchMode = true
        name.inputType = InputType.TYPE_CLASS_TEXT
        surname.isFocusable = true
        surname.isFocusableInTouchMode = true
        surname.inputType = InputType.TYPE_CLASS_TEXT
        phone.isFocusable = true
        phone.isFocusableInTouchMode = true
        phone.inputType = InputType.TYPE_CLASS_PHONE
        saveButton.isVisible = true
        editButton.isVisible = false
    }

    private fun makeUneditable() {
        name.isFocusable = false
        name.isFocusableInTouchMode = false
        name.inputType = InputType.TYPE_NULL
        surname.isFocusable = false
        surname.isFocusableInTouchMode = false
        surname.inputType = InputType.TYPE_NULL
        phone.isFocusable = false
        phone.isFocusableInTouchMode = false
        phone.inputType = InputType.TYPE_NULL
        editButton.isVisible = true
        saveButton.isVisible = false
    }

    private fun saveChanges() {
        contact?.name = name.text.toString()
        contact?.surname = surname.text.toString()
        contact?.phone = phone.text.toString()
        if (resources.getBoolean(R.bool.isTablet)) {
            (parentFragmentManager.findFragmentById(R.id.fragment_container_1) as ListContactsFragment)
                .notifyAdapter()
            makeUneditable()
        }
        else findNavController().navigateUp()
    }
}