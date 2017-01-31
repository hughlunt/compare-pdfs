package compare.pdfs

import java.awt.image.BufferedImage
import java.io.File

import org.apache.pdfbox.pdmodel.PDDocument
import org.apache.pdfbox.rendering.PDFRenderer


class Pdf(file: File) {
  private val document: PDDocument = parsePdf(file)

  lazy val renderer = new PDFRenderer(document)

  private def parsePdf(file: File): PDDocument = {
    PDDocument.load(file)
  }

  def toImage: Seq[BufferedImage] = {
    for (
      page <- 0 until document.getNumberOfPages
    ) yield renderer.renderImage(page)
  }

  def close() = document.close()
}
