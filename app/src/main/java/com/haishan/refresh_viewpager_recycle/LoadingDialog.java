package com.haishan.refresh_viewpager_recycle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * Created by apple on 2018/3/18.
 */

public class LoadingDialog extends Dialog {
    private View mPanel;
    private Context context;
    private TextView title;

    public LoadingDialog(Context context) {
        super(context, R.style.dialog_loading_style);
        this.context = context;
        mPanel = getLayoutInflater().inflate(R.layout.dialog_loading, null);
        title = mPanel.findViewById(R.id.title);
        int width;
        width = ScreenUtils.getScreenWidth() / 4;

        mPanel.setMinimumHeight(width);
        mPanel.setMinimumWidth(width);

        setContentView(mPanel);
    }


    public static Builder create(Context context) {
        return new Builder(context);
    }

    public static class Builder {

        private Context mContext;
        private CharSequence message;
        private boolean cancelaOnTouchOutside;
        private boolean cancelable;
        private boolean mFinishWhenDismiss;
        private OnCancelListener mCancelListener;

        public Builder(Context context) {
            mContext = context;
            cancelable = true;
            cancelaOnTouchOutside = true;
        }

        public Builder setMessage(CharSequence message) {
            this.message = message;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean cancelable) {
            this.cancelaOnTouchOutside = cancelable;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setOnCancelListener(OnCancelListener listener) {
            this.mCancelListener = listener;
            return this;
        }

        public Builder setFinishWhenDismiss(boolean finish) {
            this.mFinishWhenDismiss = finish;
            return this;
        }

        public LoadingDialog show() {
            LoadingDialog dialog = new LoadingDialog(mContext);
            dialog.setCanceledOnTouchOutside(cancelaOnTouchOutside);
            dialog.setCancelable(cancelable);
            dialog.setOnCancelListener(mCancelListener);
            dialog.show();
            if (mFinishWhenDismiss) {
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (mContext != null
                                && ((mContext instanceof Activity) && !((Activity) mContext).isFinishing())) {
                            ((Activity) mContext).finish();
                        }
                    }
                });
            }
            return dialog;
        }

        public LoadingDialog show(String msg) {
            LoadingDialog dialog = new LoadingDialog(mContext);
            dialog.setCanceledOnTouchOutside(cancelaOnTouchOutside);
            dialog.setCancelable(cancelable);
            dialog.setOnCancelListener(mCancelListener);
            dialog.show(msg);
            if (mFinishWhenDismiss) {
                dialog.setOnDismissListener(new OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (mContext != null
                                && ((mContext instanceof Activity) && !((Activity) mContext).isFinishing())) {
                            ((Activity) mContext).finish();
                        }
                    }
                });
            }
            return dialog;
        }
    }

    @Override
    public void show() {
        if (!isInvaid(context) && !isShowing()) {
            super.show();
        }
        title.setVisibility(View.VISIBLE);
        title.setText("请稍等...");
    }

    public void show(String msg) {
        if (!isInvaid(context) && !isShowing()) {
            super.show();
        }
        title.setVisibility(View.VISIBLE);
        title.setText(msg);
    }

    @Override
    public void dismiss() {
        if (!isInvaid(context)) {
            super.dismiss();
        }
    }

    public static boolean isInvaid(Context context) {
        return context == null || ((context instanceof Activity) && ((Activity) context).isFinishing());
    }

}
