package com.oreo7.mapamiejsc;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomImageAdapter extends ArrayAdapter<String> {

    private final List<String> imagePaths;

    public CustomImageAdapter(Context context, int resource, List<String> imagePaths) {
        super(context, resource, imagePaths);
        this.imagePaths = imagePaths;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;

        // Reuse views to improve performance
        if (rowView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            rowView = inflater.inflate(R.layout.item_row, parent, false);

            // Configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.imageView = rowView.findViewById(R.id.zdjecieGaleria);
            rowView.setTag(viewHolder);
        }

        // Get the data item for this position
        String imagePath = imagePaths.get(position);

        // Load image from file using BitmapFactory
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);

        // Set the bitmap to the ImageView
        ViewHolder viewHolder = (ViewHolder) rowView.getTag();
        viewHolder.imageView.setImageBitmap(bitmap);

        return rowView;
    }

    private static class ViewHolder {
        ImageView imageView;
    }
}

