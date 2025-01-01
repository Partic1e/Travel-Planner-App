package com.example.travelplanerapp.presenter.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.databinding.FragmentRegisterBinding
import com.example.travelplanerapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class RegisterFragment: Fragment(R.layout.fragment_register) {

    private val binding: FragmentRegisterBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val registerViewModel: RegisterViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registerButton.setOnClickListener {
            registerViewModel.registerUser(
                binding.firstnameEditText.text.toString(),
                binding.lastnameEditText.text.toString(),
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString()
            )
            findNavController().popBackStack()
        }
    }
}
