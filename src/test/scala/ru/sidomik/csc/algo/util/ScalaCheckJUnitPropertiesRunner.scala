package ru.sidomik.csc.algo.util

import org.junit.runner.Description
import org.junit.runner.notification.{Failure, RunNotifier}
import org.scalacheck.Test.Parameters
import org.scalacheck.util.ConsoleReporter
import org.scalacheck.{Test => SchkTest, _}

class ScalaCheckJUnitPropertiesRunner(suiteClass: java.lang.Class[Properties]) extends org.junit.runner.Runner {

  private val properties = suiteClass.newInstance

  lazy val getDescription: Description = createDescription(properties)

  /**
    * Create a description
    */
  private def createDescription(props: Properties): Description = {
    val description = Description.createSuiteDescription(props.name)
    props.properties.foreach(p => Description.createTestDescription(p._2.getClass, p._1))
    description
  }

  // Our custom tes callback, used to keep JUnit's runner updated about test progress
  private class CustomTestCallback(notifier:RunNotifier, desc: Description) extends SchkTest.TestCallback {
    // TODO: is it even possible to obtain the correct stack trace? ScalaCheck doesn't throw Exceptions for property failures!
    def failure = new Failure(desc, new Throwable("ScalaCheck property did not hold true"))

    /** Called whenever a property has finished testing */
    override def onTestResult(name: String, res: SchkTest.Result): Unit = {
      res.status match {
        case SchkTest.Passed => // Test passed, nothing to do
        case SchkTest.Proved(_) => // Test passed, nothing to do
        case SchkTest.Exhausted => notifier.fireTestIgnored(desc) // exhausted tests are marked as ignored in JUnit
        case _ => notifier.fireTestFailure(failure) // everything else is a failed test
      }
    }
  }

  // we'll use this one to report status to the console, and we'll chain it with our custom reporter
  val consoleReporter = new ConsoleReporter(1)

  /**
    * Run this <code>Suite</code> of tests, reporting results to the passed <code>RunNotifier</code>.
    * This class's implementation of this method invokes <code>run</code> on an instance of the
    * <code>suiteClass</code> <code>Class</code> passed to the primary constructor, passing
    * in a <code>Reporter</code> that forwards to the  <code>RunNotifier</code> passed to this
    * method as <code>notifier</code>.
    *
    * @param notifier the JUnit <code>RunNotifier</code> to which to report the results of executing
    * this suite of tests
    */
  def run(notifier: RunNotifier) {

    properties.properties.foreach({
      case (desc, prop) =>
        val descObj = Description.createTestDescription(prop.getClass, desc)

        // TODO: is there a better way to do this? It seems that JUnit is not printing the actual name of the test case to the screen as it runs
        print("Running property: " + desc)

        notifier.fireTestStarted(descObj)
        SchkTest.check(Parameters.default.withTestCallback(consoleReporter chain new CustomTestCallback(notifier, descObj)), prop)
        notifier.fireTestFinished(descObj)
    })
  }

  /**
    * Returns the number of tests that are expected to run when this ScalaTest <code>Suite</code>
    * is run.
    *
    * @return the expected number of tests that will run when this suite is run
    */
  override def testCount(): Int = properties.properties.size
}