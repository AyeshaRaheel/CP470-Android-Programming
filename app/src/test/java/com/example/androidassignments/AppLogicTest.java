package com.example.androidassignments;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AppLogicTest {

    // ---------- isValidEmail ----------

    @Test
    public void isValidEmail_simpleAddress_returnsTrue() {
        assertTrue(AppLogic.isValidEmail("user@example.com"));
    }

    @Test
    public void isValidEmail_subdomainAndSymbols_returnsTrue() {
        assertTrue(AppLogic.isValidEmail("first.last+tag@mail.example.co"));
    }

    @Test
    public void isValidEmail_surroundingSpaces_areTrimmed() {
        assertTrue(AppLogic.isValidEmail("  user@example.com  "));
    }

    @Test
    public void isValidEmail_null_returnsFalse() {
        assertFalse(AppLogic.isValidEmail(null));
    }

    @Test
    public void isValidEmail_empty_returnsFalse() {
        assertFalse(AppLogic.isValidEmail(""));
    }

    @Test
    public void isValidEmail_missingAtSymbol_returnsFalse() {
        assertFalse(AppLogic.isValidEmail("userexample.com"));
    }

    @Test
    public void isValidEmail_missingDomain_returnsFalse() {
        assertFalse(AppLogic.isValidEmail("user@"));
    }

    @Test
    public void isValidEmail_missingTld_returnsFalse() {
        assertFalse(AppLogic.isValidEmail("user@example"));
    }

    @Test
    public void isValidEmail_shortTld_returnsFalse() {
        assertFalse(AppLogic.isValidEmail("user@example.c"));
    }

    @Test
    public void isValidEmail_spaceInside_returnsFalse() {
        assertFalse(AppLogic.isValidEmail("us er@example.com"));
    }

    // ---------- isPasswordEmpty ----------

    @Test
    public void isPasswordEmpty_null_returnsTrue() {
        assertTrue(AppLogic.isPasswordEmpty(null));
    }

    @Test
    public void isPasswordEmpty_emptyString_returnsTrue() {
        assertTrue(AppLogic.isPasswordEmpty(""));
    }

    @Test
    public void isPasswordEmpty_nonEmpty_returnsFalse() {
        assertFalse(AppLogic.isPasswordEmpty("secret123"));
    }

    // ---------- isIncomingRow ----------

    @Test
    public void isIncomingRow_evenPosition_returnsTrue() {
        assertTrue(AppLogic.isIncomingRow(0));
        assertTrue(AppLogic.isIncomingRow(2));
        assertTrue(AppLogic.isIncomingRow(10));
    }

    @Test
    public void isIncomingRow_oddPosition_returnsFalse() {
        assertFalse(AppLogic.isIncomingRow(1));
        assertFalse(AppLogic.isIncomingRow(3));
        assertFalse(AppLogic.isIncomingRow(11));
    }
}