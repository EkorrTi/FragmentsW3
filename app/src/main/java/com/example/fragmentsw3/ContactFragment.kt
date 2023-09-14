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
    private lateinit var contact: Contact

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

        val name: EditText = view.findViewById(R.id.contact_name)
        val surname: EditText = view.findViewById(R.id.contact_surname)
        val phone: EditText = view.findViewById(R.id.contact_phone)
        val editButton: Button = view.findViewById(R.id.edit_contact_button)
        val saveButton: Button = view.findViewById(R.id.save_contact_button)

        name.setText(contact.name)
        surname.setText(contact.surname)
        phone.setText(contact.phone)
        editButton.setOnClickListener {
            makeEditable(name, surname, phone)
            saveButton.isVisible = true
            editButton.isVisible = false
        }
        saveButton.setOnClickListener {
            saveChanges(name.text.toString(), surname.text.toString(), phone.text.toString())
        }
    }

    private fun makeEditable(nameET: EditText, surnameET: EditText, phoneET: EditText) {
        nameET.isFocusable = true
        nameET.isFocusableInTouchMode = true
        nameET.inputType = InputType.TYPE_CLASS_TEXT
        surnameET.isFocusable = true
        surnameET.isFocusableInTouchMode = true
        surnameET.inputType = InputType.TYPE_CLASS_TEXT
        phoneET.isFocusable = true
        phoneET.isFocusableInTouchMode = true
        phoneET.inputType = InputType.TYPE_CLASS_PHONE
    }

    private fun saveChanges(name: String, surname: String, phone: String) {
        contact.name = name
        contact.surname = surname
        contact.phone = phone
        findNavController().navigateUp()
    }
}