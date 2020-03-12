package com.example.bestgrocerylistapp.grocerystore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.bestgrocerylistapp.R
import com.example.bestgrocerylistapp.database.GroceryDatabase
import com.example.bestgrocerylistapp.databinding.FragmentGroceryStoreBinding

class GroceryStoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGroceryStoreBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_grocery_store, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GroceryDatabase.getInstance(application).groceryDatabaseDao
        val viewModelFactory = GroceryStoreViewModelFactory(dataSource, application)
        val groceryStoreViewModel = ViewModelProvider(this, viewModelFactory).get(GroceryStoreViewModel::class.java)
        binding.groceryStoreViewModel = groceryStoreViewModel
        binding.lifecycleOwner = this


        val adapter = GroceryStoreAdapter(GroceryItemListener { groceryId ->
            groceryStoreViewModel.onGroceryItemClicked(groceryId)
        })

        groceryStoreViewModel.navigateToAddGrocery.observe(viewLifecycleOwner, Observer { grocery  ->
            grocery?.let {
                this.findNavController().navigate(
                    GroceryStoreFragmentDirections
                        .actionGroceryStoreFragmentToAddGroceryFragment(grocery))
                groceryStoreViewModel.onUpdateGroceryNavigated()
            }
        })

        groceryStoreViewModel.groceryItems.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        binding.cancelStoreButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_groceryStoreFragment_to_groceryListFragment)
        }

        binding.groceryStore.adapter = adapter
        return binding.root
    }
}
