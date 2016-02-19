package edu.mum.framework.ui;

import javafx.scene.Node;

public interface IGUIFactory {

	public abstract HeaderPane getHeaderPane();

	public abstract RightPane getRightPane();

	public abstract ContentPane getContentPane();

}