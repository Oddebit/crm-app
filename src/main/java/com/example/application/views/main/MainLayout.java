package com.example.application.views.main;

import com.example.application.service.SecurityService;
import com.example.application.views.contact.ContactListView;
import com.example.application.views.event.EventListView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

public class MainLayout extends AppLayout {
  private SecurityService securityService;
  
  public MainLayout(SecurityService securityService) {
    this.securityService = securityService;
    createHeader();
    createDrawer();
  }
  
  private void createHeader() {
    H1 logo = new H1("Vaadin CRM");
    logo.addClassNames("text-l", "m-m");
    
    Button logOutButton = new Button("Log out", event -> securityService.logout());
    HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logOutButton);
    header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
    header.expand(logo);
    header.setWidthFull();
    header.addClassNames("py-0", "px-m");
    
    addToNavbar(header);
  }
  
  private void createDrawer() {
    RouterLink contactLink = new RouterLink("Contacts", ContactListView.class);
    contactLink.setHighlightCondition(HighlightConditions.sameLocation());
    
    RouterLink eventLink = new RouterLink("Events", EventListView.class);
    contactLink.setHighlightCondition(HighlightConditions.sameLocation());
    
    addToDrawer(new VerticalLayout(
        contactLink,
        eventLink
    ));
    setDrawerOpened(false);
  }
}
