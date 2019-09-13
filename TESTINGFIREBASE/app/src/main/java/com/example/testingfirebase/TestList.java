package com.example.testingfirebase;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class TestList extends ArrayAdapter<TestClass> {

    private Activity context;
    private List<TestClass> testList;

    public TestList(Activity context, List<TestClass> testList){
        super(context, R.layout.list_all, testList);
        this.context = context;
        this.testList = testList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inf = context.getLayoutInflater();

        View listViewItem = inf.inflate(R.layout.list_all, null , true);
        TextView tvData = (TextView) listViewItem.findViewById(R.id.data);
        TextView tvType = (TextView) listViewItem.findViewById(R.id.type);

        TestClass testClassGet = testList.get(position);

        tvData.setText(testClassGet.gettData());
        tvType.setText(testClassGet.gettType());

        return listViewItem;
    }
}
