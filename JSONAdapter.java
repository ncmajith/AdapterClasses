package com.example.user.good_carriermacfast;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Ajith on 14-10-2017.
 */

class JSONAdapter extends BaseAdapter implements ListAdapter {
    Button button;
    String ids;
    TextView name,request,phone;
    private final Activity activity;
    private final JSONArray jsonArray;
    public JSONAdapter(Activity activity, JSONArray jsonArray) {
        assert activity != null;
        assert jsonArray != null;

        this.jsonArray = jsonArray;
        this.activity = activity;
    }


    @Override public int getCount() {

        return jsonArray.length();
    }

    @Override public JSONObject getItem(int position) {

        return jsonArray.optJSONObject(position);
    }

    @Override public long getItemId(int position) {
        JSONObject jsonObject = getItem(position);

        return jsonObject.optLong("id");
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        if (view == null)
            view = activity.getLayoutInflater().inflate(R.layout.userview, null);

        JSONObject jsonObject = getItem(position);
        button=(Button) view.findViewById(R.id.adduser);
        name=(TextView)view.findViewById(R.id.name);
        request=(TextView)view.findViewById(R.id.etS);
        phone=(TextView)view.findViewById(R.id.etD);

        try {
            phone.setText(jsonObject.getString("U_EMAIL"));
            name.setText(jsonObject.getString("U_NAME"));
            request.setText(jsonObject.getString("U_PHONE"));
            ids=jsonObject.getString("U_ID");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent intent = new Intent(activity, PerformBargain.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("ID", ids.trim());
                    activity.getApplicationContext().startActivity(intent);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }
}