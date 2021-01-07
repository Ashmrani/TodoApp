package com.example.todoapp.todo.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.todo.model.Todo
import com.example.todoapp.R

class TodoAdapter(
    private val context: Context,
    private val todos: MutableList<Todo>,
) : RecyclerView.Adapter<TodoAdapter.ViewHolder>() {

    fun update(list: List<Todo>) {
        todos.clear()
        todos.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val todo = todos[position]
        viewHolder.title.text = todo.title
        viewHolder.body.text = todo.body
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.titleTextView)
        val body: TextView = view.findViewById(R.id.bodyTextView)
    }
}
