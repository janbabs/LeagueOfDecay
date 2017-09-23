package Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jasie on 14.09.2017.
 */

public class Utils {

    public static final String SummonerByIdURL = "api.riotgames.com/lol/summoner/v3/summoners/by-name/";
    public static final String MatchListByAcIdURL = "api.riotgames.com/lol/match/v3/matchlists/by-account/";
    public static final String MatchDetailsByIdURL = "api.riotgames.com/lol/match/v3/matches/";
    public static final String LeagueByIdURL = "api.riotgames.com/lol/league/v3/leagues/by-summoner/";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
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
        JSONArray jsonArray = jsonObject.getJSONArray(tagname);
        return jsonArray;
    }

}
