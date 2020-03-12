package com.example.bestgrocerylistapp.addgrocery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.core.content.contentValuesOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.bestgrocerylistapp.R
import com.example.bestgrocerylistapp.database.GroceryDatabase
import com.example.bestgrocerylistapp.databinding.FragmentAddGroceryBinding
import kotlinx.android.synthetic.main.fragment_add_grocery.*
import kotlinx.android.synthetic.main.fragment_grocery_list.*
import java.lang.Integer.parseInt

class AddGroceryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentAddGroceryBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_add_grocery, container, false
        )

        val application = requireNotNull(this.activity).application
        val args = AddGroceryFragmentArgs.fromBundle(arguments!!)

        // Create instance of ViewModel Factory
        val dataSource = GroceryDatabase.getInstance(application).groceryDatabaseDao
        val viewModelFactory = AddGroceryViewModelFactory(args.groceryId, dataSource, application)

        // Get a reference to the ViewModel associated with this fragment
        val addGroceryViewModel = ViewModelProvider(this, viewModelFactory).get(AddGroceryViewModel::class.java)
        binding.addGroceryViewModel = addGroceryViewModel

        //val name = binding.nameTextView.text.toString()

        binding.cancel.setOnClickListener{view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_addGroceryFragment_to_groceryStoreFragment)
        }

        binding.addItem.setOnClickListener{view: View ->
            Navigation.findNavController(view)
                .navigate(R.id.action_addGroceryFragment_to_groceryListFragment)
            addGroceryViewModel.onClickAddItem(addGroceryViewModel.groceryObject, editQuantity.text.toString())
        }

        binding.lifecycleOwner = this
        return binding.root
    }
}
