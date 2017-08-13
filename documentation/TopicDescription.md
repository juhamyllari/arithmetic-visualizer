## Topic Description

**Topic:**
A visualizer of arithmetic expressions. Expressions may consist of scalars, vectors and matrices. These can be added, subtracted, multiplied and raised to powers. Expressions will be evaluated step by step with intermediate results displayed. In particular, matrix-matrix multiplication and matrix-vector multiplication will be visually presented as a sequence of dot products.

**Actors:**
A single user.

**Functionality:**
* Creating arithmetic expressions via a graphical interface.
* Expressions may also be entered as strings. (This will only be implemented if time permits.)

**Class Diagram:**
* A class diagram is provided below.
* A few things to consider about the diagram:
  * The class UnaryNode is not strictly necessary (and the class UnaryOperation is not yet implemented). It may be removed at some stage.
  * Expression objects are not very useful at the moment. They will come in handy if inputting expressions as strings is implemented at some point.
![Alt Class Diagram](ClassDiagram.png "Class Diagram")
