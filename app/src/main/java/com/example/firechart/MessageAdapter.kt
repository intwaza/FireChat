package com.example.firechart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(var messageList: MutableList<String>): RecyclerView.Adapter<MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        var itemView= LayoutInflater.from(parent.context).inflate(R.layout.messages, parent, false)
        return MessageViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.tvMessage.text= messageList[position].toString()
        holder.tvSender.text= messageList[position].toString()
    }

    override fun getItemCount(): Int {
        return  messageList.size
    }
}

class MessageViewHolder(var itemView: View): RecyclerView.ViewHolder(itemView){
    var tvSender= itemView.findViewById<TextView>(R.id.tvSender)
    var tvMessage= itemView.findViewById<TextView>(R.id.tvMessage)
}
