package com.leagueofdecay.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.leagueofdecay.Utils.Utils;
import com.leagueofdecay.model.Player;

/**
 * Created by jasie on 14.09.2017.
 */

public class JSONDecayParser {
    public static Player getPlayer(String data) {
        Player player = new Player();
        try {
            JSONObject jsonObject = new JSONObject(data);

            player.setAccountId(jsonObject.getLong("accountId"));
            player.setId(jsonObject.getLong("id"));
            player.setName(jsonObject.getString("name"));
            player.setProfileIconId(jsonObject.getInt("profileIconId"));
            player.setRevicisonDate(jsonObject.getInt("revisionDate"));
            player.setSummonerLevel(jsonObject.getInt("summonerLevel"));

            return player;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static long getMatchId (String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray jsonArray = Utils.getArray("matches", jsonObject);
            JSONObject match0 = jsonArray.getJSONObject(0);
            long gameId = match0.getLong("gameId");
            return gameId;


        } catch (JSONException e) {
            e.printStackTrace();
        }
    return 0;
    }

    public static int getTimeDiff(String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            long timestamp = Utils.getLong("gameCreation",jsonObject);
            long currentDate = System.currentTimeMillis();
            int diffinDays = (int)((currentDate - timestamp) /(1000*60*60*24));
            return diffinDays;


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static String getLeague(String data) {
        try {
            JSONArray jsonArray = new JSONArray(data);
            int i = 0;
            JSONObject jsonObject = null;
            while(jsonArray.getJSONObject(i) != null) {
                jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.getString("queue").equals("RANKED_SOLO_5x5")) {
                    return jsonObject.getString("tier");
                }
                i++;
            }
            return "";
        } catch (JSONException e) {
            e.printStackTrace();
        }
    return "";
    }
}
