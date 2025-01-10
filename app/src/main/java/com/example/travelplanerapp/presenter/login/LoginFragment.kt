package com.example.travelplanerapp.presenter.login

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.databinding.FragmentLoginBinding
import com.example.travelplanerapp.di.viewModel.ViewModelFactory
import javax.inject.Inject

class LoginFragment: Fragment(R.layout.fragment_login) {

    private val binding: FragmentLoginBinding by viewBinding()

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val loginViewModel: LoginViewModel by viewModels { viewModelFactory }

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            loginViewModel.loginUser(
                login = binding.loginEditText.text.toString(),
                password = binding.passwordEditText.text.toString()
            )

            loginViewModel.userEntityLiveData.observe(viewLifecycleOwner) { user ->
                if (user != null) {
                    val userData = "${user.firstName} ${user.lastName}"
                    val action = LoginFragmentDirections.actionLoginFragmentToRootTravelFragment(userData)
                    findNavController().navigate(action)
                    loginViewModel.userEntityLiveData.removeObservers(viewLifecycleOwner)
                }
            }
        }

        binding.registerText.setOnClickListener {
            val destination = LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            findNavController().navigate(destination)
        }
    }
}
