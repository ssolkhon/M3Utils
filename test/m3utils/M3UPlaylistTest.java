/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import m3utils.M3UTrack;
import m3utils.M3UFinder;
import m3utils.M3UPlaylist;
import java.io.File;
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
public class M3UPlaylistTest {

    /**
     * Test of remove method, of class M3UPlaylist.
     */
    @Test
    public void testRemove_int() {
        System.out.println("Remove_int");
        int thisIndex = 3;
        M3UPlaylist expResult, result;
       
        expResult = new M3UPlaylist();
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));
        
        result = new M3UPlaylist();
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));

        result.remove(thisIndex);
        assertEquals(result, expResult);
    }

    /**
     * Test of removeAllFrom method, of class M3UPlaylist.
     */
    @Test
    public void testRemoveAllFrom() {
        System.out.println("removeAllFrom");
        int thisIndex = 6;
        M3UPlaylist expResult, result;
       
        expResult = new M3UPlaylist();
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));
        
        result = new M3UPlaylist();
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));

        result.removeAllFrom(thisIndex);
        assertEquals(result, expResult);
    }

    /**
     * Test of removeAllUpTo method, of class M3UPlaylist.
     */
    @Test
    public void testRemoveAllUpTo() {
        System.out.println("removeAllUpTo");
        int thisIndex = 2;
        M3UPlaylist expResult, result;
       
        expResult = new M3UPlaylist();
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        //expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        expResult.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));
        
        result = new M3UPlaylist();
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/After Many Days/Cannibal Eyes.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Anshlavs - Second Trip.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/DARKPOP BAND ANGELIQUE - PERFECT WORLD (AMBIENT).mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Freak Fandango Orchestra/Freak Fandango Orchestra - No means no.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Gem Reflection - Tubeman.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/IX - la chichonera.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Omnibrain - Neverending.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/als nous amos.mp3"));
        result.addTrack(new M3UTrack().setTrackFilename("test-data/myMusicCollection/collection-A/Orxata Sound System/fuster meets guevara.mp3"));

        result.removeAllUpTo(thisIndex);
        assertEquals(result, expResult);
    }

    /**
     * Test of remove method, of class M3UPlaylist.
     */
    @Test
    public void testRemove_String() {
        System.out.println("remove");
        String thisString = "Skyscraper";

        // Build Expected
        M3UPlaylist expResult = new M3UPlaylist();
        expResult.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:455,Underworld - Dark & Long"));
        expResult.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:375,Underworld - Surfboy"));

        // Build Tester
        M3UPlaylist result = new M3UPlaylist();
        result.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:455,Underworld - Dark & Long"));
        result.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:789,Underworld - Mmm Skyscraper I Love You"));
        result.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:375,Underworld - Surfboy"));
        result.addTrack(new M3UTrack().setTrackMetaData("#EXTINF:789,Underworld - Mmm Skyscraper I Love You"));
        result.remove(thisString);

        assertEquals(result, expResult);
    }

    /**
     * Test of resetMetaData method, of class M3UPlaylist.
     */
    @Test
    public void testResetMetaData() throws Exception {
        System.out.println("resetMetaData");
        M3UPlaylist expResult = new M3UPlaylist();

        M3UTrack t1 = new M3UTrack();
        t1.setTrackMetaData("#EXTINF:403,Orxata Sound System - als nous amos");
        t1.setTrackFilename("test-data" + File.separator + "myMusicCollection" + File.separator + "collection-A" + File.separator + "Orxata Sound System" + File.separator + "als nous amos.mp3");

        M3UTrack t2 = new M3UTrack();
        t2.setTrackMetaData("#EXTINF:258,Orxata Sound System - fuster meets guevara");
        t2.setTrackFilename("test-data" + File.separator + "myMusicCollection" + File.separator + "collection-A" + File.separator + "Orxata Sound System" + File.separator + "fuster meets guevara.mp3");

        expResult.addTrack(t1);
        expResult.addTrack(t2);

        Path p = Paths.get("test-data" + File.separator + "myMusicCollection" + File.separator + "collection-A" + File.separator + "Orxata Sound System");
        M3UPlaylist result = M3UFinder.makePlayList(p);
        result.resetMetaData();
        assertEquals(result, expResult);
    }

    /**
     * Test of addTrack method, of class M3UPlaylist.
     */
    @Test
    public void testAddTrack() {
        System.out.println("addTrack");
        M3UTrack thisTrack = new M3UTrack();

        // Build Expected
        List<M3UTrack> expResult = new ArrayList();
        expResult.add(thisTrack);

        // Build Tester
        M3UPlaylist myList = new M3UPlaylist();
        myList.addTrack(thisTrack);

        List result = myList.tracks;

        assertEquals(result, expResult);
    }

    /**
     * Test of getSizeOfPlaylist method, of class M3UPlaylist.
     */
    @Test
    public void testGetSizeOfPlaylist() {
        fail("testGetSizeOfPlaylist needs adding");
    }


}
