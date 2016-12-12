package io.codehouse.stocksolution;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.codehouse.stocksolution.adaptors.ProductsAdaptor;
import io.codehouse.stocksolution.misc.DividerItemDecoration;
import it.sephiroth.android.library.floatingmenu.FloatingActionItem;
import it.sephiroth.android.library.floatingmenu.FloatingActionMenu;

public class Products extends AppCompatActivity {
    private List<io.codehouse.stocksolution.adaptors.Products> productsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private io.codehouse.stocksolution.adaptors.ProductsAdaptor mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        FloatingActionItem item1 = new FloatingActionItem.Builder(0)
                .withResId(R.drawable.ic_action_action_search)
                .withDelay(0)
                .build();
        FloatingActionMenu mFloatingMenu = new FloatingActionMenu
                .Builder(this)
                .addItem(item1)
                .withGravity(FloatingActionMenu.Gravity.CENTER_HORIZONTAL | FloatingActionMenu.Gravity.BOTTOM)
                .withDirection(FloatingActionMenu.Direction.Vertical)
                .animationDuration(300)
                .animationInterpolator(new OvershootInterpolator())
                .visible(true)
                .build();

        mFloatingMenu.setOnItemClickListener(new FloatingActionMenu.OnItemClickListener() {
            @Override
            public void onItemClick(FloatingActionMenu floatingActionMenu, int i) {

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        mAdapter = new ProductsAdaptor(productsList);

        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnItemTouchListener(new Products.RecyclerTouchListener(getApplicationContext(), recyclerView, new Products.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                io.codehouse.stocksolution.adaptors.Products movie = productsList.get(position);
                Toast.makeText(getApplicationContext(), movie.getName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }


    public interface ClickListener {
        void onClick(View view, int position);
        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Products.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Products.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

}
