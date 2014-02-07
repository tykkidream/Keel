package dream.keel.test.spring;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import dream.keel.base.BaseDao;
import dream.keel.base.BaseModel;
import dream.keel.util.TestUtils;

@RunWith(Parameterized.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class TestDao<T extends BaseModel<?>>{
	
	@Parameter(0)
	public T t1 = null;
	@Parameter(1)
	public T t2 = null;
	@Parameter(2)
	public long id0;
	@Parameter(3)
	public long id1;
	@Parameter(4)
	public long id2;
	
	public abstract BaseDao<T> getBaseDao();
	
	public abstract void setBaseDao(BaseDao<T> baseDao);
	
	protected abstract String getBeanName();
	
	@Before
	@SuppressWarnings("unchecked")
	public void setUp(){
		this.setBaseDao((BaseDao<T>) TestUtils.getApplicationContext().getBean(getBeanName()));
	}
	
	@Test
	public void test01SelectByID(){
		T t = this.getBaseDao().select(id0);
		
		assertNotNull("无法获取目标数据！没有匹配id0为" + id0 + "的数据！", t);
		assertSame("获取非目标数据！", id0, t.getId());
	}
	
	@Test
	public void test02Insert(){
		int rs = 0;

		t1 = this.getBaseDao().select(id1);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test02Insert_getModel();
		
		rs = this.getBaseDao().insert(t1);
		assertEquals("保存数据失败！",1, rs);
		
		t2 = this.getBaseDao().select(t1.getId());
		assertNotNull("无法获取保存的数据！", t2);
	}
	
	protected abstract T test02Insert_getModel();
	
	@Test
	public void test03InsertSelective(){
		int rs = 0;

		t1 = this.getBaseDao().select(id2);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test03InsertSelective_getModel();

		rs = this.getBaseDao().insertSelective(t1);

		assertSame("保存数据失败！",1, rs);
		
		assertNotSame("数据的ID没有获得自数据库的更新！",  id2,  t1.getId());
		id2 = t1.getId();
	}

	protected abstract T test03InsertSelective_getModel();
	
	@Test
	public void test04UpdateByID(){
		int rs = 0;

		t1 = this.getBaseDao().select(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test04UpdateByID_getModel();
		
		rs = this.getBaseDao().update(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = this.getBaseDao().select(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test04UpdateByID_assert();
	}
	
	protected abstract T test04UpdateByID_getModel();
	
	protected abstract void test04UpdateByID_assert();
	
	@Test
	public void test05UpdateByIDSelective(){
		int rs = 0;

		t1 = this.getBaseDao().select(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test05UpdateByIDSelective_getModel();
		
		rs = this.getBaseDao().updateSelective(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = this.getBaseDao().select(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test05UpdateByIDSelective_assert();
	}
	
	protected abstract T test05UpdateByIDSelective_getModel();
	
	protected abstract void test05UpdateByIDSelective_assert();
	
	@Test
	public void test06DeleteByID1(){
		int rs = 0;

		t1 = this.getBaseDao().select(id1);
		assertNotNull("不存在要删除的数据！没有匹配id2为" + id2 + "的数据！", t1);

		rs = this.getBaseDao().delete(t1.getId());
		assertSame("删除数据失败！没有删除匹配id2为" + id2 + "的数据！", 1, rs);
	}
	
	@Test
	public void test07DeleteByID2(){
		int rs = 0;

		t1 = this.getBaseDao().select(id2);
		assertNotNull("不存在要删除的数据！没有匹配id2为" + id2 + "的数据！", t1);

		rs = this.getBaseDao().delete(t1.getId());
		assertSame("删除数据失败！没有删除匹配id2为" + id2 + "的数据！", 1, rs);
	}

}
