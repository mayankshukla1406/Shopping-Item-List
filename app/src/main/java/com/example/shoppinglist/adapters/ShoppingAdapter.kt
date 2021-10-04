package com.example.shoppinglist.adapters

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.data.db.entities.ShoppingItem
import com.example.shoppinglist.ui.ShoppingViewModel
import org.w3c.dom.Text

class ShoppingAdapter(
    private var itemList : List<ShoppingItem>,
    private val viewModel : ShoppingViewModel
) : RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ShoppingAdapter.ShoppingViewHolder {
        val shoppingView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_shoppinglist,parent,false)
        return ShoppingViewHolder(shoppingView)
    }

    override fun onBindViewHolder(holder: ShoppingAdapter.ShoppingViewHolder, position: Int) {
        val currentShoppingitem = itemList[position]
        holder.itemName.text = currentShoppingitem.name
        holder.itemAmount.text = currentShoppingitem.amount.toString()
        holder.addAmount.setOnClickListener{
            currentShoppingitem.amount++
            viewModel.upsert(currentShoppingitem)
        }
        holder.subtractAmount.setOnClickListener{
            if(currentShoppingitem.amount>0) {
                currentShoppingitem.amount--
                viewModel.upsert(currentShoppingitem)
            }
        }
        holder.deleteItem.setOnClickListener{
            viewModel.delete(currentShoppingitem)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    inner class ShoppingViewHolder(itemView : View):RecyclerView.ViewHolder(itemView)
    {
        val itemName        : TextView    = itemView.findViewById(R.id.txtItemName)
        val itemAmount      : TextView    = itemView.findViewById(R.id.txtItemAmount)
        val addAmount       : ImageButton = itemView.findViewById(R.id.imgBtAdd)
        val subtractAmount  : ImageButton = itemView.findViewById(R.id.imgBtSubtract)
        val deleteItem      : ImageButton = itemView.findViewById(R.id.imgbtDelete)
    }
}