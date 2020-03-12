package com.example.bestgrocerylistapp.updategrocery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.bestgrocerylistapp.R
import com.example.bestgrocerylistapp.database.GroceryDatabase
import com.example.bestgrocerylistapp.databinding.FragmentUpdateGroceryBinding

class UpdateGroceryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentUpdateGroceryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_update_grocery, container, false
        )

        val application = requireNotNull(this.activity).application
        val args: UpdateGroceryFragmentArgs = UpdateGroceryFragmentArgs.fromBundle(arguments!!)
        val dataSource = GroceryDatabase.getInstance(application).groceryDatabaseDao
        val viewModelFactory = UpdateGroceryViewModelFactory(args.groceryListId, dataSource, application)
        val updateGroceryViewModel = ViewModelProvider(this, viewModelFactory).get(UpdateGroceryViewModel::class.java)

        binding.updateGroceryViewModel = updateGroceryViewModel

        binding.lifecycleOwner = this
        return binding.root
    }

}
