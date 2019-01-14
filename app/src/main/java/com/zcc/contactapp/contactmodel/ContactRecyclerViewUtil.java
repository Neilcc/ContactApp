package com.zcc.contactapp.contactmodel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zcc.contactapp.R;
import com.zcc.contactapp.base.recyclerviewbase.BaseLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.AvatarLinearAdapter;
import com.zcc.contactapp.contactmodel.adapters.DetailLinearAdapter;
import com.zcc.contactapp.utils.DebugLog;
import com.zcc.contactapp.utils.ScreenUtil;

import static android.support.v7.widget.RecyclerView.NO_POSITION;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_DRAGGING;
import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

class ContactRecyclerViewUtil {
    private static final String TAG = ContactRecyclerViewUtil.class.getSimpleName();
    private static final float FLING_MS_PER_INCH = 100f;
    private Context mContext;
    private RecyclerView mAvatarRecyclerView;
    private RecyclerView mDetailRecyclerView;
    private AvatarLinearAdapter mAvatarAdapter;
    private DetailLinearAdapter mDetailAdapter;
    private LinearLayoutManager mAvatarLayoutManager;
    private LinearLayoutManager mDetailLayoutManager;
    private float mAvatarRecyclerViewLeftPadding = 0.0f;
    private float mDetailItemHeight = 0.0f;
    private float mAvatarItemWidth = 0.0f;
    private float mAvatarDxToDetailDyRatio = 0.0f;
    private boolean mAvatarScrollTriggerFlag = false;
    private boolean mDetailScrollTriggerFlag = false;
    private boolean isAdjustingAvatarSnap = false;
    private boolean isAdjustingDetailSnap = false;
    private FlingSnapHelper mAvatarFlingSnapHelper;
    private FlingSnapHelper mDetailFlingSnapHelper;

    ContactRecyclerViewUtil(RecyclerView mAvatarRecyclerView,
                            RecyclerView mDetailRecyclerView) {
        this.mContext = mAvatarRecyclerView.getContext();
        this.mAvatarRecyclerView = mAvatarRecyclerView;
        this.mDetailRecyclerView = mDetailRecyclerView;
    }

