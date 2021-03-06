package market.zy.com.myapplication.activity.comments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import market.zy.com.myapplication.R;
import market.zy.com.myapplication.activity.BaseActivity;
import market.zy.com.myapplication.adapter.recyclerviewAdapter.CommentsAdapter;
import market.zy.com.myapplication.ui.support.DividerItemDecoration;

/**
 * Created by root on 16-5-10.
 */
public class CommentsActivity extends BaseActivity {
    @Bind(R.id.comments_toolbar)
    protected Toolbar mToolbar;

    @Bind(R.id.comments_recyclerview)
    protected RecyclerView mRecyclerView;

    private CommentsAdapter mRAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        ButterKnife.bind(this);

        setOnBackTwiceToTrue();

        mRAdapter = new CommentsAdapter(this);

        initView();
    }

    private void initView() {
        setUpToolbar();
        setUpRecyclerView();
    }

    private void setUpToolbar() {
        mToolbar.setTitle(R.string.comments);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setUpRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mRAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, 1));
        final LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisiableItemPosition =  layoutManager.findLastVisibleItemPosition();
                if (lastVisiableItemPosition == mRAdapter.getItemCount() - 2) {
                    mRAdapter.setShowLoadMore(true);
                } else if (lastVisiableItemPosition == mRAdapter.getItemCount() - 1
                        && mRAdapter.isShowingLoadMore()) {
                    mRAdapter.setShowLoadMore(true);
                } else {
                    mRAdapter.setShowLoadMore(false);
                }
            }
        });
    }
}
