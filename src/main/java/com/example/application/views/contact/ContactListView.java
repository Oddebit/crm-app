package com.example.application.views.contact;

import com.example.application.data.Contact;
import com.example.application.service.CrmService;
import com.example.application.views.main.MainLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.PermitAll;

@PageTitle("Contacts | Vaadin CRM")
@Route(value = "", layout = MainLayout.class)
@PermitAll
public class ContactListView extends VerticalLayout {
  private CrmService service;
  
  Grid<Contact> grid = new Grid<>(Contact.class);
  TextField filterText = new TextField();
  ContactFormView form;
  
  public ContactListView(CrmService service) {
    this.service = service;
    addClassName("contact-list");
    setSizeFull();
    
    configureForm();
    configureGrid();
    
    add(createToolbar(), createContent());
    
    updateList();
    closeEditor();
  }
  
  private void configureForm() {
    form = new ContactFormView(service.findAllCompanies(), service.findAllStatuses());
    form.setWidth("25em");
    
    form.addListener(ContactFormView.SaveEvent.class, this::saveContact);
    form.addListener(ContactFormView.DeleteEvent.class, this::deleteContact);
    form.addListener(ContactFormView.CancelEvent.class, event -> closeEditor());
  }
  
  private void saveContact(ContactFormView.SaveEvent event) {
    service.saveContact(event.getContact());
    updateList();
    closeEditor();
  }
  
  private void deleteContact(ContactFormView.DeleteEvent event) {
    service.deleteContact(event.getContact());
    updateList();
    closeEditor();
  }
  
  private void configureGrid() {
    grid.addClassName("contact-grid");
    grid.setSizeFull();
    
    grid.setColumns("firstName", "lastName", "email");
    grid.addColumn(contact -> contact.getCompany().getName()).setHeader("Company");
    grid.addColumn(contact -> contact.getStatus().getName()).setHeader("Status");
    grid.getColumns().forEach(col -> col.setAutoWidth(true));
    
    grid.asSingleSelect().addValueChangeListener(line -> editContact(line.getValue()));
  }
  
  private Component createToolbar() {
    filterText.setPlaceholder("Filter by name...");
    filterText.setClearButtonVisible(true);
    filterText.setValueChangeMode(ValueChangeMode.LAZY);
    filterText.addValueChangeListener(event -> updateList());
    
    Button addContactButton = new Button("Add contact", event -> addContact());
    HorizontalLayout toolBar = new HorizontalLayout(filterText, addContactButton);
    toolBar.addClassName("toolbar");
    return toolBar;
  }
  
  private void addContact() {
    grid.asSingleSelect().clear();
    editContact(new Contact());
  }
  
  private Component createContent() {
    HorizontalLayout content = new HorizontalLayout(grid, form);
    content.addClassName("content");
    
    content.setFlexGrow(2, grid);
    content.setFlexGrow(1, form);
    content.setSizeFull();
    
    return content;
  }
  
  private void updateList() {
    grid.setItems(service.findAllContacts(filterText.getValue()));
  }
  
  private void closeEditor() {
    form.setContact(null);
    form.setVisible(false);
    removeClassName("editing");
  }
  
  private void editContact(Contact contact) {
    if (contact == null) {
      closeEditor();
    } else {
      form.setContact(contact);
      form.setVisible(true);
      addClassName("editing");
    }
  }
  
}
