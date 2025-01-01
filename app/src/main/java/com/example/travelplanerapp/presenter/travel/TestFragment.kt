package com.example.travelplanerapp.presenter.travel

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.databinding.FragmentTestBinding

class TestFragment : Fragment(R.layout.fragment_test) {

    private val binding: FragmentTestBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = TestFragmentArgs.fromBundle(requireArguments()).userData
        binding.userData.text = userData
    }
}
