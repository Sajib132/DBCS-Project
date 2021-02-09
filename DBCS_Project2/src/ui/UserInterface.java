package ui;

import actions.RegistrationSpecific;

import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import ui.UserInterface.TabNames;

public class UserInterface {
	/**********************************************************************************************

	Class Attributes
	
	**********************************************************************************************/
	
	// Attributes used to establish the display and control panel within the window provided to us
	// This values are passed in when this class is instantiated
	private static double CONTROL_PANEL_HEIGHT;
	private static double WINDOW_HEIGHT;
	private static double WINDOW_WIDTH;
	
	// These attributes put a graphical frame around the portion of the window that receives the
	// text for the various tabs. These are for decoration and perform no other function.
	private Rectangle rect_outer;
	private Rectangle rect_middle;
	private Rectangle rect_inner;
	
	// This is the root of the user interface
	private Group theRoot = null;
	
	//---------------------------------------------------------------------------------------------
		// These Tab, Group, and action classes define each of the tabs used by this user interface
		public enum TabNames {STUDENT_MASTER, SUBJECT_MASTER}
		
		private Tab studentMasterTab = new Tab("Student Master");
		private Group studentMasterControls = new Group();
		public RegistrationSpecific theStudentMasterActions = new RegistrationSpecific(studentMasterControls, 6, 40, TabNames.STUDENT_MASTER);
	
	
		
		//---------------------------------------------------------------------------------------------
		// The TabPane for the user interface
		
		private TabPane tabPane;
		
		/**********
		 * This constructor establishes the user interface with the needed tabs and widgets
		 * 
		 * @param r		The root of the widgets
		 * @param t		The TabPane we are to use
		 * @param h		The height we should use for the application window
		 * @param w		The width of the application window
		 * @param cph	The location of the controls widgets
		 */
		public UserInterface(Group r, TabPane t, double h, double w, double cph) {
			
			/**********************************************************************************************

			Class Attributes
			
			**********************************************************************************************/
			
			// Set the Stage boundaries to the visual bounds so the window does not totally fill the screen 
			WINDOW_WIDTH = w;
			WINDOW_HEIGHT = h;
			CONTROL_PANEL_HEIGHT = cph;
			
			tabPane = t;
			theRoot = r;
			
			studentMasterControls.setVisible(false);
			
			
			// These attributes put a graphical frame around the portion of the window that holds the
			// information this knowledge management tool hold, displays, and manipulates.  These
			// graphical elements give a three-dimensional look to the user interface.
			rect_outer =  new Rectangle(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
			rect_middle = new Rectangle(5, 5, WINDOW_WIDTH-10, CONTROL_PANEL_HEIGHT-15);
			rect_inner =  new Rectangle(6, 6, WINDOW_WIDTH-12, CONTROL_PANEL_HEIGHT-17);
			
			// Set the fill colors for the border frame to give that three-dimensional look
			rect_outer.setFill(Color.LIGHTGRAY);
			rect_middle.setFill(Color.BLACK);
			rect_inner.setFill(Color.WHITE);
	        
			// Use a BorderPane to hold the various tabs
	        BorderPane borderPane = new BorderPane();
	        borderPane.setLayoutX(9);							// The left edge
	        borderPane.setLayoutY(9);							// The upper edge
	        borderPane.setPrefWidth(WINDOW_WIDTH-18);			// The right edge
	        borderPane.setPrefHeight(CONTROL_PANEL_HEIGHT-24);	// The lower edge
	        borderPane.setCenter(tabPane);						// Place the tab pane in the center
	        
	        // Do not allow the user to close a tab 
	        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
	        
	        // Sets up the basic elements for the studentMaster tab 
	        studentMasterTab.setOnSelectionChanged((event) -> {studentMasterChanged() ; });
	       
	        
			//theLifeCycleActions = new LifeCycleSpecific(lifeCyclesControls, 6, 40, TabNames.LIFECYCLES);
	        tabPane.getTabs().addAll(studentMasterTab);
	        
	        // Place the border pane with the table panel into the window
			theRoot.getChildren().addAll(rect_outer, rect_middle, rect_inner, borderPane);
			
			theRoot.getChildren().addAll(studentMasterControls);
	       
		}
		

		//---------------------------------------------------------------------------------------------
		//
		// The following private methods are used to manage the various kinds of information that this
		// tools supports from Life Cycles through to Defects
		//
		//---------------------------------------------------------------------------------------------

		/**********
		 * Whenever the user enters or leaves the studentMaster Tab, this method is called
		 */
		private void studentMasterChanged() {
			// See if this is a deactivate or an activate event
			
			if (!studentMasterTab.isSelected()) {
				
				// This is a deactivate event, so hide this tab
				studentMasterControls.setVisible(false);
				return;
			}

			// This is an activate event, so make these controls visible
			studentMasterControls.setVisible(true);
		}
		
	
}
