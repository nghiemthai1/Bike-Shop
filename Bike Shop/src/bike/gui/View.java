package bike.gui;

/**
 * An class to represent the various views, or pages, 
 * a user interface can display.
 * 
 * @author Eugene Koval
 */
public abstract class View {
	
	/** The frame that will display this view */
	private Display display;
	
	public Display getDisplay() {
		return display;
	}
	
	/**
	 * Adds the necessary components and takes the necessary steps to properly
	 * set up this view for the frame.
	 */
	public void visit(Display display) {
		this.display = display;
		addViewTransitions();
		displayView();
	}
	
	public abstract void addViewTransitions();
	
	public abstract void displayView();
}
