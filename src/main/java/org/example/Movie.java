package org.example;

import java.util.Arrays;

class Movie {
    private int id;
    private String title;
    private int year;
    private double rating;
    private String genre;
    private String duration;
    private int directorId;
    private int[] actorIds;

    // Constructor
    public Movie(int id, String title, int year, double rating, String genre, String duration, int directorId, int[] actorIds) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
        this.duration = duration;
        this.directorId = directorId;
        this.actorIds = Arrays.copyOf(actorIds, actorIds.length); // Fix actorIds copying
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public int getYear() { return year; }
    public double getRating() { return rating; }
    public String getGenre() { return genre; }
    public String getDuration() { return duration; }
    public int getDirectorId() { return directorId; }
    public int[] getActorIds() { return actorIds; }

    public void setTitle(String title) { this.title = title; }
    public void setYear(int year) { this.year = year; }
    public void setRating(double rating) { this.rating = rating; }

    @Override
    public String toString() {
        return id + "," + title + "," + year + "," + rating + "," + genre + "," + duration + "," + directorId + "," + Arrays.toString(actorIds);
    }
}
