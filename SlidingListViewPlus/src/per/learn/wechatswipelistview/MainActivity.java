package per.learn.wechatswipelistview;

import java.util.ArrayList;
import java.util.Arrays;

import per.learn.wechatswipelistview.lib.SwipeListView;
import per.learn.wechatswipelistview.util.Util;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SwipeListView mSwipeLv;
    private MyAdapter mAdapter;
    private ArrayList<String> arrays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrays = new ArrayList<String>(Arrays.asList(Util.arrays));

        mSwipeLv = (SwipeListView)findViewById(R.id.swipe_lv);
        mAdapter = new MyAdapter(this, arrays);
        mSwipeLv.setAdapter(mAdapter);
        mSwipeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(getApplicationContext(), "Click Item " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        mSwipeLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                    int position, long id) {
                Toast.makeText(getApplicationContext(), "Long click item " + position,
                        Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public static class MyAdapter extends BaseAdapter {

        private Context mContext;
        private ArrayList<String> datas;

        public MyAdapter(Context c, ArrayList<String> arrays) {
            mContext = c;
            datas = arrays;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;

            if(convertView == null) {
                holder = new ViewHolder();

                convertView = LayoutInflater.from(mContext).inflate(
                        R.layout.layout_swipe_item, null);
                holder.mContentTv = (TextView)convertView.findViewById(R.id.content_tv);
                holder.mDeleteBtn = (Button)convertView.findViewById(R.id.delete_btn);
                holder.mTopBtn = (Button)convertView.findViewById(R.id.top_btn);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder)convertView.getTag();
            }

            holder.mContentTv.setText(datas.get(position));
            holder.mDeleteBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click delete button!",
                            Toast.LENGTH_SHORT).show();
                }
            });
            holder.mTopBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "click top button!",
                            Toast.LENGTH_SHORT).show();
                }
            });

            return convertView;
        }
    }

    private static class ViewHolder {
        TextView mContentTv;
        Button mDeleteBtn;
        Button mTopBtn;
    }
}
