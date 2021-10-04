package com.example.shoppinglist.ui

import com.example.shoppinglist.data.db.entities.ShoppingItem

interface DialogListener {
    fun onAddButtonClicked(item:ShoppingItem)
}