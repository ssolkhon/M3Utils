/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package m3utils;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author scott
 */
public class M3UTrackTest {
    
    /**
     * Test of getSuggestedArtists method, of class M3UTrack.
     */
    @Test
    public void testGetSuggestedArtists() {
        System.out.println("getSuggestedArtists");
        M3UTrack instance = new M3UTrack();
        List expResult = null;
        List result = instance.getSuggestedArtists();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateTrackSpotify method, of class M3UTrack.
     */
    @Test
    public void testGenerateTrackSpotify() {
        System.out.println("generateTrackSpotify");
        M3UTrack instance = new M3UTrack();
        boolean expResult = false;
        boolean result = instance.generateTrackSpotify();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidTrack method, of class M3UTrack.
     */
    @Test
    public void testIsValidTrack() {
        System.out.println("isValidTrack");
        M3UTrack instance = new M3UTrack();
        boolean expResult = false;
        boolean result = instance.isValidTrack();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrackTitle method, of class M3UTrack.
     */
    @Test
    public void testGetTrackTitle() {
        System.out.println("getTrackTitle");
        M3UTrack instance = new M3UTrack();
        String expResult = "";
        String result = instance.getTrackTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTrackArtist method, of class M3UTrack.
     */
    @Test
    public void testGetTrackArtist() {
        System.out.println("getTrackArtist");
        M3UTrack instance = new M3UTrack();
        String expResult = "";
        String result = instance.getTrackArtist();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
