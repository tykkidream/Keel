package tykkidream.keel.test.spring;

import java.io.Serializable;

import org.junit.Before;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import tykkidream.keel.base.tta.BaseModel;

@ContextConfiguration(locations = "classpath:applicationContext.xml")
public abstract class SpringTestDao<T extends BaseModel<?, I>, I  extends Serializable> extends TestDao<T, I>{
	
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
