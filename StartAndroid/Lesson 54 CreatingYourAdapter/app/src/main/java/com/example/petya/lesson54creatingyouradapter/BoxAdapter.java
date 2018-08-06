package com.example.petya.lesson54creatingyouradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {

    Context ctx;
    LayoutInflater layoutInflater;
    ArrayList<Product> objects;

    public BoxAdapter(Context ctx, ArrayList<Product> object) {
        this.ctx = ctx;
        this.layoutInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.objects = object;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, parent, false);
        }

        Product p = getProduct(position);

        ((TextView) view.findViewById(R.id.textView)).setText(p.name);
        ((TextView) view.findViewById(R.id.textView2)).setText(p.price + "");
        ((ImageView) view.findViewById(R.id.imageView)).setImageResource(p.image);

        CheckBox cbBuy = (CheckBox) view.findViewById(R.id.checkBox);

        cbBuy.setOnCheckedChangeListener(myCheckChangeList);

        cbBuy.setTag(position);

        cbBuy.setChecked(p.box);
        return null;
    }

    private Product getProduct(int position) {
        return ((Product) getItem(position));
    }

    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<>();
        for (Product p: objects) {
            if (p.box) {
                box.add(p);
            }
        }
        return box;
    }

    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            getProduct((Integer) compoundButton.getTag()).box = b;
        }
    };
}
