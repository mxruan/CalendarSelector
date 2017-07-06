package cn.aigestudio.datepicker.demo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;

/**
 * Created by max on 2017/7/6.
 */
public class DateDialog extends Dialog{

    private View mRootView;

    private TabLayout mTabLayout;

    private MaxViewpager mViewPager;

    private List<View> mViews=new ArrayList<>();

    private ViewPagerAdapter mAdapter;
    private Context mContext;

    public DateDialog(Context context) {
        super(context, R.style.style_my_dialog);
        mRootView=View.inflate(context,R.layout.dialog_date,null);
        this.mContext=context;
        setContentView(mRootView);

        initView();
        initData();
        initEvent();
    }

    private void initView(){
        mTabLayout= (TabLayout) mRootView.findViewById(R.id.tablayout);
        mViewPager= (MaxViewpager) mRootView.findViewById(R.id.viewpager);

        View view1 = View.inflate(mContext,R.layout.layout_date,null);

        DatePicker picker= (DatePicker) view1.findViewById(R.id.date_picker);
        picker.setDate(2015, 10);
        picker.setFestivalDisplay(false);
        picker.setTodayDisplay(false);
        picker.setHolidayDisplay(false);
        picker.setDeferredDisplay(false);
        picker.setMode(DPMode.SINGLE);

        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
                Toast.makeText(mContext, date, Toast.LENGTH_LONG).show();
                mViewPager.setCurrentItem(1);
            }
        });

        picker.setViewParent(mViewPager);

        mViews.add(view1);

        View view2=View.inflate(mContext,R.layout.layout_time,null);

        mViews.add(view2);


//        View view3=View.inflate(mContext,R.layout.layout_time,null);

//        mViews.add(view3);


        mTabLayout.setupWithViewPager(mViewPager);

        mTabLayout.addTab(mTabLayout.newTab().setText("日期"));
        mTabLayout.addTab(mTabLayout.newTab().setText("时间"));
    }

    private void initData(){
        mAdapter=new ViewPagerAdapter();
        mViewPager.setAdapter(mAdapter);
    }

    private void initEvent(){

    }

    @Override
    public void show() {
        super.show();
        int[] phoneWh = UIUtil.getScreenHight(mContext);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.width = (int) (phoneWh[0]); // 设置宽度
        lp.height = (int) (phoneWh[1]); // 设置宽度
        this.getWindow().setAttributes(lp);
    }


    public class ViewPagerAdapter extends PagerAdapter{

        private String[] TITLE_ARR = new String[]{
                "日期",
                "时间"};

        @Override
        public int getCount() {
            return mViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mViews.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mViews.get(position));
            return mViews.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE_ARR[position];
        }
    }
}
