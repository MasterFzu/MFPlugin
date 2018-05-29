package demo1.example.mfreplugin.mfplugindemo1;

import android.os.Bundle;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import mypluginsdk.android.masterfzu.mypluginsdk.MFPluginActivity;

public class MainActivity extends MFPluginActivity {

    @BindView(R.id.mainlist)
    ListView mMainList;
    MainPeresent peresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ButterKnife.bind(this);


        MainPeresent.MyAdapter adapter = new MainPeresent.MyAdapter(this);
        peresent = new MainPeresent(adapter);

        mMainList.setAdapter(adapter);
        peresent.addItemsOnCreate();
    }

}
