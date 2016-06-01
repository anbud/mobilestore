package gui;

public abstract class Controller {

	private Gui gui;
	
	public void setGui(Gui gui) {
		this.gui = gui;
	}
	
	public Gui getGui() {
		return gui;
	}
	
}
