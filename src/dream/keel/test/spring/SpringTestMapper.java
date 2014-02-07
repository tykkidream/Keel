package dream.keel.test.spring;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContextManager;

import dream.keel.base.BaseDao;
import dream.keel.base.BaseModel;

//@RunWith(SpringJUnit4ClassRunner.class)
@RunWith(Parameterized.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class SpringTestMapper<T extends BaseModel<?>> extends TestMapper<T>{
	
	public SpringTestMapper(){
		super(null);
		System.out.println(">>>>>     一个新的SpringTestMapper实例     <<<<<");
	}
	private TestContextManager testContextManager;

	@Before
	public void setUpContext() throws Exception {
		this.testContextManager = new TestContextManager(getClass());
		this.testContextManager.prepareTestInstance(this);
	}
	
	@BeforeClass
	public static void setUpBeforeClass(){
		id0 = 1L;
		id1 = 318001L;
		id2 = 318002L;
	}
		
	@Override
	public abstract BaseDao<T> getMapper();

	@Override
	protected String getBeanName() {
		return null;
	}
}
