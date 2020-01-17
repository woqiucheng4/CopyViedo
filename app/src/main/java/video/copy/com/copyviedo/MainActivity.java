package video.copy.com.copyviedo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.refactor.Customer;
import com.refactor.Movie;
import com.refactor.Rental;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_FOR_WRITE_PERMISSION = 1;
    private static final int CODE_FOR_CAMERA = 2;

    private Button copyButton;
    private Button playButton;

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() //
            + File.separator + "aaa" + File.separator;

    private List<VideoInfo> videoList = new ArrayList<>();
    private List<VideoInfo> isoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_FOR_WRITE_PERMISSION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CODE_FOR_CAMERA);
        }
        copyButton = (Button) findViewById(R.id.copy);
        playButton = (Button) findViewById(R.id.play);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getViedo();
                if (!videoList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "num is " + videoList.size(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < videoList.size(); i++) {
                        copySdcardFile(i + 1, videoList.get(i).getFilePath(), makeFilePath(path, videoList.get(i).getDisplayName() + ".iso"));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getISO();
                if (!isoList.isEmpty()) {
                    Toast.makeText(MainActivity.this, "num is " + isoList.size(), Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < isoList.size(); i++) {
                        copySdcardFile(i + 1, isoList.get(i).getFilePath(), makeFilePath(path, isoList.get(i).getDisplayName() + ".mp4"));
                    }
                } else {
                    Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void getViedo() {
        VideoUtils.getVideoFile(videoList, new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"));
    }

    private void getISO() {
        VideoUtils.getISOFile(isoList, new File(path));
    }

    public void copySdcardFile(final int position, final String fromFile, final File toFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    InputStream fosfrom = new FileInputStream(fromFile);
                    OutputStream fosto = new FileOutputStream(toFile);
                    byte bt[] = new byte[1024];
                    int c;
                    while ((c = fosfrom.read(bt)) > 0) {
                        fosto.write(bt, 0, c);
                    }
                    fosfrom.close();
                    fosto.close();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, position + " is over", Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (Exception ex) {
                    Log.i("nnn", "ex==" + ex.getMessage().toString());
                }
            }
        }).start();
    }

    // 生成文件
    public File makeFilePath(String filePath, String fileName) {
        File file = null;
        makeRootDirectory(filePath);
        try {
            file = new File(filePath + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    // 生成文件夹
    public static void makeRootDirectory(String filePath) {
        File file = null;
        try {
            file = new File(filePath);
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            Log.i("error:", e + "");
        }
    }
}
