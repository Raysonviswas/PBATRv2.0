package generators

import org.scalacheck.Arbitrary
import pages._

trait PageGenerators {

  implicit lazy val arbitraryMyIntPagePage: Arbitrary[MyIntPagePage.type] =
    Arbitrary(MyIntPagePage)
}
