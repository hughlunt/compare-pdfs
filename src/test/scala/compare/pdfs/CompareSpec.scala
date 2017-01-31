package compare.pdfs

import better.files._
import compare.pdfs.PdfComparator._
import org.specs2.mutable.Specification

import scala.util.{Failure, Success}

class CompareSpec extends Specification {

  "Given two identical pdfs, testing for equality" should {
    "return true when comparing files" in {
      file1 === file1Copy
    }

    "return true when comparing how the pdfs look" in {
      val result = compare(file1, file1Copy)
      result.isSuccess must beTrue
      result match {
        case Success(bool) => bool must beTrue
        case Failure(e) => throw new IllegalStateException("Should not have reached here")
      }
    }
  }

  "Given two non-identical pdfs that look the same, testing for equality" should {
    "return false when comparing files" in {
      file1 === file2 must beFalse
    }

    "return true when comparing how the pdfs look" in {
      val result = compare(file1, file2)
      result.isSuccess must beTrue
      result match {
        case Success(b) => b must beTrue
        case Failure(e) => throw new IllegalStateException("Should not have reached here")
      }
    }
  }

  "Given two non-identical pdfs that do not look the same, testing for equality" should {
    "return false when comparing files" in {
      file1 === file3 must beFalse
    }

    "return false when comparing how the pdfs look" in {
      val result = compare(file1, file3)
      result.isSuccess must beTrue
      result match {
        case Success(b) => b must beFalse
        case Failure(e) => throw new IllegalStateException("Should not have reached here")
      }
    }
  }

  "Given a pdf and a non-pdf file, testing for equality" should {
    "return false when comparing files" in {
      file1 === nonPdf must beFalse
    }

    "return failure when comparing look of files" in {
      val result = compare(file1, nonPdf)
      result.isFailure must beTrue
    }
  }

  "Given a multi page pdf and a similar looking pdf, testing for equality" should {
    "return false when comparing files" in {
      twoPagePdf === twoPagePdfWithWhitespace must beFalse
    }

    "return false when comparing look of files" in {
      val result = compare(twoPagePdf, twoPagePdfWithWhitespace)
      result.isSuccess must beTrue
      result match {
        case Success(b) => b must beTrue
        case Failure(e) => throw new IllegalStateException("Should not have reached here")
      }
    }
  }

  "Given a multi page pdf and a pdf with an extra page, testing for equality" should {
    "return false when comparing files" in {
      twoPagePdf === threePagePdf must beFalse
    }

    "return false when comparing look of files" in {
      val result = compare(twoPagePdf, threePagePdf)
      result.isSuccess must beTrue
      result match {
        case Success(b) => b must beFalse
        case Failure(e) => throw new IllegalStateException("Should not have reached here")
      }
    }
  }

  val file1 = File("./src/test/scala/resources/pdf1.pdf")
  val file1Copy = File("./src/test/scala/resources/pdf1_copy.pdf")
  val file2 = File("./src/test/scala/resources/pdf2_like_pdf1.pdf")
  val file3 = File("./src/test/scala/resources/pdf3.pdf")
  val nonPdf = File("./src/test/scala/resources/non_pdf.txt")
  val twoPagePdf = File("./src/test/scala/resources/2pagePdf.pdf")
  val twoPagePdfWithWhitespace = File("./src/test/scala/resources/2pagePdf_with_whitespace.pdf")
  val threePagePdf = File("./src/test/scala/resources/3pagePdf.pdf")
}