    void init(AvatarLinearAdapter avatarLinearAdapter, DetailLinearAdapter detailLinearAdapter) {
        mAvatarAdapter = avatarLinearAdapter;
        mAvatarLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false);
        mAvatarRecyclerView.setLayoutManager(mAvatarLayoutManager);
        mAvatarRecyclerView.setAdapter(mAvatarAdapter);
        mAvatarRecyclerView.setPadding((int) getAvatarLayoutPaddingPx(), 0,
                (int) getAvatarLayoutPaddingPx(), 0);
        mAvatarAdapter.setOnItemClickListener(new BaseLinearAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                smoothScrollTo(pos);
            }
        });
        mDetailAdapter = detailLinearAdapter;
        mDetailLayoutManager = new LinearLayoutManager(mContext,
                LinearLayoutManager.VERTICAL, false);
        mDetailRecyclerView.setLayoutManager(mDetailLayoutManager);
        mDetailRecyclerView.setAdapter(mDetailAdapter);
        ViewGroup.LayoutParams lp = mDetailRecyclerView.getLayoutParams();
        lp.height = (int) getDetailLayoutHeightPx();
    }

    void bindScroll() {
        mAvatarRecyclerView.addOnScrollListener(new ContactAvatarRecyclerViewScrollListener());
        mDetailRecyclerView.addOnScrollListener(new ContactDetailRecyclerViewScrollListener());
        mAvatarFlingSnapHelper = new FlingSnapHelper(FLING_MS_PER_INCH);
        mAvatarFlingSnapHelper.attachToRecyclerView(mAvatarRecyclerView);
        mAvatarFlingSnapHelper.setSnapFlingListener(new FlingSnapHelper.OnSnapFlingListener() {
            @Override
            public boolean onSnapFling(int position) {
                smoothScrollTo(position);
                return true;
            }
        });
        mDetailFlingSnapHelper = new FlingSnapHelper(FLING_MS_PER_INCH
                * getAvatarItemWidthPx() / getDetailLayoutHeightPx());
        mDetailFlingSnapHelper.attachToRecyclerView(mDetailRecyclerView);
        mDetailFlingSnapHelper.setSnapFlingListener(new FlingSnapHelper.OnSnapFlingListener() {
            @Override
            public boolean onSnapFling(int position) {
                smoothScrollTo(position);
                return true;
            }
        });
    }

    private void smoothScrollTo(int position) {
        RecyclerView.SmoothScroller avatarScroller
                = mAvatarFlingSnapHelper.createScroller(mAvatarLayoutManager);
        avatarScroller.setTargetPosition(position);
        RecyclerView.SmoothScroller detailScroller
                = mDetailFlingSnapHelper.createScroller(mDetailLayoutManager);
        detailScroller.setTargetPosition(position);
        isAdjustingDetailSnap = true;
        isAdjustingAvatarSnap = true;
        mAvatarLayoutManager.startSmoothScroll(avatarScroller);
        mDetailLayoutManager.startSmoothScroll(detailScroller);
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

    private int findAvatarScrollTargetPos() {
        int pos1 = mAvatarLayoutManager.findFirstCompletelyVisibleItemPosition();
        if (pos1 != NO_POSITION) {
            return NO_POSITION;
        }
        pos1 = mAvatarLayoutManager.findFirstVisibleItemPosition();
        int pos2 = mAvatarLayoutManager.findLastVisibleItemPosition();
        int retPos = pos1;
        float distance = Float.MAX_VALUE;
        for (int i = pos1; i <= pos2; i++) {
            View child = mAvatarLayoutManager.getChildAt(i);
            if (child == null) {
                break;
            }
            int childWidth = child.getMeasuredWidth();
            int childDistance = (int) Math.abs((child.getX() + childWidth / 2)
                    - (ScreenUtil.getDisplayWidth() / 2));
            if (childDistance < distance) {
                distance = childDistance;
                retPos = i;
            }
        }
        return retPos;
    }

    private int findDetailScrollTargetPos() {
        if (mDetailLayoutManager.findFirstCompletelyVisibleItemPosition() != NO_POSITION) {
            return NO_POSITION;
        }
        int pos1 = mDetailLayoutManager.findFirstVisibleItemPosition();
        View child = mDetailLayoutManager.findViewByPosition(pos1);
        if (child == null) {
            return NO_POSITION;
        }
        float childY = child.getY();
        if (Math.abs(childY) > getDetailLayoutHeightPx() / 2) {
            return pos1 + 1;
        } else {
            return pos1;
        }
    }

    private class ContactAvatarRecyclerViewScrollListener extends RecyclerView.OnScrollListener {

        private float delta = 0.0f;

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                mAvatarScrollTriggerFlag = false;
                isAdjustingAvatarSnap = false;
                int targetPos = findAvatarScrollTargetPos();
                if (targetPos != NO_POSITION) {
                    DebugLog.d(TAG, "avatar snap to pos" + targetPos);
                    isAdjustingDetailSnap = true;
                    mDetailLayoutManager.smoothScrollToPosition(mDetailRecyclerView, null, targetPos);
                    isAdjustingAvatarSnap = true;
                    mAvatarLayoutManager.smoothScrollToPosition(mAvatarRecyclerView, null, targetPos);
                } else {
                    int pos = mAvatarLayoutManager.findFirstCompletelyVisibleItemPosition();
                    mAvatarAdapter.updateSelected(pos);
                }
            } else if (newState == SCROLL_STATE_DRAGGING) {
                mAvatarScrollTriggerFlag = true;
            }
            DebugLog.e(TAG, "avatar state: " + newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mDetailScrollTriggerFlag && !isAdjustingAvatarSnap && (dx != 0)) {
                int detailDy = cacDy(dx);
                mDetailRecyclerView.scrollBy(0, detailDy);
                DebugLog.d(TAG, "on avatar control");
            }
            DebugLog.d(TAG, "on avatar scroll" + dx + "\t" + dy);
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

        ContactDetailRecyclerViewScrollListener() {
            super();
        }

        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == SCROLL_STATE_IDLE) {
                isAdjustingDetailSnap = false;
                mDetailScrollTriggerFlag = false;
                int targetPos = findDetailScrollTargetPos();
                if (targetPos != NO_POSITION) {
                    DebugLog.d(TAG, "detail snap to pos : " + targetPos);
                    isAdjustingDetailSnap = true;
                    mDetailLayoutManager.smoothScrollToPosition(mDetailRecyclerView, null, targetPos);
                    isAdjustingAvatarSnap = true;
                    mAvatarLayoutManager.smoothScrollToPosition(mAvatarRecyclerView, null, targetPos);
                }
            } else if (newState == SCROLL_STATE_DRAGGING) {
                mDetailScrollTriggerFlag = true;
            }
            DebugLog.e(TAG, "detail state: " + newState);
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            if (!mAvatarScrollTriggerFlag && !isAdjustingDetailSnap && (dy != 0)) {
                int avatarDx = cacDx(dy);
                mAvatarRecyclerView.scrollBy(avatarDx, 0);
                DebugLog.d(TAG, "on detail control");
            }
            DebugLog.d(TAG, "on detail scroll" + dx + "\t" + dy);
        }

        private int cacDx(int dy) {
            float avatarDx = getAvatarDxByDetailDy(dy) + delta;
            int avatarDxInt = (int) avatarDx;
            delta = avatarDx - avatarDxInt;
            return avatarDxInt;
        }
    }
}
