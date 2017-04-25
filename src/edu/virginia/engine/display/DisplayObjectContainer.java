package edu.virginia.engine.display;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class DisplayObjectContainer extends DisplayObject {

	private ArrayList<DisplayObject> children = new ArrayList<>();
	private ArrayList<DisplayObject> newChildren = new ArrayList<>();

	public DisplayObjectContainer(String id) {
		super(id);
	}

	public DisplayObjectContainer(String id, String fileName) {
		super(id, fileName);
	}
	
	@Override
	protected void drawInner(Graphics2D g) {
		for (DisplayObject child : children) {
			child.draw(g);
		}
	}
	
	@Override
	public void update(ArrayList<Integer> pressedKeys) {
		super.update(pressedKeys);
		for (int i = 0; i < children.size(); i++) {
			DisplayObject child = children.get(i);
			child.update(pressedKeys);
			if (!child.isAlive())
				children.remove(i--);
		}
		children.addAll(newChildren);
		newChildren.clear();
		children.sort(Comparator.comparingInt(DisplayObject::getzOrder));
	}
	
	public void addChildConcurrent(DisplayObject child) {
		newChildren.add(child);
		child.setParent(this);
	}
	
	public void addChild(DisplayObject child) {
		children.add(child);
		child.setParent(this);
	}
	
	public void addChild(int ind, DisplayObject child) {
		children.add(ind, child);
		child.setParent(this);
	}

	public void removeChild(DisplayObject child) {
		children.remove(child);
		child.setParent(null);
	}
	
	public void removeChild(int ind) {
		children.remove(ind).setParent(null);
	}
	
	public void removeAll() {
		for (DisplayObject child : children) {
			child.setParent(null);
		}
		children.clear();
	}
	
	public boolean contains(DisplayObject child) {
		return children.contains(child);
	}
	
	public DisplayObject getChild(int ind) {
		return children.get(ind);
	}
	
	public DisplayObject getChild(String id) {
		for (DisplayObject child : children) {
			if (child.getId().equals(id))
				return child;
		}
		return null;
	}
	
	public ArrayList<DisplayObject> getChildren() {
		return new ArrayList<>(children);
	}
	
	@Override
	public boolean collidesWith(DisplayObject other) {
		if (super.collidesWith(other))
			return true;
		for (DisplayObject child : children) {
			if (child.collidesWith(other))
				return true;
		}
		return false;
	}
}
