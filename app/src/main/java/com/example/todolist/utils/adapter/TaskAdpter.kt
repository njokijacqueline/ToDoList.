package com.example.todolist.utils.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.databinding.EachTodoItemBinding
import com.example.todolist.utils.Model.ToDoData
import java.util.EventListener

class TaskAdapter (private val list: MutableList<ToDoData>) :RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){
    private val TAG = "TaskAdapter"
    private var listener:TaskAdapterInterface? = null

    fun setListener(listener: TaskAdapterInterface){
        this.listener = listener

    }
    class TaskViewHolder(val binding: EachTodoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder{
        val binding =
            EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        with(holder){
            with(list[position]) {
                binding.todoTask.text = this.task

                Log.d(TAG, "onBindViewHolder: "+this)
                binding.editTask.setOnClickListener{
                    listener?.onEditItemClicked(this, position)
                }
                binding.deleteTask.setOnClickListener{
                    listener?.onDeleteItemClicked(this, position)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
    interface TaskAdapterInterface{
        fun onDeleteItemClicked(toDoData: ToDoData, position :Int)
        fun onEditItemClicked(toDoData: ToDoData, position :Int)
    }
}