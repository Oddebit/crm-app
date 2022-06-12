package com.example.application.views.event;

import com.example.application.views.main.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Events | Vaadin CRM")
@Route(value = "/events", layout = MainLayout.class)
@PermitAll
public class EventListView extends VerticalLayout {
  
  public EventListView() {
    addClassName("event-list");
    setSizeFull();
    
    setAlignItems(Alignment.CENTER);
    setJustifyContentMode(JustifyContentMode.CENTER);
    
    add(
        new H2("Work in progress...")
    );
  }
}
