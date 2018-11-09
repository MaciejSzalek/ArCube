package pl.arcube.arcube;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView imageDirection;
    public Button setImageButton;
    public Button startButton;
    public Uri uri;
    private String path;
    private final int PICK_IMAGE_REQUEST = 1;

    private RealPathUtils realPathUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_activity);

        imageView = findViewById(R.id.set_image_view);
        imageDirection = findViewById(R.id.set_image_textView);
        setImageButton = findViewById(R.id.set_image_button);
        startButton = findViewById(R.id.start_button);

        realPathUtils = new RealPathUtils();

        setImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGalleryActivity();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(path)){
                    Toast.makeText(StartActivity.this, "Image isn't selected",
                            Toast.LENGTH_SHORT).show();
                }else{
                    goToArActivity();
                }
            }
        });
    }

    public void goToArActivity(){
        Intent intent = new Intent(StartActivity.this, ArActivity.class);
        intent.putExtra("PATH", path);
        startActivity(intent);
    }
    public void goToGalleryActivity(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == PICK_IMAGE_REQUEST){
                uri = data.getData();
                path = realPathUtils.getRealPathFromURI(StartActivity.this, uri);

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = 5;
                Bitmap bitmap = BitmapFactory.decodeFile(path, options);
                imageView.setImageBitmap(bitmap);
                imageDirection.setText(path);
            }

        }
    }
}
