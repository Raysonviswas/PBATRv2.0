package views

import views.behaviours.ViewBehaviours
import views.html.myNewPage

class MyNewPageViewSpec extends ViewBehaviours {

  val messageKeyPrefix = "myNewPage"

  def createView = () => myNewPage(frontendAppConfig)(fakeRequest, messages)

  "MyNewPage view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)
  }
}
