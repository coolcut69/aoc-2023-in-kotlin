fun Array<CharArray>.isSafe(at: Point2D) =
    at.y in this.indices && at.x in this[at.y].indices

operator fun Array<CharArray>.set(at: Point2D, c: Char) {
    this[at.y][at.x] = c
}

operator fun Array<CharArray>.get(at: Point2D): Char =
    this[at.y][at.x]
