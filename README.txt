#Visual Stack Simulator

Summary of Program

When the program is executed, a panel will be presented. The first click will paint the TrainEngine component.

The next 5 clicks will paint 5 RailCar objects with respective numbers painted on them.

The the 7th click will paint the stack with 5 letter boxes represented with letters ascendingly increasing.

Selection of painted objects are indicated by a repaint of RED. To select an object, click within its bounds.

The RailCars can then be dragged and attached to each other in any particular order. They can also be attached to the back of the engine as a collective.

LIST MENU: 	AddFirst - adds the selected RailCar(s) to the FRONT of the line relative to the Engine
	  	AddLast  - adds the selected RailCar(s) to the BACK  of the line relative to the Engine
		RemoveFirst - removes the FIRST RailCar in line and places it elsewhere on the panel
		RemoveLast  - removes the LAST  RailCar in line and places it elsewhere on the panel

FILE MENU:	New - clears the panel. (essentially restarts the program)
		Exit - closes the program

STACK MENU:	Pop! - takes a letter element from the top of the stack and places it on the selected car.
		       If there are linked cars, it will pop it to the leading one.
		Push! - removes the first element from the selected car(s) and places it back on top of the 
			stack.
		

 
