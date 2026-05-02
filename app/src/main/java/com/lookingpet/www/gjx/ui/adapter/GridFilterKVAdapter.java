package com.lookingpet.www.gjx.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lookingpet.www.gjx.R;

import java.util.ArrayList;

public class GridFilterKVAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public GridFilterKVAdapter() {
        super(R.layout.item_grid_filter_value, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.filterValue, item);
    }
}