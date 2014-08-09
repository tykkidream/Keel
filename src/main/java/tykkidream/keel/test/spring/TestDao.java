package tykkidream.keel.test.spring;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import tykkidream.keel.base.sta.BaseDao;
import tykkidream.keel.base.sta.BaseModel;
import tykkidream.keel.util.TestUtils;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestDao<T extends BaseModel<?, I>, I extends Serializable>{
	
	@Parameter(0)
	public T t1 = null;
	@Parameter(1)
	public T t2 = null;
	@Parameter(2)
	public T t3 = null;
	@Parameter(3)
	public T t4 = null;
	@Parameter(4)
	public I id;
	
	public abstract BaseDao<T, I> getBaseDao();
	
	public abstract void setBaseDao(BaseDao<T, I> baseDao);
	
	protected abstract String getBeanName();
	
	@Before
	@SuppressWarnings("unchecked")
	public void setUp(){
		this.setBaseDao((BaseDao<T, I>) TestUtils.getApplicationContext().getBean(getBeanName()));
	}
	
	@Test
	public void test01SelectByID(){
		T t = this.getBaseDao().selectByKey(id);
		
		assertNotNull("无法获取目标数据！没有匹配id0为" + id + "的数据！", t);
		assertSame("获取非目标数据！", id, t.getId());
	}
	
	@Test
	public void test02Insert(){
		int rs = this.getBaseDao().insert(t1);
		assertEquals("保存数据失败！",1, rs);
	}
	
	@Test
	public void test04UpdateByID(){
		int rs = 0;
		
		rs = this.getBaseDao().update(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = this.getBaseDao().selectByKey(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test04UpdateByID_assert();
	}
	
	protected abstract void test04UpdateByID_assert();
	
	protected abstract void test05UpdateByIDSelective_assert();
	
	@Test
	public void test06DeleteByID(){
		int rs = 0;

		rs += this.getBaseDao().delete(t1.getId());
		rs += this.getBaseDao().delete(t2.getId());
		assertSame("删除数据失败！", 2, rs);
	}
}
