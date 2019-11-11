package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void luodunKortinSaldoOikein() {
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(10);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void rahanOttaminenVahentaaSaldoa() {
        kortti.otaRahaa(5);
        assertEquals(5, kortti.saldo());
    }
    
    @Test
    public void saldoEiMeneMiinukselle() {
        kortti.otaRahaa(15);
        assertEquals(10, kortti.saldo());
    }
    
    @Test
    public void tarpeeksiSaldoaPalauttaaTrue() {
        assertTrue(kortti.otaRahaa(7));
    }
    
    @Test
    public void riittamatonSaldoPalauttaaFalse() {
        assertFalse(kortti.otaRahaa(17));
    }
    
    @Test
    public void kortinToStringPalauttaaSaldon() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
}
