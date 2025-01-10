package com.example.travelplanerapp.presenter.travel

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.travelplanerapp.R
import com.example.travelplanerapp.appComponent
import com.example.travelplanerapp.databinding.FragmentRootTravelBinding
import com.example.travelplanerapp.presenter.travel.create.CreateFragment
import com.example.travelplanerapp.presenter.travel.routes.RoutesFragment

class RootTravelFragment : Fragment(R.layout.fragment_root_travel) {

    private val binding: FragmentRootTravelBinding by viewBinding()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = RootTravelFragmentArgs.fromBundle(requireArguments()).userData
        binding.userData.text = userData

        val navController = childFragmentManager.findFragmentById(R.id.travel_container)?.findNavController()
            ?: throw IllegalStateException("NavController for travel_container not found")

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.create -> {
                    navController.navigate(R.id.createFragment)
                    true
                }
                R.id.list -> {
                    navController.navigate(R.id.routesFragment)
                    true
                }
                else -> false
            }
        }

//        childFragmentManager.beginTransaction()
//            .replace(R.id.travel_container, CreateFragment())
//            .commit()
//
//        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            when (item.itemId) {
//                R.id.create -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.travel_container, CreateFragment())
//                        .commit()
//                    true
//                }
//                R.id.list -> {
//                    childFragmentManager.beginTransaction()
//                        .replace(R.id.travel_container, RoutesFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
    }
}
