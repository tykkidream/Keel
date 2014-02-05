package dream.keel.test.spring;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dream.keel.base.BaseDao;
import dream.keel.base.BaseModel;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(JUnit4.class)
public abstract class TestMapper<T extends BaseModel<?>>{

	public static ApplicationContext applicationContext = null;
	
	public ApplicationContext context = null;
	private BaseDao<T> baseMapper = null;
	public T t1 = null;
	public T t2 = null;
	public static long id0;
	public static long id1;
	public static long id2;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		if (applicationContext == null) {
			System.out.println(">>>>>     初始化Spring的Application     <<<<<");
			applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		}
		id0 = 1L;
		id1 = 318001L;
		id2 = 318002L;
	}
	
	public TestMapper(){
		this(null,null);
		System.out.println(">>>>>     一个新的TestMapper实例     <<<<<");
	}
	
	public TestMapper(BaseDao<T> baseMapper){
		this.baseMapper = baseMapper;
	}
	
	@SuppressWarnings("unchecked")
	public TestMapper(ApplicationContext context, BaseDao<T> baseMapper){
		this.context = context == null ? applicationContext : context;
		this.baseMapper = baseMapper == null ? (BaseDao<T>) this.context.getBean(getBeanName()) : baseMapper;;
	}
	
	public BaseDao<T> getMapper(){
		return this.baseMapper;
	}
	
	protected abstract String getBeanName();
	
	@Test
	public void test01SelectByID(){
		T t = this.getMapper().select(id0);
		
		assertNotNull("无法获取目标数据！没有匹配id0为" + id0 + "的数据！", t);
		assertSame("获取非目标数据！", id0, t.getId());
	}
	
	@Test
	public void test02Insert(){
		int rs = 0;

		t1 = this.getMapper().select(id1);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test02Insert_getModel();
		
		rs = this.getMapper().insert(t1);
		assertEquals("保存数据失败！",1, rs);
		
		t2 = this.getMapper().select(t1.getId());
		assertNotNull("无法获取保存的数据！", t2);
	}
	
	protected abstract T test02Insert_getModel();
	
	@Test
	public void test03InsertSelective(){
		int rs = 0;

		t1 = this.getMapper().select(id2);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test03InsertSelective_getModel();

		rs = this.getMapper().insertSelective(t1);

		assertSame("保存数据失败！",1, rs);
		
		assertNotSame("数据的ID没有获得自数据库的更新！",  id2,  t1.getId());
		id2 = t1.getId();
	}

	protected abstract T test03InsertSelective_getModel();
	
	@Test
	public void test04UpdateByID(){
		int rs = 0;

		t1 = this.getMapper().select(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test04UpdateByID_getModel();
		
		rs = this.getMapper().update(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = this.getMapper().select(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test04UpdateByID_assert();
	}
	
	protected abstract T test04UpdateByID_getModel();
	
	protected abstract void test04UpdateByID_assert();
	
	@Test
	public void test05UpdateByIDSelective(){
		int rs = 0;

		t1 = this.getMapper().select(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test05UpdateByIDSelective_getModel();
		
		rs = this.getMapper().updateSelective(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = this.getMapper().select(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test05UpdateByIDSelective_assert();
	}
	
	protected abstract T test05UpdateByIDSelective_getModel();
	
	protected abstract void test05UpdateByIDSelective_assert();
	
	@Test
	public void test06DeleteByID1(){
		int rs = 0;

		t1 = this.getMapper().select(id1);
		assertNotNull("不存在要删除的数据！没有匹配id2为" + id2 + "的数据！", t1);

		rs = this.getMapper().delete(t1.getId());
		assertSame("删除数据失败！没有删除匹配id2为" + id2 + "的数据！", 1, rs);
	}
	
	@Test
	public void test07DeleteByID2(){
		int rs = 0;

		t1 = this.getMapper().select(id2);
		assertNotNull("不存在要删除的数据！没有匹配id2为" + id2 + "的数据！", t1);

		rs = this.getMapper().delete(t1.getId());
		assertSame("删除数据失败！没有删除匹配id2为" + id2 + "的数据！", 1, rs);
	}

}