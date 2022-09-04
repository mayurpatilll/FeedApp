package com.example.feedapp

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class UserAdapter(private val companyList : ArrayList<CompanyDetails>) : RecyclerView.Adapter<viewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return viewHolder(view)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.titleTextView.text = companyList[position].title
        holder.descriptionTextView.text = companyList[position].description
        Picasso.get().load(companyList[position].imageSrc).resize(90,90).placeholder(R.mipmap.ic_launcher).into(holder.userImage)
        holder.itemView.setOnClickListener {
            val uri: Uri = Uri.parse(companyList[position].applyLink)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return companyList.size
    }
}

class viewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val userImage = itemView.findViewById<ImageView>(R.id.user_image)
    val titleTextView = itemView.findViewById<TextView>(R.id.title_textView)
    val descriptionTextView = itemView.findViewById<TextView>(R.id.description_textView)
}