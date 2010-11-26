require 'test/unit'
require 'rubygems'
require 'watir'

class TestFindKioskFlow < Test::Unit::TestCase
  
def setup
  Watir::Browser.default = 'firefox'
  @browser = Watir::Browser.new
  @browser.goto "http://localhost:8080/fluffbox-rwx"
end
  
def test_find_kiosk_flow
  @browser.link(:xpath, "//a[contains(@href, '/fluffbox-rwx/kiosk/find')]").click
  
  @browser.text_field(:id, "searchCriteria").set("Ft. Lauderdale")
  @browser.button(:id, "searchButton").click
  
  until @browser.link(:text, "Find Speakers Here").exists? do
    sleep 0.5
  end
  
  @browser.link(:text, "Find Speakers Here").click
  @browser.link(:text, "Aleksandar Seovic").click
  @browser.link(:text, "RENT NOW!").click
  @browser.link(:text, "Continue").click
  
  assert(@browser.text.include? "This concludes your fake rental experience!")  
end  

def teardown
  @browser.close
end
  
end