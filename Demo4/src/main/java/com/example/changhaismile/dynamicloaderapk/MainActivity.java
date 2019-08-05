package com.example.changhaismile.dynamicloaderapk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dalvik.system.DexClassLoader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RelativeLayout relativeLayout;
    private Button button;
    private String apkDir = Environment.getExternalStorageDirectory().getPath() + File.separator + "lzl";
    private ListView mListView;
    private List<HashMap<String, String>> datas;
    private List<String> apkNames = new ArrayList<>();
    private HashMap<String, String > map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.relative);
        button = (Button) findViewById(R.id.btn_loader_apk);
        //1.拷贝apk至sd卡的plugin目录下
        copyApkFile("apkthemeplugin-1.apk");
        copyApkFile("apkthemeplugin-2.apk");
        copyApkFile("apkthemeplugin-3.apk");
        Toast.makeText(this, "拷贝完成", Toast.LENGTH_SHORT).show();
        button.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.skin:

                //2.查找并得到该apk目录下所有apk信息
                datas = searchAllPlugin(apkDir);
                //3.显示查找后可用的apk插件
                showCanEnabledPlugin(datas);
                //4.处理用户的点击事件，并设置相应的皮肤
                mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        HashMap<String, String> map = datas.get(position);
                        if (map != null) {
                            String pkgName = map.get("pkgName");
                            String apkName = apkNames.get(position);
                            try {
                                //动态加载得到相应资源
                                dynamicLoadApk(apkDir, apkName, pkgName);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /***
     * 拷贝apk文件至sd卡plugin目录下
     * @param apkName
     */
    private void copyApkFile(String apkName) {
        File file = new File(apkDir);
        if (!file.exists()) {
            file.mkdirs();
        }

        File apk = new File(apkDir + File.separator + apkName);
        try {
            if (apk.exists()) {
                return;
            }

            FileOutputStream fos = new FileOutputStream(apk);
            InputStream is = getResources().getAssets().open(apkName);
            BufferedInputStream bis = new BufferedInputStream(is);
            int len = -1;
            byte[] bytes = new byte[1024];
            while ((len = bis.read(bytes)) != -1) {
                fos.write(bytes, 0, len);
                fos.flush();
            }
            fos.close();
            is.close();
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找所有插件
      * @param apkDir
     * @return
     */
    private List<HashMap<String, String>> searchAllPlugin(String apkDir) {
        List<HashMap<String, String>> lists = new ArrayList<>();
        File dir = new File(apkDir);
        if (dir.isDirectory()) {
            //过滤除.apk之外文件
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(".apk");
                }
            };
            File[] apks = dir.listFiles(filter);
            for (int i = 0; i < apks.length; i ++) {
                File temp = apks[i];
                //存储apk名称
                apkNames.add(temp.getName());

                String[] info = getUninstallApkInfo(MainActivity.this, apkDir + File.separator + temp.getName());
                map = new HashMap<>();
                map.put("label", info[0]);
                map.put("pkgName", info[1]);
                lists.add(map);
            }
        }
        return lists;
    }

    /**
     * 获取未安装apk的信息
     * @param context
     * @param archiveFilePath
     * @return
     */
    private String[] getUninstallApkInfo(Context context, String archiveFilePath) {
        String[] info = new String[2];
        PackageManager pm = context.getPackageManager();
        PackageInfo pkgInfo = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
        if (pkgInfo != null) {
            ApplicationInfo appInfo = pkgInfo.applicationInfo;
            //版本号
            String versionName = pkgInfo.versionName;
            //图标
            Drawable icon = pm.getApplicationIcon(appInfo);
            //app名称
            String appName = pm.getApplicationLabel(appInfo).toString();
            //包名
            String pkgName = appInfo.packageName;
            info[0] = appName;
            info[1] = pkgName;
        }
        return info;
    }

    /**
     * 列出应用中可用插件，由于只是演示实例，该demo简单将apk放置assets目录，然后运行放入sd卡plugin目录
     * @param datas
     */
    private void showCanEnabledPlugin(List<HashMap<String, String>> datas) {
        if (datas == null && datas.isEmpty()) {
            Toast.makeText(this, "请先下载相关插件！", Toast.LENGTH_SHORT).show();
            return;
        }
        View rootView = LayoutInflater.from(this).inflate(R.layout.layout_item, null);
        mListView = (ListView) rootView.findViewById(R.id.listview);
        mListView.setAdapter(new SimpleAdapter(this, datas, android.R.layout.simple_list_item_1,
                new String[] {"label"}, new int[] {android.R.id.text1}));
        PopupWindow popupWindow = new PopupWindow(rootView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(rootView, Gravity.TOP|Gravity.RIGHT, 0, 0);
    }

    /**
     * 加载apk获取内部资源
     * @param apkDir
     * @param apkName
     * @param apkPackageName
     */
    private void dynamicLoadApk(String apkDir, String apkName, String apkPackageName) throws Exception{
        //在应用安装目录下创建
        File optimizedDirectoryFile = getDir("dex", Context.MODE_PRIVATE);
        /**参数
         * 1.包含 dex文件的apk文件或者jar文件的路径
         * 2.apk,jar解压缩生成dex存储目录
         * 3.本地library库目录，一般为null
         * 4.父ClassLoader
         */
        DexClassLoader dexClassLoader = new DexClassLoader(apkDir + File.separator + apkName,
                optimizedDirectoryFile.getParent(), null, ClassLoader.getSystemClassLoader());
        //通过使用apk自己的构造器，反射出R类中相应的内部进而获取我们需要的资源id
        Class<?> clazz = dexClassLoader.loadClass(apkPackageName + ".R$mipmap");
        Field field = clazz.getDeclaredField("one");
        int resId = field.getInt(R.id.class);
        //得到插件apk中的Resources
        Resources resources = getPluginResources(apkName);
        if (resources != null) {
            findViewById(R.id.relative).setBackgroundDrawable(resources.getDrawable(resId));
        }
    }

    /**
     * 得到对应插件的Resources
     * @param apkName
     * @return
     */
    private Resources getPluginResources(String apkName) throws Exception{
        AssetManager assetManager = AssetManager.class.newInstance();
        //反射调用方法addAssetPath(String path)
        Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
        addAssetPath.invoke(assetManager, apkDir + File.separator + apkName);
        Resources superRes = this.getResources();
        Resources mResources = new Resources(assetManager, superRes.getDisplayMetrics(),
                superRes.getConfiguration());
        return mResources;
    }

    @Override
    public void onClick(View v) {
        if(R.id.btn_loader_apk==v.getId()){
            //2.查找并得到该apk目录下所有apk信息
            datas = searchAllPlugin(apkDir);
            //3.显示查找后可用的apk插件
            showCanEnabledPlugin(datas);
            //4.处理用户的点击事件，并设置相应的皮肤
            mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    HashMap<String, String> map = datas.get(position);
                    if (map != null) {
                        String pkgName = map.get("pkgName");
                        String apkName = apkNames.get(position);
                        try {
                            //动态加载得到相应资源
                            dynamicLoadApk(apkDir, apkName, pkgName);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
