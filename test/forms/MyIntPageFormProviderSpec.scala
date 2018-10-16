package forms

import forms.behaviours.IntFieldBehaviours
import play.api.data.FormError

class MyIntPageFormProviderSpec extends IntFieldBehaviours {

  val form = new MyIntPageFormProvider()()

  ".value" must {

    val fieldName = "value"

    val minimum = 0
    val maximum = 240

    val validDataGenerator = intsInRangeWithCommas(minimum, maximum)

    behave like fieldThatBindsValidData(
      form,
      fieldName,
      validDataGenerator
    )

    behave like intField(
      form,
      fieldName,
      nonNumericError  = FormError(fieldName, "myIntPage.error.nonNumeric"),
      wholeNumberError = FormError(fieldName, "myIntPage.error.wholeNumber")
    )

    behave like intFieldWithRange(
      form,
      fieldName,
      minimum       = minimum,
      maximum       = maximum,
      expectedError = FormError(fieldName, "myIntPage.error.outOfRange", Seq(minimum, maximum))
    )

    behave like mandatoryField(
      form,
      fieldName,
      requiredError = FormError(fieldName, "myIntPage.error.required")
    )
  }
}
