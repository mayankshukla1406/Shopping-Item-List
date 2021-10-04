package com.example.shoppinglist.ui

import android.content.Context
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem

class ShoppingItemDialogBox(context: Context,var addDialogBoxListener:DialogListener): AppCompatDialog(context) {
    private lateinit var addItem : TextView
    private lateinit var cancelItem:TextView
    private lateinit var addItemText : TextView
    private lateinit var nameItem : EditText
    private lateinit var amountItem:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_box_add_shopping_item)
        addItem = findViewById(R.id.txtAddItem)!!
        cancelItem = findViewById(R.id.txtCancel)!!
        addItemText=findViewById(R.id.txtAddShoppingItems)!!
        nameItem = findViewById(R.id.etAddItem)!!
        amountItem = findViewById(R.id.etItemAmount)!!
        addItem.setOnClickListener{
            val name = nameItem.text.toString()
            val amount = amountItem.text.toString()
            if(name.isEmpty() || amount.isEmpty())
            {
                Toast.makeText(context,"Please Enter all the information",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else {
                val item = ShoppingItem(name, amount.toInt())
                addDialogBoxListener.onAddButtonClicked(item)
                dismiss()
            }
        }
        cancelItem.setOnClickListener{
            cancel()
        }
    }
}