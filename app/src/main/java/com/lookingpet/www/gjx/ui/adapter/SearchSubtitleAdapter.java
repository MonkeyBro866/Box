package com.lookingpet.www.gjx.ui.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lookingpet.www.gjx.R;
import com.lookingpet.www.gjx.bean.SubtitleBean;

import java.util.ArrayList;

public class SearchSubtitleAdapter extends BaseQuickAdapter<SubtitleBean, BaseViewHolder> {

    public SearchSubtitleAdapter() {
        super(R.layout.item_search_subtitle_result, new ArrayList<>());
    }

    @Override
    protected void convert(BaseViewHolder helper, SubtitleBean item) {
        helper.setText(R.id.subtitleName, item.getName());
        helper.setText(R.id.subtitleNameInfo, item.getIsZip() ? "压缩包" : "文件");
    }
}