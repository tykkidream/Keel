package dream.keel.spring;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dream.keel.BaseDao;
import dream.keel.BaseModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class SpringTestMapper<T extends BaseModel<?>> extends TestMapper<T>{
	
	public SpringTestMapper(){
		super(null);
		System.out.println(">>>>>     一个新的SpringTestMapper实例     <<<<<");
	}
	
	@BeforeClass
	public static void setUpBeforeClass(){
		id0 = -1L;
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
