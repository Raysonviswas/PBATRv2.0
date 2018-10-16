package views

import play.api.data.Form
import controllers.routes
import forms.MyIntPageFormProvider
import models.NormalMode
import views.behaviours.IntViewBehaviours
import views.html.myIntPage

class MyIntPageViewSpec extends IntViewBehaviours {

  val messageKeyPrefix = "myIntPage"

  val form = new MyIntPageFormProvider()()

  def createView = () => myIntPage(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  def createViewUsingForm = (form: Form[_]) => myIntPage(frontendAppConfig, form, NormalMode)(fakeRequest, messages)

  "MyIntPage view" must {
    behave like normalPage(createView, messageKeyPrefix)

    behave like pageWithBackLink(createView)

    behave like intPage(createViewUsingForm, messageKeyPrefix, routes.MyIntPageController.onSubmit(NormalMode).url)
  }
}
