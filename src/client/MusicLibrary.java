package client;

import org.json.JSONObject;
import org.json.JSONTokener;
import ser321.assign2.lindquis.MediaDescription;
import ser321.assign2.lindquis.MediaLibrary;

import java.io.*;
import java.util.*;

public class MusicLibrary implements Serializable {
    public ArrayList<Album> albumArrayList;
    private static final String fileName = "musics.json";

    private Hashtable<String, MediaDescription> aLibrary;

    public MusicLibrary(ArrayList<Album> albums) {
        albumArrayList = new ArrayList<>();
        albumArrayList.addAll(albums);
    }

    public MusicLibrary() {
        albumArrayList = new ArrayList<>();


        //TODO: Maybe delete
        this.aLibrary = new Hashtable<String, MediaDescription>();
        try{
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.fileName);
            if(is==null){
                is = new FileInputStream(new File(this.fileName));
            }
            JSONObject media = new JSONObject(new JSONTokener(is));
            Iterator<String> it = media.keys();
            while (it.hasNext()){
                String mediaTitle = it.next();
                JSONObject aMed = media.optJSONObject(mediaTitle);
                if (aMed != null){
                    MediaDescription md = new MediaDescription(aMed);
                    aLibrary.put(mediaTitle, md);
                }
            }
        }catch (Exception ex){
            System.out.println("Exception reading "+fileName+": "+ex.getMessage());
        }
    }

    public Album getAlbum(String albumName) {
        Album album = new Album();
        for(int i = 0; i < albumArrayList.size(); i++) {
            if(albumName.equals(albumArrayList.get(i).albumName)) {
                album = albumArrayList.get(i);
            } else {
                System.err.println("Album not found.");
                album = null;
            }
        }

        return album;
    }

    public void addAlbum(Album album) {
        albumArrayList.add(album);
        System.out.println("Album: " + album.albumName + " was added to library");


        for(int i = 0; i < albumArrayList.size(); i++) {
            System.out.println("Albums in list: " + albumArrayList.get(i).albumName);
        }
    }

    public void removeAlbum(String albumName) {
        for(int i = 0; i < albumArrayList.size(); i++) {
            if(albumName.equals(albumArrayList.get(i).albumName)) {
                albumArrayList.remove(albumArrayList.get(i));
            } else {
                System.err.println("Album not found");
            }
        }
    }

    public ArrayList<Album> getAllAlbums() {
        return albumArrayList;
    }

    public ArrayList<String> getAlbumNames() {
        ArrayList<String> albumNames = new ArrayList<>();
        for(int i = 0; i < albumArrayList.size(); i++) {
            albumNames.add(albumArrayList.get(i).albumName);
        }


//        for (String albumName : albumNames) {
//            System.out.print("Albums in the library: " + albumName);
//        }

        albumNames.forEach(names -> {
            System.out.print("getAlbumNames(): "+ names + ", ");
        });

        if(albumNames == null) {
            System.out.println("Album Names MUSICLIBRARY IS EMPTY");
        }

        return albumNames;
    }

//    public ArrayList<String> getTrackNames() {
//        ArrayList<String> trackNames = new ArrayList<>();
//        for(int i = 0; i < )
//    }


//    public void saveLibraryToFile(String file, MusicLibrary libraryToSave) throws IOException {
//        PrintWriter out = new PrintWriter("admin.json");
//        out.println(libraryToSave);
//
//        File outFile = new File(file);
//        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFile));
//        os.writeObject();
//        os.flush();
//        System.out.println("Used Java serialization of the group to music.ser");
//        os.close();
//
//    }

    public void saveLibraryToFile(String file, ArrayList<Album> albums) throws IOException {
//        albumArrayList.addAll(albums);
//        PrintWriter out = new PrintWriter(file);
//        out.println(albums.get(0).tracks.get(2).toJsonString());
//
//        File outFile = new File(file);
//        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(outFile));
//        os.writeObject(albums.get(0).tracks.get(2).toJsonString());
//        os.flush();
//        System.out.println("Used Java serialization of the group to music.ser");
//        os.close();


        for(int i=0; i < albums.size();) {
            for(int j = 0; j < albums.get(i).tracks.size(); j++) {
                System.out.println("SIZE OF ALBUM TRACKS IN ML: " + albumArrayList.get(i).tracks.size());
                System.out.println("Track as json string: " + albumArrayList.get(i).tracks.get(j).toJsonString());
            }
            i++;
        }

    }


    public String[] getAllTitles() {
        String[] result = null;

        try {
            Set<String> vec = aLibrary.keySet();
            result = vec.toArray(new String[]{});
            System.out.println("ALL TITLES: " + Arrays.toString(result));
        } catch(Exception ex) {
            System.out.println("EXCEPTION in GETALLTITLES " + ex.getMessage());
        }
        return result;
    }



}