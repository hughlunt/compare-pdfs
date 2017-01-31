## compare-pdfs

### Overview

compare-pdfs is a library to compare if two pdfs look the same.

### Examples

##### Using Java Files  
```scala
import compare.pdfs.PdfComparator._
import java.io.File

val file1 = new File("path/to/file1")
val file2 = new File("path/to/file2")

val lookTheSame: Try[Boolean] = compare(file1, file2)
```

##### Using better-files library:
```scala
import compare.pdfs.PdfComparator._
import better.files._

val betterFile1 = File("path/to/file1")
val betterFile2 = File("path/to/file2")

val lookTheSame: Try[Boolean] = compare(file1, file2)

lookTheSame match {
  case Success(true)  => println("The files look the same")
  case Success(false) => println("The files do not look the same")
  case Failure(e)     => println("One or more of the files could not be parsed as a pdf") 
}
```


### Getting compare-pdfs

compare-pdfs is currently in development.

### Detailed Information

compare-pdfs was inspired by a recent regression test issue in comparing documents and a desire to encapsulate the underlying java libraries.
Whilst it is fairly straightforward to test whether two documents are identical, where metadata is added to a document such as a PDF, it can be useful for regression reasons to determine whether two documents look the same. This library converts PDF documents to images and then performs a pixel by pixel comparison to determine whether they look identical.

* Uses Apache PDFBox to parse the pdf document.
* Converts each parsed document to a list of images (one image per page).
* Compares each image for a document against the corresponding image for the other document.
* Returns `Scala.util.Success(true)` if all images match, `Scala.util.Success(false)` otherwise.
* Returns `Scala.util.Failure(IOException)` if the file cannot be parsed as a pdf.

### Known Issues

None at present.

### Future Work

* Add metadata comparison.
* Test with more complex PDF Documents.
* Release it.

### Related Projects

* [Better-Files](https://github.com/pathikrit/better-files) Provided inspiration for wrapping up Java libraries and file content comparison.

### Copyright and License

All code is available to you under the MIT license, available at
http://opensource.org/licenses/mit-license.php and also in the
[LICENSE](LICENSE) file.

Copyright Hugh Lunt, 2017.

### No Warranty

> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
> EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
> MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
> NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
> BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
> ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
> CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
> SOFTWARE.