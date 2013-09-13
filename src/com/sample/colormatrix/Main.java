/*******************************************************************************
 * Copyright 2012 Steven Rudenko
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.sample.colormatrix;

import android.app.Activity;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Main extends Activity implements View.OnClickListener {

  private ImageView viewImage;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    findViewById(R.id.original).setOnClickListener(this);
    findViewById(R.id.bw).setOnClickListener(this);
    findViewById(R.id.sepia).setOnClickListener(this);

    viewImage = (ImageView) findViewById(R.id.image);
  }

  @Override
  public void onClick(View v) {
    final int id = v.getId();
    final Drawable drawable = viewImage.getDrawable();

    switch (id) {
    case R.id.original:
      setNoColorFilter(drawable);
      break;
    case R.id.bw:
      setBlackAndWhiteColorFilter(drawable);
      break;
    case R.id.sepia:
      setSepiaColorFilter(drawable);
      break;
    default:
      break;
    }
  }

  private static void setNoColorFilter(Drawable drawable) {
    if (drawable == null)
      return;
    drawable.setColorFilter(null);
  }

  private static void setBlackAndWhiteColorFilter(Drawable drawable) {
    if (drawable == null)
      return;

    final ColorMatrix matrixA = new ColorMatrix();
    matrixA.setSaturation(0);

    final ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrixA);
    drawable.setColorFilter(filter);
  }

  private static void setSepiaColorFilter(Drawable drawable) {
    if (drawable == null)
      return;

    final ColorMatrix matrixA = new ColorMatrix();
    matrixA.setSaturation(0);

    final ColorMatrix matrixB = new ColorMatrix();
    matrixB.setScale(1f, .95f, .82f, 1.0f);
    matrixA.setConcat(matrixB, matrixA);

    final ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrixA);
    drawable.setColorFilter(filter);
  }
}
