package com.xiaoyuan.shoppingcart;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView edit, total_price;
    private ListView lv;
    private CheckBox all_choose;
    private Button pay;
    private List<CartItem> list = new ArrayList<>();
    private CartAdapter adapter;
    private int count;
    private float price;
    private boolean mode;//  false 结算模式    true编辑模式


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setUpOptions();
    }

    /**
     * 填充数据
     */
    private void setUpOptions() {
        list.add(new CartItem("疯抢 品质好货加厚羊毛昵大衣", "155.6"));
        list.add(new CartItem("风干消毒智能电动全国包安装", "99.5"));
        list.add(new CartItem("假一罚万 全棉斜纹四件套", "49.9"));
        list.add(new CartItem("德国双立人刀具7件套", "29.5"));
        adapter = new CartAdapter();
        lv.setAdapter(adapter);
        notifyData();

        //全选按钮的事件
        all_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (CartItem item : list) {
                    item.setCheck(all_choose.isChecked());
                }
                adapter.notifyDataSetChanged();
                notifyData();
            }
        });
        //编辑、完成按钮的事件
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeMode();
            }
        });
        //支付  删除操作
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mode) {
                    Toast.makeText(MainActivity.this, "删除" + getPositions(), Toast.LENGTH_SHORT).show();
                    changeMode();
                } else {
                    Toast.makeText(MainActivity.this, "支付" + getPositions(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    /**
     * 切换模式
     */
    private void changeMode() {
        if (mode) {
            mode = false;
            edit.setText("编辑");
            notifyData();
        } else {
            mode = true;
            edit.setText("完成");
            pay.setText("删除");
        }
    }


    // 拼接购物车下标
    private String getPositions() {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isCheck()) {
                sb.append(i).append(" ");
            }
        }
        return sb.toString();
    }


    /**
     * 初始化控件
     */
    private void initView() {
        edit = (TextView) findViewById(R.id.edit);
        total_price = (TextView) findViewById(R.id.price);
        lv = (ListView) findViewById(R.id.lv);
        all_choose = (CheckBox) findViewById(R.id.check);
        pay = (Button) findViewById(R.id.pay);
    }

    /**
     * 更新数量   价格
     */
    private void notifyData() {
        count = 0;
        price = 0.0f;
        for (CartItem item : list) {
            if (item.isCheck()) {
                count++;
                price += Double.valueOf(item.getPrice());

            }
        }
        price = new BigDecimal(price).setScale(1, BigDecimal.ROUND_HALF_UP)
                .floatValue();
        if (mode) {
            pay.setText("删除");
        } else {
            pay.setText("结算(" + count + ")");
        }
        total_price.setText("￥" + price);
    }


    class CartAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = View.inflate(MainActivity.this, R.layout.cart_item, null);
            }
            TextView title = (TextView) view.findViewById(R.id.item_title);
            TextView price = (TextView) view.findViewById(R.id.item_price);
            CheckBox check = (CheckBox) view.findViewById(R.id.item_check);
            title.setText(list.get(i).getTitle());
            price.setText("￥" + list.get(i).getPrice());
            check.setChecked(list.get(i).isCheck());
            check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    list.get(i).setCheck(isChecked);
                    notifyData();
                    if (!isChecked && all_choose.isChecked()) {
                        all_choose.setChecked(false);
                        return;
                    }
                    boolean all = true;
                    for (CartItem item : list) {
                        // 有一个没选中就不全选
                        if (!item.isCheck()) {
                            all = false;
                        }
                    }
                    all_choose.setChecked(all);
                }
            });
            return view;
        }
    }

}
