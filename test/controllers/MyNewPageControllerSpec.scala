package controllers

import controllers.actions._
import play.api.test.Helpers._
import views.html.myNewPage

class MyNewPageControllerSpec extends ControllerSpecBase {

  def controller(dataRetrievalAction: DataRetrievalAction = getEmptyCacheMap) =
    new MyNewPageController(frontendAppConfig, messagesApi, FakeIdentifierAction,
      dataRetrievalAction, new DataRequiredActionImpl)

  def viewAsString() = myNewPage(frontendAppConfig)(fakeRequest, messages).toString

  "MyNewPage Controller" must {

    "return OK and the correct view for a GET" in {
      val result = controller().onPageLoad(fakeRequest)

      status(result) mustBe OK
      contentAsString(result) mustBe viewAsString()
    }
  }
}




