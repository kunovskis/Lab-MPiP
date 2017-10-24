package com.example.lab1_1;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class ImplicitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        final ListView lw = (ListView) findViewById(R.id.listView);

        final List<ResolveInfo> apps;
        final PackageManager pm = getPackageManager();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        apps=pm.queryIntentActivities(intent, 0);


        final ArrayAdapter<ResolveInfo> adapter =
                new ArrayAdapter<ResolveInfo>(this, R.layout.linear, apps)
                {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent){
                        if(convertView == null){
                            convertView = LayoutInflater.from(parent.getContext()).
                                    inflate(R.layout.linear, parent, false);
                        }

                        final String text = apps.get(position).activityInfo.
                                applicationInfo.loadLabel(pm).toString();
                        ((TextView)convertView.findViewById(R.id.text)).setText(text);

                        final Drawable drawable = apps.get(position).activityInfo.
                                loadIcon(pm);
                        ((ImageView)convertView.findViewById(R.id.image)).setImageDrawable(drawable);

                        return convertView;
                    }
                };
        lw.setAdapter(adapter);

        lw.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResolveInfo ri = (ResolveInfo) lw.getItemAtPosition(position);
                ActivityInfo ai = ri.activityInfo;
                ComponentName name = new ComponentName(ai.applicationInfo.packageName, ai.name);
                Intent i = new Intent(Intent.ACTION_MAIN);
                i.addCategory(Intent.CATEGORY_LAUNCHER);
                i.setComponent(name);
                startActivity(i);
            }
        });
    }

}
