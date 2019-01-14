package com.zcc.contactapp.contactmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.contactapp.R;
import com.zcc.contactapp.base.recyclerviewbase.BaseLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.AvatarLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.DetailLinearAdapter;
import com.zcc.contactapp.utils.ScreenUtil;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class ContactRecyclerViewUtil {

    private RecyclerView mAvatarRecyclerView;
    private RecyclerView mDetailRecyclerView;
    private AvatarLinearAdapter mAvatarAdapter;
    private DetailLinearAdapter mDetailAdapter;
    private Context mContext;
    private float mAvatarRecyclerViewLeftPadding = 0;
    private float mDetailItemHeight = 0;
    private float mAvatarItemWidth = 0;
    private boolean mAvatarScrollTriggerFlag = false;
    private boolean mDetailScrollTriggerFlag = false;
    private boolean mAjustDpFlag = false;
    private float mAvatarDxToDetailDyRatio = 0.0f;
    private OrientationHelper mDetailOritentionHelper;
    private OrientationHelper mAvatarOritentionHelper;

    public ContactRecyclerViewUtil(RecyclerView mAvatarRecyclerView,
                                   RecyclerView mDetailRecyclerView) {
        this.mContext = mAvatarRecyclerView.getContext();
        this.mAvatarRecyclerView = mAvatarRecyclerView;
        this.mDetailRecyclerView = mDetailRecyclerView;
    }

    public void init(AvatarLinearAdapter avatarLinearAdapter, DetailLinearAdapter detailLinearAdapter) {
        mAvatarAdapter = avatarLinearAdapter;
        mDetailAdapter = detailLinearAdapter;
        LinearLayoutManager avatarLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false);
        LinearLayoutManager detailLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL, false);
        mAvatarRecyclerView.setLayoutManager(avatarLayoutManager);
        mDetailRecyclerView.setLayoutManager(detailLayoutManager);


        mAvatarRecyclerView.setPadding((int) getAvatarLayoutPaddingPx(), 0,
                (int) getAvatarLayoutPaddingPx(), 0);
        mAvatarAdapter.setOnItemClickListener(new BaseLinearAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                mAvatarRecyclerView.smoothScrollToPosition(pos);
            }
        });

        ViewGroup.LayoutParams lp = mDetailRecyclerView.getLayoutParams();
        lp.height = (int) getDetailLayoutHeightPx();
    }

    private float getAvatarLayoutPaddingPx() {
        if (mAvatarRecyclerViewLeftPadding == 0) {
            mAvatarRecyclerViewLeftPadding = (ScreenUtil.getDisplayWidth() - getAvatarItemWidthPx()) / 2;
        }
        return mAvatarRecyclerViewLeftPadding;
    }

    private float getAvatarItemWidthPx() {
        if (mAvatarItemWidth == 0) {
            mAvatarItemWidth = (int) (mContext.getResources().getDimension(R.dimen.avatar_width)
                    + 2 * mContext.getResources().getDimension(R.dimen.middle_padding));
        }
        return mAvatarItemWidth;
    }

    private float getDetailLayoutHeightPx() {
        if (mDetailItemHeight == 0) {
            float titleHeight = mContext.getResources().getDimension(R.dimen.title_height);
            float avatarLayoutHeight = mContext.getResources().getDimension(R.dimen.avatar_width)
                    + 2 * mContext.getResources().getDimension(R.dimen.big_padding);
            mDetailItemHeight = (int) (ScreenUtil.getDisplayHeight()
                    - titleHeight - avatarLayoutHeight);
        }
        return mDetailItemHeight;
    }

    public void bindScroll() {
//        mAvatarOritentionHelper = OrientationHelper.createHorizontalHelper(mAvatarRecyclerView.getLayoutManager());
        mAvatarRecyclerView.addOnScrollListener(new ContactAvatarRecyclerViewScrollListener());
//        mDetailOritentionHelper = OrientationHelper.createVerticalHelper(mDetailRecyclerView.getLayoutManager());
        mDetailRecyclerView.addOnScrollListener(new ContactDetailRecyclerViewScrollListener());
    }

    private float getAvatarDxByDetailDy(int detailDY) {
        if (mAvatarDxToDetailDyRatio == 0.0f) {
            mAvatarDxToDetailDyRatio = getAvatarItemWidthPx() * 1.0f / getDetailLayoutHeightPx();
        }
        return detailDY * mAvatarDxToDetailDyRatio;
    }

    private float getDetailDyByAvatarDx(int avatarDx) {
        if (mAvatarDxToDetailDyRatio == 0.0f) {
            mAvatarDxToDetailDyRatio = getAvatarItemWidthPx() * 1.0f / getDetailLayoutHeightPx();
        }
        return avatarDx / mAvatarDxToDetailDyRatio;
    }

    /**
     * copied from SnapHelper
     *
     * @param layoutManager
     * @param helper
     * @return
     */
    private View findCenterView(RecyclerView.LayoutManager layoutManager,
                                OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        final int center;
        if (layoutManager.getClipToPadding()) {
            center = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
        } else {
            center = helper.getEnd() / 2;
        }
        int absClosest = Integer.MAX_VALUE;

        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);
            int childCenter = helper.getDecoratedStart(child)
                    + (helper.getDecoratedMeasurement(child) / 2);
            int absDistance = Math.abs(childCenter - center);

            /** if child center is closer than previous closest, set it as closest  **/
            if (absDistance < absClosest) {
                absClosest = absDistance;
                closestChild = child;
            }
        }
        return closestChild;
    }

    private class ContactAvatarRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

        private float delta = 0.0f;

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                mAvatarScrollTriggerFlag = false;
                Log.d("zcc", "avatar idle");
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mDetailScrollTriggerFlag && recyclerView.isAttachedToWindow()) {
                mAvatarScrollTriggerFlag = true;
                int detailDy = cacDy(dx);
                mDetailRecyclerView.smoothScrollBy(0, detailDy);
            }
            Log.d("zcc", "avatar scroll \t" + dx + "\t"
                    + dy
            );
        }

        private int cacDy(int dx) {
            float detailDy = getDetailDyByAvatarDx(dx);
            detailDy += delta;
            int detailDyInt = (int) detailDy;
            delta = detailDy - detailDyInt;
            return detailDyInt;
        }
    }

    private class ContactDetailRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
        private float delta = 0.0f;

        public ContactDetailRecyclerViewScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                mDetailScrollTriggerFlag = false;
//                View target = findCenterView(recyclerView.getLayoutManager(), mDetailOritentionHelper);
//                if (target != null) {
//                    int pos = recyclerView.getLayoutManager().getPosition(target);
//                    recyclerView.smoothScrollToPosition(pos);
//                    mAvatarRecyclerView.smoothScrollToPosition(pos);
//                }
                Log.d("zcc", "detail idle");
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mAvatarScrollTriggerFlag) {
                mDetailScrollTriggerFlag = true;
                int avatarDx = cacDx(dy);
                mAvatarRecyclerView.smoothScrollBy(avatarDx, 0);
            }
            Log.d("zcc", "detail scroll \t" + dx + "\t"
                    + dy
            );
        }

        private int cacDx(int dy) {
            float avatarDx = getAvatarDxByDetailDy(dy) + delta;
            int avatarDxInt = (int) avatarDx;
            delta = avatarDx - avatarDxInt;
            return avatarDxInt;
        }

    }

}
