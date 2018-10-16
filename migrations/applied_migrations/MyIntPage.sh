#!/bin/bash

echo "Applying migration MyIntPage"

echo "Adding routes to conf/app.routes"

echo "" >> ../conf/app.routes
echo "GET        /myIntPage               controllers.MyIntPageController.onPageLoad(mode: Mode = NormalMode)" >> ../conf/app.routes
echo "POST       /myIntPage               controllers.MyIntPageController.onSubmit(mode: Mode = NormalMode)" >> ../conf/app.routes

echo "GET        /changeMyIntPage                        controllers.MyIntPageController.onPageLoad(mode: Mode = CheckMode)" >> ../conf/app.routes
echo "POST       /changeMyIntPage                        controllers.MyIntPageController.onSubmit(mode: Mode = CheckMode)" >> ../conf/app.routes

echo "Adding messages to conf.messages"
echo "" >> ../conf/messages.en
echo "myIntPage.title = myIntPage" >> ../conf/messages.en
echo "myIntPage.heading = myIntPage" >> ../conf/messages.en
echo "myIntPage.checkYourAnswersLabel = myIntPage" >> ../conf/messages.en
echo "myIntPage.error.nonNumeric = Enter your myIntPage using numbers" >> ../conf/messages.en
echo "myIntPage.error.required = Enter your myIntPage" >> ../conf/messages.en
echo "myIntPage.error.wholeNumber = Enter your myIntPage using whole numbers" >> ../conf/messages.en
echo "myIntPage.error.outOfRange = MyIntPage must be between {0} and {1}" >> ../conf/messages.en

echo "Adding to UserAnswersEntryGenerators"
awk '/trait UserAnswersEntryGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryMyIntPageUserAnswersEntry: Arbitrary[(MyIntPagePage.type, JsValue)] =";\
    print "    Arbitrary {";\
    print "      for {";\
    print "        page  <- arbitrary[MyIntPagePage.type]";\
    print "        value <- arbitrary[Int].map(Json.toJson(_))";\
    print "      } yield (page, value)";\
    print "    }";\
    next }1' ../test/generators/UserAnswersEntryGenerators.scala > tmp && mv tmp ../test/generators/UserAnswersEntryGenerators.scala

echo "Adding to PageGenerators"
awk '/trait PageGenerators/ {\
    print;\
    print "";\
    print "  implicit lazy val arbitraryMyIntPagePage: Arbitrary[MyIntPagePage.type] =";\
    print "    Arbitrary(MyIntPagePage)";\
    next }1' ../test/generators/PageGenerators.scala > tmp && mv tmp ../test/generators/PageGenerators.scala

echo "Adding to CacheMapGenerator"
awk '/val generators/ {\
    print;\
    print "    arbitrary[(MyIntPagePage.type, JsValue)] ::";\
    next }1' ../test/generators/CacheMapGenerator.scala > tmp && mv tmp ../test/generators/CacheMapGenerator.scala

echo "Adding helper method to CheckYourAnswersHelper"
awk '/class/ {\
     print;\
     print "";\
     print "  def myIntPage: Option[AnswerRow] = userAnswers.get(MyIntPagePage) map {";\
     print "    x => AnswerRow(\"myIntPage.checkYourAnswersLabel\", s\"$x\", false, routes.MyIntPageController.onPageLoad(CheckMode).url)";\
     print "  }";\
     next }1' ../app/utils/CheckYourAnswersHelper.scala > tmp && mv tmp ../app/utils/CheckYourAnswersHelper.scala

echo "Migration MyIntPage completed"
