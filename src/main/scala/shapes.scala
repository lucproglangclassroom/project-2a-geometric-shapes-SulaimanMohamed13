package edu.luc.cs.laufer.cs371.shapes

/** Shape algebra.
  * Rectangle and Ellipse are leaves.
  * Location translates a shape.
  * Group composes multiple shapes.
  */
enum Shape derives CanEqual:
  case Rectangle(width: Int, height: Int)
  case Ellipse(rx: Int, ry: Int)                  // radii
  case Location(x: Int, y: Int, shape: Shape)     // translate
  case Group(shapes: Shape*)                      // n-ary composition
end Shape
