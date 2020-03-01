import android.Manifest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.garyfimo.breakingbadinfo.MainActivity
import com.garyfimo.breakingbadinfo.ui.util.ScreenshotWatcher
import org.junit.Rule
import org.junit.rules.RuleChain
import org.junit.rules.TestName

open class BaseTest {
    @get:Rule
    var activityTestRule = ActivityTestRule(MainActivity::class.java)

    @get:Rule
    var testName = TestName()

    @get:Rule
    var screenshotWatcher = ScreenshotWatcher()

    @get:Rule
    var ruleChain: RuleChain = RuleChain
        .outerRule(
            GrantPermissionRule.grant(
            )
        )
        .around(TestName())
        .around(ScreenshotWatcher())
}