package com.rethinkdb;

import com.rethinkdb.net.Result;
import org.junit.Test;

import java.net.URI;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class DbUrlTest {
    public static final RethinkDB r = RethinkDB.r;
    private static final String DB_URL_STANDARD = "rethinkdb://bogus_man:bogus_pass@myhost:1234/mydb?auth_key=mykey&timeout=30";
    private static final String DB_URL_NON_STANDARD = "rethinkdb://bogus_man:bogus_pass@myhost:1234/mydb?auth_key=mykey&timeout=30&java.default_fetch_mode=lazy&java.unwrap_lists=true";
    private static final String DB_URL_NON_STANDARD_ALTERNATE = "rethinkdb://bogus_man:bogus_pass@myhost:1234/mydb?authKey=mykey&timeout=30&java.defaultFetchMode=lazy&java.unwrapLists=true";

    @Test
    public void testStandardDbUrl() {
        assertEquals(DB_URL_STANDARD, r.connection(DB_URL_STANDARD).dbUrlString());
        assertEquals(URI.create(DB_URL_STANDARD), r.connection(DB_URL_STANDARD).dbUrl());
        assertEquals(
            r.connection(DB_URL_STANDARD),
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L)
        );
        assertEquals(
            DB_URL_STANDARD,
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).dbUrlString()
        );
    }

    @Test
    public void testNonStandardDbUrl() {
        assertEquals(DB_URL_NON_STANDARD, r.connection(DB_URL_NON_STANDARD).dbUrlString());
        assertEquals(URI.create(DB_URL_NON_STANDARD), r.connection(DB_URL_NON_STANDARD).dbUrl());
        assertEquals(
            r.connection(DB_URL_NON_STANDARD),
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true)
        );
        assertEquals(
            DB_URL_NON_STANDARD,
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true).dbUrlString()
        );
    }


    @Test
    public void testNonStandardAlternateDbUrl() {
        assertEquals(DB_URL_NON_STANDARD, r.connection(DB_URL_NON_STANDARD_ALTERNATE).dbUrlString());
        assertEquals(URI.create(DB_URL_NON_STANDARD), r.connection(DB_URL_NON_STANDARD_ALTERNATE).dbUrl());
        assertEquals(
            r.connection(DB_URL_NON_STANDARD),
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true)
        );
        assertEquals(
            r.connection(DB_URL_NON_STANDARD_ALTERNATE),
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true)
        );
        assertEquals(
            DB_URL_NON_STANDARD,
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true).dbUrlString()
        );
        assertNotEquals(DB_URL_NON_STANDARD_ALTERNATE, r.connection(DB_URL_NON_STANDARD_ALTERNATE).dbUrlString());
        assertNotEquals(URI.create(DB_URL_NON_STANDARD_ALTERNATE), r.connection(DB_URL_NON_STANDARD_ALTERNATE).dbUrl());
        assertNotEquals(
            DB_URL_NON_STANDARD_ALTERNATE,
            r.connection().user("bogus_man", "bogus_pass").hostname("myhost").port(1234).db("mydb")
                .authKey("mykey").timeout(30L).defaultFetchMode(Result.FetchMode.LAZY).unwrapLists(true).dbUrlString()
        );
    }
}
