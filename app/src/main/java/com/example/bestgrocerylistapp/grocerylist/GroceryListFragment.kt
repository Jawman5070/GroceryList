package com.example.bestgrocerylistapp.grocerylist

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
import com.example.bestgrocerylistapp.databinding.FragmentGroceryListBinding
import com.example.bestgrocerylistapp.grocerylist.GroceryListFragmentDirections

class GroceryListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentGroceryListBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_grocery_list, container, false
        )
        val application = requireNotNull(this.activity).application
        val dataSource = GroceryDatabase.getInstance(application).groceryDatabaseDao
        val viewModelFactory = GroceryListViewModelFactory(dataSource, application)
        val groceryListViewModel = ViewModelProvider(this, viewModelFactory).get(GroceryListViewModel::class.java)
        binding.groceryListViewModel = groceryListViewModel
        binding.lifecycleOwner = this

        val adapter = GroceryListAdapter(GroceryListListener { groceryListId ->
            groceryListViewModel.onGroceryItemClicked(groceryListId)
        })

        groceryListViewModel.groceryList.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })

        groceryListViewModel.navigateToUpdateGrocery.observe(viewLifecycleOwner, Observer { groceryList ->
            groceryList?.let {
                this.findNavController().navigate(
                    GroceryListFragmentDirections
                        .actionGroceryListFragmentToUpdategrocery(groceryList))
                groceryListViewModel.onUpdateGroceryNavigated()
            }
        })

        binding.addItemButton.setOnClickListener { view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_groceryListFragment_to_groceryStoreFragment)
        }

        binding.clearListButton.setOnClickListener{
            groceryListViewModel.onClickClearList()
        }

        binding.groceryList.adapter = adapter
        return binding.root
    }
}

