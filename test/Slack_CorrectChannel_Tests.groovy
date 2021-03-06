import TestData.SlackTestData
import Utils.Helper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized.class)
class Slack_CorrectChannel_Tests extends GroovyTestCase {


    @Parameterized.Parameters(name = "{0}")
    static Collection<Object> data(){
        SlackTestData.suite_ChannelIsDefined_AllureIsAny()
    }

    private slack_ = new slack()
    private channel
    private allure

    Slack_CorrectChannel_Tests(List list){
        this.channel = list[0]
        this.allure = list[1]
    }

    @Before
    void setUp(){
        def variables = SlackTestData.commonVariables()
        Helper.setEnvVariables(variables, slack_)
        InjectVars.injectTo(slack_, 'imageName')
    }

    @Test
    void test_SlackSuccessCorrectChannelTest_ChannelIsCorrect(){

        Helper.setBuildStatus('SUCCESS', slack_)
        Map actualParameters = [:]
        slack_.slackSend = { Map map -> actualParameters = map; return null}
        def expectedChannel = channel?.toString()

        slack_ channel: channel, allure: allure

        assertEquals(expectedChannel, actualParameters['channel'])

    }

    @Test
    void test_SlackFailedCorrectChannelTest_ChannelIsCorrect(){

        Helper.setBuildStatus('FAILURE', slack_)
        Map actualParameters = [:]
        slack_.slackSend = { Map map -> actualParameters = map; return null}
        def expectedChannel = channel?.toString()

        slack_ channel: channel, allure: allure

        assertEquals(expectedChannel, actualParameters['channel'])

    }

    @Test
    void test_SlackUnstableCorrectChannelTest_ChannelIsCorrect(){

        Helper.setBuildStatus('UNSTABLE', slack_)
        Map actualParameters = [:]
        slack_.slackSend = { Map map -> actualParameters = map; return null}
        def expectedChannel = channel?.toString()

        slack_ channel: channel, allure: allure

        assertEquals(expectedChannel, actualParameters['channel'])

    }

}
