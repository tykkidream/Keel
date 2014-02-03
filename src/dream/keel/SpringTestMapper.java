package dream.keel;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dream.keel.BaseDao;
import dream.keel.BaseModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class SpringTestMapper<T extends BaseModel<?>> extends AbstractJUnit4SpringContextTests{

	public T t1 = null;
	public T t2 = null;
	public long id0 = -1L;
	public long id1 = 318001L;
	public long id2 = 318002L;
	
	public abstract BaseDao<T> getMapper();
	@Test
	public void test01SelectByID(){
		T t = getMapper().selectByID(id0);
		
		assertNotNull("无法获取目标数据！", t);
		assertSame("获取非目标数据！", id0, t.getId());
	}
	
	@Test
	public void test02Insert(){
		int rs = 0;

		t1 = getMapper().selectByID(id1);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test02Insert_getModel();
		
		rs = getMapper().insert(t1);
		assertEquals("保存数据失败！",1, rs);
		
		t2 = getMapper().selectByID(t1.getId());
		assertNotNull("无法获取保存的数据！", t2);
	}
	
	protected abstract T test02Insert_getModel();
	
	@Test
	public void test03InsertSelective(){
		int rs = 0;

		t1 = getMapper().selectByID(id2);
		assertNull("已存在要保存的数据！", t1);
		
		t1 = test03Insert_getModel();

		rs = getMapper().insertSelective(t1);

		assertSame("保存数据失败！",1, rs);
		
		assertNotSame("数据的ID没有获得自数据库的更新！",  id2,  t1.getId());
		id2 = t1.getId();
	}

	protected abstract T test03Insert_getModel();
	
	@Test
	public void test04UpdateByID(){
		int rs = 0;

		t1 = getMapper().selectByID(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test04Insert_getModel();
		
		rs = getMapper().updateByID(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = getMapper().selectByID(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test04Insert_assert();
	}
	
	protected abstract T test04Insert_getModel();
	
	protected void test04Insert_assert(){};
	
	@Test
	public void test05UpdateByIDSelective(){
		int rs = 0;

		t1 = getMapper().selectByID(id1);
		assertNotNull("不存在要更新的数据！", t1);
		
		t2 = test05Insert_getModel();
		
		rs = getMapper().updateByIDSelective(t2);
		assertEquals("更新数据失败！",1, rs);
		
		t2 = getMapper().selectByID(t2.getId());
		assertNotNull("无法获取数据的数据！", t2);
		
		test05Insert_assert();
	}
	
	protected abstract T test05Insert_getModel();
	
	protected void test05Insert_assert(){}
	
	@Test
	public void test06DeleteByID1(){
		int rs = 0;

		t1 = getMapper().selectByID(id1);
		assertNotNull("不存在要更新的数据！", t1);

		rs = getMapper().deleteByID(t1.getId());
		assertSame("删除数据失败！", 1, rs);
	}
	
	@Test
	public void test07DeleteByID2(){
		int rs = 0;

		t1 = getMapper().selectByID(id2);
		assertNotNull("不存在要更新的数据！", t1);

		rs = getMapper().deleteByID(t1.getId());
		assertSame("删除数据失败！", 1, rs);
	}

}
