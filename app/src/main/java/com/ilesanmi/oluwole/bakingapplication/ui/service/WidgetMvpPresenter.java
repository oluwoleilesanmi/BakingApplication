package com.ilesanmi.oluwole.bakingapplication.ui.service;

public interface WidgetMvpPresenter {

    void onViewPrepared(Boolean isInternetBound);

    void onAttach(WidgetMvpService service);
   void onDetach();
}
