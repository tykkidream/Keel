package tykkidream.keel.test.spring;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import tykkidream.keel.base.sta.BaseModel;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public abstract class SpringTestService<T extends BaseModel<?, I>, I> extends TestService<T, I> {

	private TestContextManager testContextManager;

	@Before
	@Override
	public void setUp() {
		try {
			this.testContextManager = new TestContextManager(getClass());
			this.testContextManager.prepareTestInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	protected String getBeanName() {
		return null;
	}

}
