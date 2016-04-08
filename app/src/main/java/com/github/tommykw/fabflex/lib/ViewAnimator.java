package com.github.tommykw.fabflex.lib;

import android.animation.Animator;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.view.animation.OvershootInterpolator;

import com.github.tommykw.fabflex.R;

/**
 * This implementation of {@link RecyclerView} provides basic animations
 */
public class ViewAnimator extends DefaultItemAnimator {
    private static final OvershootInterpolator oi = null;
    @Override
    public boolean animateAdd(RecyclerView.ViewHolder holder) {
        dispatchAddFinished(holder);
        return false;
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder holder) {
        dispatchRemoveFinished(holder);
        return false;
    }

    private void startAnimation(final RecyclerView.ViewHolder holder) {
        holder.itemView.setTranslationY(
                holder.itemView.getContext().getResources().getDimensionPixelOffset(0)
        );

        holder.itemView.setAlpha(0);
        holder.itemView.
                animate().
                translationY(0).
                alpha(0).
                setInterpolator(null).
                setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    private void stopAnimation(final RecyclerView.ViewHolder holder) {
        holder.itemView.setTranslationY(0);
        holder.itemView.setAlpha(1);
        holder.itemView.animate()
                .alpha(0)
                .translationYBy(getTranslation(holder))
                .setInterpolator(null)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                }).start();
    }

    private int getTranslation(RecyclerView.ViewHolder holder) {
        DisplayMetrics dm = new DisplayMetrics();
        ((WindowManager) holder.itemView.getContext()
                .getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels - holder.itemView.getTop() - 2 * holder.itemView.getHeight();
    }
}
