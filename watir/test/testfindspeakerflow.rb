require 'test/unit'
require 'rubygems'
require 'watir'

class TestFindSpeakerFlow < Test::Unit::TestCase
  
def setup
  Watir::Browser.default = 'firefox'
  @browser = Watir::Browser.new
  @browser.goto "http://localhost:8080/fluffbox-rwx"
end
  
def test_find_speaker_flow
  @browser.link(:xpath, "//a[contains(@href, '/fluffbox-rwx/speaker/find')]").click
  @browser.link(:text, "Alex Miller").click
  @browser.link(:text, "Find this Speaker").click
  @browser.link(:text, "RENT NOW").click
  @browser.link(:text, "Continue").click
  
  assert(@browser.text.include? "This concludes your fake rental experience!")  
end  

def teardown
  @browser.close
end
  
end