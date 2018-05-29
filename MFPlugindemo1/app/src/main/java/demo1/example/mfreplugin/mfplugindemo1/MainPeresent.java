package demo1.example.mfreplugin.mfplugindemo1;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ryan on 2018/5/25.
 * 简单的MVP，P
 */
public class MainPeresent {
    MyAdapter mAdapter;

    MainPeresent(MyAdapter adapter) {
        this.mAdapter = adapter;
    }

    public void addItemsOnCreate() {
        mAdapter.addItem("Just Toast!", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Toast something！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    static class MyAdapter extends BaseAdapter {
        private List<TestItem> mList;
        private Context ctx;

        MyAdapter(Context context) {
            this.ctx = context;
            mList = new ArrayList<>();
        }

        void addItem(String title, View.OnClickListener onClickListener) {
            mList.add(new TestItem(title, onClickListener));
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public TestItem getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            if (view == null)
                view = new TextView(ctx);

            TestItem item = getItem(position);
            ((TextView) view).setText(item.title);
            view.setOnClickListener(item.mClickListener);

            return view;
        }
    }

    static class TestItem {
        String title;
        View.OnClickListener mClickListener;

        TestItem(String title, View.OnClickListener listener) {
            this.title = title;
            this.mClickListener = listener;
        }
    }
}
