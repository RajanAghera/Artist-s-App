

package com.bnsmv.artistsapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.util.AttributeSet;

// Display

public class Display extends TunerView
{
    private static final int OCTAVE = 12;

    private int larger;
    private int large;
    private int medium;
    private int small;
    
    private int margin;
    private Bitmap bitmap;

    // Note values for display

    private static final String notes[] =
    {"C", "C", "D", "E", "E", "F",
     "F", "G", "A", "A", "B", "B"};

    private static final String sharps[] =
    {"", "\u266F", "", "\u266D", "", "",
     "\u266F", "", "\u266D", "", "\u266D", ""};
 
    // Constructor

    public Display(Context context, AttributeSet attrs)
    {
	super(context, attrs);

	bitmap = BitmapFactory.decodeResource(resources,
					      android.R.drawable.ic_lock_lock);
    }

    // On size changed

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
	super.onSizeChanged(w, h, oldw, oldh);

	// Recalculate dimensions

	width = clipRect.right - clipRect.left;
	height = clipRect.bottom - clipRect.top;

	// Calculate text sizes
	
	larger = height / 2;
	large = height / 3;
	medium = height / 5;
	small = height / 9;
	margin = width / 32;

	// Make sure the text will fit the width

	paint.setTextSize(medium);
	float dx = paint.measureText("0000.00Hz");
	
	// Scale the text if it won't fit

	if (dx + (margin * 2) >= width / 2)
	{
	    float xscale = (width / 2) / (dx + (margin * 2));
	    paint.setTextScaleX(xscale);
	}
    }

    // On draw

    @Override
    @SuppressLint("DefaultLocale")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        // Set up paint

        paint.setStrokeWidth(1);
        paint.setColor(resources.getColor(android.R.color.primary_text_light));
        paint.setTextAlign(Align.LEFT);
        paint.setStyle(Style.FILL);

    }}