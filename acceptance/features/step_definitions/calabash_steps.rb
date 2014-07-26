require 'calabash-android/calabash_steps'

ならば /^"(.*?)"と表示されていること$/ do |text|
  step %{I should see "#{text}"}
end
 
ならば /^"(.*?)"と表示されていないこと$/ do |text|
  step %{I should not see "#{text}"}
end
 
もし /^画面を横回転$/ do
  step %{I rotate the device to landscape}
end
 
もし /^画面を縦方向に回転$/ do
  step %{I rotate the device to portrait}
end
 
もし /^スクリーンショットを撮る$/ do
  step %{I take a screenshot}
end
 
もし /^写真を撮る$/ do
  step %{take picture}
end
 
もし /^スクショ$/ do
  step %{I take a screenshot}
end
 
もし /^"(.*?)"ボタンをクリック$/ do |text|
  step %{I press the "#{text}" button}
end
 
もし /^"(.*?)"ボタンをタップする$/ do |text|
  step %{I press the "#{text}" button}
end
 
もし /^"(.*?)"ボタンをクリックする$/ do |text|
  step %{I press the "#{text}" button}
end
 
ならば /^"(.*?)"ボタンが表示されていること$/ do |text|
  step %{I see "#{text}"}
end
 
もし /^プログレスバーが終わるまで待つ$/ do
  step %{I wait for progress}
end
 
もし /^ダイアログが閉じるまで待つ$/ do
  step %{I wait for dialog to close}
end
 
もし /^少し待つ$/ do
  step %{I wait}
end
 
もし /^"(.*?)"画面が表示されるまで待つ$/ do |activity|
  step %{I wait for the "#{activity}" screen to appear}
end
 
もし /(\d+)秒待つ/ do |seconds|
  step %{I wait for #{seconds} seconds}
end
 
もし /^戻るボタンをタップする$/ do
  step %{I go back}
end
 
もし /^メニューボタンをタップする$/ do
  step %{I press the menu key}
end
 
もし /^エンターボタンをタップする$/ do
  step %{I press the enter button}
end
 
もし /^スクロールアップする$/ do
  step %{I scroll up}
end
 
もし /^スクロールダウンする$/ do
  step %{I scroll down}
end
 
もし /^左にスワイプする$/ do
  step %{I swipe left}
end
 
もし /^右にスワイプする$/ do
  step %{I swipe right}
end
 
もし /^選択項目"(.*?)"より"(.*?)"を選択する$/ do |spinner_content_description, item_text|
  step %{I select "#{item_text}" from "#{spinner_content_description}"}
end
 
もし /^入力項目"(.*?)"に"(.*?)"と入力する/ do |content_description, text|
  step %{I enter "#{text}" into "#{content_description}"}
end
 
もし /^入力項目"(.*?)"をクリアする$/ do |content_description|
  step %{I clear "#{content_description}"}
end
 
もし /^"([^\"]*)"をロングタップした後、"([^\"]*)"を選択する$/ do |text_to_press, content_text|
  step %{I long press "#{text_to_press}" and select "#{content_text}"}
end
 
もし /^DatePicker"([^\"]*)"より"(\d\d-\d\d-\d\d\d\d)"を選択する$/ do |content_description, date|
  step %{I set the "#{content_description}" date to "#{date}"}
end
 
もし /^TimePicker"([^\"]*)"より"(\d\d:\d\d)"を選択する$/ do |content_description, time|
  step %{I set the "#{content_description}" time to "#{time}"}
end
 
もし /^緯度"([-+]?[0-9]*\.?[0-9]+)"、経度"([-+]?[0-9]*\.?[0-9]+)"に設定する$/ do |lat, lng|
  step %{I am at #{lat}, #{lng}}
end
 
もし /^ID"([^\"]*)"のビューをタップする$/ do |id|
  step %{I press view with id "#{id}"}
end

前提 /^アクティビティ"([^\"]*)"が表示されていること$/ do |activity_name|
  wait_for_activity activity_name
end

ならば /^リストビューに"(\d+)"個以上のアイテムが表示されていること$/ do |count|
  elements = wait_for_elements_exist("android.widget.ListView index:0 *")
  p elements
  raise wait_error("ListView does not have #{count} items") if elements.count < count
end

もし /リストアイテムの"(\d+)"番目をタップする$/ do |index|
  step %{I press list item number #{index}}
end
