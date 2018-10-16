package pages

import pages.behaviours.PageBehaviours

class MyIntPagePageSpec extends PageBehaviours {

  "MyIntPagePage" must {

    beRetrievable[Int](MyIntPagePage)

    beSettable[Int](MyIntPagePage)

    beRemovable[Int](MyIntPagePage)
  }
}
