@Grapes([
	@Grab("net.sourceforge.htmlunit:htmlunit:latest.release")
])
import com.gargoylesoftware.htmlunit.*

def webClient = new WebClient()
def page = webClient.getPage("http://htmlunit.sourceforge.net")
assert "HtmlUnit - Welcome to HtmlUnit" == page.getTitleText()

def pageAsXml = page.asXml()
assert pageAsXml.contains("<body class=\"composite\">")

def pageAsText = page.asText()
println pageAsText
assert pageAsText.contains("Support for the HTTP and HTTPS protocols")

webClient.closeAllWindows()