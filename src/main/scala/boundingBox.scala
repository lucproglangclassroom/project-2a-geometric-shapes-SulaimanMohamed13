package edu.luc.cs.laufer.cs371.shapes

import Shape.*

/** Compute the axis-aligned bounding box for any Shape.
  * Always returns a Location(0, 0, Rectangle(width, height)).
  */
object boundingBox:

  private case class Coords(minX: Int, minY: Int, maxX: Int, maxY: Int):
    def widen(that: Coords): Coords =
      Coords(
        math.min(this.minX, that.minX),
        math.min(this.minY, that.minY),
        math.max(this.maxX, that.maxX),
        math.max(this.maxY, that.maxY)
      )

  private def coords(s: Shape): Coords = s match
    case Rectangle(w, h) => Coords(0, 0, w, h)
    case Ellipse(rx, ry) => Coords(-rx, -ry, rx, ry)
    case Location(x, y, shape) =>
      val c = coords(shape)
      Coords(c.minX + x, c.minY + y, c.maxX + x, c.maxY + y)
    case Group(shapes*) =>
      val cs = shapes.map(coords)
      cs.tail.foldLeft(cs.head)(_.widen(_))

  /** Normalize the bounding box to origin (0, 0). */
  def apply(s: Shape): Location =
    val c = coords(s)
    val width  = c.maxX - c.minX
    val height = c.maxY - c.minY
    // Return the bounding box with its actual position
    Location(c.minX, c.minY, Rectangle(width, height))
end boundingBox