package com.miniproj.paragchaudhari.tathastu;

import com.google.ar.core.Config;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ux.ArFragment;

public class Custom_arFragment extends ArFragment {

    @Override
    protected Config getSessionConfiguration(Session session)
    {
        getPlaneDiscoveryController().setInstructionView(null);
        Config config = super.getSessionConfiguration(session);
        config.setFocusMode(Config.FocusMode.AUTO);
        return config;

    }
}
