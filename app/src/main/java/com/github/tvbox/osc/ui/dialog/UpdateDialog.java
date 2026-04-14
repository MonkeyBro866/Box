package com.github.tvbox.osc.ui.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.github.tvbox.osc.R;
import com.github.tvbox.osc.bean.UpdateInfo;

import org.jetbrains.annotations.NotNull;

public class UpdateDialog extends BaseDialog {

    public UpdateDialog(@NonNull @NotNull Context context, UpdateInfo updateInfo) {
        super(context);
        setContentView(R.layout.dialog_tip);
        setCanceledOnTouchOutside(!updateInfo.isMustUpdate());
        setCancelable(!updateInfo.isMustUpdate());

        TextView tipInfo = findViewById(R.id.tipInfo);
        TextView leftBtn = findViewById(R.id.leftBtn);
        TextView rightBtn = findViewById(R.id.rightBtn);

        String content = updateInfo.getTitle() + "\n\n" + updateInfo.getContent();
        tipInfo.setText(content);
        leftBtn.setText("立即更新");
        rightBtn.setText("取消");

        if (updateInfo.isMustUpdate()) {
            rightBtn.setVisibility(View.GONE);
        }

        leftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(updateInfo.getDownloadUrl()));
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        rightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return !isCancelable();
        }
        return super.onKeyDown(keyCode, event);
    }
}
