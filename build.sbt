name := """compare-pdfs"""

version := "1.0"

scalaVersion := "2.11.7"

// Change this to another test framework if you prefer
libraryDependencies ++= Seq(
  "org.specs2"                 %% "specs2-core"              % "3.8.5" % "test",
  "org.apache.pdfbox"           % "pdfbox"                   % "2.0.4",
  "com.github.pathikrit"       %% "better-files"             % "2.16.0"
)
