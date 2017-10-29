package com.leagueofdecay.Utils;

import com.leagueofdecay.data.DecayHttpClient;
import com.leagueofdecay.data.JSONDecayParser;
import com.leagueofdecay.model.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    public static final String SummonerByIdURL = "api.riotgames.com/lol/summoner/v3/summoners/by-name/";
    public static final String MatchListByAcIdURL = "api.riotgames.com/lol/match/v3/matchlists/by-account/";
    public static final String MatchDetailsByIdURL = "api.riotgames.com/lol/match/v3/matches/";
    public static final String LeagueByIdURL = "api.riotgames.com/lol/league/v3/leagues/by-summoner/";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONObject(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws  JSONException {
        return jsonObject.getInt(tagName);
    }

    public static long getLong(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getLong(tagName);
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException {
        return jsonObject.getString(tagName);
    }

    public static JSONArray getArray(String tagname, JSONObject jsonObject) throws JSONException {
        return jsonObject.getJSONArray(tagname);
    }
    public static int TimeDiff (String name, String server, String apikey ) {
        String data = ((new DecayHttpClient()).getSummonerData(name, server, apikey));
        if (data == null) {
            return -1;
        }
        Player player = JSONDecayParser.getPlayer(data);
        String league = JSONDecayParser.getLeague(new DecayHttpClient().getLeagueById(player.getId(), server, apikey));
        league = league.toUpperCase();
        if (!(league.equals("PLATINIUM") || league.equals("DIAMOND") || league.equals("MASTER") || league.equals("CHALLENGER"))) {
            return -2;
        }
        Long matchId = JSONDecayParser.getMatchId((new DecayHttpClient()).getLastRankedMatchData(player.getAccountId(), server, apikey));
        return JSONDecayParser.getTimeDiff((new DecayHttpClient()).getMatchDetailsById(matchId, server, apikey));
    }
}
