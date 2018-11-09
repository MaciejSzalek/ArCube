package pl.arcube.arcube;

import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;

public class ArActivity extends AppCompatActivity {

    private CameraPreview cameraPreview;
    private GLSurfaceView mGlSurfaceView;
    private PhotoImage photoImage;
    private RelativeLayout arLayout;
    private String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ar_actvity);

        arLayout = findViewById(R.id.ar_activity_layout);
        mGlSurfaceView = findViewById(R.id.gl_surface_view);
        cameraPreview = new CameraPreview(this);

        path = getIntent().getStringExtra("PATH");

        photoImage = new PhotoImage();
        photoImage.setPhotoImage(path);

        mGlSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        mGlSurfaceView.setZOrderOnTop(true);
        mGlSurfaceView.setRenderer(new GLRenderer(true, this.getApplicationContext(), photoImage));
        mGlSurfaceView.getHolder().setFormat(PixelFormat.RGBA_8888);

        arLayout.addView(cameraPreview);

    }
}
