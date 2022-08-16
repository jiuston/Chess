package com.chess.chess.views;

import com.chess.chess.modelo.player.Player;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.GeneratedVaadinEmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.shared.Registration;
import lombok.Getter;

public class PlayerForm extends FormLayout {

  Binder<Player> binder = new BeanValidationBinder<>(Player.class);

  private Player player;
  TextField nickname = new TextField("NickName");
  EmailField email = new EmailField("Email");
  Button save = new Button("Save");
  Button delete = new Button("Delete");
  Button close = new Button("Cancel");

  public PlayerForm() {
    player =new Player();
    binder.bindInstanceFields(this);
    nickname.setPlaceholder("Add a nickName");
    email.setPlaceholder("Add the email");
    addClassName("player-form");
    setValueChanges(nickname);
    email.setValueChangeMode(ValueChangeMode.ON_CHANGE);
    email.addValueChangeListener(e ->save.setEnabled(binder.isValid()));
    add(email);
    add(nickname, createButtonsLayout());
  }

  private void setValueChanges(TextField... textFields){
    for (TextField t: textFields) {
      t.setValueChangeMode(ValueChangeMode.EAGER);
      t.addValueChangeListener(e ->save.setEnabled(binder.isValid()));
    }
  }

  private HorizontalLayout createButtonsLayout() {
    save.setEnabled(false);
    save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
    close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);
    save.addClickShortcut(Key.ENTER);
    close.addClickShortcut(Key.ESCAPE);
    save.addClickListener(event -> validateAndSave());
    delete.addClickListener(event -> fireEvent(new DeleteEvent(this, player)));
    close.addClickListener(event -> fireEvent(new CloseEvent(this)));
    return new HorizontalLayout(save, delete, close);
  }

  private void validateAndSave() {
    try {
      binder.writeBean(player);
      fireEvent(new SaveEvent(this, player));
    } catch (ValidationException e) {
      e.printStackTrace();
    }
  }

  public void setPlayer(Player player) {
    this.player = player;
    binder.readBean(player);
  }

  @Getter
  public static abstract class PlayerFormEvent extends ComponentEvent<PlayerForm>{
    private Player player;

    public PlayerFormEvent(PlayerForm source, Player player) {
      super(source, false);
      this.player=player;
    }
  }

  public static class SaveEvent extends PlayerFormEvent {
    SaveEvent(PlayerForm source, Player player) {
      super(source, player);
    }
  }

  public static class DeleteEvent extends PlayerFormEvent {
    DeleteEvent(PlayerForm source, Player player) {
      super(source, player);
    }

  }

  public static class CloseEvent extends PlayerFormEvent {
    CloseEvent(PlayerForm source) {
      super(source, null);
    }
  }

  public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType, ComponentEventListener<T> listener) {
    return getEventBus().addListener(eventType, listener);
  }

}