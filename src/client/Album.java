package client;


import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import ser321.assign2.lindquis.MediaLibraryGui;

public class Album extends Object implements Serializable {
    public String albumName;
    public String artist;
//    public ArrayList<Track> tracks = new ArrayList<>();
    public ArrayList<Track> tracks;
    public String imageURL;
    public String runTime;
    public String summary;

    public Album() {
        this.albumName = "";
        this.artist = "";
        this.imageURL = "";
        this.runTime = "";
        this.summary = "";
        this.tracks = new ArrayList<>();
    }

    public Album(ArrayList<Track> trackArrayList) {
        this.tracks = trackArrayList;
    }

    public Album parseLastFMJson(String aJsonString) {
        Album album = new Album();

        album.tracks.addAll(Track.parseLastFMJson(aJsonString));

        JSONObject albumObj = (JSONObject) new JSONObject(aJsonString).get("album");
        album.albumName = albumObj.get("name").toString();

        if(albumObj.has("wiki")) {
            album.summary = albumObj.getJSONObject("wiki").getString("summary");
        } else {
            album.summary = "Wiki and summary not found for this album";
        }

        album.imageURL = albumObj.getJSONArray("image").getJSONObject(2).get("#text").toString();
        album.artist = albumObj.get("artist").toString();


        System.out.println("\n--------------------------------");
        System.out.println("Album information below: \n");

        System.out.println("Album name: " + albumObj.get("name"));
        System.out.println("Summary: " + album.summary);
        System.out.println("Image: " + album.imageURL);
        System.out.println("Artist: " + album.artist);

        for(int i = 0; i < tracks.size(); i++) {
            //TODO: Loop through AL and print out tracks with FROM ALBUM PARSE IN IT
        }

        for(int i = 0; i < tracks.size(); i++) {
            album.runTime += Integer.parseInt(tracks.get(i).duration);
        }

        return album;
    }


    //Can return null
    public Track getTrack(Track track, ArrayList<Track> trackArrayList) {
        if(!trackArrayList.contains(track)) {
            System.out.println("Track not found");
        }
        return track;
    }

    public void addTrack(Track track) {
        tracks.add(track);
    }

    public void removeTrack(Track track, ArrayList<Track> trackArrayList) {
        if(trackArrayList.contains(track)) {
            trackArrayList.remove(track);
        } else {
            System.err.println("Track not present in the library");
        }
    }

//    public void addAllTracks(ArrayList<Track> trackArrayList) {
//        Track tempTrack = new Track();
//        JSONObject jsonObject = new JSONObject(aJsonString);
//        for(int i = 0; i < trackArrayList.length(); i++);
//        tracks.addAll(trackArrayList);
//    }

    public void printSize() {
        System.out.println("Size of tracks arraylist in album: " + this.tracks.size());
    }

    public JSONObject toJson() {
        JSONObject obj = new JSONObject();
        try {
            for (int i = 0; i < tracks.size(); i++) {
                obj.put("title", this.tracks.get(i).trackName);
                obj.put("artist", this.tracks.get(i).artist);
                obj.put("album", this.albumName);
//                obj.put("fileName", this.);
            }
        } catch (Exception ex) {
            System.err.println("Exception in toJson: " + ex.getMessage());
        }
        return obj;
    }

    public String toJsonString() {
        String ret = "{}";
        try {
            ret = this.toJson().toString(0);
        } catch(Exception ex) {
            System.err.println("Exception in toJsonString: " + ex.getMessage());
        }
        return ret;
    }

    public void searchGUIUpdate(Album album) {

    }

    public void addEntireAlbum(Album album) {

    }

    public void createAlbumFromTrackAL(ArrayList<Track> trackArrayList) {
        this.tracks = trackArrayList;
    }
}