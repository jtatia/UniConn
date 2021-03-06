package com.pat_041.android.uniconn.loaders;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.pat_041.android.uniconn.SearchCaseConstants;
import com.pat_041.android.uniconn.definitions.Event;
import com.pat_041.android.uniconn.networkingutils.CallAPIUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class EventLoader extends AsyncTaskLoader<List<Event>> {


    private String query;
    private int searchCase;
    private String key;
    Context context;

    public EventLoader(Context context, String query, String key, int searchCase) {
        super(context);
        this.context = context;
        this.query = query;
        this.searchCase = searchCase;
        this.key = key;
    }

    @Override
    public List<Event> loadInBackground() {

        ArrayList<Event> list = null;

        System.out.println("Inside asynctask");
        // call function here according to case
        try {
            switch (searchCase) {
                case SearchCaseConstants.NORMAL_SEARCH:
                    list =  CallAPIUtils.getListOfEvents(context, query);
                    break;
                case SearchCaseConstants.PARAMETERIZED_SEARCH:
                    list = CallAPIUtils.getListOfEvents(context,key,query);
                    break;

            }
        } catch (JSONException e) {
            Log.e("CollegeLoader","Unable to get list");
        }

        return list;
    }

    @Override
    protected void onStartLoading() {

        System.out.println("hello force loading");
        forceLoad();
    }
}
