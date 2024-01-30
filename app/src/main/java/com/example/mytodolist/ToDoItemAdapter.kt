package com.example.mytodolist

import android.content.Context
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView


class ToDoItemAdapter(private val mList: MutableList<ToDoItemModel>, private val context: Context) :
    RecyclerView.Adapter<ToDoItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.to_do_recyclerview_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ToDoItemAdapter.ViewHolder, position: Int) {

        val list = mList[position]
        holder.taskText.text = list.name

        holder.deleteIcon.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Do you want to delete ?")
            builder.setTitle("Alert !")
            builder.setCancelable(false)
            builder.setPositiveButton(
                "Yes"
            ) { dialog: DialogInterface?, which: Int ->
                mList.removeAt(position)
                notifyDataSetChanged()
            }
            builder.setNegativeButton(
                "No"
            ) { dialog: DialogInterface, which: Int ->
                dialog.cancel()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

        holder.tskCheck.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setMessage("Are you sure, you completed the task")
            builder.setTitle("Alert !")
            builder.setCancelable(false)
            builder.setPositiveButton(
                "Yes"
            ) { dialog: DialogInterface?, which: Int ->
                holder.tskCheck.isChecked = true
                holder.tskCheck.isEnabled = false
                holder.deleteIcon.isEnabled = false
                notifyDataSetChanged()
            }
            builder.setNegativeButton(
                "No"
            ) { dialog: DialogInterface, which: Int ->
                holder.tskCheck.isChecked = false
                dialog.cancel()
            }
            val alertDialog = builder.create()
            alertDialog.show()
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)
        val tskCheck: CheckBox = itemView.findViewById(R.id.taskCheckBox)
        val taskText: TextView = itemView.findViewById(R.id.taskName)
    }
}