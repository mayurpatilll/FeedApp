package com.example.feedapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var companyDetailsList : ArrayList<CompanyDetails>
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseReference = Firebase.database.reference

        recyclerView = findViewById(R.id.myList_recyclerView)

        companyDetailsList = ArrayList<CompanyDetails>()


        val companyDetails1 = CompanyDetails("https://iconarchive.com/download/i83818/designbolts/cute-social-2014/Amazon.ico","SDE1 - Android","Have decent knowledge in android architecture","https://stackoverflow.com/questions/2734270/how-to-make-links-in-a-textview-clickable")
        val companyDetails2 = CompanyDetails("https://pbs.twimg.com/profile_images/1455185376876826625/s1AjSxph_400x400.jpg","Google - Android Dev 2","Have worked on at least 4 androidn apps","https://boards.greenhouse.io/alterahealth/jobs/5156222003")
        val companyDetails3 = CompanyDetails("https://avatars.githubusercontent.com/u/6154722?s=280&v=4","Microsoft Senior Producr Manager","Must have experience in startup","https://boards.greenhouse.io/alterahealth/jobs/5156222003")
        val companyDetails4 = CompanyDetails("https://avatars.githubusercontent.com/u/10639145?s=280&v=4","Apple - QA Tester","BA Pass","https://boards.greenhouse.io/alterahealth/jobs/5156222003")

//        databaseReference.child("companies").push().setValue(companyDetails1)
//        databaseReference.child("companies").push().setValue(companyDetails2)
//        databaseReference.child("companies").push().setValue(companyDetails3)
//        databaseReference.child("companies").push().setValue(companyDetails4)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.stackFromEnd = true
        linearLayoutManager.reverseLayout = true


        recyclerView.layoutManager = linearLayoutManager


        val userAdapter = UserAdapter(companyDetailsList)
        recyclerView.adapter = userAdapter

        databaseReference.child("companies").addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for(dataSnapshot in snapshot.children)
                {
                    companyDetailsList.add(CompanyDetails(dataSnapshot.child("imageSrc").value.toString(),
                        dataSnapshot.child("title").value.toString(),
                        dataSnapshot.child("description").value.toString(),
                        dataSnapshot.child("applyLink").value.toString()))
                }

                userAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

    }
}