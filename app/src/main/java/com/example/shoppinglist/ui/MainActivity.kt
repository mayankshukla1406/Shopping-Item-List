package com.example.shoppinglist.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.adapters.ShoppingAdapter
import com.example.shoppinglist.data.db.ShoppingDatabase
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.data.repositories.ShoppingRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var openDialog : FloatingActionButton
    private lateinit var adapter : ShoppingAdapter
    private var listitems = listOf<ShoppingItem>()
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        openDialog = findViewById(R.id.btOpenDialogBox)
        val database = ShoppingDatabase(this)
        val repository = ShoppingRepository(database)
        val factory = ShoppingViewModelFactory(repository)
        val shoppingviewmodel = ViewModelProvider(this,factory).get(ShoppingViewModel::class.java)
        recyclerView = findViewById(R.id.recyclerViewItems)
        recyclerView.layoutManager = LinearLayoutManager(this)
        shoppingviewmodel.getAllShoppingItems().observe(this, {
            listitems = it
            adapter = ShoppingAdapter(listitems,shoppingviewmodel)
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        })
        openDialog.setOnClickListener{
            ShoppingItemDialogBox(this,object : DialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    shoppingviewmodel.upsert(item)
                }
            }).show()
        }
    }
}