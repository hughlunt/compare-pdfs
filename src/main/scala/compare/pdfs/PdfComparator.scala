package compare.pdfs

import java.awt.image.BufferedImage

import better.files.{File => ScalaFile}
import java.io.File

import scala.util.{Failure, Success, Try}


object PdfComparator {
  def compare(pdf1: ScalaFile, pdf2: ScalaFile): Try[Boolean] = {
    compare(pdf1.toJava, pdf2.toJava)
  }

  def compare(file1: File, file2: File): Try[Boolean] = {
    val tryPdfs = Seq(Try(new Pdf(file1)), Try(new Pdf(file2)))

    tryPdfs.filter(_.isFailure) match {
      case Failure(e) :: _ =>
        tryPdfs.filter(_.isSuccess).foreach(_.get.close())
        Failure(e)
      case _ =>
        val pdfs: Seq[Pdf] = tryPdfs.map(_.get)
        val Seq(pdf1Images: Seq[BufferedImage], pdf2Images: Seq[BufferedImage]) = pdfs.map(_.toImage)
        tryPdfs.foreach(_.get.close())
        pdf1Images.size == pdf2Images.size match {
          case true =>
            val comparisons = pdf1Images zip pdf2Images map (images => isSameImage(images._1, images._2))
            Success(!comparisons.contains(false))
          case _ => Success(false)
        }
    }
  }

  private def isSameImage(imgA: BufferedImage, imgB: BufferedImage): Boolean = {
    if (imgA.getWidth() == imgB.getWidth() && imgA.getHeight() == imgB.getHeight()) {
      val width: Int = imgA.getWidth
      val height: Int = imgA.getHeight

      val differentPixels =
        for (
          x <- 0 until width;
          y <- 0 until height
          if imgA.getRGB(x, y) != imgB.getRGB(x, y)
        ) yield (x,y)

      differentPixels.isEmpty
    }
    else false
  }
}