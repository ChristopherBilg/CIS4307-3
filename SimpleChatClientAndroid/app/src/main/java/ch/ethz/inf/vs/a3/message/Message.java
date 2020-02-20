package ch.ethz.inf.vs.a3.message;

import org.json.JSONException;
import org.json.JSONObject;

public class Message {

    private String messageContents;
    private String bodyContents;
    private String timestampContents;

    public Message(String messageContents) {
        this.messageContents = messageContents;
        this.bodyContents = this.parseBodyContents(this.messageContents);
        this.timestampContents = this.parseTimestampContents(this.messageContents);
    }

    private String parseBodyContents(String messageContents) {
        JSONObject json;
        try {
            json = new JSONObject(messageContents);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        JSONObject body;
        try {
            body = json.getJSONObject("body");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        String bodyContents;
        try {
            bodyContents = body.getString("content");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        return bodyContents;
    }

    private String parseTimestampContents(String messageContents) {
        JSONObject json;
        try {
            json = new JSONObject(messageContents);
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        JSONObject header;
        try {
            header = json.getJSONObject("header");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        String timestampContents;
        try {
            timestampContents = header.getString("timestamp");
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }

        return timestampContents;
    }

    public String getBodyContents() {
        return this.bodyContents;
    }

    public void setBodyContents(String contents) {
        this.bodyContents = contents;
    }

    public String getTimestampContents() {
        return this.timestampContents;
    }
}
