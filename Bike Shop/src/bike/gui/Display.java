package bike.gui;

/**
 * Any Frame that implements this interface will be able to change view states
 * determined by subclasses of the View class.
 * 
 * @author Eugene Koval
 * @see View
 */
public interface Display {
	
	/**
	 * Sets the frame's view to the view passed as the argument.
	 * 
	 * @param view the frame's new view.
	 */
	public void accept(View view);
}
