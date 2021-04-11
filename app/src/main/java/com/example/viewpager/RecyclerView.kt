package com.example.viewpager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contact_layout.view.*


class RecyclerAdapter(db : ArrayList<contact>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>(){
    /**
     * Declaration of data and instance of onNodeClick interface
     */
    var data = db
    var listner  : OnNodeClick? = null

    /**
     * Declaration of the ViewHolderClass  having 3 declared view(A textView and 2 ImageView)
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textview2 = itemView.textViewId
        var delete  = itemView.delete_image
        var edit   = itemView.edit_image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       var view = LayoutInflater.from(parent.context).inflate(R.layout.contact_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    /**
     * onBindViewHolder passing variables or functions to the views created on the viewHolder class
     *name parameter assigned to the textview
     * onClick function is assigned to the textview and imageView to run onclickIcon function from the Listen interface with the parameters {view and node position}
     */

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textview2.text = data[position].fullname

        holder.delete.setOnClickListener {
            listner?.onclickIcon(it,position)
        }

        holder.edit.setOnClickListener {
            listner?.onclickIcon(it,position)
        }

        holder.textview2.setOnClickListener {
            listner?.onclickIcon(it,position)
        }

    }



}