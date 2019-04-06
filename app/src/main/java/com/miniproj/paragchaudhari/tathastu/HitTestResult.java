package com.miniproj.paragchaudhari.tathastu;



import android.support.annotation.Nullable;


import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.collision.RayHit;

public class HitTestResult extends RayHit  {
    @Nullable
    private Custom_node node;

    public HitTestResult() {
    }

    public void setNode(@Nullable Custom_node var1) {
        this.node = var1;
    }

    @Nullable
    public Custom_node getNode() {
        return this.node;
    }

    public void set(com.miniproj.paragchaudhari.tathastu.HitTestResult var1) {
        super.set(var1);
        this.setNode(var1.node);
    }

    public void reset() {
        super.reset();
        this.node = null;
    }
}
