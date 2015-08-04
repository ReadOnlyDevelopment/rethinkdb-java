// Autogenerated by metajava.py.
// Do not edit this file directly.
// The template for this file is located at:
// ../../../../../../../templates/Response.java
package com.rethinkdb.response;

import com.rethinkdb.proto.ResponseType;
import com.rethinkdb.proto.ResponseNote;
import com.rethinkdb.ReqlError;
import com.rethinkdb.ast.Query;
import org.json.simple.*;

import java.util.*;
import java.nio.ByteBuffer;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.stream.Collectors;


public class Response {
    public final long token;
    public final ResponseType type;
    public final ArrayList<ResponseNote> notes;
    public final Optional<JSONArray> data;
    public final Optional<Profile> profile;
    public final Optional<Backtrace> backtrace;


    private static class ByteBufferInputStream extends InputStream {
        ByteBuffer buf;
        ByteBufferInputStream(ByteBuffer buf) {
            this.buf = buf;
        }

        public synchronized int read() throws IOException {
            if(!buf.hasRemaining()){
                return -1;
            }
            return buf.get();
        }

        public synchronized int read(byte[] bytes, int off, int len) throws IOException {
            len = Math.min(len, buf.remaining());
            buf.get(bytes, off, len);
            return len;
        }
    }

    public static Response parseFrom(long token, ByteBuffer buf) {
        InputStreamReader codepointReader =
            new InputStreamReader(new ByteBufferInputStream(buf));
        JSONObject jsonResp = (JSONObject) JSONValue.parse(codepointReader);
        ResponseType responseType = ResponseType.fromValue((int)jsonResp.get("t"));
        ArrayList<Integer> responseNoteVals = (ArrayList<Integer>) jsonResp
            .getOrDefault("n", new ArrayList<>());
        ArrayList<ResponseNote> responseNotes = responseNoteVals
            .stream()
            .map(ResponseNote::fromValue)
            .collect(Collectors.toCollection(ArrayList::new));
        return new Response(token, responseType, responseNotes,
                            Optional.empty(),
                            Optional.empty(),
                            Optional.empty());
    }

    public Response(long token,
             ResponseType responseType,
             ArrayList<ResponseNote> responseNotes,
             Optional<Profile> profile,
             Optional<Backtrace> backtrace,
             Optional<JSONArray> data
    ) {
        this.token = token;
        this.type = responseType;
        this.notes = responseNotes;
        this.profile = profile;
        this.backtrace = backtrace;
        this.data = data;
    }

    public ReqlError makeError(Query query) {
        throw new RuntimeException("makeError not implemented");
    }

    public boolean isWaitComplete() {
        return type == ResponseType.WAIT_COMPLETE;
    }

    /* Whether the response is any kind of feed */
    public boolean isFeed() {
        return notes.stream().allMatch(ResponseNote::isFeed);
    }

    /* Whether the response is any kind of error */
    public boolean isError() {
        return type.isError();
    }

    public static JSONArray convertPseudotypes(
      JSONArray obj, Optional<Profile> profile) {
        throw new RuntimeException("convertPseudotypes not implemented");
    }

    /* Autogenerated methods below */

    /* What type of success the response contains */
    public boolean isAtom() {
        return type == ResponseType.SUCCESS_ATOM;
    }
    public boolean isSequence() {
        return type == ResponseType.SUCCESS_SEQUENCE;
    }
    public boolean isPartial() {
        return type == ResponseType.SUCCESS_PARTIAL;
    }
}
