package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding

class UserAdapter(
    private val onClick: (User,List<User>) -> Unit,
//    private val itemOnClick: ItemOnClick
) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private val listUser: MutableList<User> = mutableListOf()

    class UserViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        /* val index = view.findViewById<ImageView>(R.id.index)
         val userName = view.findViewById<TextView>(R.id.userName)*/

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {

        val layoutInflater = LayoutInflater
            .from(parent.context)

        val binding = ItemUserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
//        holder.index.setImageResource(listUser[position].img)
        Glide.with(holder.itemView.context)
            .load(listUser[position].linkAvt)
            .into(holder.binding.index)
        holder.binding.userName.text = listUser[position].fullName
        holder.binding.root.setOnClickListener {
            onClick(listUser[position], listUser)
        }

    }

    fun setData(list: List<User>) {
        listUser.clear()
        listUser.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = listUser.size
}