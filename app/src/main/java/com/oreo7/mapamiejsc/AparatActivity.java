package com.oreo7.mapamiejsc;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.Manifest;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;
import androidx.camera.core.AspectRatio;

import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class AparatActivity extends AppCompatActivity {
    ImageButton camerabtn, flashbtn, flipbtn, powrotbtn, skonczbtn;
    private PreviewView previewView;
    private Toast toast;
    int cameraFacing = CameraSelector.LENS_FACING_BACK;
    String nazwaPliku;
    List<String> listaPlikow = new ArrayList<String>();
    List<String> tempListaPlikow = new ArrayList<String>();

    List<File> tempList;
    List<File> staticList;
    private int length = -1;
    static final String appDirectoryName = "Mapa Zdjec";
    private final ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if (result) {
                startCamera(cameraFacing);
            }
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aparat);
        previewView = findViewById(R.id.camerapreview);
        camerabtn = findViewById(R.id.camerabutton);
        flashbtn = findViewById(R.id.flashbutton);
        flipbtn = findViewById(R.id.flipbutton);
        skonczbtn = findViewById(R.id.skonczaparat);
        powrotbtn = findViewById(R.id.powrotaparat);


        // View inflatedView = getLayoutInflater().inflate(R.layout.dodajpinezke, null);
        // ListView listView = (ListView) inflatedView.findViewById(R.id.listazdjec);
        // List<String> items = new ArrayList<String>();
        // items.add("Element 1");
        // items.add("Element 2");
        // items.add("Element 3");
        // ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);

        // listView.setAdapter(adapter);

        if (ContextCompat.checkSelfPermission(AparatActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            activityResultLauncher.launch(Manifest.permission.CAMERA);
        } else {
            startCamera(cameraFacing);
        }
        flipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cameraFacing == CameraSelector.LENS_FACING_BACK) {
                    cameraFacing = CameraSelector.LENS_FACING_FRONT;
                } else {
                    cameraFacing = CameraSelector.LENS_FACING_BACK;
                }
                startCamera(cameraFacing);
            }
        });
    }

    public void startCamera(int cameraFacing) {
        int aspectRatio = aspectRatio(previewView.getWidth(), previewView.getHeight());
        ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(this);

        listenableFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = (ProcessCameraProvider) listenableFuture.get();

                Preview preview = new Preview.Builder().setTargetAspectRatio(aspectRatio).build();

                ImageCapture imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                        .setTargetRotation(getWindowManager().getDefaultDisplay().getRotation()).build();

                CameraSelector cameraSelector = new CameraSelector.Builder()
                        .requireLensFacing(cameraFacing).build();

                cameraProvider.unbindAll();

                Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture);

                camerabtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (ContextCompat.checkSelfPermission(AparatActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            activityResultLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                        }
                        takePicture(imageCapture);
                        length += 1;
                    }
                });
                powrotbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tempListaPlikow.clear();
                        Intent intent = new Intent(AparatActivity.this, MapActivity.class);
                        startActivity(intent);
                    }
                });
                skonczbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setContentView(R.layout.dodajpinezke);
                        ListView listView = findViewById(R.id.listazdjec);
                        List<String> items = new ArrayList<String>();
                        String path;
                        items = tempListaPlikow;
                        //for(int i=0; i<items.size(); i++){
                            //path = items.get(i);
                            //if(path.exists()){

                              //  Bitmap myBitmap = BitmapFactory.decodeFile(path.getAbsolutePath());

                                //ImageView myImage = (ImageView) findViewById(R.id.imageviewTest);

                                //myImage.setImageBitmap(myBitmap);

                            //}

                        //}
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(AparatActivity.this, android.R.layout.simple_list_item_1, items);

                        listView.setAdapter(adapter);
                        // listaPlikow = tempListaPlikow;
                        //Intent intent = new Intent(AparatActivity.this, DodajPinezkeActivity.class);
                        //startActivity(intent);
                    }
                });
                flashbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setFlashIcon(camera);
                    }
                });

                preview.setSurfaceProvider(previewView.getSurfaceProvider());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }, ContextCompat.getMainExecutor(this));
    }
    public void takePicture(ImageCapture imageCapture) {
        final File file = new File(getExternalFilesDir(null), System.currentTimeMillis() + ".jpg");
        nazwaPliku = file.toString();
        tempListaPlikow.add(nazwaPliku);


        ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();
        imageCapture.takePicture(outputFileOptions, Executors.newCachedThreadPool(), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AparatActivity.this, "Image saved at: " + file.getPath(), Toast.LENGTH_SHORT).show();
                    }
                });
                startCamera(cameraFacing);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(AparatActivity.this, "Failed to save: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
                startCamera(cameraFacing);
            }
        });
    }

    private void setFlashIcon(Camera camera) {
        if (camera.getCameraInfo().hasFlashUnit()) {
            if (camera.getCameraInfo().getTorchState().getValue() == 0) {
                camera.getCameraControl().enableTorch(true);
                flashbtn.setImageResource(R.drawable.flashoff);
            } else {
                camera.getCameraControl().enableTorch(false);
                flashbtn.setImageResource(R.drawable.flash);
            }
        } else {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(AparatActivity.this, "Latarka niestety nie działa", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private int aspectRatio(int width, int height) {
        double previewRatio = (double) Math.max(width, height) / Math.min(width, height);
        if (Math.abs(previewRatio - 4.0 / 3.0) <= Math.abs(previewRatio - 16.0 / 9.0)) {
            return AspectRatio.RATIO_4_3;
        }
        return AspectRatio.RATIO_16_9;
    }
        
}



