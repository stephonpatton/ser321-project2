package client;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Track extends Object implements Serializable {
    public String trackName;
    public String artist;
    public int rankOrder;
    public String albumName;
    public String duration; //Format: mm:ss

    public static ArrayList<Track> trackArrayList;

    public Track() {
        this.setTrackName("");
        this.setArtist("");
        this.setRankOrder(0);
        this.setDuration("00:00:00");
    }

    public Track(String trackName, String artist, int rankOrder, String duration) {
        setTrackName(trackName);
        setArtist(artist);
        setRankOrder(rankOrder);
        setDuration(duration);
    }

    public static ArrayList<Track> parseLastFMJson(String aJsonString) {

        //TODO: Get information from jsonstring
        //TODO: Save the information into variables
        //TODO: Create a track with overloaded constructor
        String tempTrackName;
        String tempArtist;
        int tempRankOrder;
        String tempDuration;


        Track track = new Track();
//        ArrayList<Track> trackArrayList = new ArrayList<>();
        trackArrayList = new ArrayList<>();

        //see what jsonObject prints
        JSONObject jsonObject = new JSONObject(aJsonString);

        String tracks = jsonObject.getJSONObject("album").toString();
        JSONObject album = new JSONObject(tracks);
        JSONArray allTracks = album.getJSONObject("tracks").getJSONArray("track");
//        System.out.println("TRACKS ARE: " + allTracks.toString());


        for (int i = 0; i < allTracks.length(); i++) {
            Track tempTrack = new Track();
            //gets entire track obj

            tempTrack.trackName = allTracks.getJSONObject(i).getString("name");
            System.out.println("TRACK NAME: " + tempTrack.trackName);

            tempTrack.albumName = album.getString("name");


            tempTrack.artist = album.getString("artist");
            System.out.println("Artist: " + tempTrack.artist);

            tempTrack.rankOrder = allTracks.getJSONObject(i).getJSONObject("@attr").getInt("rank");
            System.out.println("Rank Order: " + tempTrack.rankOrder);

            tempTrack.duration = allTracks.getJSONObject(i).get("duration").toString();
            System.out.println("Duration of track " + tempTrack.duration);

            trackArrayList.add(tempTrack);
            System.out.println(tempTrack.trackName + " was added.");

            System.out.println("JSON DATA: " + tempTrack.toJson());

            System.out.println("Size of trackArrayList: " + trackArrayList.size());

        }



//        for(int i = 0; i < trackArrayList.size(); i++) {
//            System.out.println("Track name: " + trackArrayList.get(i).trackName);
//        }

        printTrackArrayList();

        return trackArrayList;
    }

    public static void printTrackArrayList() {
        for(int i = 0; i < trackArrayList.size(); i++) {
            System.out.println("Track #:" + i + " " + trackArrayList.get(i).trackName);
            System.out.println("Track #:" + i + " jsonString: " + trackArrayList.get(i).toJsonString());
        }
    }




    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("title", this.trackName);
            obj.put("author", this.artist);
            obj.put("album", this.albumName);
            obj.put("fileName", this.duration);
        } catch (Exception ex) {
            System.out.println("Exception in toJson: " + ex.getMessage());
        }
        return obj;
    }

    public String toJsonString() {
        String ret = "{}";
        try {
            ret = this.toJson().toString(0);
        } catch (Exception ex) {
            System.out.println("Exception in toJsonString: " + ex.getMessage());
        }
        return ret;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getArtist() {
        return this.artist;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return this.duration;
    }

    public void setRankOrder(int rankOrder) {
        this.rankOrder = rankOrder;
    }

    public int getRankOrder() {
        return this.rankOrder;
    }
}