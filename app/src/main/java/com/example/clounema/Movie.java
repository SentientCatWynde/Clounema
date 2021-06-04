package com.example.clounema;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Movie {
    private String title, cost, director, poster, sino, trailer, year;
    private DatabaseReference dbRef;
    private FirebaseDatabase rootNode;

    public Movie(){
        rootNode = FirebaseDatabase.getInstance();
        dbRef = rootNode.getReference("Movie");
        // dbRef.setValue("Fucking Database");
    }

    public Movie(String title, String cost, String director, String poster, String sino, String trailer, String year) {
        this.title = title;
        this.cost = cost;
        this.director = director;
        this.poster = poster;
        this.sino = sino;
        this.trailer = trailer;
        this.year = year;
    }

    public void writeNewMovie(String title, String cost, String director, String poster, String sino, String trailer, String year){
        Movie mv = new Movie(title, cost, director, poster, sino, trailer, year);
        
    }
    // Kalau bikin setter pake dibawah ini.
    //mDatabase.child("users").child(userId).child("username").setValue(name);

    public String getTitle() {
        return title;
    }

    public String getCost() {
        return cost;
    }

    public String getDirector() {
        return director;
    }

    public String getPoster() {
        return poster;
    }

    public String getSino() {
        return sino;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getYear() {
        return year;
    }
}
