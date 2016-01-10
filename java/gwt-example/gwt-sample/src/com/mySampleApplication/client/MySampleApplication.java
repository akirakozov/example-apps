package com.mySampleApplication.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;

public class MySampleApplication implements EntryPoint {

    public void onModuleLoad() {
        final Button button = new Button("Click me");
        final Label label = new Label();
        final TextBox textBox = new TextBox();

        button.addClickHandler(new ClickHandler() {
                public void onClick(ClickEvent event) {
                    MySampleApplicationService.App.getInstance().getMessage(
                                textBox.getValue(), new MyAsyncCallback(label));
                }
            });

        RootPanel.get("slot0").add(textBox);
        RootPanel.get("slot1").add(button);
        RootPanel.get("slot2").add(label);

        Element loading = DOM.getElementById("loading");
        RootPanel.getBodyElement().removeChild(loading);
    }

    private static class MyAsyncCallback implements AsyncCallback<String> {
        private Label label;

        public MyAsyncCallback(Label label) {
            this.label = label;
        }

        public void onSuccess(String result) {
            label.getElement().setInnerHTML(result);
        }

        public void onFailure(Throwable throwable) {
            label.setText("Failed to receive answer from server!");
        }
    }
}
