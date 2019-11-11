package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class KassapaateTest {
    
    Kassapaate kassapaate;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }
    
    @Test
    public void rahaaAlussaOikein() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void alussaEiMyytyjaLounaita() {
        int myydyt = kassapaate.edullisiaLounaitaMyyty() +kassapaate.maukkaitaLounaitaMyyty();
        assertEquals(0, myydyt);
    }
    
    @Test
    public void edullinenKateisosto() {
        assertEquals(260, kassapaate.syoEdullisesti(500));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100240, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukasKateisosto() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100400, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void eiRahaaEdulliseen() {
        assertEquals(123, kassapaate.syoEdullisesti(123));
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void eiRahaaMaukkaaseen() {
        assertEquals(123, kassapaate.syoMaukkaasti(123));
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void edullinenKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void maukasKortilla() {
        Maksukortti kortti = new Maksukortti(500);
        
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortillaEiKatetta() {
        Maksukortti kortti = new Maksukortti(10);
        
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinLataaminen() {
        Maksukortti kortti = new Maksukortti(10);
        kassapaate.lataaRahaaKortille(kortti, 1500);
        
        assertEquals(1510, kortti.saldo());
        assertEquals(101500, kassapaate.kassassaRahaa());
    }
    
    @Test
    public void kortinNegatiivinenLataaminen() {
        Maksukortti kortti = new Maksukortti(10);
        kassapaate.lataaRahaaKortille(kortti, -1500);
        
        assertEquals(10, kortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }   
    
}
