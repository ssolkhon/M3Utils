/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import m3utils.M3UTrack;
import m3utils.M3UPlaylist;
import m3utils.M3UFinder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Scott Solkhon
 */
public class M3UFinderTest {

    /**
     * Test of getNumberOfPlaylists method, of class M3UFinder.
     */
    @Test
    public void testGetNumberOfPlaylists() {
        System.out.println("getNumberOfPlaylists::");
        Path p;
        int expResult, result;

        p = Paths.get("test-data" + File.separator + "testGetNumberOfPlaylists" + File.separator + "containsFourPlaylists");
        expResult = 4;
        result = M3UFinder.getNumberOfPlaylists(p);
        assertEquals(expResult, result);

        p = Paths.get("test-data" + File.separator + "testGetNumberOfPlaylists" + File.separator + "containsTwoPlaylists");
        expResult = 2;
        result = M3UFinder.getNumberOfPlaylists(p);
        assertEquals(expResult, result);

        p = Paths.get("test-data" + File.separator + "testGetNumberOfPlaylists" + File.separator + "containsZeroPlaylists");
        expResult = 0;
        result = M3UFinder.getNumberOfPlaylists(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlaylistFilenames method, of class M3UFinder.
     */
    @Test
    public void testGetPlaylistFilenames() {
        System.out.println("getPlaylistFilenames::");
        Path p;
        List<String> expResult, result;

        p = Paths.get("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsFourPlaylists");
        expResult = new ArrayList<>();
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsFourPlaylists" + File.separator + "child_folder1" + File.separator + "five_tracks.m3u");
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsFourPlaylists" + File.separator + "child_folder2" + File.separator + "four_tracks.m3u");
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsFourPlaylists" + File.separator + "four_tracks.m3u");
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsFourPlaylists" + File.separator + "three_tracks.m3u");
        result = M3UFinder.getPlaylistFilenames(p);
        assertEquals(expResult, result);

        p = Paths.get("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsTwoPlaylists");
        expResult = new ArrayList<>();
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsTwoPlaylists" + File.separator + "four_tracks.m3u");
        expResult.add("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsTwoPlaylists" + File.separator + "three_tracks.m3u");
        result = M3UFinder.getPlaylistFilenames(p);
        assertEquals(expResult, result);

        p = Paths.get("test-data" + File.separator + "testGetPlaylistFilenames" + File.separator + "containsZeroPlaylists");
        expResult = new ArrayList<>();
        result = M3UFinder.getPlaylistFilenames(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of makePlayList method, of class M3UFinder.
     */
    @Test
    public void testMakePlayList() {
        System.out.println("makePlayList");
        M3UPlaylist expResult, result;
        Path p;
        
        expResult = new M3UPlaylist();
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));
        
        p = Paths.get("test-data" + File.separator + "myMusicCollection" + File.separator + "collection-A");
        result = M3UFinder.makePlayList(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of isMusicFile method, of class M3UFinder.
     */
    @Test
    public void testIsMusicFile() {
        System.out.println("isMusicFile");
        String thisFilename;
        boolean expResult, result;
        
        thisFilename = "test_true.mp3";
        expResult = true;
        result = M3UFinder.isMusicFile(thisFilename);
        assertEquals(expResult, result);
        
        thisFilename = "test_true.mp4";
        expResult = false;
        result = M3UFinder.isMusicFile(thisFilename);
        assertEquals(expResult, result);
    }
}
