package video.copy.com.copyviedo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_FOR_WRITE_PERMISSION = 1;

    private Button copyButton;
    private Button playButton;

    private String path = Environment.getExternalStorageDirectory().getAbsolutePath() //
            + File.separator;

    private List<VideoInfo> videoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_FOR_WRITE_PERMISSION);
        } else {
            getViedo();
        }
        copyButton = (Button) findViewById(R.id.copy);
        playButton = (Button) findViewById(R.id.play);
        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoList.isEmpty()) {
                    for (int i = 0; i < videoList.size(); i++) {
                        copyFile(videoList.get(i).getFilePath(), path+i+".iso");
                        if(i==videoList.size()-1){
                            Toast.makeText(MainActivity.this,"over",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!videoList.isEmpty()) {
                    for (int i = 0; i < videoList.size(); i++) {
                        copyFile( path+i+".iso", path+ i+".mp4");
                    }
                }
            }
        });
    }

    private void getViedo() {
        VideoUtils.getVideoFile(videoList, new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DCIM"));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        if (requestCode == CODE_FOR_WRITE_PERMISSION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getViedo();
            } else {
                // Permission Denied
                Toast.makeText(MainActivity.this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public void copyFile(String oldPath, String newPath) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPath); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1000000];
                int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    bytesum += byteread; //字节数 文件大小
                    Log.i("nnn", "bytesum==" + bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            Log.i("nnn", "复制单个文件操作出错");
            e.printStackTrace();

        }
    }
}
